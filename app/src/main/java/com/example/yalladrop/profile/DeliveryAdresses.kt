package com.example.yalladrop.profile

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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.yalladrop.Address
import com.example.yalladrop.AddressViewModel
import com.example.yalladrop.models.PrincipaleBackGroound
import com.example.yalladrop.R


@Composable
fun DeliveryAdresses(navController: NavHostController, viewModel: AddressViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
) {

    val addresses = viewModel.allAddresses.collectAsState(initial = emptyList()).value






    PrincipaleBackGroound(title = "Delivery Addresses", navController) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            HorizontalDivider(thickness = 1.5.dp , color = Color("#FFD8C7".toColorInt()) , modifier = Modifier.padding(top = 10.dp , bottom = 15.dp))


            Box (
                modifier = Modifier
                    .fillMaxHeight(0.6f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.TopCenter
            ){
                LazyColumn(
                    modifier = Modifier.fillMaxHeight()
                ) {

                    itemsIndexed(addresses) { index, item ->
                        AdressCard(adr = item ,  onDelete = { viewModel.deleteAddress(it) })
                    }
                }
            }
            Box (
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ){
                Button(
                    onClick = {
                        navController.navigate("NewAdress")
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
fun AdressCard(adr : Address , onDelete: (Address) -> Unit){
    var showDialog by remember { mutableStateOf(false) }

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
                        text = adr.address,
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.Gray,
                        overflow = TextOverflow.Ellipsis
                    )

                }


            }

            TextButton(onClick = {
                  showDialog=true

            }) {
                Text(
                    text = "Delete",
                    style = MaterialTheme.typography.displayMedium,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary,

                    )
            }

            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = { Text("Delete Address" , style = MaterialTheme.typography.displayLarge ) },
                    text = { Text("Are you sure you want to delete this address?" , style = MaterialTheme.typography.displayMedium , fontSize = 17.sp) },
                    confirmButton = {
                        TextButton(onClick = {
                            onDelete(adr)
                            showDialog = false
                        }) {
                            Text("Delete", color = MaterialTheme.colorScheme.primary)
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showDialog = false }) {
                            Text("Cancel")
                        }
                    }
                )
            }

        }
    }
    HorizontalDivider(thickness = 1.5.dp , color = Color("#FFD8C7".toColorInt()) , modifier = Modifier.padding(top = 10.dp , bottom = 15.dp))


}


