package com.example.yalladrop

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.yalladrop.models.PrincipaleBackGroound

@Composable
fun ContactUs(navController: NavHostController) {
    PrincipaleBackGroound(title = "Help Center", navController =navController ) {
        Column(
            modifier = Modifier.padding(top = 30.dp)
        ) {
            ContactCard(Text = "Customer service", painterID = R.drawable.help_headphones  , {})
            ContactCard(Text = "WebSite", painterID = R.drawable.help_globalicon, {})
            ContactCard(Text = "Facebook", painterID = R.drawable.help_facebook, {})
            ContactCard(Text = "Whatsapp", painterID = R.drawable.help_whatapp, {})
            ContactCard(Text = "Instagram", painterID = R.drawable.help_instagram, {} )



        }


    }
}

@Composable
fun ContactCard(Text : String , painterID : Int , onClick:() -> Unit){
    Row(
        verticalAlignment = Alignment.CenterVertically ,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 10.dp).clickable { onClick()  }
    ) {
        Image(
            painter = painterResource(id = painterID), // Replace with your image resource
            contentDescription = null,
            modifier = Modifier.size(35.dp),
        )
        
        Text(text = Text , style = MaterialTheme.typography.displayMedium , fontSize = 20.sp , fontWeight = FontWeight.SemiBold , modifier = Modifier.padding(start = 15.dp))
    }
}