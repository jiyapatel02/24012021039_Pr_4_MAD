package com.example.a24012021039_pr_4_mad

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder

class AlarmService : Service() {

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate() {
        super.onCreate()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val channel = NotificationChannel(
                "alarm_channel",
                "Alarm",
                NotificationManager.IMPORTANCE_HIGH
            )

            val manager =
                getSystemService(NotificationManager::class.java)

            manager.createNotificationChannel(channel)
        }

        val notification =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                Notification.Builder(this, "alarm_channel")
                    .setContentTitle("Alarm")
                    .setContentText("Alarm is ringing")
                    .setSmallIcon(R.drawable.alarm_mad)
                    .build()

            } else {

                Notification.Builder(this)
                    .setContentTitle("Alarm")
                    .setContentText("Alarm is ringing")
                    .setSmallIcon(R.drawable.alarm_mad)
                    .build()
            }

        startForeground(1, notification)

        mediaPlayer =
            MediaPlayer.create(this, R.raw.alarm)

        mediaPlayer?.isLooping = true
    }

    override fun onStartCommand(
        intent: Intent?,
        flags: Int,
        startId: Int
    ): Int {

        if (mediaPlayer != null &&
            !mediaPlayer!!.isPlaying
        ) {
            mediaPlayer!!.start()
        }

        return START_NOT_STICKY
    }

    override fun onDestroy() {

        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null

        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}