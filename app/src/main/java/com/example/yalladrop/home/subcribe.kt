package com.example.yalladrop.home


import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
data class DeliveryPlan(
    val name: String,
    val price: String,
    val color: Color,
    val benefits: List<String>,
    val icon: ImageVector,
    val deliveryFee: String,
    val estimatedSavings: String
)
// Custom Colors
private val PrimaryColor = Color(0xFF00BFA5)
private val LightGreen = Color(0xFFE0F2F1)
private val DarkGreen = Color(0xFF00897B)
private val PremiumColor = Color(0xFF6200EA)
private val StandardColor = Color(0xFF0091EA)
private val BasicColor = Color(0xFF00BFA5)
private val BackgroundColor = Color(0xFFF5F5F5)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeliverySubscriptionScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    var selectedPlan by remember { mutableStateOf<DeliveryPlan?>(null) }
    var cardNumber by remember { mutableStateOf("") }
    var cardHolder by remember { mutableStateOf("") }
    var expiryDate by remember { mutableStateOf("") }
    var cvv by remember { mutableStateOf("") }
    var isCardFlipped by remember { mutableStateOf(false) }

    val plans = listOf(
        DeliveryPlan(
            name = "Premium Delivery",
            price = "$14.99/month",
            color = PremiumColor,
            benefits = listOf(
                "Unlimited Free Deliveries",
                "Priority Order Processing",
                "Exclusive Restaurant Access",
                "Special Discounts & Offers",
                "24/7 Priority Support"
            ),
            icon = Icons.Default.Rocket,
            deliveryFee = "FREE",
            estimatedSavings = "$45/month"
        ),
        DeliveryPlan(
            name = "Standard Delivery",
            price = "$9.99/month",
            color = StandardColor,
            benefits = listOf(
                "15 Free Deliveries/month",
                "Regular Order Processing",
                "50% Off Peak Hour Fees",
                "Weekend Special Offers",
                "Priority Support"
            ),
            icon = Icons.Default.DeliveryDining,
            deliveryFee = "$1.99",
            estimatedSavings = "$30/month"
        ),
        DeliveryPlan(
            name = "Basic Delivery",
            price = "$5.99/month",
            color = BasicColor,
            benefits = listOf(
                "5 Free Deliveries/month",
                "Standard Order Processing",
                "25% Off Peak Hour Fees",
                "Basic Support"
            ),
            icon = Icons.Default.LocalShipping,
            deliveryFee = "$2.99",
            estimatedSavings = "$15/month"
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Delivery Membership",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = BackgroundColor
                )
            )
        },
        containerColor = BackgroundColor
    ) { padding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {
            // Info Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = LightGreen
                )
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.Info,
                        contentDescription = null,
                        tint = DarkGreen,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        "Subscribe to save on delivery fees and get exclusive benefits!",
                        style = MaterialTheme.typography.bodyMedium,
                        color = DarkGreen
                    )
                }
            }

            // Subscription Plans
            LazyRow(
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(plans.size) { index ->
                    DeliveryPlanCard(
                        plan = plans[index],
                        isSelected = selectedPlan == plans[index],
                        onSelect = { selectedPlan = plans[index] }
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Payment Section
            AnimatedVisibility(
                visible = selectedPlan != null,
                enter = expandVertically() + fadeIn(),
                exit = shrinkVertically() + fadeOut()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        "Payment Details",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
// Credit Card Input Fields
                    OutlinedTextField(
                        value = cardNumber,
                        onValueChange = { if (it.length <= 16) cardNumber = it.filter { char -> char.isDigit() } },
                        label = { Text("Card Number") },
                        leadingIcon = { Icon(Icons.Default.CreditCard, contentDescription = null) },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = cardHolder,
                        onValueChange = { cardHolder = it },
                        label = { Text("Card Holder Name") },
                        leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        OutlinedTextField(
                            value = expiryDate,
                            onValueChange = {
                                if (it.length <= 5) {
                                    expiryDate = it.filter { char -> char.isDigit() || char == '/' }
                                    if (it.length == 2 && !it.contains("/")) {
                                        expiryDate = "$it/"
                                    }
                                }
                            },
                            label = { Text("MM/YY") },
                            modifier = Modifier.weight(1f),
                            singleLine = true
                        )

                        OutlinedTextField(
                            value = cvv,
                            onValueChange = { if (it.length <= 3) cvv = it.filter { char -> char.isDigit() } },
                            label = { Text("CVV") },
                            modifier = Modifier.weight(1f),
                            visualTransformation = PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            singleLine = true
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))


                    Button(
                        onClick = {
                            selectedPlan?.let {
                                navController.navigate("HomePage")
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = selectedPlan?.color ?: PrimaryColor
                        )
                    ) {
                        Text(
                            "Start Saving Now",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        "You can cancel anytime",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
    }
}


@Composable
private fun DeliveryPlanCard(
    plan: DeliveryPlan,
    isSelected: Boolean,
    onSelect: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(280.dp)
            .shadow(if (isSelected) 12.dp else 4.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = onSelect)
            .border(
                width = if (isSelected) 2.dp else 0.dp,
                color = if (isSelected) plan.color else Color.Transparent,
                shape = RoundedCornerShape(16.dp)
            ),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) plan.color.copy(alpha = 0.1f) else Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = plan.icon,
                    contentDescription = null,
                    tint = plan.color,
                    modifier = Modifier.size(32.dp)
                )
                if (isSelected) {
                    Icon(
                        Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint = plan.color
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = plan.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = plan.color
            )

            Text(
                text = plan.price,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Delivery Fee Card
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = plan.color.copy(alpha = 0.1f)
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        "Delivery Fee",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        plan.deliveryFee,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        color = plan.color
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Estimated Savings Card
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = plan.color.copy(alpha = 0.1f)
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        "Est. Monthly Savings",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        plan.estimatedSavings,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        color = plan.color
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Benefits List
            plan.benefits.forEach { benefit ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.Check,
                        contentDescription = null,
                        tint = plan.color,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = benefit,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}