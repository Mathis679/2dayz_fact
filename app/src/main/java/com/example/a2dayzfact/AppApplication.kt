package com.example.a2dayzfact

import android.app.Application
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}
