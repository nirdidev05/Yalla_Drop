package com.example.yalladrop.profile


import android.Manifest
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.yalladrop.models.PrincipaleBackGroound
import com.example.yalladrop.R
import com.example.yalladrop.models.TextFieldOutlined
import com.example.yalladrop.auth.validateName
import com.example.yalladrop.auth.validatePhone
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun Profile(navController: NavHostController){

    val focusManager = LocalFocusManager.current
    var nameValue by remember { mutableStateOf("") }
    var nameError by remember { mutableStateOf("") }
    fun setName(value: String){
        nameValue = value
    }

    var phoneValue by remember { mutableStateOf("") }
    var phoneError by remember { mutableStateOf("") }
    fun setPhone(value: String){
        phoneValue = value
    }

    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    var showImagePickerOptions by remember { mutableStateOf(false) }
    var tempUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current

    // Create temporary file for camera photo
    fun createImageFile(): Uri {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_"
        val storageDir = context.getExternalFilesDir(null)
        val imageFile = File.createTempFile(
            imageFileName,
            ".jpg",
            storageDir
        )
        return FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",
            imageFile
        )
    }

    // Photo Picker Launcher
    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        uri?.let { selectedImageUri = it }
    }

    // Camera Launcher
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success) {
            tempUri?.let { uri ->
                selectedImageUri = uri
            }
        }
    }

    // Permission Launcher
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            try {
                tempUri = createImageFile()
                tempUri?.let { uri ->
                    cameraLauncher.launch(uri)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                // Handle error - maybe show a toast
            }
        }
    }



    PrincipaleBackGroound(title = "My profile" , navController ){
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 130.dp),
            horizontalAlignment =   Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .size(160.dp, 160.dp)
                    .clip(RoundedCornerShape(20))
                    .padding(vertical = 10.dp, horizontal = 15.dp)
            ) {



                if (selectedImageUri != null) {
                    AsyncImage(
                        model = selectedImageUri,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(7.dp)
                            .fillMaxWidth()
                            .align(Alignment.Center)
                            .clip(shape = RoundedCornerShape(20.dp))
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.images),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(7.dp)
                            .fillMaxWidth()
                            .align(Alignment.Center)
                            .clip(shape = RoundedCornerShape(20.dp))
                    )
                }
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .size(25.dp, 25.dp)
                        .clip(shape = RoundedCornerShape(20.dp))
                        .background(Color.Red)
                        .clickable{
                            showImagePickerOptions = true
                        },
                )
                {
                    Icon(
                        Icons.Default.Camera,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(18.dp, 18.dp)

                    )
                }
                DropdownMenu(
                    expanded = showImagePickerOptions,
                    onDismissRequest = { showImagePickerOptions = false },

                    modifier = Modifier.clip(RoundedCornerShape(25))
                ) {
                    DropdownMenuItem(
                        text = { Text("Choose from Gallery" , style = MaterialTheme.typography.displaySmall , fontWeight = FontWeight.Medium , color = Color.White , textAlign = TextAlign.Center ) },
                        modifier = Modifier.fillMaxWidth().padding(7.dp).clip(RoundedCornerShape(30)).background(color = MaterialTheme.colorScheme.primary , ) ,
                        onClick = {
                            photoPickerLauncher.launch(
                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                            )
                            showImagePickerOptions = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Take photo" , style = MaterialTheme.typography.displaySmall , fontWeight = FontWeight.Medium , color = Color.White , textAlign = TextAlign.Center ) },
                        modifier = Modifier.fillMaxWidth().padding(7.dp).clip(RoundedCornerShape(30)).background(color = MaterialTheme.colorScheme.primary , ) ,

                        onClick = {
                            permissionLauncher.launch(Manifest.permission.CAMERA)
                            showImagePickerOptions = false
                        }
                    )
                }

            }
            Column {


                Text(
                    text = "Full Name",
                    style = MaterialTheme.typography.displayMedium,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(bottom = 2.dp , top = 20.dp)

                )

                TextFieldOutlined(
                    value = nameValue,
                    placeholder = "Enter your name",
                    onChange = { setName(it) },
                    isError = nameError.isNotEmpty(),
                    errorMessage = nameError,
                    icon = Icons.Rounded.Person,
                    keyboardType = KeyboardType.Text,
                    unfocusedcontainercolor = MaterialTheme.colorScheme.secondary,
                    size = 20.dp,
                    unfocusedbordercolor = Color.Transparent

                )
                Text(
                    text = "Phone Number",
                    style = MaterialTheme.typography.displayMedium,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(bottom = 2.dp , top = 20.dp)

                )
                TextFieldOutlined(
                    value = phoneValue,
                    placeholder = "Enter your phone number",
                    onChange = { setPhone(it) },
                    isError = phoneError.isNotEmpty(),
                    errorMessage = phoneError,
                    icon = Icons.Rounded.Phone,
                    unfocusedcontainercolor = MaterialTheme.colorScheme.secondary,
                    size = 20.dp,
                    keyboardType = KeyboardType.Phone,
                    unfocusedbordercolor = Color.Transparent
                )

            }

            Box (
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ){
                Button(
                    onClick = {
                        focusManager.clearFocus()
                        nameError = validateName(nameValue)
                        phoneError= validatePhone(phoneValue)

                        if(phoneError.isEmpty() && phoneValue.isEmpty())
                        {
                            println("correct")
                        }
                    },
                    modifier = Modifier
                        .height(40.dp)
                        .width(200.dp)
                        .clip(RoundedCornerShape(38)),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),


                    ) {
                    Text(
                        text = "Update Profile",
                        style = MaterialTheme.typography.displayMedium,
                        color = Color.White
                    )
                }
            }



        }

    }
}