package com.example.yalladrop.walid.nothing

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayMain() {
    val context = LocalContext.current
    val factNumber = remember { mutableStateOf("") }
    val listSize = remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier.fillMaxHeight(.3f),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TextField(
                value = factNumber.value,
                onValueChange = { factNumber.value = it },
                label = { Text("Number") },
                placeholder = { Text("Number") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                )
            )

            Button(onClick = {
                val input = factNumber.value

                // Check if the input is numeric
                if (input.all { it.isDigit() }) {
                    // If numeric, compute factorial
                    val fact = factorial(input.toInt())
                    Toast.makeText(context, "The factorial is: $fact", Toast.LENGTH_SHORT).show()
                } else {
                    // If not numeric, show a message to the user
                    Toast.makeText(context, "Please enter a numeric value.", Toast.LENGTH_SHORT).show()
                }
            }) {
                Text("Compute Factorial")
            }
        }

        Column(
            modifier = Modifier.fillMaxHeight(.3f),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TextField(
                value = listSize.value,
                onValueChange = { listSize.value = it },
                label = { Text("List Size") },
                placeholder = { Text("List Size") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number // Show numeric keyboard
                )
            )

            Button(onClick = {
                val input = listSize.value

                // Check if input is numeric
                if (input.isEmpty() || !input.all { it.isDigit() }) {
                    Toast.makeText(context, "Please enter a numeric value.", Toast.LENGTH_SHORT).show()
                    return@Button
                }

                val size = input.toInt()

                // Check if the input exceeds the maximum value for Int
                if (size < 0) {
                    Toast.makeText(context, "Please enter a non-negative value.", Toast.LENGTH_SHORT).show()
                    return@Button
                } else if (size > Int.MAX_VALUE) {  // This check is actually redundant since Int.MAX_VALUE is the upper limit.
                    Toast.makeText(context, "Please enter a smaller value. The maximum allowed is ${Int.MAX_VALUE}.", Toast.LENGTH_SHORT).show()
                    return@Button
                }

                // Assuming you have a function to calculate the average
                val avg = randomListAverage(size)
                Toast.makeText(context, "The AVG is: $avg", Toast.LENGTH_SHORT).show()
            }) {
                Text("Compute List AVG")
            }
        }

        Column(
            modifier = Modifier.fillMaxHeight(.3f),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(modifier = Modifier.padding(32.dp),
                onClick = {
                    val url = "fb://page/218641444910278"
                    openPage(context, url)
                }) {
                Text("Open Facebook Page")
            }
        }
    }
}


