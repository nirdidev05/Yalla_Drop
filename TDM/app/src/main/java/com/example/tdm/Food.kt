package com.example.tdm.ui

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tdm.R
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import com.example.tdm.model.FoodItem
import com.example.tdm.model.Restaurant
import com.example.tdm.model.allFoodItems
import com.example.tdm.model.allRestaurants

@Composable
fun CategoryItem(name: String, iconResId: Int, frameResId: Int, selectedCategory: String?, onCategorySelected: (String?) -> Unit) {
    val content: @Composable () -> Unit = when (selectedCategory) {
        name -> {
            {
                Image(
                    painter = painterResource(id = frameResId),
                    contentDescription = name,
                    modifier = Modifier.size(60.dp)
                )
            }
        }
        else -> {
            {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFFF5722).copy(alpha = 0.1f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = iconResId),
                        contentDescription = name,
                        tint = Color(0xFFFF5722),
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                onCategorySelected(if (selectedCategory == name) null else name)
            }
    ) {
        content()
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = name,
            fontSize = 12.sp,
            color = if (selectedCategory == name) Color(0xFFFF5722) else Color.Black
        )
    }
}

@Composable
fun BestSellersScreen(
    restaurants: List<Restaurant>,
    favoriteRestaurants: Set<Int>,
    onFavoriteClick: (Int) -> Unit,
    onBackClick: () -> Unit,
    onRestaurantClick: (Restaurant) -> Unit
) {
    val bestSellers = restaurants.filter { it.rating >= 4.5 }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFF5722))
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.all),
                contentDescription = "Back",
                tint = Color.White,
                modifier = Modifier
                    .size(24.dp)
                    .clickable(onClick = onBackClick)
            )
            Text(
                text = "Best Sellers",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.size(24.dp))
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(bestSellers) { restaurant ->
                RestaurantCardDetailed(
                    restaurant = restaurant,
                    isFavorite = restaurant.id in favoriteRestaurants,
                    onFavoriteClick = { onFavoriteClick(restaurant.id) },
                    onRestaurantClick = onRestaurantClick  // Added parameter
                )
            }
        }
    }
}

@Composable
fun BestSellerSection(
    restaurants: List<Restaurant>,
    onViewAllClick: () -> Unit
) {
    val bestSellers = restaurants.filter { it.rating >= 4.5 }

    Column {
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
                color = Color(0xFFFF5722),
                modifier = Modifier.clickable(onClick = onViewAllClick)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(bestSellers) { restaurant ->
                Column(
                    modifier = Modifier
                        .width(100.dp)
                        .padding(end = 8.dp)
                ) {
                    Image(
                        painter = painterResource(id = restaurant.photoResId),
                        contentDescription = restaurant.name,
                        modifier = Modifier
                            .size(width = 100.dp, height = 150.dp)

                            .clip(RoundedCornerShape(20.dp)),

                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = restaurant.name,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Rating",
                            tint = Color(0xFFFFC107),
                            modifier = Modifier.size(14.dp)
                        )
                        Text(
                            text = "${restaurant.rating}",
                            fontSize = 12.sp,
                            modifier = Modifier.padding(start = 4.dp)
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun RecommendItem(
    foodItem: FoodItem,
    restaurant: Restaurant,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .width(160.dp)
            .padding(8.dp)
    ) {
        // Food Image
        Image(
            painter = painterResource(id = foodItem.imageRes),
            contentDescription = foodItem.name,
            modifier = Modifier
                .size(160.dp)
                .clip(RoundedCornerShape(20.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Food Name
        Text(
            text = foodItem.name,
            style = MaterialTheme.typography.bodyLarge,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(4.dp))

        // Restaurant Name
        Text(
            text = restaurant.name,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.height(4.dp))

        // Price
        Text(
            text = foodItem.price,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFFF5722),
            style = MaterialTheme.typography.bodyLarge
        )

        // Optional: Display discount if available
        foodItem.discount?.let { discount ->
            Text(
                text = "$discount% OFF",
                color = Color(0xFFFF5722),
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun RecommendedFoodSection(
    allFoodItems: List<FoodItem>,
    restaurants: Map<Int, Restaurant>
) {
    // Filter food items with more than 120 reviews
    val popularFoodItems = allFoodItems.filter { it.reviews != null && it.reviews > 120 }

    Column {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(popularFoodItems) { foodItem ->
                restaurants[foodItem.restaurantId]?.let { restaurant ->
                    Column(
                        modifier = Modifier
                            .width(160.dp)
                            .padding(8.dp)
                    ) {
                        // Food Image
                        Image(
                            painter = painterResource(id = foodItem.imageRes),
                            contentDescription = foodItem.name,
                            modifier = Modifier
                                .size(160.dp)
                                .clip(RoundedCornerShape(20.dp)),
                            contentScale = ContentScale.Crop
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        // Food Name
                        Text(
                            text = foodItem.name,
                            style = MaterialTheme.typography.bodyLarge,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontWeight = FontWeight.Medium
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        // Restaurant Name
                        Text(
                            text = restaurant.name,
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        // Price with optional discount
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = foodItem.price,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFFF5722),
                                style = MaterialTheme.typography.bodyLarge
                            )

                            foodItem.discount?.let { discount ->
                                Text(
                                    text = "$discount% OFF",
                                    color = Color(0xFFFF5722),
                                    style = MaterialTheme.typography.bodySmall,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        // Rating and review count
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(top = 4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = "Rating",
                                tint = Color(0xFFFFC107),
                                modifier = Modifier.size(16.dp)
                            )
                            foodItem.rating?.let { rating ->
                                Text(
                                    text = rating.toString(),
                                    style = MaterialTheme.typography.bodySmall,
                                    modifier = Modifier.padding(start = 4.dp)
                                )
                            }
                            Text(
                                text = "(${foodItem.reviews} reviews)",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.Gray,
                                modifier = Modifier.padding(start = 2.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
@Composable
fun RestaurantCard(
    restaurant: Restaurant,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,
    onRestaurantClick: (Restaurant) -> Unit // Add this parameter
) {
    Card(
        modifier = Modifier
            .width(200.dp)
            .padding(8.dp)
            .clickable { onRestaurantClick(restaurant) }, // Add click handler
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFEF1E7))
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
            ) {
                Image(
                    painter = painterResource(id = restaurant.photoResId),
                    contentDescription = restaurant.name,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)),
                    contentScale = ContentScale.Crop
                )

                IconButton(
                    onClick = onFavoriteClick,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(8.dp)
                        .size(32.dp)
                        .background(Color.White, CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Favorite",
                        tint = if (isFavorite) Color.Red else Color.Gray,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = restaurant.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.star1),
                        contentDescription = "Rating",
                        modifier = Modifier.size(16.dp),
                        tint = Color(0xFFFF5722)
                    )
                    Text(
                        text = "${restaurant.rating}",
                        fontSize = 14.sp,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }

                Text(
                    text = "Delivery: ${restaurant.deliveryTime}",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun RestaurantCardDetailed(
    restaurant: Restaurant,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,
    onRestaurantClick: (Restaurant) -> Unit // Add this parameter
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(280.dp)
            .clickable { onRestaurantClick(restaurant) },


        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFEF1E7))
    )


    {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
            ) {
                Image(
                    painter = painterResource(id = restaurant.photoResId),
                    contentDescription = restaurant.name,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                IconButton(
                    onClick = onFavoriteClick,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(8.dp)
                        .background(Color.White, CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Favorite",
                        tint = if (isFavorite) Color.Red else Color.Gray
                    )
                }

                if (restaurant.discount != null) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(8.dp)
                            .background(Color(0xFFFF5722), RoundedCornerShape(4.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = restaurant.discount.toString(),
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = restaurant.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = restaurant.specialties.joinToString(", "),
                    fontSize = 14.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.star1),
                        contentDescription = "Rating",
                        modifier = Modifier.size(16.dp),
                        tint = Color(0xFFFF5722)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = restaurant.rating.toString(),
                        fontSize = 14.sp
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Icon(
                        painter = painterResource(id = R.drawable.clock),
                        contentDescription = "Time",
                        modifier = Modifier.size(16.dp),
                        tint = Color(0xFFFF5722)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "${restaurant.deliveryTime}", fontSize = 12.sp, color = Color.Black)
                    Spacer(modifier = Modifier.width(8.dp))

                    Icon(
                        painter = painterResource(id = R.drawable.deliveryicon),
                        contentDescription = "Delivery",
                        modifier = Modifier.size(16.dp),
                        tint = Color(0xFFFF5722)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = restaurant.deliveryFee, fontSize = 12.sp, color = Color.Black)

                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
        }
    }
}


@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun FilterDialog(
    isVisible: Boolean,
    onDismiss: () -> Unit,
    onApplyFilters: (FilterOptions) -> Unit,
    currentFilters: FilterOptions,
    availableSpecialties: List<String> // Pass this from FoodPage
) {
    if (isVisible) {
        var selectedRating by remember { mutableStateOf(currentFilters.minRating) }
        var selectedMaxDeliveryTime by remember { mutableStateOf(currentFilters.maxDeliveryTime) }
        var selectedMaxPrice by remember { mutableStateOf(currentFilters.maxDeliveryFee) }
        var selectedSpecialties by remember { mutableStateOf(currentFilters.selectedSpecialties) }
        var showDiscountedOnly by remember { mutableStateOf(currentFilters.showDiscountedOnly) }

        AlertDialog(
            onDismissRequest = onDismiss,
            title = {
                Text(
                    "Filter Restaurants",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color(0xFFE95322) // Main color for the title
                )
            },
            text = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                        .padding(16.dp) // Increased padding for a cleaner look
                ) {
                    // Rating slider
                    Text("Minimum Rating", style = MaterialTheme.typography.bodyLarge)
                    Slider(
                        value = selectedRating,
                        onValueChange = { selectedRating = it },
                        valueRange = 0f..5f,
                        steps = 8,
                        colors = SliderDefaults.colors(
                            thumbColor = Color(0xFFE95322),
                            activeTrackColor = Color(0xFFE95322),
                            inactiveTrackColor = Color(0xFFD7D7D7)
                        )
                    )
                    Text("${selectedRating.round(1)} stars", style = MaterialTheme.typography.bodyMedium)

                    Spacer(modifier = Modifier.height(16.dp))

                    // Delivery time slider
                    Text("Maximum Delivery Time", style = MaterialTheme.typography.bodyLarge)
                    Slider(
                        value = selectedMaxDeliveryTime.toFloat(),
                        onValueChange = { selectedMaxDeliveryTime = it.toInt() },
                        valueRange = 20f..60f,
                        steps = 7,
                        colors = SliderDefaults.colors(
                            thumbColor = Color(0xFFE95322),
                            activeTrackColor = Color(0xFFE95322),
                            inactiveTrackColor = Color(0xFFD7D7D7)
                        )
                    )
                    Text("$selectedMaxDeliveryTime minutes", style = MaterialTheme.typography.bodyMedium)

                    Spacer(modifier = Modifier.height(16.dp))

                    // Delivery fee slider
                    Text("Maximum Delivery Fee", style = MaterialTheme.typography.bodyLarge)
                    Slider(
                        value = selectedMaxPrice,
                        onValueChange = { selectedMaxPrice = it },
                        valueRange = 0f..10f,
                        steps = 19,
                        colors = SliderDefaults.colors(
                            thumbColor = Color(0xFFE95322),
                            activeTrackColor = Color(0xFFE95322),
                            inactiveTrackColor = Color(0xFFD7D7D7)
                        )
                    )
                    Text("$${selectedMaxPrice.round(2)}", style = MaterialTheme.typography.bodyMedium)

                    Spacer(modifier = Modifier.height(16.dp))

                    // Specialties chips
                    Text("Specialties", style = MaterialTheme.typography.bodyLarge)
                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        availableSpecialties.forEach { specialty ->
                            FilterChip(
                                selected = specialty in selectedSpecialties,
                                onClick = {
                                    selectedSpecialties = if (specialty in selectedSpecialties) {
                                        selectedSpecialties - specialty
                                    } else {
                                        selectedSpecialties + specialty
                                    }
                                },
                                label = { Text(specialty) },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = Color(0xFFE95322),
                                    selectedLabelColor = Color.White,
                                    containerColor = Color(0xFFFFD8C7) // Light background for unselected
                                )
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Discounted only checkbox
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = showDiscountedOnly,
                            onCheckedChange = { showDiscountedOnly = it },
                            colors = CheckboxDefaults.colors(checkedColor = Color(0xFFE95322))
                        )
                        Text(
                            "Show only discounted restaurants",
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            },
            confirmButton = {
                Row {
                    TextButton(
                        onClick = {
                            onApplyFilters(
                                FilterOptions(
                                    minRating = selectedRating,
                                    maxDeliveryTime = selectedMaxDeliveryTime,
                                    maxDeliveryFee = selectedMaxPrice,
                                    selectedSpecialties = selectedSpecialties,
                                    showDiscountedOnly = showDiscountedOnly
                                )
                            )
                            onDismiss()
                        }
                    ) {
                        Text("Apply", color = Color(0xFFE95322)) // Use main color for action button
                    }
                    TextButton(
                        onClick = {
                            onApplyFilters(FilterOptions()) // Reset to defaults
                            onDismiss()
                        }
                    ) {
                        Text("Reset", color = Color.Gray)
                    }
                }
            },
            dismissButton = {
                TextButton(onClick = onDismiss) {
                    Text("Cancel", color = Color.Gray)
                }
            }
        )
    }
}


data class FilterOptions(
    val minRating: Float = 0f,
    val maxDeliveryTime: Int = 60,
    val maxDeliveryFee: Float = 10f,
    val selectedSpecialties: Set<String> = emptySet(),
    val showDiscountedOnly: Boolean = false
)

// Helper functions remain the same
private fun String.extractDeliveryTime(): Int {
    val regex = "(\\d+)-(\\d+)".toRegex()
    val match = regex.find(this)
    return match?.groupValues?.get(2)?.toIntOrNull() ?: 60
}

private fun String.extractDeliveryFee(): Float {
    return replace("$", "").toFloatOrNull() ?: 10f
}

private fun Float.round(decimals: Int): Float {
    var multiplier = 1.0f
    repeat(decimals) { multiplier *= 10 }
    return kotlin.math.round(this * multiplier) / multiplier
}
@Composable
fun FoodPage(navController: NavController) {
    var searchText by remember { mutableStateOf(TextFieldValue("")) }
    var selectedCategory by remember { mutableStateOf<String?>(null) }
    var showAllRestaurants by remember { mutableStateOf(false) }
    var showBestSellers by remember { mutableStateOf(false) }
    var favoriteRestaurants by remember { mutableStateOf(setOf<Int>()) }
    var showFilterDialog by remember { mutableStateOf(false) }
    var filterOptions by remember { mutableStateOf(FilterOptions()) }
    val handleRestaurantClick: (Restaurant) -> Unit = { restaurant ->
        navController.navigate("foodDelivery/${restaurant.id}/${restaurant.name}/${restaurant.photoResId}")
    }


    val availableSpecialties = remember {
        allRestaurants.flatMap { it.specialties }.distinct()
    }
    val filteredRestaurants = remember(searchText.text, selectedCategory, filterOptions) {
        allRestaurants.filter { restaurant ->
            val matchesSearch = restaurant.name.contains(searchText.text, ignoreCase = true)
            val matchesCategory =
                selectedCategory == null || restaurant.category == selectedCategory
            val matchesRating = restaurant.rating >= filterOptions.minRating
            val matchesDeliveryTime =
                restaurant.deliveryTime.extractDeliveryTime() <= filterOptions.maxDeliveryTime
            val matchesDeliveryFee =
                restaurant.deliveryFee.extractDeliveryFee() <= filterOptions.maxDeliveryFee
            val matchesSpecialties = filterOptions.selectedSpecialties.isEmpty() ||
                    restaurant.specialties.any { it in filterOptions.selectedSpecialties }
            val matchesDiscount = !filterOptions.showDiscountedOnly || restaurant.discount != null

            matchesSearch && matchesCategory && matchesRating &&
                    matchesDeliveryTime && matchesDeliveryFee &&
                    matchesSpecialties && matchesDiscount
        }
    }

    FilterDialog(
        isVisible = showFilterDialog,
        onDismiss = { showFilterDialog = false },
        onApplyFilters = { newFilters -> filterOptions = newFilters },
        currentFilters = filterOptions,
        availableSpecialties = availableSpecialties
    )

    when {
        showAllRestaurants -> {
            AllRestaurantsScreen(
                restaurants = filteredRestaurants,
                favoriteRestaurants = favoriteRestaurants,
                onFavoriteClick = { restaurantId ->
                    favoriteRestaurants = if (restaurantId in favoriteRestaurants) {
                        favoriteRestaurants - restaurantId
                    } else {
                        favoriteRestaurants + restaurantId
                    }
                },
                onBackClick = { showAllRestaurants = false },
                onRestaurantClick = handleRestaurantClick  // Added parameter
            )
        }

        showBestSellers -> {
            BestSellersScreen(
                restaurants = filteredRestaurants,
                favoriteRestaurants = favoriteRestaurants,
                onFavoriteClick = { restaurantId ->
                    favoriteRestaurants = if (restaurantId in favoriteRestaurants) {
                        favoriteRestaurants - restaurantId
                    } else {
                        favoriteRestaurants + restaurantId
                    }
                },
                onBackClick = { showBestSellers = false },
                onRestaurantClick = handleRestaurantClick  // Added parameter
            )
        }

        else -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                // Top Section with orange background
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFFF5722))
                        .padding(16.dp)
                ) {
                    // Search and Icons Row
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        // Search Bar
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .weight(1f)
                                .height(40.dp)
                                .background(Color.White, RoundedCornerShape(30.dp))
                                .padding(horizontal = 12.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search icon",
                                tint = Color.Gray,
                                modifier = Modifier.size(20.dp)
                            )

                            BasicTextField(
                                value = searchText,
                                onValueChange = { searchText = it },
                                singleLine = true,
                                textStyle = TextStyle(
                                    color = Color.Black,
                                    fontSize = 14.sp
                                ),
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(horizontal = 8.dp),
                                decorationBox = { innerTextField ->
                                    if (searchText.text.isEmpty()) {
                                        Text(
                                            "Search restaurants",
                                            color = Color.Gray,
                                            fontSize = 14.sp
                                        )
                                    }
                                    innerTextField()
                                }
                            )

                            IconButton(
                                onClick = { showFilterDialog = true },
                                modifier = Modifier
                                    .size(24.dp)

                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.group109),
                                    contentDescription = "Filter",
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        // Action Icons
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            IconButton(
                                onClick = { /*TODO*/ },
                                modifier = Modifier
                                    .size(40.dp)
                                    .background(Color.White, RoundedCornerShape(12.dp))
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.carte),
                                    contentDescription = "Cart",
                                    tint = Color(0xFFFF5722)
                                )
                            }

                            IconButton(
                                onClick = { /*TODO*/ },
                                modifier = Modifier
                                    .size(40.dp)
                                    .background(Color.White, RoundedCornerShape(12.dp))
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.noti),
                                    contentDescription = "Notifications",
                                    tint = Color(0xFFFF5722)
                                )
                            }
                        }
                    }

                    // Title
                    Text(
                        text = "Bon Appetit",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                // Main Content
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Color.White,
                            shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                        )
                        .padding(16.dp)
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        CategoryItem(
                            "Fast Food",
                            R.drawable.snaks,
                            R.drawable.frame2,
                            selectedCategory
                        ) { newCategory ->
                            selectedCategory = newCategory
                        }
                        CategoryItem(
                            "Meal",
                            R.drawable.meal,
                            R.drawable.frame3,
                            selectedCategory
                        ) { newCategory ->
                            selectedCategory = newCategory
                        }
                        CategoryItem(
                            "Traditional",
                            R.drawable.vegan,
                            R.drawable.frame4,
                            selectedCategory
                        ) { newCategory ->
                            selectedCategory = newCategory
                        }
                    }
                    // Restaurants Section with View All
                    if (filteredRestaurants.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = if (selectedCategory != null) "$selectedCategory Restaurants" else "All Restaurants",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "View All >",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFFF5722),
                                modifier = Modifier.clickable { showAllRestaurants = true }
                            )
                        }

                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            contentPadding = PaddingValues(vertical = 8.dp)
                        ) {
                            items(filteredRestaurants) { restaurant ->
                                RestaurantCard(
                                    restaurant = restaurant,
                                    isFavorite = restaurant.id in favoriteRestaurants,
                                    onFavoriteClick = {
                                        favoriteRestaurants =
                                            if (restaurant.id in favoriteRestaurants) {
                                                favoriteRestaurants - restaurant.id
                                            } else {
                                                favoriteRestaurants + restaurant.id
                                            }
                                    },
                                    onRestaurantClick = handleRestaurantClick
                                )
                            }
                        }
                    }

                    BestSellerSection(
                        restaurants = filteredRestaurants,
                        onViewAllClick = { showBestSellers = true }
                    )

                    Spacer(modifier = Modifier.height(8.dp))



                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        "Best Offers",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    val images = listOf(
                        R.drawable.download,
                        R.drawable.adv,
                        R.drawable.images,
                        R.drawable.images1,
                        R.drawable.images2
                    )

                    var currentIndex by remember { mutableStateOf(0) }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    ) {
                        Image(
                            painter = painterResource(id = images[currentIndex]),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .clip(RoundedCornerShape(30.dp))
                        )

                        Icon(
                            painter = painterResource(id = R.drawable.phone),
                            contentDescription = "Previous Image",
                            modifier = Modifier
                                .size(100.dp)
                                .align(Alignment.CenterStart)
                                .padding(start = 16.dp)
                                .clickable {
                                    currentIndex =
                                        if (currentIndex > 0) currentIndex - 1 else images.size - 1
                                },
                            tint = Color.Transparent
                        )

                        Icon(
                            painter = painterResource(id = R.drawable.phone),
                            contentDescription = "Next Image",
                            modifier = Modifier
                                .size(100.dp)
                                .align(Alignment.CenterEnd)
                                .padding(end = 16.dp)
                                .clickable {
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
                                Spacer(modifier = Modifier.width(4.dp))
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Recommend",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    RecommendedFoodSection(
                        allFoodItems = allFoodItems,
                        restaurants = allRestaurants.associateBy { it.id }
                    )
                }
            }
        }
    }
}

@Composable
fun AllRestaurantsScreen(
    restaurants: List<Restaurant>,
    favoriteRestaurants: Set<Int>,
    onFavoriteClick: (Int) -> Unit,
    onBackClick: () -> Unit,
    onRestaurantClick: (Restaurant) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Top Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFF5722))
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.all),
                contentDescription = "Back",
                tint = Color.White,
                modifier = Modifier
                    .size(24.dp)
                    .clickable(onClick = onBackClick)
            )
            Text(
                text = "All Restaurants",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.size(24.dp))
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(restaurants) { restaurant ->
                RestaurantCardDetailed(
                    restaurant = restaurant,
                    isFavorite = restaurant.id in favoriteRestaurants,
                    onFavoriteClick = { onFavoriteClick(restaurant.id) },
                    onRestaurantClick = onRestaurantClick
                )
            }
        }
    }
}

