package com.example.texttospitch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CalendarView
import android.widget.TextView
import android.widget.TimePicker
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val calendar: CalendarView = findViewById(R.id.calendarView)
        val textCalendar: TextView = findViewById(R.id.calendarText)
        val timePicker: TimePicker = findViewById(R.id.timePicker)
        val textTime: TextView = findViewById(R.id.timeText)

        calendar.setOnDateChangeListener { calendarView, year, month, day ->
            textCalendar.text = "$day.$month.$year"
        }
        timePicker.setOnTimeChangedListener { timePicker, hour, minute ->
            textTime.text = "$hour:$minute"
        }

        findViewById<MaterialButton>(R.id.btnNext).setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }
}