import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tdm.FoodDataProvider.allFoodItems
import com.example.tdm.R

data class FoodItem(
    val id: Int,
    val restaurantId:Int,
    val imageRes: Int,
    val name: String,
    val price: String,
    val category: String,
    val description: String = "",
    val calories: Int = 0,
    val ingredients: List<String> = emptyList(),
    val preparationTime: Int = 45,
    val rating: Float = 4.8f,
    val reviews: Int = 41
)

@Composable
fun FoodDeliveryScreen(
    navController: NavController,
    restaurantId: Int,
    restaurantName: String,
    modifier: Modifier = Modifier,
    restaurantImage: Int
) {
    var searchQuery by remember { mutableStateOf("") }
    val backgroundColor = Color(0xFFFFF8F1)

    var filteredItems by remember(searchQuery) {
        mutableStateOf(
            if (searchQuery.isEmpty()) {
                allFoodItems.filter { it.restaurantId == restaurantId }
            } else {
                allFoodItems.filter {
                    (it.name.contains(searchQuery, ignoreCase = true) ||
                            it.category.contains(searchQuery, ignoreCase = true)) &&
                            it.restaurantId == restaurantId
                }
            }
        )
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            item { TopBar(navController, restaurantName) }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            item {
                SearchBar(
                    searchQuery = searchQuery,
                    onSearchQueryChange = { searchQuery = it }
                )
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }

            // Show search results if there's a search query
            if (searchQuery.isNotEmpty()) {
                items(filteredItems) { foodItem ->
                    SearchResultItem(
                        item = foodItem,
                        onClick = {
                            navController.navigate("foodDetail/${foodItem.id}")
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            } else {
                // Show regular categories and items when not searching
                item { BannerImage(restaurantImage) }
                item { Spacer(modifier = Modifier.height(24.dp)) }

                val groupedItems = filteredItems.groupBy { it.category }
                groupedItems.forEach { (category, items) ->
                    item {
                        CategorySection(
                            category = category,
                            items = items,
                            onItemClick = { foodItem ->
                                navController.navigate("foodDetail/${foodItem.id}")
                            }
                        )
                    }
                }
            }
        }
    }
}


@Composable
private fun SearchResultItem(
    item: FoodItem,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFF6B35).copy(alpha = 0.1f))
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
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = item.price,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFFFF6B35)
                )
                Text(
                    text = "${item.calories} calories • ${item.preparationTime} min",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
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
fun TopBar(
    navController: NavController,
    restaurantName: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { navController.navigateUp() }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color(0xFFFF6B35)
            )
        }

        Text(
            text = restaurantName,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        IconButton(onClick = { /* Handle profile click */ }) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Profile",
                tint = Color(0xFFFF6B35)
            )
        }
    }
}
private val OrangeColor = Color(0xFFFF6B35) // RGB(251,174,82)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchBar(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit
) {
    OutlinedTextField(
        value = searchQuery,
        onValueChange = onSearchQueryChange,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp)),
        placeholder = { Text("Find What You Want....") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = Color(0xFFFF6B35)
            )
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = "Cart",
                tint = Color(0xFFFF6B35)
            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = Color(0xFFFF6B35).copy(alpha = 0.1f),
            unfocusedBorderColor = Color.Transparent,
            focusedBorderColor = Color(0xFFFF6B35)
        ),
        singleLine = true
    )
}

@Composable
private fun BannerImage(x: Int) {
    Image(
        painter = painterResource(id = x),
        contentDescription = "Food Banner",
        modifier = Modifier
            .fillMaxWidth()                   // Makes the image fill the available width
            .height(200.dp)                   // Sets the height of the image to 200 dp
            .clip(RoundedCornerShape(16.dp)), // Clips the image with rounded corners of 16 dp
        contentScale = ContentScale.Crop      // Crops the image to fill its bounds
    )
}
@Composable
fun CategorySection(
    category: String,
    items: List<FoodItem>,
    onItemClick: (FoodItem) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
    ) {
        Text(
            text = category,
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FoodItemCard(
    item: FoodItem,
    onClick: () -> Unit
) {
    var isFavorite by remember { mutableStateOf(false) }

    Card(
        onClick = onClick,
        modifier = Modifier.width(180.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFD8C7),
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp,
            pressedElevation = 4.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
            ) {
                Image(
                    painter = painterResource(id = item.imageRes),
                    contentDescription = item.name,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )

                IconButton(
                    onClick = { isFavorite = !isFavorite },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(4.dp)
                ) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription = if (isFavorite) "Remove from favorites" else "Add to favorites",
                        tint = if (isFavorite) OrangeColor else Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = item.name,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                ),
                maxLines = 1
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = item.price,
                style = MaterialTheme.typography.titleMedium.copy(
                    color = OrangeColor,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}
