package com.mindvalley.mindvalleyapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.mindvalley.mindvalleyapp.presentation.theme.Dark
import com.mindvalley.mindvalleyapp.presentation.theme.MindvalleyAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MindvalleyAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Dark
                ) {

                }
            }
        }
    }
}
