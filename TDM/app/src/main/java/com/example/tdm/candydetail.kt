package com.example.tdm

import FoodItem
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tdm.model.foodItemList
import com.example.tdm.ui.theme.*
val CandyPink = Color(0xFFFF85A1)
val SoftPurple = Color(0xFFE0BBE4)
val BubbleGumPink = Color(0xFFFFB5C2)
val MintGreen = Color(0xFF98E4C9)
val CreamsicleOrange = Color(0xFFFFB347)
val CottonCandyBlue = Color(0xFF87CEEB)
val LollipopRed = Color(0xFFFF6B6B)
val VanillaCream = Color(0xFFFFF5E1)
val ChocolateBrown = Color(0xFF7B5B4F)
val LicoriceGray = Color(0xFF4A4A4A)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CandyDetailScreen(
    foodId: Int,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val foodItem = remember {
        foodItemList.find { it.id == foodId }
    } ?: return

    var quantity by remember { mutableStateOf(1) }
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = BubbleGumPink
                        )
                    }
                },
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "SWEET DETAILS",
                            color = CandyPink,
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = VanillaCream
                )
            )
        },
        containerColor = VanillaCream
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(VanillaCream)
                .verticalScroll(scrollState)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .padding(16.dp)
                    .shadow(12.dp, CircleShape)
                    .background(Color.White, CircleShape)
            ) {
                Image(
                    painter = painterResource(id = foodItem.imageRes),
                    contentDescription = foodItem.name,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .background(Color.White, RoundedCornerShape(24.dp))
                    .padding(16.dp)
            ) {
                Text(
                    text = foodItem.name,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = CandyPink
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Filled.Star,
                            contentDescription = "Rating",
                            tint = CreamsicleOrange
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "${foodItem.rating} (${foodItem.reviews} reviews)",
                            style = MaterialTheme.typography.bodySmall,
                            color = LicoriceGray
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${foodItem.category} •",
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                            color = SoftPurple
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "${foodItem.price}",
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                            color = CandyPink
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Description",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                    color = BubbleGumPink
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = foodItem.description,
                    style = MaterialTheme.typography.bodyLarge.copy(lineHeight = 24.sp),
                    color = LicoriceGray
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Ingredients",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                    color = BubbleGumPink
                )

                Spacer(modifier = Modifier.height(8.dp))

                Column {
                    foodItem.ingredients.forEach { ingredient ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(vertical = 4.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(6.dp)
                                    .background(MintGreen, CircleShape)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = ingredient,
                                style = MaterialTheme.typography.bodySmall,
                                color = LicoriceGray
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Action Section
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(Color.White, RoundedCornerShape(24.dp))
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { if (quantity > 1) quantity-- },
                    modifier = Modifier
                        .background(CandyPink, CircleShape)
                        .size(40.dp)
                ) {
                    Icon(Icons.Default.Remove, contentDescription = "Decrease", tint = Color.White)
                }

                Text(
                    text = "$quantity",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(horizontal = 16.dp),
                    color = LicoriceGray
                )

                IconButton(
                    onClick = { quantity++ },
                    modifier = Modifier
                        .background(CandyPink, CircleShape)
                        .size(40.dp)
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Increase", tint = Color.White)
                }

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = { /* Add to cart */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = BubbleGumPink
                    ),
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .shadow(8.dp, RoundedCornerShape(20.dp))
                        .height(48.dp)
                ) {
                    Text(
                        "Add to Cart",
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = FontWeight.Medium
                        ),
                        color = Color.White
                    )
                }
            }
        }
    }
}