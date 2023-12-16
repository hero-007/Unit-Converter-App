package com.example.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter() {
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meters") } // State Variable
    var outputUnit by remember { mutableStateOf("Meters") }
    var isInputExpanded by remember { mutableStateOf(false) }
    var isOutputExpanded by remember { mutableStateOf(false) }
    var inputMultiplier = remember { mutableDoubleStateOf(1.00) }
    var outputMultiplier = remember { mutableDoubleStateOf(1.00) }

    fun convertUnit() {
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * inputMultiplier.doubleValue * outputMultiplier.doubleValue).roundToInt()
        outputValue = "$result $outputUnit"
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Unit Converter", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = inputValue,
            onValueChange = { inputValue = it },
            label = { Text(text = "Enter a Value") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            // Input Box
            Box {
                Button(onClick = { isInputExpanded = true }) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                }
                DropdownMenu(
                    expanded = isInputExpanded,
                    onDismissRequest = { isInputExpanded = false }) {
                    DropdownMenuItem(text = { Text(text = "Centimeters") }, onClick = {
                        isInputExpanded = false
                        inputUnit = "Centimeters"
                        inputMultiplier.doubleValue = 0.01
                        convertUnit()
                    })
                    DropdownMenuItem(text = { Text(text = "Meters") }, onClick = {
                        isInputExpanded = false
                        inputUnit = "Meters"
                        inputMultiplier.doubleValue = 1.0
                        convertUnit()
                    })
                    DropdownMenuItem(text = { Text(text = "Feet") }, onClick = {
                        isInputExpanded = false
                        inputUnit = "Feet"
                        inputMultiplier.doubleValue = 0.3048
                        convertUnit()
                    })
                    DropdownMenuItem(text = { Text(text = "Millimetres") }, onClick = {
                        isInputExpanded = false
                        inputUnit = "Millimetres"
                        inputMultiplier.doubleValue = 0.001
                        convertUnit()
                    })
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Output Box
            Box {
                Button(onClick = { isOutputExpanded = true }) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                }
                DropdownMenu(
                    expanded = isOutputExpanded,
                    onDismissRequest = { isOutputExpanded = false }) {
                    DropdownMenuItem(text = { Text(text = "Centimeters") }, onClick = {
                        isOutputExpanded = false
                        outputUnit = "Centimeters"
                        outputMultiplier.doubleValue = 100.0
                        convertUnit()
                    })
                    DropdownMenuItem(text = { Text(text = "Meters") }, onClick = {
                        isOutputExpanded = false
                        outputUnit = "Meters"
                        outputMultiplier.doubleValue = 1.0
                        convertUnit()
                    })
                    DropdownMenuItem(text = { Text(text = "Feet") }, onClick = {
                        isOutputExpanded = false
                        outputUnit = "Feet"
                        outputMultiplier.doubleValue = 3.048
                        convertUnit()
                    })
                    DropdownMenuItem(text = { Text(text = "Millimetres") }, onClick = {
                        isOutputExpanded = false
                        outputUnit = "Millimetres"
                        outputMultiplier.doubleValue = 1000.0
                        convertUnit()
                    })
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Result: $outputValue", style = MaterialTheme.typography.headlineMedium)
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverter()
}