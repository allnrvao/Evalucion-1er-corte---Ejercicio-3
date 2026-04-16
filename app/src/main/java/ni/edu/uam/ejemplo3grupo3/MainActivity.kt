package ni.edu.uam.ejemplo3grupo3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import ni.edu.uam.ejemplo3grupo3.ui.theme.Ejemplo3Grupo3Theme

// 🔹 DATA CLASS
data class Pedido(
    val nombre: String,
    val producto: String,
    val cantidad: Double
)

// 🔹 MAIN
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ejemplo3Grupo3Theme {
                ScreenFinal()
            }
        }
    }
}

// 🔹 TEMA
@Composable
fun PulperiaAppTheme(content: @Composable () -> Unit) {
    val colorScheme = lightColorScheme(
        primary = Color(0xFF006494),
        primaryContainer = Color(0xFFCBE6FF),
        secondary = Color(0xFF50606E)
    )
    MaterialTheme(colorScheme = colorScheme, content = content)
}

// 🔹 SCAFFOLD
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffold(content: @Composable (PaddingValues) -> Unit) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Pedido de Pulpería", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        },
        content = content
    )
}

// 🔹 FORMULARIO
@Composable
fun PedidoForm(
    onRegister: (String, String, String) -> Unit,
    limpiarCampos: Boolean
) {

    var nombre by remember { mutableStateOf("") }
    var producto by remember { mutableStateOf("") }
    var cantidad by remember { mutableStateOf("") }

    // 🔥 Limpiar automáticamente
    LaunchedEffect(limpiarCampos) {
        if (limpiarCampos) {
            nombre = ""
            producto = ""
            cantidad = ""
        }
    }

    Card(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Cliente") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = producto,
                onValueChange = { producto = it },
                label = { Text("Producto") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = cantidad,
                onValueChange = { cantidad = it },
                label = { Text("Cantidad") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    onRegister(nombre, producto, cantidad)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Registrar Pedido")
            }
        }
    }
}


@Composable
fun RegistroExitosoBanner() {
    Surface(
        color = MaterialTheme.colorScheme.secondaryContainer,
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(modifier = Modifier.padding(12.dp)) {
            Icon(Icons.Default.CheckCircle, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("¡Pedido guardado con éxito!")
        }
    }
}

@Composable
fun PedidoCard(pedido: Pedido) {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Cliente: ${pedido.nombre}")
            Text("Producto: ${pedido.producto}")
            Text("Cantidad: ${pedido.cantidad}")
        }
    }
}

// 🔹 PANTALLA FINAL
@Composable
fun ScreenFinal() {

    var listaPedidos by remember { mutableStateOf(listOf<Pedido>()) }
    var mensajeError by remember { mutableStateOf("") }
    var mostrarExito by remember { mutableStateOf(false) }
    var limpiarCampos by remember { mutableStateOf(false) }

    // 🔥 Ocultar mensaje después de 3 segundos
    LaunchedEffect(mostrarExito) {
        if (mostrarExito) {
            delay(3000)
            mostrarExito = false
        }
    }

    PulperiaAppTheme {
        MainScaffold { padding ->

            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                PedidoForm(
                    onRegister = { n, p, c ->

                        val nombreValido = n.all { it.isLetter() || it.isWhitespace() }
                        val cantidadValida = c.toDoubleOrNull()

                        when {
                            n.isBlank() || p.isBlank() || c.isBlank() -> {
                                mensajeError = "⚠️ Todos los campos son obligatorios"
                                mostrarExito = false
                            }

                            !nombreValido -> {
                                mensajeError = "⚠️ El nombre no puede tener números"
                                mostrarExito = false
                            }

                            cantidadValida == null || cantidadValida <= 0 -> {
                                mensajeError = "⚠️ Cantidad inválida"
                                mostrarExito = false
                            }

                            else -> {
                                mensajeError = ""
                                mostrarExito = true

                                // 🔥 Agregar pedido (orden cronológico automático)
                                listaPedidos = listaPedidos + Pedido(n, p, cantidadValida)

                                // 🔥 Limpiar campos
                                limpiarCampos = true
                            }
                        }
                    },
                    limpiarCampos = limpiarCampos
                )


                limpiarCampos = false


                if (mensajeError.isNotEmpty()) {
                    Text(
                        text = mensajeError,
                        color = MaterialTheme.colorScheme.error
                    )
                }


                if (mostrarExito) {
                    RegistroExitosoBanner()
                }


                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(listaPedidos) { pedido ->
                        PedidoCard(pedido)
                    }
                }
            }
        }
    }
}