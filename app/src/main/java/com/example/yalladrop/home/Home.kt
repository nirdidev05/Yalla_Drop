package com.example.yalladrop.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.runtime.Composable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavHostController
import com.example.yalladrop.R
import kotlinx.coroutines.launch
import java.time.LocalTime

@Composable
fun HomePage(navController: NavHostController) {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(

        drawerState = drawerState,
        drawerContent = {

            ModalDrawerSheet(
                drawerContainerColor = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .fillMaxWidth(0.75f)
                    .clip(RoundedCornerShape(topEnd = 80.dp)) // Set the desired background color

            ) {
                DrawerHeader()
            }
        }
    ) {
        Scaffold(

        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp , vertical = 30.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.Menu,
                        contentDescription = "Drawer",
                        tint = Color.Black,
                        modifier = Modifier
                            .size(25.dp)
                            .clickable {
                                scope.launch {
                                    drawerState.apply {
                                        if (isClosed) open() else close()
                                    }
                                }
                            }
                    )
                    Spacer(modifier = Modifier.padding(horizontal = 10.dp))
                    GreetingSection("Walid")
                }
                Spacer(modifier = Modifier.height(10.dp))
                YallaPaySection(balance = "2000 DA")
                Spacer(modifier = Modifier.height(10.dp))
                CategoriesSection(navController)
                Spacer(modifier = Modifier.height(10.dp))
                PromoBanner()
                Spacer(modifier = Modifier.height(10.dp))
                SearchBar()
                Spacer(modifier = Modifier.height(10.dp))
                CategoriesChips()
                Spacer(modifier = Modifier.height(10.dp))
                RestaurantsSection(navController)
                Spacer(modifier = Modifier.height(10.dp))
                CancerAwarenessBanner()
                LogoutButton(navController)


            }
        }
    }
}

@Composable
fun LogoutButton(navController: NavController) {
    val context = LocalContext.current

    Button(onClick = {
      //  clearUserSession(context) // Passer le contexte ici

        navController.navigate("connectionPage") {
            popUpTo(0) { inclusive = true } // Supprimer l'historique de navigation
        }
    }) {
        Text("Logout")
    }
}



@Composable
fun GreetingSection(name: String) {

    

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
            verticalAlignment = Alignment.CenterVertically
            ) {
            
            Text(text = "Hey $name, ", fontSize = 20.sp)
            Text(text = greetingMessage, fontWeight = FontWeight.Bold, fontSize = 20.sp)

            Spacer(modifier = Modifier.weight(1f))

            Box(

                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(30.dp)
                    .background(Color(0x77FF5722), CircleShape)
                    .clickable { }
            ) {
                Text(
                    text = name.firstOrNull()?.uppercase() ?: "",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }


}

@Composable
fun YallaPaySection(balance: String) {
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
                .clickable(onClick = { /* TODO: Add click action */ })
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
            painter = painterResource(id = R.drawable.home_food),
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
                painter = painterResource(id = R.drawable.home_grocery),
                contentDescription = "Grocery 1",
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(end = 4.dp)
                    .clickable {
                        navController.navigate("grocerypage")
                    }
            )

            Image(
                painter = painterResource(id = R.drawable.home_dessert),
                contentDescription = "Grocery 2",
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(start = 4.dp)
                    .clickable { /* Add action if needed */ }
            )
        }
    }
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar() {
    var searchText by remember { mutableStateOf("") }
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
           androidx.compose.material3.Icon(
               imageVector =Icons.Default.Search , contentDescription = null,tint = Color.Gray,
               modifier = Modifier
                   .size(30.dp)
                   .clickable { },
           )
            Spacer(modifier = Modifier.width(3.dp))
            TextField(
                value = searchText,
                onValueChange = { searchText = it },
                placeholder = { Text("Search dishes, restaurants", color = Color.Gray) },
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesChips() {
    val categories = listOf(
        "All" to R.drawable.test_category_all,
        "Hot Dog" to R.drawable.test_category_hot,
        "Dessert" to R.drawable.test_category_dessert,
        "Grocery" to R.drawable.test_category_shopping,
        "Drinks" to R.drawable.test_category_cocktail,
        "Fast Food" to R.drawable.test_category_burger,
        "Pizza" to R.drawable.test_category_pizza,
        "Fish Restaurant" to R.drawable.test_category_fish
    )

    val (selectedCategory, setSelectedCategory) = remember { mutableStateOf("") }
    val isDialogOpen = remember { mutableStateOf(false) }

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("All Categories", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black)
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
                    onClick = { setSelectedCategory(category) }
                )
            }
        }

        if (isDialogOpen.value) {
           /* AlertDialog(
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
                                        setSelectedCategory(category)
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
                        modifier = Modifier
                            .fillMaxWidth()

                    ) {
                        Text("Close")
                    }
                }
            )

            */
            AlertDialog(
                onDismissRequest = { isDialogOpen.value = false },
                properties = DialogProperties(
                    dismissOnClickOutside = true // Optional, to control dismiss behavior
                ),
                content = {
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = MaterialTheme.shapes.medium,
                        color = MaterialTheme.colorScheme.surface,
                        tonalElevation = 8.dp
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            // Title
                            Text(
                                text = "All Categories",
                                style = MaterialTheme.typography.headlineSmall,
                                modifier = Modifier.padding(bottom = 16.dp)
                            )

                            // List of Categories
                            categories.forEach { (category, iconResId) ->
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .padding(vertical = 4.dp)
                                        .clickable {
                                            setSelectedCategory(category)
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

                            // Buttons
                            Spacer(modifier = Modifier.height(16.dp))
                            Button(
                                onClick = { isDialogOpen.value = false },
                                colors = ButtonDefaults.buttonColors(
                                    contentColor = Color.Black,
                                    containerColor = Color(0xFFFF5722),
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Text("Close")
                            }
                        }
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
            Text("Open Restaurants", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            Text("View All >", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color(0xFFFF5722),
                    modifier = Modifier.clickable {    }
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

            Image(
            painter = painterResource(id = R.drawable.restaurant1),
            contentDescription = "starbucks",
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable { navController.navigate("ecran2") },
        )
        RestaurantCard(
            name = "Starbucks Alger",
            rating = 4.7,
            deliveryFee = "Free",
            deliveryTime = "20 min",
            specialties = listOf("cafe", "the", "sugar")


        )

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
        Image(
            painter = painterResource(id = R.drawable.test_food_burger),
            contentDescription = "starbucks"
        )
        RestaurantCard(
            name = "Rose Garden Restaurant",
            rating = 5.0,
            deliveryFee = "Paid",
            deliveryTime = "10 min",
            specialties = listOf("Italian", "Vegetarian", "Vegan")
        )
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
                painter = painterResource(id = R.drawable.icon_star),
                contentDescription = "Rating",
                modifier = Modifier.size(16.dp),
                tint = Color(0xFFFF5722)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = "$rating", fontSize = 12.sp, color = Color.Black, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.width(8.dp))

            Icon(
                painter = painterResource(id = R.drawable.icon_deliveryicon),
                contentDescription = "Delivery",
                modifier = Modifier.size(16.dp),
                tint = Color(0xFFFF5722)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = deliveryFee, fontSize = 12.sp, color = Color.Black)

            Spacer(modifier = Modifier.width(8.dp))


            Icon(
                painter = painterResource(id = R.drawable.icon_clock),
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
            painter = painterResource(id = R.drawable.test_pub2), contentDescription ="october" )

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
                painter = painterResource(id = R.drawable.test_pub1),
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
            modifier = Modifier
                .padding(16.dp)
                .background(Color(0xFF4E3209), RoundedCornerShape(16.dp)) // Card color and shape
                .padding(24.dp)
        ) {
            Image(painter = painterResource(id = R.drawable.test_pub3), contentDescription ="discount" )
        }
        }
}
