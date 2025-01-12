import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tdm.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayImage() {
    // Remember state for the editable text fields
    val cameraValue = remember { mutableStateOf("Leica M Typ 240") }
    val settingsValue = remember { mutableStateOf("f/4 16s ISO 200") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Image at the top
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            painter = painterResource(id = R.drawable.zombo),
            contentDescription = "Singapore Image",
            contentScale = ContentScale.Crop
        )

        // Title (e.g., Singapore)
        Text(
            text = "ZOMBOOO",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(top = 8.dp)
        )

        // Camera text field
        Text(
            text = "Camera",
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.padding(top = 8.dp)
        )
        OutlinedTextField(
            value = cameraValue.value,
            onValueChange = { cameraValue.value = it },
            modifier = Modifier.fillMaxWidth()
        )

        // Settings text field
        Text(
            text = "Settings",
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.padding(top = 8.dp)
        )
        OutlinedTextField(
            value = settingsValue.value,
            onValueChange = { settingsValue.value = it },
            modifier = Modifier.fillMaxWidth()
        )

        // Description paragraph
        Text(
            text = "Singapore officially the Republic of Singapore, and often referred to as the Lion City, the Garden City, and the Red Dot, is a global city in Southeast Asia and the world's only island city-state...",
            fontSize = 14.sp,
            modifier = Modifier.padding(top = 8.dp)
        )

        // Align the buttons to the right and at the bottom of the layout
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.End // Align buttons to the right
        ) {
            Button(onClick = { /* Handle discard action */ }) {
                Text(text = "DISCARD")
            }
            Spacer(modifier = Modifier.width(16.dp)) // Space between buttons
            Button(onClick = { /* Handle upload action */ }) {
                Text(text = "UPLOAD")
            }
        }
    }
}
