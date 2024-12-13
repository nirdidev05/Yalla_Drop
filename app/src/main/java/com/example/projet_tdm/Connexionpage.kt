package com.example.projet_tdm
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ConnectionPage() {
    var isChecked by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),


        horizontalAlignment = Alignment.CenterHorizontally

    ) {


        Image(
            painter = painterResource(id = R.drawable.img),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        Image(
            painter = painterResource(id = R.drawable.home),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth() // Prend toute la largeur
                .offset(y = 555.dp) // Positionne en bas
        )

        Column(
            modifier = Modifier.height(296.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            CustomButton("Login via Google", "#FFFFFF", "#4285F4", "#4285F4",R.drawable.google)
            CustomButton("Login via Phone Number", "#FFFFFF", "#000000", "#000000",R.drawable.phone)
            CustomButton("Login via Email", "#FFFFFF", "#DD4B39", "#DD4B39",R.drawable.email)
            CustomButton("Create an account", "#8E8E93", "#8E8E93", "#FFFFFF")
        }

        // Checkbox for Terms and Conditions
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Checkbox(
                checked = isChecked,
                onCheckedChange = { isChecked = it }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "I agree to the Terms & Conditions", style = TextStyle(fontSize = 14.sp))
        }
    }
}

@Composable

fun CustomButton(
    content: String,
    contentColor: String,
    colorBorder: String,
    colorSting: String,
    iconResId: Int? = null
) {
    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .width(328.dp)
            .height(62.dp),
        enabled = true,
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color(android.graphics.Color.parseColor(contentColor)),
            containerColor = Color(android.graphics.Color.parseColor(colorSting))
        ),
        border = BorderStroke(
            width = 1.dp,
            brush = SolidColor(Color(android.graphics.Color.parseColor(colorBorder)))
        ),
        contentPadding = PaddingValues(
            start = 20.dp,
            top = 12.dp,
            end = 20.dp,
            bottom = 12.dp
        ),
    ) {
        // If iconResId is provided, show the icon
        if (iconResId != null) {
            Image(
                painter = painterResource(id = iconResId),
                contentDescription = null,
                modifier = Modifier
                    .width(24.dp) // Adjust icon size as needed
                    .height(24.dp)
                    .padding(end = 8.dp), // Space between icon and text
                contentScale = ContentScale.Fit // Scale icon appropriately
            )
        }
        Text(
            text = content,
            style = TextStyle(fontSize = 17.sp, fontWeight = FontWeight.W700)
        )
    }

}
