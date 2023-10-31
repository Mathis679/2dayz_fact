package com.example.a2dayzfact

import android.content.Intent
import android.net.Uri
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
                FactNavigationGraph(
                    navController = rememberNavController(),
                    goToSource = {
                        openSource(it)
                    }
                 )
            }
        }
    }

    private fun openSource(source: String) {
        try {
            startActivity(
                Intent(Intent.ACTION_VIEW, Uri.parse(source))
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
