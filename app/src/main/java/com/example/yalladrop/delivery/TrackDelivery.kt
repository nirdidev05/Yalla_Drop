package com.example.yalladrop.delivery

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ChangeCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.yalladrop.R
import com.example.yalladrop.models.FoodItems
import com.example.yalladrop.orders.list

@Composable
fun TrackDelivery(navController: NavHostController) {
    var listCard : List<FoodItems> = list

    Box(modifier =Modifier.fillMaxSize() ){
        Image(
            painter = painterResource(id = R.drawable.img_map), // Replace with your image resource
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7f)
                .background(color = Color.White)
                .padding(top = 30.dp, bottom = 150.dp)
                .padding(horizontal = 20.dp)
                .align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Box(
                    modifier = Modifier
                        .size(70.dp, 70.dp)
                        .clip(shape = RoundedCornerShape(20.dp)),

                    contentAlignment = Alignment.Center

                )
                {
                    Image(
                        painter = painterResource(id = R.drawable.food_coffe),
                        contentDescription = null,
                        contentScale = ContentScale.Crop ,
                        modifier = Modifier.clip(shape = RoundedCornerShape(20.dp)),
                    )

                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, top = 10.dp)
                        .padding(bottom = 10.dp),
                ) {
                    Text(text = "Restaurant name" , style = MaterialTheme.typography.displayMedium , fontSize = 22.sp , fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(bottom = 5.dp)  )
                    Text(text = "Resaurant adress" ,  style = MaterialTheme.typography.labelSmall , fontSize = 14.sp ,  color = Color.Black.copy(alpha = 0.3f))

                    Column (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                    ) {
                        listCard.forEach { item ->
                            Row(modifier = Modifier
                                .padding(bottom = 2.dp)) {
                                Text(text = "${item.name}" , style = MaterialTheme.typography.labelSmall , fontSize = 12.sp ,  color = Color.Black.copy(alpha = 0.3f) )
                                Text(text = "x ${item.numItem} items" ,  style = MaterialTheme.typography.labelSmall ,  fontSize = 12.sp , modifier = Modifier.padding(start = 10.dp , bottom = 5.dp) , color = Color.Black.copy(alpha = 0.3f))
                            }
                        }
                    }
                }


            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Text(
                    text = "20 min",
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center
                )
                Text(text = "Estimated delivery time" , textAlign = TextAlign.Center ,style = MaterialTheme.typography.labelMedium , fontSize = 17.sp ,  color = Color.Black.copy(alpha = 0.3f))

            }

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row (
                   modifier = Modifier.fillMaxWidth() ,
                    horizontalArrangement = Arrangement.Start ,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(
                        Icons.Default.CheckCircle,
                        modifier = Modifier.size(17.dp),
                        tint = MaterialTheme.colorScheme.primary ,
                        contentDescription = null
                    )
                    Text(text = "Your order has been received" ,  style = MaterialTheme.typography.labelSmall ,  fontSize = 13.sp , modifier = Modifier.padding(start = 10.dp ) , color = Color.Black.copy(alpha = 0.3f))
                }
                Spacer(modifier = Modifier
                    .padding(start = 8.dp)
                    .size(width = 1.dp, 15.dp)
                    .background(color = MaterialTheme.colorScheme.primary))
                Row(
                    modifier = Modifier.fillMaxWidth() ,
                    horizontalArrangement = Arrangement.Start ,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.ChangeCircle,
                        contentDescription = null,
                        modifier = Modifier.size(17.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Text(text = "Your order has been received" ,  style = MaterialTheme.typography.labelSmall ,  fontSize = 13.sp , modifier = Modifier.padding(start = 10.dp ) , color = Color.Black.copy(alpha = 0.3f))
                }
                Spacer(modifier = Modifier
                    .padding(start = 8.dp)
                    .size(width = 1.dp, 15.dp)
                    .background(color = MaterialTheme.colorScheme.primary))
                Row (
                    modifier = Modifier.fillMaxWidth() ,
                    horizontalArrangement = Arrangement.Start ,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(
                        Icons.Default.CheckCircle,
                        modifier = Modifier.size(17.dp),
                        tint = MaterialTheme.colorScheme.primary ,
                        contentDescription = null
                    )
                    Text(text = "Your order has been received" ,  style = MaterialTheme.typography.labelSmall ,  fontSize = 13.sp , modifier = Modifier.padding(start = 10.dp ) , color = Color.Black.copy(alpha = 0.3f))
                }
                Spacer(modifier = Modifier
                    .padding(start = 8.dp)
                    .size(width = 1.dp, 20.dp)
                    .background(color = MaterialTheme.colorScheme.primary))
                Row(
                    modifier = Modifier.fillMaxWidth() ,
                    horizontalArrangement = Arrangement.Start ,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.CheckCircle,
                        contentDescription = null,
                        modifier = Modifier.size(17.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Text(text = "Your order has been received" ,  style = MaterialTheme.typography.labelSmall ,  fontSize = 13.sp , modifier = Modifier.padding(start = 10.dp ) , color = Color.Black.copy(alpha = 0.3f))
                }
            }





        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 50.dp)
                .align(Alignment.BottomCenter)

        ) {
            HorizontalDivider(thickness = 1.dp , color = Color.Black.copy(alpha = 0.2f) , modifier = Modifier.padding(top = 10.dp , bottom = 20.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {

                Image(
                    painter = painterResource(id = R.drawable.img_map), // Replace with your image resource
                    contentDescription = null,
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.75f)
                        .padding(start = 10.dp, top = 10.dp)
                        .padding(bottom = 10.dp),
                ) {
                    Text(
                        text = "Restaurant name",
                        style = MaterialTheme.typography.displayMedium,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(bottom = 5.dp)
                    )
                    Text(
                        text = "Courier",
                        style = MaterialTheme.typography.labelSmall,
                        fontSize = 14.sp,
                        color = Color.Black.copy(alpha = 0.3f)
                    )
                }

                Box(
                    modifier = Modifier
                        .size(45.dp)
                        .shadow(
                            elevation = 10.dp,
                            shape = RoundedCornerShape(50.dp)
                        )
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Default.Phone,
                        contentDescription = null,
                        modifier = Modifier.size(35.dp),
                        tint = Color.White
                    )
                }


            }
        }

        Row (
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp).padding(top = 30.dp).align(Alignment.TopStart),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(35.dp)
                    .clip(CircleShape)
                    .background(color = Color.Black)
                    .clickable{
                        navController.popBackStack()
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.ArrowBackIosNew,
                    contentDescription = null,
                    modifier = Modifier.size(17.dp),
                    tint = Color.White
                )
            }
            
            Text(text = "Track Order" , style = MaterialTheme.typography.displayMedium , modifier = Modifier.padding(start = 10.dp))
        }
    }
}