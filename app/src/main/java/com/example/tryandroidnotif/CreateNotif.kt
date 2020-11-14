package com.example.tryandroidnotif

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView

class CreateNotif : AppCompatActivity() {
    val sharedPrefFile = "kotlinsharedpreference"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_notif)
        val notif_name = findViewById<TextView>(R.id.notif_name)
        val notif_title = findViewById<TextView>(R.id.notif_title)
        val create_notif = findViewById<Button>(R.id.create_notif)
        val spinner = findViewById<Spinner>(R.id.spinner)
        fill_drop_down_list(this, spinner, this)

        val sharedPreferences: SharedPreferences = this.getSharedPreferences(
            sharedPrefFile,
            Context.MODE_PRIVATE
        )

        create_notif.setOnClickListener {
            val id = spinner.getSelectedItem().toString()
            try {
                if (sharedPreferences.getBoolean(id, false)) {
                    create_notif(
                        this,
                        id = spinner.getSelectedItem().toString(),
                        Title = notif_title.text.toString(),
                        Text = notif_name.text.toString()
                    )
                }
            } catch (e: Exception) {
//                text.setText(e.toString())
            }
        }
    }
}