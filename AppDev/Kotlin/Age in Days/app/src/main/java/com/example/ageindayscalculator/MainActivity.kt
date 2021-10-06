package com.example.ageindayscalculator

import android.app.DatePickerDialog
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
        var btn = findViewById<Button>(R.id.button)

        btn.setOnClickListener { view ->
            clickbtn(view)
          }
    }
    fun clickbtn(view: View) {
        val mycal = Calendar.getInstance()
        val year = mycal.get(Calendar.YEAR)
        val month = mycal.get(Calendar.MONTH)
        val day = mycal.get(Calendar.DAY_OF_MONTH)
        val dpd =DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener {
                view, selectedyear, selectedmonth, selecteddayOfMonth ->
                val selectedDate="$selecteddayOfMonth/${selectedmonth+1}/$selectedyear"
                val selecteddate = findViewById<TextView>(R.id.selecteddate)
                selecteddate.setText(selectedDate)
                val sdf=SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val thedate = sdf.parse(selectedDate)
                val dateinmin = thedate!!.time/60000
                val currentdate =sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentdateinmin = currentdate!!.time/60000
                val diff = (currentdateinmin-dateinmin)/(60*24)
                val ageindays =findViewById<TextView>(R.id.age)
                ageindays.setText(diff.toString())
            }
            ,year
            ,month
            ,day)
        dpd.datePicker.setMaxDate(Date().time-86400000)
        dpd.show()

    }

}