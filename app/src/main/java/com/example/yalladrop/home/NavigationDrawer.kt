package com.example.yalladrop.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.core.graphics.toColorInt
import com.example.yalladrop.R

@Composable
fun DrawerHeader(modifier: Modifier = Modifier) {

     Column(
         modifier = Modifier.padding(horizontal = 20.dp).padding(top = 30.dp)
     ) {
         Row(
             verticalAlignment = Alignment.CenterVertically ,
             modifier = Modifier.padding(bottom = 30.dp)
         ) {
             Image(
                 painter = painterResource(id = R.drawable.map), // Replace with your image resource
                 contentDescription = null,
                 modifier = Modifier
                     .size(70.dp)
                     .clip(CircleShape),
                 contentScale = ContentScale.Crop
             )
             Column(
                 modifier = Modifier
                     .padding(start = 10.dp, top = 10.dp)
                     .padding(bottom = 10.dp),
             ) {
                 Text(
                     text = "Restaurant name",
                     style = MaterialTheme.typography.displayLarge,
                     fontSize = 24.sp,
                     fontWeight = FontWeight.SemiBold,
                     color = Color.White,
                     modifier = Modifier.padding(bottom = 5.dp)
                 )
                 Text(
                     text = "Courier",
                     style = MaterialTheme.typography.labelMedium,
                     fontSize = 17.sp,
                     color = Color.Black.copy(alpha = 0.3f)
                 )
             }
         }

         DrawerItem("My Orders" , R.drawable.myordersicon , {})
         HorizontalDivider(thickness = 1.5.dp , color = Color.Black , modifier = Modifier.padding(vertical = 13.dp ))
         DrawerItem("My Profile" , R.drawable.profileicon ,  {})
         HorizontalDivider(thickness = 1.5.dp , color = Color.Black , modifier = Modifier.padding(vertical = 13.dp ))
         DrawerItem("Delivery Address" , R.drawable.addressesicon,  {})
         HorizontalDivider(thickness = 1.5.dp , color = Color.Black , modifier = Modifier.padding(vertical = 13.dp ))
         DrawerItem("Payment Methods" , R.drawable.paymenticon,  {})
         HorizontalDivider(thickness = 1.5.dp , color = Color.Black , modifier = Modifier.padding(vertical = 13.dp ))
         DrawerItem("Contact Us" , R.drawable.contactusicon,  {})
         HorizontalDivider(thickness = 1.5.dp , color = Color.Black , modifier = Modifier.padding(vertical = 13.dp ))
         DrawerItem("Help Center" , R.drawable.helpicon,  {})
         HorizontalDivider(thickness = 1.5.dp , color = Color.Black , modifier = Modifier.padding(vertical = 13.dp ))
         DrawerItem("Settings" , R.drawable.settingsicon,  {})
         HorizontalDivider(thickness = 1.5.dp , color = Color.Black , modifier = Modifier.padding(vertical = 13.dp ))
         DrawerItem("Log Out", R.drawable.logouticon,  {})


     }
}

@Composable
fun DrawerItem(title : String  , iconId : Int, onClick: ()-> Unit){
    Row(
        modifier = Modifier.clickable {
            onClick()
        },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = iconId), // Replace with your image resource
            contentDescription = null,
            modifier = Modifier
                .size(35.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Text(
            text = title,
            style = MaterialTheme.typography.displayMedium,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp)
        )
    }
}

