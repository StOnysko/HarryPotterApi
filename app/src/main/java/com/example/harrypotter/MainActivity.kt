package com.example.harrypotter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.harrypotter.presentation.ui.screens.navigation.NavigationGraph
import com.example.harrypotter.presentation.ui.theme.HarryPotterTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HarryPotterTheme {
              NavigationGraph()
            }
        }
    }
}

