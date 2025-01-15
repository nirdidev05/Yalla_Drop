package com.example.tdm.ui.theme

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tdm.R
import androidx.compose.runtime.Composable
import androidx.compose.ui.draw.clip

import com.example.tdm.model.DessertShop
import com.example.tdm.model.FoodItem
import com.example.tdm.model.FoodIteme
import com.example.tdm.model.Restaurant
import com.example.tdm.model.allDessertShops
import com.example.tdm.model.allFoodItems
import com.example.tdm.model.allRestaurants
import com.example.tdm.model.foodItemList
import java.time.LocalTime


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomePage(navController: NavController, planName: String) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("All") }

    // Filtered lists based on search query
    val filteredRestaurants = remember(searchQuery, selectedCategory) {
        val lowerQuery = searchQuery.lowercase()
        when (selectedCategory) {
            "All" -> allRestaurants.filter {
                it.name.lowercase().contains(lowerQuery) ||
                        it.specialties.any { specialty -> specialty.lowercase().contains(lowerQuery) }
            }
            else -> allRestaurants.filter {
                it.category == selectedCategory &&
                        (it.name.lowercase().contains(lowerQuery) ||
                                it.specialties.any { specialty -> specialty.lowercase().contains(lowerQuery) })
            }
        }
    }

    val filteredDessertShops = remember(searchQuery, selectedCategory) {
        val lowerQuery = searchQuery.lowercase()
        when (selectedCategory) {
            "All" -> allDessertShops.filter {
                it.name.lowercase().contains(lowerQuery) ||
                        it.specialties.any { specialty -> specialty.lowercase().contains(lowerQuery) }
            }
            else -> allDessertShops.filter {
                it.category == selectedCategory &&
                        (it.name.lowercase().contains(lowerQuery) ||
                                it.specialties.any { specialty -> specialty.lowercase().contains(lowerQuery) })
            }
        }
    }

    val filteredFoodItems = remember(searchQuery, selectedCategory) {
        val lowerQuery = searchQuery.lowercase()
        when (selectedCategory) {
            "All" -> allFoodItems.filter {
                it.name.lowercase().contains(lowerQuery) ||
                        it.description.lowercase().contains(lowerQuery)
            }
            else -> allFoodItems.filter {
                it.category == selectedCategory &&
                        (it.name.lowercase().contains(lowerQuery) ||
                                it.description.lowercase().contains(lowerQuery))
            }
        }
    }

    val filteredDessertItems = remember(searchQuery, selectedCategory) {
        val lowerQuery = searchQuery.lowercase()
        when (selectedCategory) {
            "All" -> foodItemList.filter {
                it.name.lowercase().contains(lowerQuery) ||
                        it.description.lowercase().contains(lowerQuery)
            }
            else -> foodItemList.filter {
                it.category == selectedCategory &&
                        (it.name.lowercase().contains(lowerQuery) ||
                                it.description.lowercase().contains(lowerQuery))
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        GreetingSection("Walid", "oued smar,Alger, 16")
        Spacer(modifier = Modifier.height(16.dp))
        YallaPaySection(balance = planName, navController)
        Spacer(modifier = Modifier.height(16.dp))
        CategoriesSection(navController)
        Spacer(modifier = Modifier.height(16.dp))
        PromoBanner()
        Spacer(modifier = Modifier.height(16.dp))
        SearchBar(
            searchQuery = searchQuery,
            onSearchQueryChange = { searchQuery = it }
        )
        Spacer(modifier = Modifier.height(16.dp))
        CategoriesChips(
            selectedCategory = selectedCategory,
            onCategorySelected = { selectedCategory = it }
        )
        Spacer(modifier = Modifier.height(16.dp))

        if (searchQuery.isNotEmpty()) {
            SearchResults(
                restaurants = filteredRestaurants,
                dessertShops = filteredDessertShops,
                foodItems = filteredFoodItems,
                dessertItems = filteredDessertItems,
                navController = navController
            )
        } else {
            RestaurantsSection(navController)
            Spacer(modifier = Modifier.height(16.dp))
            CancerAwarenessBanner()
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun GreetingSection(name: String, location: String) {
    val greetingMessage = remember {
        val currentHour = LocalTime.now().hour
        when {
            currentHour in 5..11 -> "Good Morning!"
            currentHour in 12..16 -> "Good Afternoon!"
            currentHour in 17..20 -> "Good Evening!"
            else -> "Good Night!"
        }
    }

    Column {
        Row(
            modifier = Modifier
                .padding(4.dp),

            ) {
            Text(text = "Hey $name, ", fontSize = 20.sp)
            Text(text = greetingMessage, fontWeight = FontWeight.Bold, fontSize = 20.sp)

            Spacer(modifier = Modifier.weight(1f))

            Box(

                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(30.dp)
                    .background(Color(0x77FF5722), CircleShape)
                    .clickable {  }
            ) {
                Text(
                    text = name.firstOrNull()?.uppercase() ?: "",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }

    Row(
        modifier = Modifier.padding(horizontal = 20.dp)
    ){
        Text(text = location, fontSize = 14.sp, color = Color.Gray)
    }}

@Composable
fun YallaPaySection(balance: String,navController: NavController) {
    Row(
        modifier = Modifier
            .width(350.dp)
            .background(Color(0xA8FF7622), RoundedCornerShape(12.dp))
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Yalla Pay",
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))
            Box(modifier = Modifier.padding(horizontal = 30.dp)) {
                Text(
                    text = "$balance ",
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black
                )
            }
        }
        Box(
            modifier = Modifier
                .padding(start = 16.dp)
                .clickable(onClick = { navController.navigate("deliverySubscription") })
                .background(Color(0xFFFF5722), RoundedCornerShape(20.dp))
                .padding(horizontal = 12.dp, vertical = 6.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.AddCircle,
                    contentDescription = "Plus icon",
                    tint = Color.Black,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Subscribe",
                    color = Color.Black,
                    fontWeight = FontWeight.ExtraBold
                )
            }
        }
    }
}
@Composable
fun CategoriesSection(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.fod),
            contentDescription = "Food",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.5f)
                .clickable {
                    navController.navigate("foodPage")
                }
        )

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.grocery),
                contentDescription = "Grocery 1",
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(end = 4.dp)
                    .clickable {
                        navController.navigate("grocery")}
            )

            Image(
                painter = painterResource(id = R.drawable.dessert),
                contentDescription = "Grocery 2",
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(start = 4.dp)
                    .clickable { navController.navigate("DessertPage") }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFE6E6E6), RoundedCornerShape(15.dp))
            .padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.width(10.dp))
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier.size(30.dp)
            )
            Spacer(modifier = Modifier.width(3.dp))
            TextField(
                value = searchQuery,
                onValueChange = onSearchQueryChange,
                placeholder = { Text("Search dishes, restaurants", color = Color.Gray) },
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    containerColor = Color.Transparent
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun SearchResults(
    restaurants: List<Restaurant>,
    dessertShops: List<DessertShop>,
    foodItems: List<FoodItem>,
    dessertItems: List<FoodIteme>,
    navController: NavController
) {
    Column {
        // Restaurants and Dessert Shops Section
        if (restaurants.isNotEmpty() || dessertShops.isNotEmpty()) {
            Text(
                "Restaurants & Shops",
                modifier = Modifier.padding(vertical = 8.dp)
            )

            restaurants.forEach { restaurant ->
                Box(
                    modifier = Modifier.clickable {
                        navController.navigate("foodDelivery/${restaurant.id}/${restaurant.name}/${restaurant.photoResId}")
                    }
                ) {
                    RestaurantCard(
                        name = restaurant.name,
                        rating = restaurant.rating.toDouble(),
                        deliveryFee = restaurant.deliveryFee,
                        deliveryTime = restaurant.deliveryTime,
                        specialties = restaurant.specialties
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            dessertShops.forEach { shop ->
                Box(
                    modifier = Modifier.clickable {
                        navController.navigate("candyDelivery/${shop.id}/${shop.name}/${shop.photoResId}")
                    }
                ) {
                    RestaurantCard(
                        name = shop.name,
                        rating = shop.rating.toDouble(),
                        deliveryFee = shop.deliveryFee,
                        deliveryTime = shop.deliveryTime,
                        specialties = shop.specialties
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        // Food Items Section
        if (foodItems.isNotEmpty() || dessertItems.isNotEmpty()) {
            Text(
                "Dishes & Desserts",
                modifier = Modifier.padding(vertical = 8.dp)
            )

            foodItems.forEach { item ->
                Box(
                    modifier = Modifier.clickable {
                        navController.navigate("foodDetail/${item.id}")
                    }
                ) {
                    FoodItemCard(item)
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            dessertItems.forEach { item ->
                Box(
                    modifier = Modifier.clickable {
                        navController.navigate("candyDetail/${item.id}")
                    }
                ) {
                    DessertItemCard(item)
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
@Composable
fun FoodItemCard(item: FoodItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = item.name,
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(4.dp))
            )

            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .weight(1f)
            ) {
                Text(
                    text = item.name,
                )
                Text(
                    text = item.price,
                    color = Color(0xFFFF5722)
                )
            }
        }
    }
}

@Composable
fun DessertItemCard(item: FoodIteme) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = item.name,
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(4.dp))
            )

            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .weight(1f)
            ) {
                Text(
                    text = item.name,
                )
                Text(
                    text = item.price,
                    color = Color(0xFFFF5722)
                )
            }
        }
    }
}
@Composable
fun CategoriesChips(
    selectedCategory: String,
    onCategorySelected: (String) -> Unit
) {
    val categories = listOf(
        "All" to R.drawable.all,
        "Fast Food" to R.drawable.burger,
        "Meal" to R.drawable.meal,
        "Traditional" to R.drawable.tra,
        "Grocery" to R.drawable.shopping,
        "Dessert" to R.drawable.des,
        "Candy" to R.drawable.candy,
        "Cafe" to R.drawable.cafe
    )

    val isDialogOpen = remember { mutableStateOf(false) }

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "All Categories",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                "View All >",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFF5722),
                modifier = Modifier.clickable { isDialogOpen.value = true }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(categories) { (category, _) ->
                Chip(
                    label = category,
                    isSelected = category == selectedCategory,
                    onClick = { onCategorySelected(category) }
                )
            }
        }

        if (isDialogOpen.value) {
            AlertDialog(
                onDismissRequest = { isDialogOpen.value = false },
                title = { Text("All Categories") },
                text = {
                    Column {
                        categories.forEach { (category, iconResId) ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .padding(vertical = 4.dp)
                                    .clickable {
                                        onCategorySelected(category)
                                        isDialogOpen.value = false
                                    }
                            ) {
                                Image(
                                    painter = painterResource(id = iconResId),
                                    contentDescription = "$category icon",
                                    modifier = Modifier.size(24.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = category,
                                    fontSize = 16.sp,
                                    color = if (category == selectedCategory) Color(0xFFFF5722) else Color.Black
                                )
                            }
                        }
                    }
                },
                buttons = {
                    Button(
                        onClick = { isDialogOpen.value = false },
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color.Black,
                            containerColor = Color(0xFFFF5722),
                        ),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Close")
                    }
                }
            )
        }
    }
}
@Composable
fun Chip(label: String, isSelected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .background(
                if (isSelected) Color(0xFFFF5722) else Color.LightGray,
                RoundedCornerShape(15.dp)
            )
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(text = label, color = Color.White)
    }
}

@Composable
fun RestaurantsSection(navController: NavController) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("All Restaurants", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            Text("View All >", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color(0xFFFF5722),
                modifier = Modifier.clickable {    }
            )
        }
        Spacer(modifier = Modifier.height(8.dp))




        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Top Discount", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            Text("View All >", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color(0xFFFF5722),  modifier = Modifier.clickable {    }
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

    }
}

@Composable
fun RestaurantCard(
    name: String,
    rating: Double,
    deliveryFee: String,
    deliveryTime: String,
    specialties: List<String>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(10.dp))
            .padding(16.dp)
    ) {

        Text(
            text = name,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row {
            specialties.forEachIndexed { index, specialty ->
                Text(
                    text = specialty + (if (index < specialties.size - 1) " - " else ""),
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.star1),
                contentDescription = "Rating",
                modifier = Modifier.size(16.dp),
                tint = Color(0xFFFF5722)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = "$rating", fontSize = 12.sp, color = Color.Black, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.width(8.dp))

            Icon(
                painter = painterResource(id = R.drawable.deliveryicon),
                contentDescription = "Delivery",
                modifier = Modifier.size(16.dp),
                tint = Color(0xFFFF5722)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = deliveryFee, fontSize = 12.sp, color = Color.Black)

            Spacer(modifier = Modifier.width(8.dp))


            Icon(
                painter = painterResource(id = R.drawable.clock),
                contentDescription = "Time",
                modifier = Modifier.size(16.dp),
                tint = Color(0xFFFF5722)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = deliveryTime, fontSize = 12.sp, color = Color.Black)
        }





    }
}



@Composable
fun CancerAwarenessBanner() {
    Row (
        modifier = Modifier
            .padding(16.dp)
            .width(600.dp),
        horizontalArrangement = Arrangement.Center
    )
    {
        Image(
            painter = painterResource(id = R.drawable.group1), contentDescription ="october" )

    }

}
@Composable
fun PromoBanner() {
    var showDiscountScreen by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        // Banner Row
        Row(
            modifier = Modifier
                .padding(16.dp)
                .width(400.dp)
                .clickable { showDiscountScreen = true } // Show discount screen on click
        ) {
            Image(
                painter = painterResource(id = R.drawable.banner),
                contentDescription = "pub"
            )
        }

        // Display the discount screen if `showDiscountScreen` is true
        if (showDiscountScreen) {
            DiscountScreen(onDismiss = { showDiscountScreen = false })
        }
    }
}

@Composable
fun DiscountScreen(onDismiss: () -> Unit) {
    var showDiscountCard by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .clickable { onDismiss() }
    ) {
        // Discount card
        Box(
            modifier = Modifier.padding(16.dp)
                .background(Color(0xFF4E3209), RoundedCornerShape(16.dp)) // Card color and shape
                .padding(24.dp)
        ) {
            Image(painter = painterResource(id = R.drawable.groupdis), contentDescription ="discount" )
        }
        }
}
