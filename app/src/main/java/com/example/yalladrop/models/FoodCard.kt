package com.example.yalladrop.models

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavHostController
import com.example.yalladrop.R


@Composable
fun FoodCard(
    item: FoodItems,
    state: OrderState,
    navController: NavHostController,
    lastItem: Boolean,
)
{

    Column (
    ){
        Box(
            modifier = Modifier
                .height(100.dp)
                .padding(horizontal = 20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {


                Box(
                    modifier = Modifier
                        .size(72.dp, 100.dp)
                        .clip(shape = RoundedCornerShape(20.dp)),

                    contentAlignment = Alignment.Center

                )
                {
                    Image(
                        painter = painterResource(id = item.painterId),
                        contentDescription = null,
                        contentScale = ContentScale.Crop ,
                        modifier = Modifier.clip(shape = RoundedCornerShape(20.dp)),
                    )

                }

                Spacer(modifier = Modifier.width(15.dp))
                Column(
                    modifier = Modifier.fillMaxHeight(0.8f),
                    verticalArrangement = Arrangement.SpaceAround

                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Box(
                            modifier = Modifier.fillMaxWidth(0.7f),
                        ) {
                            Text(
                                text = item.name,
                                style = MaterialTheme.typography.titleMedium,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                        Text(
                            text = "${item.price} DA",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                    Spacer(modifier = Modifier.height(7.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Bottom

                    ) {
                        Text(
                            text = "${(if (item.date.dayOfMonth < 10) "0" else "") +item.date.dayOfMonth} "
                                    +"${item.date.month.name.toString().substring(0, 3).lowercase().capitalize()}, "
                                    +"${(if (item.date.hour < 10) "0" else "") +item.date.hour}:"
                                    +"${(if (item.date.minute < 10) "0" else "") +item.date.minute}",
                            style = MaterialTheme.typography.labelSmall
                        )
                        if(state != OrderState.INPROGRESS){
                        Text(
                            text = "${item.numItem} items",
                            style = MaterialTheme.typography.labelSmall
                        )}
                    }
                    if (state == OrderState.COMPLETED)
                    {
                        Row {
                            Image(
                                painter = painterResource(id = R.drawable.icon_completed_icon),
                                contentDescription = null,
                                modifier = Modifier.size(11.dp)
                            )
                            Text(
                                text = "Order delivered",
                                style = MaterialTheme.typography.displaySmall,
                                color =  MaterialTheme.colorScheme.onBackground,
                                modifier = Modifier.padding(start = 5.dp, bottom = 2.dp, top = 2.dp)
                            )
                        }


                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween

                    ) {
                        Button(
                            onClick = {

                                navController.navigate("LeaveReview")
                            },
                            modifier = Modifier
                                .height(26.dp)
                                .width(125.dp)
                                .border(
                                    0.5.dp,
                                    color = MaterialTheme.colorScheme.onBackground,
                                    RoundedCornerShape(45.dp)
                                )
                                .clip(RoundedCornerShape(38))

                        ) {
                            Text(
                                text = "Leave a review",
                                style = MaterialTheme.typography.labelSmall,
                                color = Color.White,
                                fontSize = 12.5.sp
                            )
                        }
                        Button(
                            onClick = { navController.navigate("ConfirmeOrder") },
                            modifier = Modifier
                                .height(26.dp)
                                .width(110.dp)
                                .clip(RoundedCornerShape(38)),
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),


                            ) {
                            Text(
                                text = "Order Again",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onBackground,
                                fontSize = 12.5.sp
                            )
                        }
                    }
                }
                    else if (state == OrderState.CANCELED)
                    {
                        Row {
                            Image(
                                painter = painterResource(id = R.drawable.icon_canceled_icon),
                                contentDescription = null,
                                modifier = Modifier.size(11.dp)
                            )
                            Text(
                                text = "Order cancelled",
                                style = MaterialTheme.typography.displaySmall,
                                color = MaterialTheme.colorScheme.onBackground,
                                modifier = Modifier.padding(start = 5.dp, bottom = 2.dp, top = 2.dp)
                            )
                        }


                    }
                    else if (state == OrderState.INPROGRESS)
                    {
                        var itemNum by remember { mutableStateOf(item.numItem)}
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween

                        ) {
                            Button(
                                onClick = {
                                    /*TODO*/
                                },
                                modifier = Modifier
                                    .height(26.dp)
                                    .clip(RoundedCornerShape(38)),
                                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),


                                ) {
                                Text(
                                    text = "Cancel Order",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = Color("#E95322".toColorInt())
                                )
                            }
                            Row (verticalAlignment = Alignment.CenterVertically){

                                    Image(
                                        painter = painterResource(id = R.drawable.icon_minceicon),
                                        contentDescription = null,
                                        modifier = Modifier.size(30.dp).
                                        clickable{
                                            if (item.numItem != 1){
                                                item.numItem-- ;
                                            };
                                            itemNum = item.numItem}
                                    )

                                Text(
                                    text = "${itemNum}",
                                    style = MaterialTheme.typography.labelMedium,
                                    fontSize = 18.sp,
                                    modifier = Modifier.padding(horizontal = 5.dp)

                                   )

                                    Image(
                                        painter = painterResource(id = R.drawable.icon_addicon),
                                        contentDescription = null,
                                        modifier = Modifier.size(30.dp).
                                        clickable{
                                            item.numItem++;
                                            itemNum = item.numItem
                                        }
                                    )

                            }
                        }

                    }
                    else
                    {



                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween

                        ) {
                            Button(
                                onClick = {
                                    navController.navigate("CancelOrderReasons")
                                },
                                modifier = Modifier
                                    .height(26.dp)
                                    .border(
                                        0.5.dp,
                                        color = MaterialTheme.colorScheme.onBackground,
                                        RoundedCornerShape(45.dp)
                                    )
                                    .clip(RoundedCornerShape(38))

                            ) {
                                Text(
                                    text = "Cancel Order",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = Color.White
                                )
                            }
                            Button(
                                onClick = {

                                    navController.navigate("TrackDelivery")

                                },
                                modifier = Modifier
                                    .height(26.dp)
                                    .clip(RoundedCornerShape(38)),
                                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),


                                ) {
                                Text(
                                    text = "Track Driver",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = Color("#E95322".toColorInt())
                                )
                            }
                        }
                    }
                }
            }
        }
        if(!lastItem)
        HorizontalDivider(thickness = 1.5.dp , color = Color("#FFD8C7".toColorInt()) , modifier = Modifier.padding(top = 10.dp , bottom = 15.dp))

    }
}
