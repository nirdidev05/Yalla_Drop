package com.example.yalladrop.models

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.yalladrop.R

@Composable
fun BottomNavigationBar(
    navController : NavHostController
){
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Top,
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp)

            .background(MaterialTheme.colorScheme.primary)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.navbar_home),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 15.dp)
                    .size(27.dp, 27.dp)
                    .clickable {
                        navController.navigate("DeliveryAdresses")

                    }
            )

            Text(
                text = "Home",
                style = MaterialTheme.typography.labelSmall,
                color = Color.White,
                fontSize = 13.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.navbar_shoppingcart),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 15.dp)
                    .size(27.dp, 27.dp)
                    .clickable {
                        navController.navigate("ActiveOrders")

                    }
            )

            Text(
                text = "Cart",
                style = MaterialTheme.typography.labelSmall,
                color = Color.White,
                fontSize = 13.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.navbar_heart),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 15.dp)
                    .size(27.dp, 27.dp)
                    .clickable {
                        navController.navigate("ContactUs")

                    }
            )

            Text(
                text = "Favorite",
                style = MaterialTheme.typography.labelSmall,
                color = Color.White,
                fontSize = 13.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.navbar_notification),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 15.dp)
                    .size(27.dp, 27.dp)
                    .clickable {
                        navController.navigate("Notifications")

                    }
            )

            Text(
                text = "Notifications",
                style = MaterialTheme.typography.labelSmall,
                color = Color.White,
                fontSize = 13.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.navbar_user),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 15.dp)
                    .size(27.dp, 27.dp)
                    .clickable {
                        navController.navigate("Profile")

                    }
            )

            Text(
                text = "Profile",
                style = MaterialTheme.typography.labelSmall,
                color = Color.White,
                fontSize = 13.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}