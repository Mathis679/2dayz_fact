package com.example.a2dayzfact.core

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
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
        val dayStr = SimpleDateFormat("dd MMMM", Locale.getDefault()).format(Calendar.getInstance())
        context?.let {
            val intentOpenApp = Intent(context, MainActivity::class.java)
            intentOpenApp.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            val pendingIntent = PendingIntent.getActivity(
                context,
                NOTIFICATION_REQUEST_CODE,
                intent,
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            )
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
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