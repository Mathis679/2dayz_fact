package com.example.a2dayzfact.core

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.a2dayzfact.MainActivity
import com.example.a2dayzfact.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

const val NOTIFICATION_REQUEST_CODE = 18221

class AlarmReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val channelId = "daily_alarm_id"
        val channelName = "daily_alarm_name"
        val dayStr = SimpleDateFormat("dd MMMM", Locale.getDefault()).format(Calendar.getInstance().time)
        context?.let {
            val intentOpenApp = Intent(context, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            }
            val pendingIntent = PendingIntent.getActivity(
                context,
                NOTIFICATION_REQUEST_CODE,
                intentOpenApp,
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            )
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)
                notificationManager.createNotificationChannel(channel)
            }
            val builder = NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(context.getString(R.string.title_notification))
                .setContentText(context.getString(R.string.content_notification, dayStr))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
            notificationManager.notify(1, builder.build())
        }
    }
}
