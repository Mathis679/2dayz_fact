package com.example.a2dayzfact

import android.app.Application
import com.example.a2dayzfact.core.AlarmScheduler
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class AppApplication : Application() {

    @Inject
    lateinit var alarmScheduler: AlarmScheduler

    override fun onCreate() {
        super.onCreate()
        alarmScheduler.scheduleDailyAlarm()
    }

}
