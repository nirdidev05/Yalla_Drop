package com.example.tdm

import FoodItem
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

object FoodDataProvider {
    val allFoodItems = listOf(
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodDetailScreen(
    foodId: Int,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val foodItem = remember {
        FoodDataProvider.allFoodItems.find { it.id == foodId }
    } ?: return

    var quantity by remember { mutableStateOf(1) }
    val backgroundColor = Color(0xFFFFF8F1)
    val primaryAccent = Color(0xFFFB8C00)

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "FOOD DETAILS")
                    }
                }
            )
        }
    )
    { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(backgroundColor)
        ) {
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

            Spacer(modifier = Modifier.height(16.dp))

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = foodItem.name,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Filled.Star,
                            contentDescription = "Rating",
                            tint = Color(0xFFFFD700)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "${foodItem.rating} (${foodItem.reviews} reviews)",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${foodItem.category} •",
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "${foodItem.price}",
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                            color = Color(0xFFFF6B35)
                        )
                    }
                }


                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = foodItem.description,
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                Spacer(modifier = Modifier.height(8.dp))

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = foodItem.description,
                    style = MaterialTheme.typography.bodyLarge.copy(lineHeight = 24.sp),
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Ingredients",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                    color = primaryAccent
                )
                Spacer(modifier = Modifier.height(8.dp))
                Column {
                    foodItem.ingredients.forEach { ingredient ->
                        Text(
                            text = "• $ingredient",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }

            // Action Section
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { if (quantity > 1) quantity-- },
                    modifier = Modifier.background(primaryAccent, shape = RoundedCornerShape(8.dp))
                ) {
                    Icon(Icons.Default.Remove, contentDescription = "Decrease", tint = Color.White)
                }
                Text(
                    text = "$quantity",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                IconButton(
                    onClick = { quantity++ },
                    modifier = Modifier.background(primaryAccent, shape = RoundedCornerShape(8.dp))
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Increase", tint = Color.White)
                }
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    onClick = { /* Add to cart */ },
                    colors = ButtonDefaults.buttonColors(containerColor = primaryAccent),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.shadow(8.dp, RoundedCornerShape(16.dp))
                ) {
                    Text(
                        "Add to Cart",
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = FontWeight.Medium,
                            color = Color.White
                        )
                    )
                }
            }
        }
    }
}
