package com.example.tdm.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tdm.R
import com.example.tdm.model.DessertShop
import com.example.tdm.model.FoodItem
import com.example.tdm.model.FoodIteme
import com.example.tdm.model.Restaurant
import com.example.tdm.model.allDessertShops
import com.example.tdm.model.allFoodItems
import com.example.tdm.model.foodItemList

@Composable
fun RestaurantCarde(
    restaurant: DessertShop,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,
    onRestaurantClick: (DessertShop) -> Unit // Add this parameter
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
fun BestSellersScreen(
    restaurants: List<DessertShop>,
    favoriteRestaurants: Set<Int>,
    onFavoriteClick: (Int) -> Unit,
    onBackClick: () -> Unit,
    onRestaurantClick: (DessertShop) -> Unit
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
fun RecommendedFoodSection(
    allFoodItems: List<FoodIteme>,
    restaurants: Map<Int, DessertShop>,
    onItemClick: (FoodIteme) -> Unit
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
                        // Food Image with clickable navigation
                        Image(
                            painter = painterResource(id = foodItem.imageRes),
                            contentDescription = foodItem.name,
                            modifier = Modifier
                                .size(160.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .clickable { onItemClick(foodItem) },  // Navigation trigger
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
fun BestSellerSection(
    restaurants: List<DessertShop>,
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
fun SearchBar(
    searchText: TextFieldValue,
    onSearchTextChange: (TextFieldValue) -> Unit,
    filteredRestaurants: List<DessertShop>,
    onRestaurantClick: (DessertShop) -> Unit
) {
    var isSearchFocused by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxWidth()) {
        // Search Bar
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
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
                onValueChange = onSearchTextChange,
                singleLine = true,
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 14.sp
                ),
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp)
                    .onFocusChanged { isSearchFocused = it.isFocused },
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
        }

        // Search Results Dropdown
        AnimatedVisibility(
            visible = isSearchFocused && searchText.text.isNotEmpty(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 45.dp)
                .align(Alignment.TopCenter),
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically()
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 300.dp),
                shape = RoundedCornerShape(12.dp),
                shadowElevation = 8.dp
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(vertical = 8.dp)
                ) {
                    items(filteredRestaurants) { restaurant ->
                        SearchResultItem(
                            restaurant = restaurant,
                            onClick = {
                                onRestaurantClick(restaurant)
                                isSearchFocused = false
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SearchResultItem(
    restaurant: DessertShop,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Restaurant Image
        Image(
            painter = painterResource(id = restaurant.photoResId),
            contentDescription = null,
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        // Restaurant Details
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = restaurant.name,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = restaurant.specialties.joinToString(" • "),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        // Rating and Delivery Time
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = Color(0xFFFF5722),
                modifier = Modifier.size(16.dp)
            )
            Text(
                text = restaurant.rating.toString(),
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFFFF5722)
            )
            Text(
                text = "•",
                color = Color.Gray
            )
            Text(
                text = restaurant.deliveryTime,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }
    }
}
@Composable
fun AllRestaurantsScreen(
    restaurants: List<DessertShop>,
    favoriteRestaurants: Set<Int>,
    onFavoriteClick: (Int) -> Unit,
    onBackClick: () -> Unit,
    onRestaurantClick: (DessertShop) -> Unit
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
@Composable
fun RestaurantCardDetailed(
    restaurant: DessertShop,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,
    onRestaurantClick: (DessertShop) -> Unit
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

@Composable
fun DessertPage(navController: NavController) {
    var searchText by remember { mutableStateOf(TextFieldValue("")) }
    var selectedCategory by remember { mutableStateOf<String?>(null) }
    var showAllRestaurants by remember { mutableStateOf(false) }
    var showBestSellers by remember { mutableStateOf(false) }
    var favoriteRestaurants by remember { mutableStateOf(setOf<Int>()) }
    var showFilterDialog by remember { mutableStateOf(false) }
    var filterOptions by remember { mutableStateOf(FilterOptions()) }
    val handleRestaurantClick: (DessertShop) -> Unit = { restaurant ->
        navController.navigate("candyDelivery/${restaurant.id}/${restaurant.name}/${restaurant.photoResId}")
    }


    val availableSpecialties = remember {
        allDessertShops.flatMap { it.specialties }.distinct()
    }
    val filteredRestaurants = remember(searchText.text, selectedCategory, filterOptions) {
        allDessertShops.filter { restaurant ->
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
                            SearchBar(
                                searchText = searchText,
                                onSearchTextChange = { searchText = it },
                                filteredRestaurants = allDessertShops.filter {
                                        restaurant -> restaurant.name.contains(searchText.text, ignoreCase = true)
                                },
                                onRestaurantClick = handleRestaurantClick
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
                            R.drawable.frame4,
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
                                RestaurantCarde(
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

                    val images = listOf(
                        R.drawable.download,
                        R.drawable.adv,
                        R.drawable.images,
                        R.drawable.images1,
                        R.drawable.images2
                    )


                    BestOffersCarousel(
                        images = images,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Recommend",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    RecommendedFoodSection(
                        allFoodItems = foodItemList,
                        restaurants = allDessertShops.associateBy { it.id },
                        onItemClick = { foodItem ->
                            navController.navigate("candyDetail/${foodItem.id}")
                        }

                    )
                }
            }
        }
    }


}