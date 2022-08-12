package com.project1.dobcalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.Year
import java.util.*

class MainActivity : AppCompatActivity() {

    private var txtSelectedDate : TextView? = null
    private var txtAgeInMinutes : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker : Button = findViewById(R.id.btnDatePicker)

        txtSelectedDate = findViewById(R.id.txtSelectedDate)
        txtAgeInMinutes = findViewById(R.id.txtAgeInMinutes)

        btnDatePicker.setOnClickListener{

            clickDatePicker()

        }
    }
    private fun clickDatePicker() {
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{ view, year, month, day ->

                Toast.makeText(this,
                    "$day-${month+1}-$year",Toast.LENGTH_SHORT).show()

                val selectedDate = "$day-${month+1}-$year"
                txtSelectedDate?.text = selectedDate

                val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)

                val theDate = sdf.parse(selectedDate)
                theDate?.let {
                    val selectedDateInMinutes = theDate.time / 60000

                    val  currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let {
                        val currentDateInMinutes = currentDate.time/60000
                        val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                        txtAgeInMinutes?.text = differenceInMinutes.toString()
                    }
                }
            },
        year,
        month,
        day)
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
            dpd.show()
    }


}