package com.example.yalladrop.profile

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavHostController
import com.example.yalladrop.models.PrincipaleBackGroound
import com.example.yalladrop.R
import com.example.yalladrop.models.AuthViewModel
import com.example.yalladrop.models.NavigationManager


@Composable
fun DeliveryAdresses() {




    var  adresses = listOf(
        adress("Home" , "shsgsfdgyugfhuwiifldskfjds"),
        adress("Home" , "shsgsfdgyugfhuwiifldskfjds"),
        adress("Home" , "shsgsfdgyugfhuwiifldskfjds")
    )


    PrincipaleBackGroound(title = "Delivery Addresses") {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            HorizontalDivider(thickness = 1.5.dp , color = Color("#FFD8C7".toColorInt()) , modifier = Modifier.padding(top = 10.dp , bottom = 15.dp))


            Box (
                modifier = Modifier.fillMaxHeight(0.6f).fillMaxWidth(),
                contentAlignment = Alignment.TopCenter
            ){
                LazyColumn(
                    modifier = Modifier.fillMaxHeight()
                ) {

                    itemsIndexed(adresses) { index, item ->
                        AdressCard(adr = item)
                    }
                }
            }
            Box (
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ){
                Button(
                    onClick = {
                        NavigationManager.navigate("NewAdress")
                    },
                    modifier = Modifier
                        .height(40.dp)
                        .width(250.dp)
                        .clip(RoundedCornerShape(38)),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),


                    ) {
                    Text(
                        text = "Create new address",
                        style = MaterialTheme.typography.displayMedium,
                        color = Color("#E95322".toColorInt())
                    )
                }
            }

        }

    }
}


@Composable
fun AdressCard(adr : adress){

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.Bottom
            ) {
                Image(
                    painter = painterResource(id = R.drawable.homeicon),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp, 50.dp)
                        .clickable {

                        }
                )
                Spacer(modifier = Modifier.padding(start = 10.dp))
                Column(
                    modifier = Modifier.height(45.dp),
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    Text(
                        text = adr.name,
                        style = MaterialTheme.typography.displayMedium,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = adr.adress,
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.Gray,
                        overflow = TextOverflow.Ellipsis
                    )

                }


            }

            Text(
                text = "Delete",
                style = MaterialTheme.typography.displayMedium,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary
            )

        }
    }
    HorizontalDivider(thickness = 1.5.dp , color = Color("#FFD8C7".toColorInt()) , modifier = Modifier.padding(top = 10.dp , bottom = 15.dp))


}


class adress (
    var name : String ,
    var adress : String

)