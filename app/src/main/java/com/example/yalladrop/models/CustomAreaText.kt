package com.example.yalladrop.models

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun CustomAreaText(
    text: MutableState<String>,
    isEnable : Boolean
) {
    BasicTextField(
        value = text.value,
        onValueChange = { text.value = it },
        textStyle = if (isEnable) {
            MaterialTheme.typography.displayMedium
        } else {
            MaterialTheme.typography.displayMedium.copy(MaterialTheme.colorScheme.tertiary)
        },

        maxLines = 6,
        enabled = isEnable,
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(horizontal = 10.dp)
            .border(
                width = 1.5.dp,
                color = if (isEnable) {
                    MaterialTheme.colorScheme.onBackground // Enabled border color
                } else {
                    Color.Gray // Disabled border color
                },
                shape = RoundedCornerShape(20.dp)
            )
            .clip(RoundedCornerShape(20))
            .background(
                color = if (isEnable) {
                    MaterialTheme.colorScheme.secondary // Enabled background color
                } else {
                    Color.LightGray // Disabled background color
                }
            ),
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .background(
                        color = if (isEnable) {
                            MaterialTheme.colorScheme.secondary // Enabled decoration background
                        } else {
                            Color.LightGray // Disabled decoration background
                        }
                    )
                    .padding(15.dp)
            ) {
                if (text.value.isEmpty()) {
                    Text(
                        text = "Hint",
                        color = if (isEnable) {
                            LocalContentColor.current.copy(alpha = 0.6f) // Enabled hint color
                        } else {
                            MaterialTheme.colorScheme.tertiary.copy(alpha = 0.6f) // Disabled hint color
                        },
                        style = MaterialTheme.typography.displayMedium
                    )
                }



                innerTextField()
            }
        }
    )
}