package com.example.yalladrop.restauration
/*
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
import androidx.navigation.NavHostController
import com.example.yalladrop.R

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
    navController: NavHostController,
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
                imageRes = R.drawable.test_foodcategory_pizza,
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
                imageRes = R.drawable.test_foodcategory_pizza,
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
                imageRes = R.drawable.test_img_1,
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
                imageRes = R.drawable.test_img_1,
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
                imageRes = R.drawable.test_foodcategory_burger,
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
                imageRes = R.drawable.test_foodcategory_burger,
                name = "Chicken Deluxe",
                price = "$11.99",
                category = "BURGER",
                description = "Grilled chicken with special sauce",
                calories = 550
            ),
            FoodItem(
                id = 7,
                imageRes = R.drawable.test_foodcategory_burger,
                name = "Veggie Supreme",
                price = "$9.99",
                category = "BURGER",
                description = "Plant-based patty with fresh veggies",
                calories = 400
            ),
            FoodItem(
                id = 8,
                imageRes = R.drawable.test_foodcategory_burger,
                name = "Mushroom Swiss",
                price = "$12.99",
                category = "BURGER",
                description = "Beef patty with swiss cheese and mushrooms",
                calories = 680
            ),

            // TACOS Category
            FoodItem(
                id = 9,
                imageRes = R.drawable.test_foodcategory_taac,
                name = "Carne Asada",
                price = "$8.99",
                category = "TACOS",
                description = "Grilled steak with onions and cilantro",
                calories = 320
            ),
            FoodItem(
                id = 10,
                imageRes = R.drawable.test_foodcategory_taac,
                name = "Grilled Fish",
                price = "$9.99",
                category = "TACOS",
                description = "Fresh fish with cabbage slaw",
                calories = 280
            ),
            FoodItem(
                id = 11,
                imageRes = R.drawable.test_foodcategory_taac,
                name = "Chicken Fajita",
                price = "$8.99",
                category = "TACOS",
                description = "Grilled chicken with peppers and onions",
                calories = 300
            ),
            FoodItem(
                id = 12,
                imageRes = R.drawable.test_foodcategory_taac,
                name = "Shrimp Taco",
                price = "$10.99",
                category = "TACOS",
                description = "Grilled shrimp with special sauce",
                calories = 290
            ),

            // SUSHI Category
            FoodItem(
                id = 13,
                imageRes = R.drawable.test_foodcategory_suchi,
                name = "California Roll",
                price = "$14.99",
                category = "SUSHI",
                description = "Crab, avocado, and cucumber",
                calories = 320
            ),
            FoodItem(
                id = 14,
                imageRes = R.drawable.test_foodcategory_suchi,
                name = "Dragon Roll",
                price = "$18.99",
                category = "SUSHI",
                description = "Eel and avocado special roll",
                calories = 380
            ),
            FoodItem(
                id = 15,
                imageRes = R.drawable.test_foodcategory_suchi,
                name = "Spicy Tuna",
                price = "$16.99",
                category = "SUSHI",
                description = "Fresh tuna with spicy sauce",
                calories = 340
            ),
            FoodItem(
                id = 16,
                imageRes = R.drawable.test_foodcategory_suchi,
                name = "Rainbow Roll",
                price = "$19.99",
                category = "SUSHI",
                description = "Assorted fish on California roll",
                calories = 360
            ),

            // PASTA Category
            FoodItem(
                id = 17,
                imageRes = R.drawable.test_foodcategory_pasta,
                name = "Carbonara",
                price = "$13.99",
                category = "PASTA",
                description = "Classic carbonara with pancetta",
                calories = 580
            ),
            FoodItem(
                id = 18,
                imageRes = R.drawable.test_foodcategory_pasta,
                name = "Bolognese",
                price = "$14.99",
                category = "PASTA",
                description = "Traditional meat sauce pasta",
                calories = 620
            ),
            FoodItem(
                id = 19,
                imageRes = R.drawable.test_foodcategory_pasta,
                name = "Alfredo",
                price = "$12.99",
                category = "PASTA",
                description = "Creamy Alfredo sauce with parmesan",
                calories = 560
            ),
            FoodItem(
                id = 20,
                imageRes = R.drawable.test_foodcategory_pasta,
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
        painter = painterResource(id = R.drawable.test_img_1),
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


data class Restaurant(
    val photoResId: Int,
    val name: String,
    val rating: Float,
    val deliveryFee: String,
    val deliveryTime: String,
    val specialties: List<String>,
    val discount: Any
)

 */

import androidx.compose.ui.graphics.Color
import com.example.yalladrop.R

data class Restaurant(
    val id: Int,
    val name: String,
    val category: String,
    val photoResId: Int,
    val rating: Float,
    val deliveryFee: String,
    val deliveryTime: String,
    val specialties: List<String>,
    val discount: Any
)
data class DessertRestau(
    val id: Int,
    val name: String,
    val category: String,
    val photoResId: Int,
    val rating: Float,
    val deliveryFee: String,
    val deliveryTime: String,
    val specialties: List<String>,
    val discount: Any,
)
data class FoodIteme(
    val id: Int,
    val restaurantId: Int,
    val imageRes: Int,
    val name: String,
    val price: String,
    val category: String,
    val description: String,
    val calories: Int,
    val ingredients: List<String> = emptyList(),
    val preparationTime: Int? = null,
    val rating: Float? = null,
    val reviews: Int? = null,
    val discount: Int? = null,

    )
data class FoodItem(
    val id: Int,
    val restaurantId: String,
    val imageRes: Int,
    val name: String,
    val price: String,
    val category: String,
    val description: String,
    val calories: Int,
    val ingredients: List<String> = emptyList(),
    val preparationTime: Int? = null,
    val rating: Float? = null,
    val reviews: Int? = null,
    val discount: Int? = null,

    )


val allDessertShops = listOf(
    DessertRestau(
        id = 1,
        name = "Sweet Tooth Cafe",
        category = "Cafe",
        photoResId = R.drawable.dessert_restaurant_sweet_tooth_cafe,
        rating = 4.5f,
        deliveryFee = "Free",
        deliveryTime = "20-30 min",
        specialties = listOf("Cappuccino", "Cheesecake", "Tiramisu"),
        discount = "10% off",
    ),
    DessertRestau(
        id = 2,
        name = "Patissier's Delight",
        category = "Dessert",
        photoResId = R.drawable.dessert_restaurant_patissiers_delight,
        rating = 4.7f,
        deliveryFee = "$2.99",
        deliveryTime = "30-40 min",
        specialties = listOf("Macarons", "Eclairs", "Tarts"),
        discount = "Buy 1 Get 1 Free",
    ),
    DessertRestau(
        id = 3,
        name = "Candyland",
        category = "Candy",
        photoResId = R.drawable.dessert_restaurant_candyland,
        rating = 4.3f,
        deliveryFee = "$1.99",
        deliveryTime = "15-25 min",
        specialties = listOf("Gummies", "Lollipops", "Chocolates"),
        discount = "15% off orders over $20",
    ),
    DessertRestau(
        id = 4,
        name = "Juicy Bar",
        category = "Cafe",
        photoResId = R.drawable.dessert_restaurant_juicy_bar,
        rating = 4.6f,
        deliveryFee = "Free",
        deliveryTime = "10-20 min",
        specialties = listOf("Fresh Juices", "Smoothies", "Iced Teas"),
        discount = "20% off first order",
    ),
    DessertRestau(
        id = 5,
        name = "Chocolate Haven",
        category = "Candy",
        photoResId = R.drawable.dessert_restaurant_chocolate_haven,
        rating = 4.8f,
        deliveryFee = "$3.50",
        deliveryTime = "25-35 min",
        specialties = listOf("Truffles", "Dark Chocolate", "Caramel Bars"),
        discount = "Free gift with purchase",
    ),
    DessertRestau(
        id = 6,
        name = "Cafe Cocoa",
        category = "Cafe",
        photoResId = R.drawable.dessert_restaurant_cafe_cocoa,
        rating = 4.4f,
        deliveryFee = "Free",
        deliveryTime = "15-20 min",
        specialties = listOf("Hot Chocolate", "Espresso", "Brownies"),
        discount = "5% off",
    ),
    DessertRestau(
        id = 7,
        name = "Gourmet Pastries",
        category = "Dessert",
        photoResId = R.drawable.dessert_restaurant_gourmet_pastries,
        rating = 4.9f,
        deliveryFee = "$2.50",
        deliveryTime = "30-45 min",
        specialties = listOf("Croissants", "Baklava", "Pies"),
        discount = "10% off orders over $15",
    ),
    DessertRestau(
        id = 8,
        name = "Fizz & Pop",
        category = "Cafe",
        photoResId = R.drawable.dessert_restaurant_fizz_pop,
        rating = 4.2f,
        deliveryFee = "$1.00",
        deliveryTime = "20-30 min",
        specialties = listOf("Sodas", "Milkshakes", "Lemonades"),
        discount = "Free drink with combo",
    ),
    DessertRestau(
        id = 9,
        name = "Sugar Rush",
        category = "Candy",
        photoResId = R.drawable.dessert_restaurant_sugar_rush,
        rating = 4.1f,
        deliveryFee = "$2.00",
        deliveryTime = "15-20 min",
        specialties = listOf("Cotton Candy", "Jelly Beans", "Chocolate Bars"),
        discount = "5% off orders over $10",
    ),
    DessertRestau(
        id = 10,
        name = "Sips & Bites",
        category = "Cafe",
        photoResId = R.drawable.dessert_restaurant_sips_bites,
        rating = 4.5f,
        deliveryFee = "$2.99",
        deliveryTime = "20-30 min",
        specialties = listOf("Tea", "Muffins", "Cookies"),
        discount = "Free cookie with any drink",
    )
)

val foodItemList = listOf(
    FoodItem(
        id = 1,
        restaurantId = "1",
        imageRes = R.drawable.cake,
        name = "Chocolate Cake",
        price = "$5.00",
        category = "Dessert",
        description = "Rich and moist chocolate cake with a creamy chocolate frosting.",
        calories = 350,
        ingredients = listOf("Flour", "Sugar", "Cocoa Powder", "Eggs", "Butter"),
        preparationTime = 45,
        rating = 4.8f,
        reviews = 120,

        ),
    FoodItem(
        id = 2,
        restaurantId = "2",
        imageRes = R.drawable.maccarons,
        name = "Assorted Macarons",
        price = "$8.00",
        category = "Dessert",
        description = "A selection of colorful macarons with various flavors.",
        calories = 100,
        ingredients = listOf("Almond Flour", "Sugar", "Egg Whites", "Buttercream"),
        preparationTime = 60,
        rating = 4.6f,
        reviews = 85
    ),
    FoodItem(
        id = 3,
        restaurantId = "3",
        imageRes = R.drawable.gummy_bears,
        name = "Gummy Bears",
        price = "$3.50",
        category = "Candy",
        description = "Chewy gummy bears in an assortment of fruity flavors.",
        calories = 150,
        ingredients = listOf("Gelatin", "Sugar", "Glucose Syrup", "Fruit Juice"),
        preparationTime = 30,
        rating = 4.3f,
        reviews = 60
    ),
    FoodItem(
        id = 4,
        restaurantId = "4",
        imageRes = R.drawable.mango_smoothie,
        name = "Mango Smoothie",
        price = "$4.50",
        category = "Cafe",
        description = "Refreshing mango smoothie made with fresh mangoes and yogurt.",
        calories = 200,
        ingredients = listOf("Mango", "Yogurt", "Honey", "Milk"),
        preparationTime = 10,
        rating = 4.7f,
        reviews = 45
    ),
    FoodItem(
        id = 5,
        restaurantId = "5",
        imageRes = R.drawable.truffles,
        name = "Chocolate Truffles",
        price = "$6.00",
        category = "Candy",
        description = "Decadent chocolate truffles with a rich ganache filling.",
        calories = 180,
        ingredients = listOf("Chocolate", "Cream", "Cocoa Powder"),
        preparationTime = 20,
        rating = 4.9f,
        reviews = 90
    ),
    FoodItem(
        id = 6,
        restaurantId = "6",
        imageRes = R.drawable.cappuccino,
        name = "Cappuccino",
        price = "$3.00",
        category = "Cafe",
        description = "Classic cappuccino with a perfect balance of espresso and steamed milk.",
        calories = 120,
        ingredients = listOf("Espresso", "Milk", "Cinnamon"),
        preparationTime = 5,
        rating = 4.5f,
        reviews = 70
    ),
    FoodItem(
        id = 7,
        restaurantId = "7",
        imageRes = R.drawable.eclair,
        name = "Chocolate Eclair",
        price = "$4.00",
        category = "Dessert",
        description = "Light pastry filled with vanilla custard and topped with chocolate glaze.",
        calories = 250,
        ingredients = listOf("Flour", "Eggs", "Milk", "Chocolate"),
        preparationTime = 40,
        rating = 4.6f,
        reviews = 95
    ),
    FoodItem(
        id = 8,
        restaurantId = "8",
        imageRes = R.drawable.strawberry_milkshake,
        name = "Strawberry Milkshake",
        price = "$4.50",
        category = "Cafe",
        description = "Creamy strawberry milkshake topped with whipped cream and fresh strawberries.",
        calories = 300,
        ingredients = listOf("Strawberries", "Milk", "Ice Cream", "Sugar"),
        preparationTime = 10,
        rating = 4.7f,
        reviews = 50
    ),
    FoodItem(
        id = 9,
        restaurantId = "9",
        imageRes = R.drawable.cotton_candy,
        name = "Cotton Candy",
        price = "$2.50",
        category = "Candy",
        description = "Fluffy cotton candy in vibrant colors and sweet flavors.",
        calories = 120,
        ingredients = listOf("Sugar", "Food Coloring", "Flavoring"),
        preparationTime = 5,
        rating = 4.4f,
        reviews = 40
    ),
    FoodItem(
        id = 10,
        restaurantId = "10",
        imageRes = R.drawable.blueberry_muffin,
        name = "Blueberry Muffin",
        price = "$3.50",
        category = "Cafe",
        description = "Soft and moist blueberry muffin with a hint of lemon zest.",
        calories = 250,
        ingredients = listOf("Flour", "Sugar", "Blueberries", "Butter"),
        preparationTime = 25,
        rating = 4.8f,
        reviews = 65
    ),
    FoodItem(
        id = 11,
        restaurantId = "1",
        imageRes = R.drawable.cheesecake,
        name = "Classic Cheesecake",
        price = "$5.50",
        category = "Dessert",
        description = "Creamy cheesecake with a graham cracker crust and berry topping.",
        calories = 400,
        ingredients = listOf("Cream Cheese", "Sugar", "Eggs", "Graham Crackers"),
        preparationTime = 60,
        rating = 4.9f,
        reviews = 110
    ),
    FoodItem(
        id = 12,
        restaurantId = "2",
        imageRes = R.drawable.tart,
        name = "Fruit Tart",
        price = "$4.50",
        category = "Dessert",
        description = "Delicate tart filled with custard and topped with fresh fruits.",
        calories = 280,
        ingredients = listOf("Flour", "Butter", "Sugar", "Fruit"),
        preparationTime = 50,
        rating = 4.7f,
        reviews = 75
    ),
    FoodItem(
        id = 13,
        restaurantId = "3",
        imageRes = R.drawable.chocolate_bar,
        name = "Milk Chocolate Bar",
        price = "$2.00",
        category = "Candy",
        description = "Smooth and creamy milk chocolate bar, perfect for any sweet tooth.",
        calories = 220,
        ingredients = listOf("Cocoa Butter", "Milk Powder", "Sugar"),
        preparationTime = 15,
        rating = 4.3f,
        reviews = 55
    ),
    FoodItem(
        id = 14,
        restaurantId = "4",
        imageRes = R.drawable.iced_tea,
        name = "Lemon Iced Tea",
        price = "$3.50",
        category = "Cafe",
        description = "Refreshing iced tea infused with fresh lemon slices.",
        calories = 100,
        ingredients = listOf("Tea", "Lemon", "Sugar", "Ice"),
        preparationTime = 10,
        rating = 4.6f,
        reviews = 40
    ),
    FoodItem(
        id = 15,
        restaurantId = "5",
        imageRes = R.drawable.caramel_bar,
        name = "Caramel Chocolate Bar",
        price = "$2.50",
        category = "Candy",
        description = "Rich caramel encased in a layer of smooth milk chocolate.",
        calories = 250,
        ingredients = listOf("Caramel", "Chocolate", "Sugar"),
        preparationTime = 25,
        rating = 4.5f,
        reviews = 65
    ),
    FoodItem(
        id = 16,
        restaurantId = "6",
        imageRes = R.drawable.americano,
        name = "Americano",
        price = "$2.50",
        category = "Cafe",
        description = "Bold and rich Americano, a simple yet satisfying coffee experience.",
        calories = 10,
        ingredients = listOf("Espresso", "Hot Water"),
        preparationTime = 5,
        rating = 4.4f,
        reviews = 35
    ),
    FoodItem(
        id = 17,
        restaurantId = "7",
        imageRes = R.drawable.pie,
        name = "Apple Pie",
        price = "$5.00",
        category = "Dessert",
        description = "Classic apple pie with a flaky crust and cinnamon-spiced filling.",
        calories = 320,
        ingredients = listOf("Apples", "Flour", "Sugar", "Cinnamon"),
        preparationTime = 70,
        rating = 4.8f,
        reviews = 95
    ),
    FoodItem(
        id = 18,
        restaurantId = "8",
        imageRes = R.drawable.lemonade,
        name = "Fresh Lemonade",
        price = "$3.00",
        category = "Cafe",
        description = "Tangy and refreshing lemonade made with fresh lemons.",
        calories = 120,
        ingredients = listOf("Lemons", "Water", "Sugar"),
        preparationTime = 8,
        rating = 4.7f,
        reviews = 45
    ),
    FoodItem(
        id = 19,
        restaurantId = "9",
        imageRes = R.drawable.jelly_beans,
        name = "Jelly Beans",
        price = "$2.00",
        category = "Candy",
        description = "Colorful jelly beans in a variety of fruity flavors.",
        calories = 150,
        ingredients = listOf("Sugar", "Corn Syrup", "Gelatin"),
        preparationTime = 15,
        rating = 4.5f,
        reviews = 30
    ),
    FoodItem(
        id = 20,
        restaurantId = "10",
        imageRes = R.drawable.cookie,
        name = "Chocolate Chip Cookie",
        price = "$2.50",
        category = "Cafe",
        description = "Warm and gooey chocolate chip cookie, perfect for a sweet treat.",
        calories = 200,
        ingredients = listOf("Flour", "Sugar", "Butter", "Chocolate Chips"),
        preparationTime = 20,
        rating = 4.8f,
        reviews = 80
    )
)

val dessertRestauList = listOf(
    DessertRestau(
        id = 1,
        name = "Sweet Tooth Cafe",
        category = "Cafe",
        photoResId = R.drawable.dessert_restaurant_sweet_tooth_cafe,
        rating = 4.5f,
        deliveryFee = "Free",
        deliveryTime = "20-30 min",
        specialties = listOf("Cappuccino", "Cheesecake", "Tiramisu"),
        discount = "10% off",
    ),
    DessertRestau(
        id = 2,
        name = "Patissier's Delight",
        category = "Dessert",
        photoResId = R.drawable.dessert_restaurant_patissiers_delight,
        rating = 4.7f,
        deliveryFee = "$2.99",
        deliveryTime = "30-40 min",
        specialties = listOf("Macarons", "Eclairs", "Tarts"),
        discount = "Buy 1 Get 1 Free",
    ),
    DessertRestau(
        id = 3,
        name = "Candyland",
        category = "Candy",
        photoResId = R.drawable.dessert_restaurant_candyland,
        rating = 4.3f,
        deliveryFee = "$1.99",
        deliveryTime = "15-25 min",
        specialties = listOf("Gummies", "Lollipops", "Chocolates"),
        discount = "15% off orders over $20",
    ),
    DessertRestau(
        id = 4,
        name = "Juicy Bar",
        category = "Cafe",
        photoResId = R.drawable.dessert_restaurant_juicy_bar,
        rating = 4.6f,
        deliveryFee = "Free",
        deliveryTime = "10-20 min",
        specialties = listOf("Fresh Juices", "Smoothies", "Iced Teas"),
        discount = "20% off first order",
    ),
    DessertRestau(
        id = 5,
        name = "Chocolate Haven",
        category = "Candy",
        photoResId = R.drawable.dessert_restaurant_chocolate_haven,
        rating = 4.8f,
        deliveryFee = "$3.50",
        deliveryTime = "25-35 min",
        specialties = listOf("Truffles", "Dark Chocolate", "Caramel Bars"),
        discount = "Free gift with purchase",
    ),
    DessertRestau(
        id = 6,
        name = "Cafe Cocoa",
        category = "Cafe",
        photoResId = R.drawable.dessert_restaurant_cafe_cocoa,
        rating = 4.4f,
        deliveryFee = "Free",
        deliveryTime = "15-20 min",
        specialties = listOf("Hot Chocolate", "Espresso", "Brownies"),
        discount = "5% off",
    ),
    DessertRestau(
        id = 7,
        name = "Gourmet Pastries",
        category = "Dessert",
        photoResId = R.drawable.dessert_restaurant_gourmet_pastries,
        rating = 4.9f,
        deliveryFee = "$2.50",
        deliveryTime = "30-45 min",
        specialties = listOf("Croissants", "Baklava", "Pies"),
        discount = "10% off orders over $15",
    ),
    DessertRestau(
        id = 8,
        name = "Fizz & Pop",
        category = "Cafe",
        photoResId = R.drawable.dessert_restaurant_fizz_pop,
        rating = 4.2f,
        deliveryFee = "$1.00",
        deliveryTime = "20-30 min",
        specialties = listOf("Sodas", "Milkshakes", "Lemonades"),
        discount = "Free drink with combo",
    ),
    DessertRestau(
        id = 9,
        name = "Sugar Rush",
        category = "Candy",
        photoResId = R.drawable.dessert_restaurant_sugar_rush,
        rating = 4.1f,
        deliveryFee = "$2.00",
        deliveryTime = "15-20 min",
        specialties = listOf("Cotton Candy", "Jelly Beans", "Chocolate Bars"),
        discount = "5% off orders over $10",
    ),
    DessertRestau(
        id = 10,
        name = "Sips & Bites",
        category = "Cafe",
        photoResId = R.drawable.dessert_restaurant_sips_bites,
        rating = 4.5f,
        deliveryFee = "$2.99",
        deliveryTime = "20-30 min",
        specialties = listOf("Tea", "Muffins", "Cookies"),
        discount = "Free cookie with any drink",
    )
)



val allRestaurants = listOf(
    Restaurant(1, "Pizza Heaven", "Fast Food", R.drawable.restaurant10, 4.5f, "$3.99", "30-45 mins", listOf("Pizza", "Pasta"), "10% off"),
    Restaurant(2, "Burger World", "Fast Food", R.drawable.restaurant2, 4.3f, "$2.99", "25-40 mins", listOf("Burgers", "Fries"), "Free delivery"),
    Restaurant(3, "Taco Fiesta", "Fast Food", R.drawable.restaurant3, 4.4f, "$3.49", "20-35 mins", listOf("Tacos", "Nachos"), "15% off"),
    Restaurant(4, "Curry Palace", "Meal", R.drawable.restaurant4, 4.6f, "$4.49", "35-50 mins", listOf("Curry", "Naan"), "20% off"),
    Restaurant(5, "Pasta Corner", "Meal", R.drawable.restaurant5, 4.2f, "$3.79", "30-45 mins", listOf("Pasta", "Lasagna"), "Free drink"),
    Restaurant(6, "Grill Master", "Meal", R.drawable.restaurant6, 4.8f, "$5.49", "40-55 mins", listOf("Steak", "Barbecue"), "Buy 1 Get 1 Free"),
    Restaurant(7, "Sushi Delight", "Traditional", R.drawable.restaurant7, 4.7f, "$4.99", "40-60 mins", listOf("Sushi", "Sashimi"), "10% off"),
    Restaurant(8, "Thai Taste", "Meal", R.drawable.restaurant8, 4.5f, "$4.29", "30-45 mins", listOf("Pad Thai", "Green Curry"), "Free delivery"),
    Restaurant(9, "Indian Spice", "Traditional", R.drawable.restaurant9, 4.3f, "$3.99", "30-50 mins", listOf("Biryani", "Butter Chicken"), "20% off"),
    Restaurant(10, "Mediterranean Magic", "Meal", R.drawable.restaurant10, 4.4f, "$4.49", "35-50 mins", listOf("Hummus", "Falafel"), "Free dessert"),
    Restaurant(11, "French Bistro", "Traditional", R.drawable.restaurant10, 4.6f, "$4.99", "40-60 mins", listOf("Croissant", "Quiche"), "10% off"),
    Restaurant(12, "Mexican Delight", "Fast Food", R.drawable.restaurant9, 4.2f, "$3.79", "25-40 mins", listOf("Burritos", "Quesadillas"), "Buy 1 Get 1 Free"),
    Restaurant(13, "Chinese Wok", "Meal", R.drawable.restaurant8, 4.5f, "$4.29", "30-45 mins", listOf("Noodles", "Dim Sum"), "Free drink"),
    Restaurant(14, "BBQ Nation", "Meal", R.drawable.restaurant7, 4.8f, "$5.49", "40-55 mins", listOf("Ribs", "Wings"), "20% off"),
    Restaurant(15, "Veggie Paradise", "Traditional", R.drawable.restaurant4, 4.7f, "$4.99", "40-60 mins", listOf("Salads", "Smoothies"), "Free delivery")
)

val groceries = listOf(
    Grocery(
        id = 1,
        categoryName = "Vegetables & Fruits",
        imageResId = R.drawable.test_category_veg,
        customColor = Color(0xFFFCE8E8),
        items = listOf(
            CategoryItem("Apple", 2.0, "1KG", R.drawable.apple),
            CategoryItem("Banana", 1.5, "1KG", R.drawable.banana),
            CategoryItem("Carrot", 1.2, "1KG", R.drawable.carrot),
            CategoryItem("Spinach", 1.0, "1KG", R.drawable.spinach),
            CategoryItem("Tomato", 1.8, "1KG", R.drawable.tomato),
            CategoryItem("Potato", 0.9, "1KG", R.drawable.potato)
        )
    ),
    Grocery(
        id = 2,
        categoryName = "Breakfast & Dairy",
        imageResId = R.drawable.breakfast,
        customColor = Color(0xFFE8F1FC),
        items = listOf(
            CategoryItem("Milk", 3.0, "1L", R.drawable.milk),
            CategoryItem("Butter", 4.0, "500g", R.drawable.butter),
            CategoryItem("Eggs", 5.0, "12 pcs", R.drawable.milk),
            CategoryItem("Cheese", 6.0, "200g", R.drawable.milk),
            CategoryItem("Yogurt", 2.5, "1L", R.drawable.milk),
            CategoryItem("Cottage Cheese", 4.5, "500g", R.drawable.milk)
        )
    ),
    Grocery(
        id = 3,
        categoryName = "Cold Drinks & Juices",
        imageResId = R.drawable.cook,
        customColor = Color(0xFFFFC107),
        items = listOf(
            CategoryItem("Coca-Cola", 1.5, "1L", R.drawable.cola),
            CategoryItem("Orange Juice", 3.0, "1L", R.drawable.cola),
            CategoryItem("Lemonade", 2.5, "1L", R.drawable.cola),
            CategoryItem("Apple Juice", 3.0, "1L", R.drawable.cola),
            CategoryItem("Grape Juice", 3.2, "1L", R.drawable.cola),
            CategoryItem("Peach Tea", 2.8, "1L", R.drawable.cola)
        )
    ),
    Grocery(
        id = 4,
        categoryName = "Frozen Food & Instant  ",
        imageResId = R.drawable.frozen,
        customColor = Color(0xFF9E9E9E),
        items = listOf(
            CategoryItem("Instant Noodles", 2.0, "1 pack", R.drawable.noodles),
            CategoryItem("Frozen Pizza", 5.0, "1 unit", R.drawable.noodles),
            CategoryItem("Frozen French Fries", 3.0, "1KG", R.drawable.noodles),
            CategoryItem("Frozen Vegetables", 2.5, "1KG", R.drawable.noodles),
            CategoryItem("Frozen Burgers", 4.0, "2 pcs", R.drawable.noodles),
            CategoryItem("Frozen Fish Fingers", 4.5, "500g", R.drawable.noodles)
        )
    ),
    Grocery(
        id = 5,
        categoryName = "Tea & Coffee",
        imageResId = R.drawable.the,
        customColor = Color(0xFF795548),
        items = listOf(
            CategoryItem("Tea Leaves", 2.0, "200g", R.drawable.the),
            CategoryItem("Coffee Beans", 3.5, "200g", R.drawable.the),
            CategoryItem("Green Tea", 2.8, "200g", R.drawable.the),
            CategoryItem("Black Tea", 2.5, "200g", R.drawable.the),
            CategoryItem("Chamomile Tea", 3.0, "200g", R.drawable.the),
            CategoryItem("Espresso", 4.0, "200g", R.drawable.the)
        )
    ),
    Grocery(
        id = 6,
        categoryName = "Atta, Rice & Dal",
        imageResId = R.drawable.test_grocery_rice,
        customColor = Color(0xFF9E9D24),
        items = listOf(
            CategoryItem("Basmati Rice", 2.0, "1KG", R.drawable.test_grocery_rice),
            CategoryItem("Wheat Flour", 1.5, "1KG", R.drawable.test_grocery_rice),
            CategoryItem("Chana Dal", 2.0, "1KG", R.drawable.test_grocery_rice),
            CategoryItem("Toor Dal", 2.2, "1KG", R.drawable.test_grocery_rice),
            CategoryItem("Moong Dal", 2.0, "1KG", R.drawable.test_grocery_rice),
            CategoryItem("Masoor Dal", 2.0, "1KG", R.drawable.test_grocery_rice)
        )
    ),
    Grocery(
        id = 7,
        categoryName = "Masala, Oil & Dry Fruits",
        imageResId = R.drawable.fish,
        customColor = Color(0xFFFF5722),
        items = listOf(
            CategoryItem("Sunflower Oil", 5.0, "1L", R.drawable.olivetree),
            CategoryItem("Almonds", 6.0, "500g", R.drawable.olivetree),
            CategoryItem("Cashews", 7.0, "500g", R.drawable.olivetree),
            CategoryItem("Olive Oil", 5.5, "1L", R.drawable.olivetree),
            CategoryItem("Ghee", 6.5, "1KG", R.drawable.olivetree),
            CategoryItem("Cumin Seeds", 2.0, "200g", R.drawable.olivetree)
        )
    ),
    Grocery(
        id = 8,
        categoryName = "Chicken, Meat & Fish",
        imageResId = R.drawable.meat1,
        customColor = Color(0xFFFF3D00),
        items = listOf(
            CategoryItem("Chicken Breast", 6.0, "1KG", R.drawable.chickenbreast),
            CategoryItem("Salmon", 8.0, "1KG", R.drawable.chickenbreast),
            CategoryItem("Chicken Wings", 5.5, "1KG", R.drawable.chickenbreast),
            CategoryItem("Lamb Chops", 9.0, "1KG", R.drawable.chickenbreast),
            CategoryItem("Beef Steak", 10.0, "1KG", R.drawable.chickenbreast),
            CategoryItem("Pork Ribs", 7.5, "1KG", R.drawable.chickenbreast)
        )
    ),
    Grocery(
        id = 9,
        categoryName = "Snacks",
        imageResId = R.drawable.san,
        customColor = Color(0xFFFFEB3B),
        items = listOf(
            CategoryItem("Bread", 1.0, "1 loaf", R.drawable.bread),
            CategoryItem("Chips", 1.5, "1 pack", R.drawable.bread),
            CategoryItem("Pastries", 2.5, "1 piece", R.drawable.bread),
            CategoryItem("Muffins", 2.0, "1 piece", R.drawable.bread),
            CategoryItem("Cookies", 1.8, "1 pack", R.drawable.bread),
            CategoryItem("Cakes", 3.0, "1 piece", R.drawable.bread)
        )
    ),
    Grocery(
        id = 10,
        categoryName = "Sweets & Desserts",
        imageResId = R.drawable.sweet,
        customColor = Color(0xFFE91E63),
        items = listOf(
            CategoryItem("Chocolate", 1.5, "1 bar", R.drawable.cake),
            CategoryItem("Ice Cream", 3.0, "500ml", R.drawable.cake),
            CategoryItem("Cake", 4.0, "1 piece", R.drawable.cake),
            CategoryItem("Pudding", 2.0, "1 cup", R.drawable.cake),
            CategoryItem("Brownies", 2.5, "1 piece", R.drawable.cake),
            CategoryItem("Donuts", 1.8, "1 piece", R.drawable.cake)
        )
    ),
    Grocery(
        id = 11,
        categoryName = "Spices & Condiments",
        imageResId = R.drawable.spice,
        customColor = Color(0xFF673AB7),
        items = listOf(
            CategoryItem("Salt", 0.5, "1KG", R.drawable.spicea),
            CategoryItem("Pepper", 2.0, "200g", R.drawable.spicea),
            CategoryItem("Chili Powder", 1.5, "200g", R.drawable.spicea),
            CategoryItem("Garam Masala", 2.5, "200g", R.drawable.spicea),
            CategoryItem("Turmeric Powder", 1.8, "200g", R.drawable.spicea),
            CategoryItem("Cinnamon Sticks", 2.0, "100g", R.drawable.spicea)
        )
    )
)
data class Grocery(
    val id: Int,
    val categoryName: String,
    val imageResId: Int,
    val customColor: Any,
    val items: List<CategoryItem> = emptyList()
)

data class CategoryItem(
    val name: String,
    val price: Double,
    val quantity: String,
    val imageResId: Int
)


val allFoodItems =
    listOf(
        // PIZZA Category
        FoodItem(
            id = 1,
            restaurantId = "1",
            imageRes = R.drawable.test_foodcategory_pizza,
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
            restaurantId = "1",
            imageRes = R.drawable.test_foodcategory_pizza,
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
            restaurantId = "2",
            imageRes = R.drawable.test_foodcategory_burger,
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
            restaurantId = "2",
            imageRes = R.drawable.test_foodcategory_burger,
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
        FoodItem(
            id = 5,
            restaurantId = "3",
            imageRes = R.drawable.test_foodcategory_taac,
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
            restaurantId = "3",
            imageRes = R.drawable.test_foodcategory_taac,
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
            restaurantId = "4",
            imageRes = R.drawable.test_foodcategory_taac,
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
            restaurantId = "4",
            imageRes = R.drawable.test_foodcategory_taac,
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
            restaurantId = "5",
            imageRes = R.drawable.test_foodcategory_pasta,
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
            restaurantId = "5",
            imageRes = R.drawable.test_foodcategory_pasta,
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
            restaurantId = "6",
            imageRes = R.drawable.test_foodcategory_pizza,
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
            restaurantId = "6",
            imageRes = R.drawable.test_foodcategory_pasta,
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
            restaurantId = "7",
            imageRes = R.drawable.test_foodcategory_suchi,
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
            restaurantId = "7",
            imageRes = R.drawable.test_foodcategory_suchi,
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
            reviews = 155
        ),
        // THAI Category
        FoodItem(
            id = 15,
            restaurantId = "8",
            imageRes = R.drawable.test_foodcategory_suchi,
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

