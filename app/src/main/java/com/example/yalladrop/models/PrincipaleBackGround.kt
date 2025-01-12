package com.example.yalladrop.models

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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.yalladrop.ContactUs
import com.example.yalladrop.R
import com.example.yalladrop.home.DrawerHeader
import kotlinx.coroutines.launch


@Composable
fun PrincipaleBackGroound(title: String, navController : NavHostController , content:  @Composable() () -> Unit ,) {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(

        drawerState = drawerState,
        drawerContent = {

                ModalDrawerSheet(
                            drawerContainerColor = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.fillMaxWidth(0.75f).clip(RoundedCornerShape(topEnd =  80.dp )) // Set the desired background color

                ) {
                    DrawerHeader()
                }
        }
    ) {


        Scaffold(

        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.primary),

                ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.2f)
                        .align(Alignment.TopCenter),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier = Modifier.padding(10.dp)
                    ) {
                        Icon(
                            Icons.Default.ArrowBackIosNew,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.clickable {
                                scope.launch {
                                    drawerState.apply {
                                        if (isClosed) open() else close()
                                    }
                                }


                              //  navController.popBackStack()

                            }
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(),
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(
                                text = title,
                                color = Color.White,
                                style = MaterialTheme.typography.titleLarge
                            )
                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.8f)
                        .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                        .background(color = Color.White)
                        .align(Alignment.BottomCenter),


                    ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 20.dp, vertical = 20.dp)
                    ) {
                        content()


                    }
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.Top,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(110.dp)
                        .align(Alignment.BottomCenter)
                        .background(MaterialTheme.colorScheme.primary)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.home),
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
                            painter = painterResource(id = R.drawable.shoppingcart),
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
                            painter = painterResource(id = R.drawable.heart),
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
                            painter = painterResource(id = R.drawable.notification),
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
                            painter = painterResource(id = R.drawable.user),
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
        }
    }
}

//}
