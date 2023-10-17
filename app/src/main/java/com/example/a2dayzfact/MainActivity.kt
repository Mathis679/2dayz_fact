package com.example.a2dayzfact

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.a2dayzfact.ui.screens.mainlist.content.MainListScreen
import com.example.a2dayzfact.ui.theme._2dayzFactTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            _2dayzFactTheme {
                MainListScreen()
            }
        }
    }
}
