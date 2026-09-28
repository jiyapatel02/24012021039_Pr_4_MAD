package com.example.a24012021039_pr_4_mad

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    private lateinit var alarmManager: AlarmManager

    private lateinit var btnCreateAlarm: MaterialButton
    private lateinit var btnCancelAlarm: MaterialButton
    private lateinit var txtCurrentTime: TextView
    private lateinit var txtAlarmTime: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        btnCreateAlarm = findViewById(R.id.btnCreate)
        btnCancelAlarm = findViewById(R.id.btnCancel)
        txtCurrentTime = findViewById(R.id.txtTime)
        txtAlarmTime = findViewById(R.id.txtAlarmTime)

        alarmManager =
            getSystemService(ALARM_SERVICE) as AlarmManager

        val currentTime = Calendar.getInstance()

        txtCurrentTime.text =
            String.format(
                "%02d:%02d",
                currentTime.get(Calendar.HOUR_OF_DAY),
                currentTime.get(Calendar.MINUTE)
            )

        btnCreateAlarm.setOnClickListener {

            val time = Calendar.getInstance()

            TimePickerDialog(
                this,
                { _, hour, minute ->

                    time.set(Calendar.HOUR_OF_DAY, hour)
                    time.set(Calendar.MINUTE, minute)
                    time.set(Calendar.SECOND, 0)
                    time.set(Calendar.MILLISECOND, 0)

                    if (time.timeInMillis <= System.currentTimeMillis()) {
                        time.add(Calendar.DAY_OF_YEAR, 1)
                    }

                    txtAlarmTime.text =
                        String.format(
                            "%02d:%02d",
                            hour,
                            minute
                        )

                    val intent = Intent(
                        this,
                        AlarmBroadcastReceiver::class.java
                    )

                    val pendingIntent =
                        PendingIntent.getBroadcast(
                            this,
                            0,
                            intent,
                            PendingIntent.FLAG_UPDATE_CURRENT or
                                    PendingIntent.FLAG_IMMUTABLE
                        )

                    alarmManager.setExactAndAllowWhileIdle(
                        AlarmManager.RTC_WAKEUP,
                        time.timeInMillis,
                        pendingIntent
                    )

                    Toast.makeText(
                        this,
                        "Alarm Set",
                        Toast.LENGTH_SHORT
                    ).show()

                },
                currentTime.get(Calendar.HOUR_OF_DAY),
                currentTime.get(Calendar.MINUTE),
                false
            ).show()
        }

        btnCancelAlarm.setOnClickListener {

            val intent = Intent(
                this,
                AlarmBroadcastReceiver::class.java
            )

            val pendingIntent =
                PendingIntent.getBroadcast(
                    this,
                    0,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT or
                            PendingIntent.FLAG_IMMUTABLE
                )

            alarmManager.cancel(pendingIntent)

            stopService(
                Intent(this, AlarmService::class.java)
            )

            txtAlarmTime.text = "00 : 00"

            Toast.makeText(
                this,
                "Alarm Cancelled",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}