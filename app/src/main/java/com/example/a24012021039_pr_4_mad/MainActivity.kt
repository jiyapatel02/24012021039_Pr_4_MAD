package com.example.a24012021039_pr_4_mad

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.card.MaterialCardView
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    lateinit var textAlarm: TextView
    lateinit var cardSetAlarm: MaterialCardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        textAlarm =findViewById<TextView>(R.id.txtAlarmTime)
        cardSetAlarm=findViewById(R.id.card1)
        cardSetAlarm.visibility = View.GONE
        findViewById<Button>(R.id.btnCreate).setOnClickListener {
            showTimeDialog()
        }
        findViewById<Button>(R.id.btnCancel).setOnClickListener {

        }
    }
    private fun showTimeDialog(){
        val clar: Calendar= Calendar.getInstance()
        val h: Int= clar.get(Calendar.HOUR_OF_DAY)
        val a: Int= clar.get(Calendar.MINUTE)
        val picker= TimePickerDialog(this,
            {tp,hour,minute->sendDialogToActivity(hour,minute)},
            h,a,false)

        picker.show()

    }
    private fun sendDialogToActivity(hour:Int,minute:Int){
        val alarmCalendar= Calendar.getInstance()
        val year: Int= alarmCalendar.get(Calendar.YEAR)
        val month: Int= alarmCalendar.get(Calendar.MONTH)
        val date: Int= alarmCalendar.get(Calendar.DATE)
        alarmCalendar.set(year,month,date,hour,minute,0)
        if (setalarm(alarmCalendar.timeInMillis, AlarmBroadcastReceiver.START_VAL)) {
            textAlarm.text = "$hour:$minute"
            cardSetAlarm.visibility = View.VISIBLE
        }

    }
    fun setalarm(milli: Long,str: String): Boolean{
        val intent= Intent(this, AlarmBroadcastReceiver::class.java)
        intent.putExtra(AlarmBroadcastReceiver.SERVICE_KEY,str)
        val pendingIntent= PendingIntent.getBroadcast(applicationContext,23245,intent,
            PendingIntent.FLAG_MUTABLE)
        val alarmManager=getSystemService(ALARM_SERVICE)as AlarmManager
            if (str== AlarmBroadcastReceiver.START_VAL){
                if (android.os.Build.VERSION.SDK_INT>=android.os.Build.VERSION_CODES.S){
                   if (alarmManager.canScheduleExactAlarms()){
                       alarmManager.setExact(AlarmManager.RTC_WAKEUP,milli,pendingIntent)
                       return true
                   }
                   else {
                       Toast.makeText(this, "Can't schedule alarm", Toast.LENGTH_SHORT).show()
                       val intentSettings= Intent(
                           Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM,
                           "package:$packageName".toUri()
                       )
                           startActivity(intentSettings)
                       }
                       return false
                   }else{
                       alarmManager.setExact(AlarmManager.RTC_WAKEUP,milli,pendingIntent)
                        return true
                    }
            }
        return false
    }
}