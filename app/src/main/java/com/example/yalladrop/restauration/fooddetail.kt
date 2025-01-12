package com.example.yalladrop.restauration

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import androidx.navigation.NavHostController
import com.example.yalladrop.R

// Create a singleton object to hold the food items data
object FoodDataProvider {
    val allFoodItems = listOf(
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
            imageRes = R.drawable.test_foodcategory_pizza,
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodDetailScreen(
    foodId: Int = 20,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    // Find the food item by ID from the shared data provider
    val foodItem = remember {
        FoodDataProvider.allFoodItems.find { it.id == foodId }
    } ?: return

    var quantity by remember { mutableStateOf(1) }
    val backgroundColor = Color(0xFFFFF8F1)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Kabeer Food",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* Handle cart click */ }) {
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = "Cart",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFFFF7622)
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(backgroundColor)
                .padding(paddingValues)
        ) {
            // Food Image
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .padding(16.dp)
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

            // Category
            Text(
                text = foodItem.category,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            // Food Name and Rating
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = foodItem.name,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Rating",
                        tint = Color(0xFFFFDA7D)
                    )
                    Text(
                        text = "${foodItem.rating} (${foodItem.reviews} Reviews)",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            // Ingredients Section
            Text(
                text = "About",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )
            foodItem.ingredients.forEach { ingredient ->
                Text(
                    text = ingredient,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 2.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Quantity and Add to Cart Section
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Quantity Control
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = { if (quantity > 1) quantity-- },
                        modifier = Modifier
                            .size(48.dp)
                            .background(
                                color = Color(0xFFFF7622).copy(alpha = 0.1f),
                                shape = CircleShape
                            )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Decrease Quantity",
                            tint = Color(0xFFFF7622)
                        )
                    }
                    Text(
                        text = "$quantity",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    IconButton(
                        onClick = { quantity++ },
                        modifier = Modifier
                            .size(48.dp)
                            .background(
                                color = Color(0xFFFF7622).copy(alpha = 0.1f),
                                shape = CircleShape
                            )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Increase Quantity",
                            tint = Color(0xFFFF7622)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Add to Cart Button
            Button(
                onClick = { /* Handle Add to Cart */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7622)),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = "ADD TO CART",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }
    }
}


