package com.example.agecalculator

import android.app.DatePickerDialog
import android.appwidget.AppWidgetHostView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val button = findViewById(R.id.button) as Button
        button.setOnClickListener { view ->
            clickDatePicker(view)



        }
    }

    fun clickDatePicker(view: View) {

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->
                Toast.makeText(this, "The Chosen Year is $selectedYear, The month is ${selectedMonth+1}, The day is $selectedDayOfMonth", Toast.LENGTH_LONG).show()
                val selectedDate= "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
                val tvSelectedDate = findViewById(R.id.tvSelectedDate) as TextView
                tvSelectedDate.setText(selectedDate)
                val sdf=SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)

                val theDate=sdf.parse(selectedDate)
                val selectedDateInMinutes= theDate!!.time/60000
                val currentDate=sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateInMinutes= currentDate!!.time/60000
                val differenceInMinutes= currentDateInMinutes-selectedDateInMinutes
                val tvSelectedDateInMnutes = findViewById(R.id.tvSelectedDateInMinutes) as TextView
                tvSelectedDateInMnutes.setText(differenceInMinutes.toString())



            },
            year,
            month,
            day).show()
    }
}