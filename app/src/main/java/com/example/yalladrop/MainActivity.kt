package com.example.yalladrop

import android.content.Context
import androidx.compose.material3.*
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.yalladrop.models.AuthViewModel
import com.example.yalladrop.models.NavigationManager
import com.example.yalladrop.ui.theme.YallaDropTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {

            YallaDropTheme {
                Scaffold { innerPadding ->
                    val navController = rememberNavController()
                    NavigationManager.navController = navController

                    val viewModel: AuthViewModel = hiltViewModel()

                    Navigation(navController , viewModel)
                }
            }
        }
    }
}














// width rounded 0.8 , not : 0.75