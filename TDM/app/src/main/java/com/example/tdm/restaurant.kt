import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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


    val allFoodItems = remember {
        listOf(
            // PIZZA Category
            FoodItem(
                id = 1,
                restaurantId = 1,
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
                ),
                preparationTime = 20,
                rating = 4.5f,
                reviews = 120
            ),
            FoodItem(
                id = 2,
                restaurantId = 1,
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
                ),
                preparationTime = 25,
                rating = 4.7f,
                reviews = 98
            ),
            // BURGER Category
            FoodItem(
                id = 3,
                restaurantId = 2,
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
                ),
                preparationTime = 15,
                rating = 4.6f,
                reviews = 150
            ),
            FoodItem(
                id = 4,
                restaurantId = 2,
                imageRes = R.drawable.burger1,
                name = "Bacon Double Cheeseburger",
                price = "$13.99",
                category = "BURGER",
                description = "Double patty with crispy bacon",
                calories = 920,
                ingredients = listOf(
                    "Double beef patty",
                    "Crispy bacon",
                    "Aged cheddar",
                    "Lettuce",
                    "Tomato"
                ),
                preparationTime = 20,
                rating = 4.8f,
                reviews = 170
            ),
            // TACOS Category
            FoodItem(
                id = 5,
                restaurantId = 3,
                imageRes = R.drawable.taac,
                name = "Chicken Tacos",
                price = "$10.99",
                category = "TACOS",
                description = "Grilled chicken with fresh toppings",
                calories = 650,
                ingredients = listOf(
                    "Grilled chicken",
                    "Lettuce",
                    "Tomato",
                    "Cheese",
                    "Corn tortilla"
                ),
                preparationTime = 10,
                rating = 4.4f,
                reviews = 85
            ),
            FoodItem(
                id = 6,
                restaurantId = 3,
                imageRes = R.drawable.taac,
                name = "Beef Tacos",
                price = "$11.49",
                category = "TACOS",
                description = "Seasoned beef with fresh toppings",
                calories = 700,
                ingredients = listOf(
                    "Seasoned beef",
                    "Onions",
                    "Cilantro",
                    "Corn tortilla",
                    "Salsa"
                ),
                preparationTime = 10,
                rating = 4.5f,
                reviews = 90
            ),
            // CURRY Category
            FoodItem(
                id = 7,
                restaurantId = 4,
                imageRes = R.drawable.taac,
                name = "Butter Chicken",
                price = "$15.99",
                category = "CURRY",
                description = "Creamy tomato-based curry with tender chicken",
                calories = 950,
                ingredients = listOf(
                    "Chicken",
                    "Tomato",
                    "Butter",
                    "Cream",
                    "Indian spices"
                ),
                preparationTime = 30,
                rating = 4.7f,
                reviews = 100
            ),
            FoodItem(
                id = 8,
                restaurantId = 4,
                imageRes = R.drawable.taac,
                name = "Lamb Curry",
                price = "$17.49",
                category = "CURRY",
                description = "Rich curry with tender lamb chunks",
                calories = 1100,
                ingredients = listOf(
                    "Lamb",
                    "Onions",
                    "Tomatoes",
                    "Garlic",
                    "Indian spices"
                ),
                preparationTime = 35,
                rating = 4.8f,
                reviews = 120
            ),
            // PASTA Category
            FoodItem(
                id = 9,
                restaurantId = 5,
                imageRes = R.drawable.pasta,
                name = "Spaghetti Carbonara",
                price = "$13.99",
                category = "PASTA",
                description = "Classic pasta with creamy sauce",
                calories = 850,
                ingredients = listOf(
                    "Spaghetti",
                    "Pancetta",
                    "Eggs",
                    "Parmesan cheese",
                    "Black pepper"
                ),
                preparationTime = 20,
                rating = 4.5f,
                reviews = 110
            ),
            FoodItem(
                id = 10,
                restaurantId = 5,
                imageRes = R.drawable.pasta,
                name = "Fettuccine Alfredo",
                price = "$14.49",
                category = "PASTA",
                description = "Pasta with creamy Alfredo sauce",
                calories = 900,
                ingredients = listOf(
                    "Fettuccine",
                    "Butter",
                    "Heavy cream",
                    "Parmesan cheese",
                    "Garlic"
                ),
                preparationTime = 25,
                rating = 4.6f,
                reviews = 130
            ),
            // STEAK Category
            FoodItem(
                id = 11,
                restaurantId = 6,
                imageRes = R.drawable.pizza1,
                name = "Grilled Ribeye",
                price = "$25.99",
                category = "STEAK",
                description = "Juicy ribeye steak grilled to perfection",
                calories = 1200,
                ingredients = listOf(
                    "Ribeye steak",
                    "Salt",
                    "Pepper",
                    "Garlic butter",
                    "Fresh herbs"
                ),
                preparationTime = 30,
                rating = 4.9f,
                reviews = 140
            ),
            FoodItem(
                id = 12,
                restaurantId = 6,
                imageRes = R.drawable.pasta,
                name = "BBQ Chicken",
                price = "$17.99",
                category = "BARBECUE",
                description = "Tender chicken with BBQ sauce",
                calories = 950,
                ingredients = listOf(
                    "Chicken",
                    "BBQ sauce",
                    "Spices",
                    "Garlic",
                    "Honey"
                ),
                preparationTime = 25,
                rating = 4.7f,
                reviews = 115
            ),
            // SUSHI Category
            FoodItem(
                id = 13,
                restaurantId = 7,
                imageRes = R.drawable.suchi,
                name = "California Roll",
                price = "$8.99",
                category = "SUSHI",
                description = "Sushi roll with crab, avocado, and cucumber",
                calories = 350,
                ingredients = listOf(
                    "Crab",
                    "Avocado",
                    "Cucumber",
                    "Rice",
                    "Nori"
                ),
                preparationTime = 15,
                rating = 4.5f,
                reviews = 90
            ),
            FoodItem(
                id = 14,
                restaurantId = 7,
                imageRes = R.drawable.suchi,
                name = "Spicy Tuna Roll",
                price = "$9.49",
                category = "SUSHI",
                description = "Sushi roll with spicy tuna and cucumber",
                calories = 400,
                ingredients = listOf(
                    "Spicy tuna",
                    "Cucumber",
                    "Rice",
                    "Nori",
                    "Spicy mayo"
                ),
                preparationTime = 15,
                rating = 4.6f,
                reviews = 95
            ),
            // THAI Category
            FoodItem(
                id = 15,
                restaurantId = 8,
                imageRes = R.drawable.suchi,
                name = "Pad Thai",
                price = "$14.99",
                category = "THAI",
                description = "Stir-fried noodles with shrimp and peanuts",
                calories = 800,
                ingredients = listOf(
                    "Rice noodles",
                    "Shrimp",
                    "Peanuts",
                    "Tamarind sauce",
                    "Bean sprouts"
                ),
                preparationTime = 20,
                rating = 4.7f,
                reviews = 130
            )
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


    }
}

