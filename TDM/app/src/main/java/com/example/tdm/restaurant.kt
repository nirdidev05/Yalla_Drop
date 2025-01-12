import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tdm.R

data class FoodItem(
    val id: Int,
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
    modifier: Modifier = Modifier
) {
    var searchQuery by remember { mutableStateOf("") }
    var isSearching by remember { mutableStateOf(false) }
    val backgroundColor = Color(0xFFFFF8F1)

    val allFoodItems = remember {
        listOf(
            // PIZZA Category
            FoodItem(
                id = 1,
                imageRes = R.drawable.pizza1,
                name = "Margherita Pizza",
                price = "$14.99",
                category = "PIZZA",
                description = "Classic Italian pizza with fresh mozzarella and basil",
                calories = 850,
                ingredients = listOf(
                    "Fresh mozzarella",
                    "San Marzano tomatoes",
                    "Fresh basil",
                    "Extra virgin olive oil",
                    "Sea salt"
                )
            ),
            FoodItem(
                id = 2,
                imageRes = R.drawable.pizza1,
                name = "Pepperoni Supreme",
                price = "$16.99",
                category = "PIZZA",
                description = "Loaded with pepperoni and Italian seasonings",
                calories = 980,
                ingredients = listOf(
                    "Pepperoni",
                    "Mozzarella cheese",
                    "Tomato sauce",
                    "Italian herbs",
                    "Garlic oil"
                )
            ),
            FoodItem(
                id = 3,
                imageRes = R.drawable.img1,
                name = "French Onion Chicken",
                price = "$29.00",
                category = "Lunch",
                description = "Delicious French onion chicken with caramelized onions",
                calories = 554,
                ingredients = listOf(
                    "1 tbsp olive oil",
                    "2 lbs chicken breasts",
                    "1/2 tsp kosher salt",
                    "1/2 tsp ground black pepper",
                    "2 tbsp buttero (if ordered)"
                ),
                preparationTime = 45,
                rating = 4.8f,
                reviews = 41
            ),
            FoodItem(
                id = 4,
                imageRes = R.drawable.img1,
                name = "French Onion Chicken",
                price = "$29.00",
                category = "Lunch",
                description = "Delicious French onion chicken with caramelized onions",
                calories = 554,
                ingredients = listOf(
                    "1 tbsp olive oil",
                    "2 lbs chicken breasts",
                    "1/2 tsp kosher salt",
                    "1/2 tsp ground black pepper",
                    "2 tbsp buttero (if ordered)"
                ),
                preparationTime = 45,
                rating = 4.8f,
                reviews = 41
            ),

            // BURGER Category
            FoodItem(
                id = 5,
                imageRes = R.drawable.burger1,
                name = "Classic Cheeseburger",
                price = "$12.99",
                category = "BURGER",
                description = "100% Angus beef with aged cheddar",
                calories = 750,
                ingredients = listOf(
                    "Angus beef patty",
                    "Aged cheddar",
                    "Fresh lettuce",
                    "Tomato",
                    "Special sauce"
                )
            ),
            FoodItem(
                id = 6,
                imageRes = R.drawable.burger1,
                name = "Chicken Deluxe",
                price = "$11.99",
                category = "BURGER",
                description = "Grilled chicken with special sauce",
                calories = 550
            ),
            FoodItem(
                id = 7,
                imageRes = R.drawable.burger1,
                name = "Veggie Supreme",
                price = "$9.99",
                category = "BURGER",
                description = "Plant-based patty with fresh veggies",
                calories = 400
            ),
            FoodItem(
                id = 8,
                imageRes = R.drawable.burger1,
                name = "Mushroom Swiss",
                price = "$12.99",
                category = "BURGER",
                description = "Beef patty with swiss cheese and mushrooms",
                calories = 680
            ),

            // TACOS Category
            FoodItem(
                id = 9,
                imageRes = R.drawable.taac,
                name = "Carne Asada",
                price = "$8.99",
                category = "TACOS",
                description = "Grilled steak with onions and cilantro",
                calories = 320
            ),
            FoodItem(
                id = 10,
                imageRes = R.drawable.taac,
                name = "Grilled Fish",
                price = "$9.99",
                category = "TACOS",
                description = "Fresh fish with cabbage slaw",
                calories = 280
            ),
            FoodItem(
                id = 11,
                imageRes = R.drawable.taac,
                name = "Chicken Fajita",
                price = "$8.99",
                category = "TACOS",
                description = "Grilled chicken with peppers and onions",
                calories = 300
            ),
            FoodItem(
                id = 12,
                imageRes = R.drawable.taac,
                name = "Shrimp Taco",
                price = "$10.99",
                category = "TACOS",
                description = "Grilled shrimp with special sauce",
                calories = 290
            ),

            // SUSHI Category
            FoodItem(
                id = 13,
                imageRes = R.drawable.suchi,
                name = "California Roll",
                price = "$14.99",
                category = "SUSHI",
                description = "Crab, avocado, and cucumber",
                calories = 320
            ),
            FoodItem(
                id = 14,
                imageRes = R.drawable.suchi,
                name = "Dragon Roll",
                price = "$18.99",
                category = "SUSHI",
                description = "Eel and avocado special roll",
                calories = 380
            ),
            FoodItem(
                id = 15,
                imageRes = R.drawable.suchi,
                name = "Spicy Tuna",
                price = "$16.99",
                category = "SUSHI",
                description = "Fresh tuna with spicy sauce",
                calories = 340
            ),
            FoodItem(
                id = 16,
                imageRes = R.drawable.suchi,
                name = "Rainbow Roll",
                price = "$19.99",
                category = "SUSHI",
                description = "Assorted fish on California roll",
                calories = 360
            ),

            // PASTA Category
            FoodItem(
                id = 17,
                imageRes = R.drawable.pasta,
                name = "Carbonara",
                price = "$13.99",
                category = "PASTA",
                description = "Classic carbonara with pancetta",
                calories = 580
            ),
            FoodItem(
                id = 18,
                imageRes = R.drawable.pasta,
                name = "Bolognese",
                price = "$14.99",
                category = "PASTA",
                description = "Traditional meat sauce pasta",
                calories = 620
            ),
            FoodItem(
                id = 19,
                imageRes = R.drawable.pasta,
                name = "Alfredo",
                price = "$12.99",
                category = "PASTA",
                description = "Creamy Alfredo sauce with parmesan",
                calories = 560
            ),
            FoodItem(
                id = 20,
                imageRes = R.drawable.pasta,
                name = "Pesto",
                price = "$13.99",
                category = "PASTA",
                description = "Fresh basil pesto with pine nuts",
                calories = 520
            )
        )
    }

    var filteredItems by remember { mutableStateOf(allFoodItems) }
    var searchResults by remember { mutableStateOf(emptyList<FoodItem>()) }
    LaunchedEffect(searchQuery) {
        filteredItems = if (searchQuery.isEmpty()) {
            allFoodItems
        } else {
            allFoodItems.filter {
                it.name.contains(searchQuery, ignoreCase = true) ||
                        it.category.contains(searchQuery, ignoreCase = true)
            }
        }
        searchResults = filteredItems
        isSearching = searchQuery.isNotEmpty()
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
            item { TopBar(navController) }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            item {
                SearchBar(
                    searchQuery = searchQuery,
                    onSearchQueryChange = { searchQuery = it }
                )
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }

            // Show search results if searching
            if (isSearching) {
                items(searchResults) { foodItem ->
                    SearchResultItem(
                        item = foodItem,
                        onClick = {
                            navController.navigate("foodDetail/${foodItem.id}")
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            } else {
                // Show regular categories and items
                item { BannerImage() }
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
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFF6B35).copy(alpha = 0.1f))
    )
    {
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

            Column {
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
                    text = item.category,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
        }
    }
}
@Composable
private fun CategorySection(
    category: String,
    items: List<FoodItem>,
    onItemClick: (FoodItem) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = category,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 8.dp)
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
            items(items) { foodItem ->
                FoodItemCard(
                    item = foodItem,
                    onClick = { onItemClick(foodItem) }
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(24.dp))
}
@Composable
private fun TopBar(navController: NavController) {
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
            text = "SPARTACOOS",
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
private fun BannerImage() {
    Image(
        painter = painterResource(id = R.drawable.img1),
        contentDescription = "Food Banner",
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(16.dp)),
        contentScale = ContentScale.Crop
    )
}

@Composable
private fun FoodItemCard(
    item: FoodItem,
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
                .size(100.dp)
                .clip(CircleShape)
        ) {
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = item.name,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = item.name,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium
        )

        Text(
            text = item.price,
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFFFF6B35)
        )

        IconButton(
            onClick = { /* Handle add to cart */ },
            modifier = Modifier.size(32.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add to cart",
                tint = Color(0xFFFF6B35)
            )
        }
    }
}

