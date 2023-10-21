package com.example.a2dayzfact

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.a2dayzfact.ui.navigation.FactNavigationGraph
import com.example.a2dayzfact.ui.theme._2dayzFactTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            _2dayzFactTheme {
                FactNavigationGraph(rememberNavController())
            }
        }
    }
}
