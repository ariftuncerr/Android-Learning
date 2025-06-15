package com.example.androiduicomponent.ux

import android.graphics.Color
import android.os.Bundle
import android.provider.CalendarContract
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androiduicomponent.R
import com.example.androiduicomponent.databinding.ActivityUxactivityBinding
import com.google.android.material.snackbar.Snackbar

class UXActivity : AppCompatActivity() {
    private lateinit var binding : ActivityUxactivityBinding

    //alert Dialog part
    private lateinit var specialAlertView : View
    private var userAge : Int? = null
    private lateinit var inputAge : EditText



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityUxactivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //pop up menu
        binding.popUpMenuBtn.setOnClickListener {

            val popupMenu = PopupMenu(this, binding.popUpMenuBtn)
            popupMenu.menuInflater.inflate(R.menu.popupmenu, popupMenu.menu)

            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.firstItem -> {
                        Toast.makeText(this, "First item clicked", Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.secondItem -> {
                        Toast.makeText(this, "Second item clicked", Toast.LENGTH_SHORT).show()
                        true
                    }
                    else -> false
                }
            }
            popupMenu.show()
        }


        var alertDialog : AlertDialog? = null
        //alert Normal
        binding.alertNormalButton.setOnClickListener {
                alertDialog = AlertDialog.Builder(this)
                .setTitle("Alert")
                .setMessage("Are you sure")
                .setIcon(R.drawable.baseline_directions_car_24)
                .setPositiveButton ("Yes") { dialog,_ ->
                    Toast.makeText(applicationContext,"User said yes", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton ("No") { dialog,_ ->
                    Toast.makeText(applicationContext,"User said no", Toast.LENGTH_SHORT).show()

                }
                .show()
        }

        //special alert
        binding.alertSpecilButton.setOnClickListener {
             specialAlertView = layoutInflater.inflate(R.layout.special_alert_layout, null)
             inputAge = specialAlertView.findViewById<EditText>(R.id.ageTxt)

            AlertDialog.Builder(this)
                .setTitle("Age")
                .setMessage("Are you sure")
                .setView(specialAlertView)
                .setPositiveButton ("OK"){dialog,_ ->

                    userAge = inputAge.text.toString().toIntOrNull()
                    Toast.makeText(applicationContext,"User Age $userAge", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton ("Cancel") {dialog,_ ->
                    dialog.dismiss()
                }
                .show()
        }

        //snackBarNormal
        binding.snackBarNormal.setOnClickListener { view : View ->
            Snackbar.make(view,"Snack Bar Normal Tiklanildi", Snackbar.LENGTH_SHORT).show()
        }

        //snackBarFeedBack
        binding.snackBarFeedback.setOnClickListener { view : View ->
            Snackbar.make(view,"Do You Want to return", Snackbar.LENGTH_INDEFINITE)
                .setAction("Yes") {view : View ->
                    Snackbar.make(view,"you returned successfully", Snackbar.LENGTH_SHORT).show()
                }.show()
        }

        //special Snack Bar

        binding.snackBarSpecial.setOnClickListener { view ->
            val snackBar = Snackbar.make(view,"Special Snack Bar", Snackbar.LENGTH_INDEFINITE)

            snackBar.setAction ("Click Here"){ view ->
                Toast.makeText(applicationContext,"You are Crazy", Toast.LENGTH_SHORT).show()

            }

            snackBar.setActionTextColor(Color.RED)
            snackBar.setBackgroundTint(Color.BLUE)
            snackBar.show()
        }







    }

}