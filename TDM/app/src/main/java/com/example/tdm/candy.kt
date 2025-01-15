package com.example.tdm

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.FavoriteBorder
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
import com.example.tdm.model.FoodIteme
import com.example.tdm.model.foodItemList



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CandyDeliveryScreen(
    navController: NavController,
    restaurantId: Int,
    restaurantName: String,
    modifier: Modifier = Modifier,
    restaurantImage: Int
) {
    var searchQuery by remember { mutableStateOf("") }

    var filteredItems by remember(searchQuery) {
        mutableStateOf(
            if (searchQuery.isEmpty()) {
                foodItemList.filter { it.restaurantId == restaurantId }
            } else {
                foodItemList.filter {
                    (it.name.contains(searchQuery, ignoreCase = true) ||
                            it.category.contains(searchQuery, ignoreCase = true)) &&
                            it.restaurantId == restaurantId
                }
            }
        )
    }

    Scaffold(
        topBar = {
            CandyTopBar(navController, restaurantName)
        },
        containerColor = VanillaCream
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(VanillaCream)
        ) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
                SearchBar(
                    searchQuery = searchQuery,
                    onSearchQueryChange = { searchQuery = it },
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
                BannerImage(restaurantImage)
            }

            if (searchQuery.isNotEmpty()) {
                items(filteredItems) { foodItem ->
                    SearchResultItem(
                        item = foodItem,
                        onClick = {
                            navController.navigate("candyDetail/${foodItem.id}")
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            } else {
                val groupedItems = filteredItems.groupBy { it.category }
                groupedItems.forEach { (category, items) ->
                    item {
                        CategorySection(
                            category = category,
                            items = items,
                            onItemClick = { foodItem ->
                                navController.navigate("candyDetail/${foodItem.id}")
                            }
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CandyTopBar(
    navController: NavController,
    restaurantName: String
) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(
                    Icons.Default.ArrowBack,
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
                    text = restaurantName,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = CandyPink
                )
            }
        },
        actions = {
            IconButton(onClick = { /* Handle profile click */ }) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Profile",
                    tint = BubbleGumPink
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = VanillaCream
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchBar(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = searchQuery,
        onValueChange = onSearchQueryChange,
        modifier = modifier
            .fillMaxWidth()
            .shadow(4.dp, RoundedCornerShape(24.dp))
            .clip(RoundedCornerShape(24.dp)),
        placeholder = { Text("Find Your Sweet Treat...", color = LicoriceGray.copy(alpha = 0.6f)) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = CandyPink
            )
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = "Cart",
                tint = CandyPink
            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = Color.White,
            unfocusedBorderColor = Color.Transparent,
            focusedBorderColor = BubbleGumPink
        ),
        singleLine = true
    )
}

@Composable
private fun SearchResultItem(
    item: FoodIteme,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .shadow(8.dp, RoundedCornerShape(16.dp))
            .padding(horizontal = 8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = item.name,
                modifier = Modifier
                    .size(60.dp)
                    .shadow(4.dp, CircleShape)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Medium,
                    color = CandyPink
                )
                Text(
                    text = item.price,
                    style = MaterialTheme.typography.bodyMedium,
                    color = BubbleGumPink,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${item.calories} calories • ${item.preparationTime} min",
                    style = MaterialTheme.typography.bodySmall,
                    color = LicoriceGray.copy(alpha = 0.7f)
                )
            }

            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "View Details",
                tint = Color(0xFFFF6B35),
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}
@Composable
private fun FoodItemCard(
    item: FoodIteme,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(120.dp)
            .clickable(onClick = onClick)
    ) {
        Box(
            modifier = Modifier
                .size(120.dp)
                .shadow(
                    elevation = 8.dp,
                    shape = CircleShape,
                    spotColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                )
                .clip(CircleShape)
        ) {
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = item.name,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // Favorite icon with background
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
                    .size(32.dp)
                    .background(
                        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.8f),
                        shape = CircleShape
                    )
                    .padding(6.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.FavoriteBorder,
                    contentDescription = "Add to favorites",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = item.name,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Medium,
                letterSpacing = 0.sp
            ),
            color = CandyPink
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = item.price,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.sp
            ),
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
private fun CategorySection(
    category: String,
    items: List<FoodIteme>,
    onItemClick: (FoodIteme) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        Text(
            text = category,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                letterSpacing = (-0.5).sp
            ),
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(items) { foodItem ->
                FoodItemCard(
                    item = foodItem,
                    onClick = { onItemClick(foodItem) }
                )
            }
        }
    }
}
@Composable
private fun BannerImage(imageRes: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(200.dp)
            .shadow(12.dp, RoundedCornerShape(24.dp))
            .clip(RoundedCornerShape(24.dp))
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "Banner",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}
