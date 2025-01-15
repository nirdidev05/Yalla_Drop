package com.example.tdm

import android.view.MotionEvent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items

import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.example.tdm.model.Grocery
import com.example.tdm.model.groceries

@Composable
fun GroceryPage(navController: NavController) {
    val orangePrimary = Color(0xFFFF5722)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(orangePrimary)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Top Bar
        TopBar()

        Spacer(modifier = Modifier.height(20.dp))

        // Grocery Title
        Text(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            text = "Grocery",
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(16.dp))

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
            Spacer(modifier = Modifier.height(16.dp))
            PromoBanner(navController)
            Spacer(modifier = Modifier.height(16.dp))
            CategorySection(groceries = groceries, navController = navController)
            Spacer(modifier = Modifier.height(16.dp))
            DealsSection()
        }
    }
}
@Composable
fun PromoBanner(navController: NavController) {
    Box(
        modifier = Modifier.clickable {
            navController.navigate("uno")
        }
    ) {
        Image(
            painter = painterResource(R.drawable.uno),
            contentDescription = "Promo Banner"
        )
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategorySection(groceries: List<Grocery>, navController: NavController) {
    var showDialog by remember { mutableStateOf(false) }
    var selectedCategory by remember { mutableStateOf<Grocery?>(null) }

    Column {
        // Header Section
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "All Categories",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
            )

            TextButton(
                onClick = { showDialog = true },
                colors = ButtonDefaults.textButtonColors(
                    contentColor = Color(0xFFFF7622)
                )
            ) {
                Text(
                    "View all",
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Icon(
                    Icons.Default.ArrowForward,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = Color(0xFFFF7622)
                )

            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .height(220.dp)
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(minOf(groceries.size, 6)) { index ->
                CategoryItem(
                    grocery = groceries[index],
                    navController = navController
                )
            }
        }
    }
    if (showDialog) {
        Dialog(
            onDismissRequest = { showDialog = false },
            properties = DialogProperties(
                usePlatformDefaultWidth = false,
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            )
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.92f)
                    .fillMaxHeight(0.8f)
                    .clip(RoundedCornerShape(28.dp)),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp)
                ) {
                    // Dialog Header
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "Browse Categories",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 24.sp
                            )
                        )
                        IconButton(
                            onClick = { showDialog = false }
                        ) {
                            Icon(
                                Icons.Default.Close,
                                contentDescription = "Close",
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Search Bar
                    var searchQuery by remember { mutableStateOf("") }
                    TextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text("Search categories") },
                        leadingIcon = {
                            Icon(
                                Icons.Default.Search,
                                contentDescription = "Search",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        },
                        shape = RoundedCornerShape(16.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent
                        )
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Filtered Categories Grid
                    val filteredCategories = groceries.filter {
                        it.categoryName.contains(searchQuery, ignoreCase = true)
                    }

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(3),
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(filteredCategories) { grocery ->
                            CategoryItem(
                                grocery = grocery,
                                navController = navController,
                                onClick = {
                                    selectedCategory = grocery
                                    showDialog = false
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}



@Composable
fun CategoryItem(
    grocery: Grocery,
    navController: NavController,
    onClick: () -> Unit = {}
) {

        val interactionSource = remember { MutableInteractionSource() }
        val isPressed by interactionSource.collectIsPressedAsState()

        val scale by animateFloatAsState(
            targetValue = if (isPressed) 0.95f else 1f,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        )

        Card(
            modifier = Modifier
                .padding(8.dp)
                .scale(scale)
                .size(100.dp)
                .shadow(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(16.dp),
                    spotColor = grocery.customColor as Color
                )
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    navController.navigate("category_items/${grocery.categoryName}")
                    onClick()
                },
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    // Icon container
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.White)
                            .padding(8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = grocery.imageResId),
                            contentDescription = null,
                            modifier = Modifier.size(35.dp),
                            contentScale = ContentScale.Fit
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Category name
                    Text(
                        text = grocery.categoryName,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color.Black.copy(alpha = 0.8f)
                        ),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DealItem(
    deal: Deal,
    modifier: Modifier = Modifier
) {
    var isFavorite by remember { mutableStateOf(false) }
    // Add hover state
    var isHovered by remember { mutableStateOf(false) }

    // Create animated values for hover effect
    val scale by animateFloatAsState(
        targetValue = if (isHovered) 1.05f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    val elevation by animateFloatAsState(
        targetValue = if (isHovered) 8f else 2f,
        animationSpec = tween(durationMillis = 300)
    )

    Card(
        modifier = modifier
            .width(200.dp)
            .padding(8.dp)
            .graphicsLayer {
                clip = true
                scaleX = scale
                scaleY = scale
                shadowElevation = elevation
                shape = RoundedCornerShape(24.dp)
            }
            // Use pointerInteropFilter for hover detection
            .pointerInteropFilter {
                when (it.action) {
                    MotionEvent.ACTION_HOVER_ENTER -> {
                        isHovered = true
                        true
                    }
                    MotionEvent.ACTION_HOVER_EXIT -> {
                        isHovered = false
                        true
                    }
                    else -> false
                }
            }
            .animateContentSize(),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp), // Set to 0 since we're handling elevation in graphicsLayer
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            // Favorite Button with animation
            IconButton(
                onClick = { isFavorite = !isFavorite },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
                    .size(36.dp)
                    .background(
                        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.8f),
                        shape = CircleShape
                    )
            ) {
                Icon(
                    imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite",
                    tint = if (isFavorite) Color.Red else MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.scale(animateFloatAsState(if (isFavorite) 1.2f else 1f).value)
                )
            }

            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                // Discount Badge
                Surface(
                    color = MaterialTheme.colorScheme.errorContainer,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.padding(bottom = 8.dp)
                ) {
                    val price: Double = deal.oldPrice.toDouble()
                    val price1: Double = deal.newPrice.toDouble()

                    Text(
                        text = "${calculateDiscount(price, price1)}% OFF",
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onErrorContainer
                    )
                }

                // Add a sliding animation for the image when hovered
                Box(
                    modifier = Modifier
                        .size(140.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Image(
                        painter = painterResource(id = deal.imageResId),
                        contentDescription = deal.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .graphicsLayer {
                                translationY = if (isHovered) -8f else 0f
                            }
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = deal.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = animateColorAsState(
                        if (isHovered) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.onSurface
                    ).value
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Price Section
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "${deal.oldPrice}$",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.outline,
                            textDecoration = TextDecoration.LineThrough
                        )
                        Text(
                            text = "${deal.newPrice}$",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFFF7622)
                        )
                    }

                    // Animated Add to Cart Button
                    Button(
                        onClick = { /* Add to cart logic */ },
                        shape = CircleShape,
                        contentPadding = PaddingValues(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFF7622)
                        ),
                        modifier = Modifier.graphicsLayer {
                            scaleX = if (isHovered) 1.1f else 1f
                            scaleY = if (isHovered) 1.1f else 1f
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add to Cart"
                        )
                    }
                }
            }
        }
    }
}
@Composable
fun DealsSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Best Deals",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Get special offers and more",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.outline
                )
            }

        }

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 4.dp)
        ) {
            items(deals) { deal ->
                DealItem(deal)
            }
        }
    }
}

private fun calculateDiscount(oldPrice: Double, newPrice: Double): Int {
    return ((oldPrice - newPrice) / oldPrice * 100).toInt()
}
@Composable
fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        var searchText by remember { mutableStateOf(TextFieldValue("")) }

        // Search Bar
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .weight(1f)
                .height(36.dp)
                .background(Color.White, RoundedCornerShape(30.dp))
                .padding(horizontal = 10.dp, vertical = 5.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search icon",
                tint = Color.Gray,
                modifier = Modifier
                    .size(18.dp)
                    .clickable { }
            )

            Spacer(modifier = Modifier.width(8.dp))

            BasicTextField(
                value = searchText,
                onValueChange = { searchText = it },
                singleLine = true,
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 12.sp
                ),
                modifier = Modifier.fillMaxWidth(),
                decorationBox = { innerTextField ->
                    if (searchText.text.isEmpty()) {
                        Text(
                            "Search",
                            color = Color.Gray,
                            fontSize = 12.sp
                        )
                    }
                    innerTextField()
                }
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Action Icons
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ActionIcon(iconId = R.drawable.carte)
            ActionIcon(iconId = R.drawable.noti)
        }
    }
}

@Composable
fun ActionIcon(iconId: Int) {
    IconButton(onClick = { /*TODO*/ }) {
        Box(
            modifier = Modifier
                .size(30.dp)
                .background(Color.White, shape = RoundedCornerShape(13.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = iconId),
                contentDescription = null,
                tint = Color(0xFFFF5722)
            )
        }
    }
}




data class Deal(
    val imageResId: Int,
    val name: String,
    val oldPrice: Int,
    val newPrice: Int
)

val deals = listOf(
    Deal(R.drawable.ariel, "Ariel", 14, 12),
    Deal(R.drawable.fortune, "Fortune", 12, 9),
    Deal(R.drawable.tata, "Tata Salt", 5, 3),
    Deal(R.drawable.biscuit, "Biscuits", 10, 8),
    Deal(R.drawable.thee, "The", 15, 12)
)
