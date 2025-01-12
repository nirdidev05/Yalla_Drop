package com.example.tdm

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
@Composable
fun grocerypage() {
    Column(
        modifier = Modifier.run {
            fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(Color(0xFFFF5722))
        }
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
                        painter = painterResource(id = R.drawable.carte),
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
                        painter = painterResource(id = R.drawable.noti),
                        contentDescription = null,
                        tint = Color(0xFFFF5722)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier.padding(16.dp),

        )

        {
            Text(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .fillMaxWidth(),
                text = "Grocery",
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )
        }



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
            PromoBanner()
            Spacer(modifier = Modifier.height(16.dp))
            CategorySection(categories)
            Spacer(modifier = Modifier.height(16.dp))
            DealsSection()

        }
    }
        }

@Composable
fun PromoBanner() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.uno), // Replace with actual banner resource
            contentDescription = "Promo Banner",
            modifier = Modifier.fillMaxSize()
        )
    }
}
@Composable
fun CategorySection(categories: List<Category>) {
    // State to control whether to show all categories or just the first 8
    var showAll by remember { mutableStateOf(false) }

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("All Categories", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Text(
                "See All",
                color = Color.Gray,
                fontSize = 16.sp,
                modifier = Modifier
                    .clickable { showAll = !showAll } // Toggle showAll state on click
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.height(200.dp),
            userScrollEnabled = showAll // Enable scrolling only when showAll is true
        ) {
            // Determine the number of items to display based on showAll state
            val itemsToShow = if (showAll) categories.size else minOf(categories.size, 8)

            items(itemsToShow) { index ->
                CategoryItem(categories[index])
            }
        }
    }
}

@Composable
fun CategoryItem(category: Category) {
    Column(
        modifier = Modifier.padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .background(Color.LightGray, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = category.imageResId),
                contentDescription = null,
                modifier = Modifier.fillMaxSize() // Adjust image size if needed
            )
        }
        Text(category.name, fontSize = 14.sp, fontWeight = FontWeight.Medium)
    }
}

@Composable
fun DealsSection() {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Best Deals", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Text("See All", color = Color.Gray, fontSize = 16.sp, modifier = Modifier.clickable { /* Navigate to deals */ })
        }
        Spacer(modifier = Modifier.height(8.dp))

        }
    }


@Composable
fun DealItem(deal: Deal) {
    Card(
        modifier = Modifier
            .width(150.dp)
            .padding(8.dp)
            .clickable { /* Add to cart */ },
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = deal.imageResId),
                contentDescription = deal.name,
                modifier = Modifier.size(60.dp)
            )
            Text(deal.name, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
            Text("${deal.oldPrice}$", color = Color.Gray, fontSize = 12.sp, textDecoration = TextDecoration.LineThrough)
            Text("${deal.newPrice}$", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color(0xFFFFA726))
            Button(
                onClick = { /* Add to cart */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA726))
            ) {
                Text(text = "Add", color = Color.White)
            }
        }
    }
}

// Sample data classes for categories and deals
data class Deal(val imageResId: Int, val name: String, val oldPrice: Int, val newPrice: Int)
data class Category(
    val name: String,
    val imageResId: Int // Resource ID of the category image
)

val categories = listOf(
    Category(name = "Vegetables & Fruits", imageResId = R.drawable.fruit),
    Category(name = "Dairy & Breakfast", imageResId = R.drawable.fish),
    Category(name = "Cold Drinks & Juices", imageResId = R.drawable.cook),
    Category(name = "Instant & Frozen Food", imageResId = R.drawable.cook),
    Category(name = "Tea & Coffee", imageResId = R.drawable.the),
    Category(name = "Atta, Rice & Dal", imageResId = R.drawable.rice),
    Category(name = "Masala, Oil & Dry Fruits", imageResId = R.drawable.dry),
    Category(name = "Chicken, Meat & Fish", imageResId = R.drawable.meat),
    Category(name = "Produits Laitiers", imageResId = R.drawable.the)
)

val deals = listOf(
    Deal(R.drawable.images, "Surf Excel Easy Wash", 14, 12),
    Deal(R.drawable.groupdis, "Fortune Arhar Dal", 12, 9)
)
