package com.fizzbit.howoldareyou

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //select the button
        dateSelect.setOnClickListener { view ->
            clickDatePicker(view)  //calling the datePicker function. only view

        }
    }

    fun clickDatePicker(view: View){

        val myCalender = Calendar.getInstance()     //making a calender object
        val year = myCalender.get(Calendar.YEAR)        //fetching the year from the calender object
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener {
                    view, selectedYear, selectedMonth, selectedDayOfMonth ->

                //this is the area where we can add more codes to work
                val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"  //Pattern DD/MM/YY. Months start from 0. so we have to add 1
                tvSelectedDate.setText(selectedDate)

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate)

                val selectedDateInMinutes = theDate!!.time / 60000

                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateToMinutes = currentDate!!.time / 60000

                val differenceDateInMinutes = currentDateToMinutes - selectedDateInMinutes

                tvselectedDateInMinutes.setText(differenceDateInMinutes.toString())


            }
            ,year
            ,month
            ,day)//.show                                   //enabling the datePickerDialog

        dpd.datePicker.setMaxDate(Date().time - 86400000)           //Disable Future Date
        dpd.show()

    }
}
