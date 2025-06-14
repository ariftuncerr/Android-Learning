package com.example.androiduicomponent

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.View
import android.widget.TimePicker
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androiduicomponent.databinding.ActivityDateAndTimePickerBinding

class DateAndTimePickerActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDateAndTimePickerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDateAndTimePickerBinding.inflate(layoutInflater)
        val view : View = binding.root
        setContentView(view)


        //time picker
        binding.timeEditTxt.setOnClickListener { l : View ->
            val calendar = Calendar.getInstance()

            val hour = calendar.get(Calendar.HOUR)
            val minute = calendar.get(Calendar.MINUTE)

            val timePicker = TimePickerDialog(this, {_,hour,minute ->
                binding.timeEditTxt.setText("$hour:$minute")
            },hour,minute,true)

            timePicker.setTitle("Saati Seçin")
            timePicker.show()


        }

        //dateTimePicker
        binding.dateEditTxt.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePicker = DatePickerDialog(this,{_,selectedYear,selectedMonth,selectedDay->
                binding.dateEditTxt.setText("$selectedDay.${selectedMonth+1}.$selectedYear")
            },year,month,day)
            datePicker.show()
        }




    }
}