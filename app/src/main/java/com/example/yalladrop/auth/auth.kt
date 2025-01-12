package com.example.yalladrop.auth

/*
import androidx.navigation.NavController
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
fun ConnectionPage(navController: NavController) { // Corrected the parameter type
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
        Column(
            modifier = Modifier.height(296.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            CustomButton("Login via Google", "#FFFFFF", "#4285F4", "#4285F4", R.drawable.google,navController)
            CustomButton("Login via Phone Number", "#FFFFFF", "#000000", "#000000", R.drawable.phone,navController)
            CustomButton("Login via Email", "#FFFFFF", "#DD4B39", "#DD4B39", R.drawable.email,navController)
            CustomButton("Create an account", "#8E8E93", "#8E8E93", "#FFFFFF",null,navController)
        }

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

        Button(
            onClick = { navController.navigate("homePage") },
            modifier = Modifier
                .padding(top = 16.dp),
                    colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF5722),
            contentColor = Color.White
        ) ){
            Text(text = "GO", style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold))
        }


    }
}

@Composable
fun CustomButton(
    content: String,
    contentColor: String,
    colorBorder: String,
    colorString: String,
    iconResId: Int? = null
    ,navController: NavController
) {
    Button(
        onClick = { navController.navigate("login") },
        modifier = Modifier
            .width(328.dp)
            .height(62.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color(android.graphics.Color.parseColor(contentColor)),
            containerColor = Color(android.graphics.Color.parseColor(colorString))
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
        )
    ) {
        if (iconResId != null) {
            Image(
                painter = painterResource(id = iconResId),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .padding(end = 8.dp),
                contentScale = ContentScale.Fit
            )
        }
        Text(
            text = content,
            style = TextStyle(fontSize = 17.sp, fontWeight = FontWeight.W700)
        )
    }
}



 */