package com.example.yalladrop.restauration

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.yalladrop.R
import com.example.yalladrop.models.BottomNavigationBar

@Composable
fun FoodPage(navController: NavHostController) {
    Box (
        modifier = Modifier.fillMaxSize()

    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 100.dp)
                .verticalScroll(rememberScrollState())
                .background(Color(0xFFFF5722))
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp),
                verticalAlignment = Alignment.CenterVertically


            ) {

                var searchText by remember { mutableStateOf(TextFieldValue("")) }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .width(200.dp)
                        .height(36.dp)
                        .background(Color.White, RoundedCornerShape(30.dp))
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search icon",
                        tint = Color.Gray,
                        modifier = Modifier
                            .size(18.dp)
                            .clickable { }
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    // Text field
                    BasicTextField(
                        value = searchText,
                        onValueChange = { searchText = it },
                        singleLine = true,
                        textStyle = TextStyle(
                            color = Color.Black,
                            fontSize = 12.sp,
                            textAlign = TextAlign.Start
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        decorationBox = { innerTextField ->
                            if (searchText.text.isEmpty()) {
                                Text(
                                    "Search",
                                    color = Color.Gray,
                                    fontSize = 12.sp,
                                    textAlign = TextAlign.Start
                                )
                            }
                            innerTextField()
                        }
                    )
                }


                Spacer(modifier = Modifier.width(90.dp))

                IconButton(onClick = { /*TODO*/ }) {
                    Box(
                        modifier = Modifier
                            .size(30.dp)
                            .background(Color.White, shape = RoundedCornerShape(13.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_shoppingcart),
                            contentDescription = null,
                            tint = Color(0xFFFF5722)

                        )
                    }
                }

                IconButton(onClick = { /*TODO*/ }) {
                    Box(
                        modifier = Modifier
                            .size(30.dp)
                            .background(Color.White, shape = RoundedCornerShape(13.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_notification),
                            contentDescription = null,
                            tint = Color(0xFFFF5722)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                modifier = Modifier.padding(start = 10.dp),

                text = "Bon Appetit",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Color.White,
                        shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                    )
                    .padding(16.dp)
            ) {

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),

                    horizontalArrangement = Arrangement.Center
                ) {
                    CategoryItem("Snacks", R.drawable.test_foodcategoryfilter_snaks, navController)
                    CategoryItem("Meal", R.drawable.test_foodcategoryfilter_meal, navController)
                    CategoryItem("Vegan", R.drawable.test_foodcategoryfilter_vegan, navController)
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Divider(
                        color = Color(0xFFFC6E2A),
                        thickness = 1.dp,
                        modifier = Modifier.width(300.dp) // Set specific width
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Best Seller",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        "View All >",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFFF5722)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Horizontal Scrollable Best Seller Items
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                ) {
                    repeat(8) { // Assuming 8 items
                        Image(
                            painter = painterResource(id = R.drawable.test_food_cupcake),
                            contentDescription = null,
                            modifier = Modifier
                                .size(width = 100.dp, height = 150.dp)
                                .padding(end = 8.dp)
                                .clip(RoundedCornerShape(20.dp))
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                val images = listOf(
                    R.drawable.test_pub4,
                    R.drawable.test_pub5,
                    R.drawable.test_pub6,
                    R.drawable.test_pub7,
                    R.drawable.test_pub8
                )

                // State to keep track of the current image index
                var currentIndex by remember { mutableStateOf(0) }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                ) {
                    // Display the current image
                    Image(
                        painter = painterResource(id = images[currentIndex]),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .clip(RoundedCornerShape(30.dp))
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.icon_phone),
                        contentDescription = "Previous Image",
                        modifier = Modifier
                            .size(30.dp)
                            .align(Alignment.CenterStart)
                            .padding(start = 16.dp)
                            .clickable {
                                currentIndex =
                                    if (currentIndex > 0) currentIndex - 1 else images.size - 1
                            },
                        tint = Color.Transparent
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.icon_phone),
                        contentDescription = "Next Image",
                        modifier = Modifier
                            .size(30.dp)
                            .align(Alignment.CenterEnd)
                            .padding(end = 16.dp)
                            .clickable {
                                // Increment index to go to next image
                                currentIndex =
                                    if (currentIndex < images.size - 1) currentIndex + 1 else 0
                            },
                        tint = Color.Transparent
                    )

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 8.dp)
                    ) {
                        images.forEachIndexed { index, _ ->
                            Box(
                                modifier = Modifier
                                    .size(8.dp)
                                    .background(
                                        color = if (index == currentIndex) Color(0xFFE95322) else Color(
                                            0xFFFF7622
                                        ),
                                        shape = CircleShape
                                    )
                                    .padding(4.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Recommend Section
                Text(
                    text = "Recommend",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(8.dp))


                Row(
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    RecommendItem(
                        imageRes = R.drawable.test_pub6, price = "$10.0"
                    )
                    RecommendItem(imageRes = R.drawable.test_food_rice, price = "$25.0")
                }

            }

            Spacer(modifier = Modifier.height(16.dp))

        }
        Box(
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            BottomNavigationBar(navController)
        }

    }


}


@Composable
fun CategoryItem(name: String) {
    Box(
        modifier = Modifier
            .size(80.dp, 36.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xFFE6E6E6)),
        contentAlignment = Alignment.Center
    ) {
        Text(text = name, fontSize = 14.sp, fontWeight = FontWeight.Medium, color = Color.Black)
    }
}


@Composable
fun RecommendItem(imageRes: Int, price: String, rating: String = "5.0") {
    var isFavorite by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "Recommended item image",
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(10.dp))
        )

        Row(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Rating text with star icon
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(20.dp)
                
            ) {
                Box(
                    modifier = Modifier
                        .background(Color.White.copy(alpha = 0.7f), RoundedCornerShape(10.dp))
                        .padding(horizontal = 6.dp, vertical = 4.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = rating,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Rating star",
                            tint = Color(0xFFFFC107), // Gold color for the star
                            modifier = Modifier.size(14.dp)
                        )
                    }
            }
            }
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Favorite icon",
                modifier = Modifier
                    .size(20.dp)
                    .background(Color.White.copy(alpha = 0.7f), RoundedCornerShape(50))
                    .padding(4.dp)
                    .clickable {
                        isFavorite = !isFavorite // Toggle favorite state
                    },
                tint = if (isFavorite) Color.White else Color(0xFFE57373) // Change color based on favorite state
            )

        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(60.dp, 26.dp)

                .clip(
                    RoundedCornerShape(
                        topStart = 30.dp,
                        bottomStart = 30.dp
                    )
                )
                .background(Color(0xFFFF5722))
        ) {
            Text(
                text = price,
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.Center),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}



@Composable
fun CategoryItem(name: String, iconRes: Int,navController: NavController) {
    Column(

        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Circle background with icon
        Box(
            modifier = Modifier
                .size(70.dp)
                .clickable {
                    navController.navigate("ecran2")
                },
            contentAlignment = Alignment.Center

        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = name ,
                tint = Color(0xFFFF5722),

            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        // Label
        Text(
            text = name,
            fontSize = 12.sp,
            color = Color.Black
        )
    }
}

