import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun UnoPartnershipPage(navController: NavController) {
    val benefits = listOf(
        Triple(Icons.Default.LocalShipping, "Fast Delivery", "Get your Uno products delivered within 60 minutes"),
        Triple(Icons.Default.ShoppingBag, "Wide Selection", "Access to Uno's complete catalog of products"),
        Triple(Icons.Default.Schedule, "Extended Hours", "Shop from Uno even during evening hours"),
        Triple(Icons.Default.Shield, "Quality Guaranteed", "Same in-store quality, delivered to your door")
    )
    val context = LocalContext.current
    val url = "https://www.google.com/url?sa=t&source=web&rct=j&opi=89978449&url=https://www.facebook.com/UNO.Hypermarche/%3Flocale%3Dfr_FR&ved=2ahUKEwiAl4Ot3fWKAxVr3QIHHU-aFFAQFnoECC4QAQ&usg=AOvVaw1uhV5s9d01Lx-HzsWCP22d"



    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // Hero Section
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(Color(0xFFFF5722), Color(0xFFFF7043))
                    )
                )
        ) {
            // Dark overlay
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.2f))
            )

            // Content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text("Yalla Drop",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text("×",
                        fontSize = 24.sp,
                        color = Color.White
                    )
                    Text("Uno",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    "Your Favorite Hypermarket, Delivered",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    "Get all your Uno essentials delivered straight to your door with Yalla Drop's lightning-fast delivery service",
                    fontSize = 20.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 24.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = { /* Handle click */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    shape = CircleShape,
                    modifier = Modifier.padding(horizontal = 32.dp, vertical = 16.dp)
                ) {
                    Text(
                        "Shop Now",
                        color = Color(0xFFFF5722),
                        modifier = Modifier
                            .clickable {
                                navController.navigate("grocery")}

                    )
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "Arrow",
                        tint = Color(0xFFFF5722)
                    )
                }
            }
        }

        // Benefits Section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 80.dp, bottom = 80.dp, start = 16.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Why Shop Uno with Yalla Drop?",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                "Experience the convenience of shopping from Algeria's premier hypermarket with the reliability of Yalla Drop's delivery service",
                fontSize = 16.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 24.dp)
            )

            Spacer(modifier = Modifier.height(48.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                benefits.chunked(2).forEach { columnBenefits ->
                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        columnBenefits.forEach { (icon, title, description) ->
                            BenefitCard(icon, title, description)
                        }
                    }
                }
            }
        }


        // CTA Section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFF5722))
                .padding(vertical = 64.dp, horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Ready to Start Shopping?",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                "Download the Yalla Drop app now and get access to thousands of products from Uno with exclusive offers and deals",
                fontSize = 20.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 24.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    context.startActivity(intent)
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                shape = CircleShape
            ) {
                Text(
                    "Visite UNO",
                    color = Color(0xFFFF5722),
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        }

        // Notice Section
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFFF3E0))
                .padding(vertical = 24.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = "Notice",
                tint = Color(0xFFFF5722),
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                "Delivery times may vary based on location and store operating hours",
                color = Color(0xFFFF5722),
                fontSize = 14.sp
            )
        }
    }
}

@Composable
private fun BenefitCard(icon: ImageVector, title: String, description: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(Color(0xFFFF5722), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = description,
                fontSize = 14.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun CategoryCard(category: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.Category,
                contentDescription = null,
                tint = Color(0xFFFF5722),
                modifier = Modifier.size(32.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = category,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}