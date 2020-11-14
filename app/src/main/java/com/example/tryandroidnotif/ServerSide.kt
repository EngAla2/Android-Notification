package com.example.tryandroidnotif

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView

class ServerSide : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_server_side)
        val sharedPrefFile = "kotlinsharedpreference"

        val id_ch = findViewById<TextView>(R.id.id_ch)
        val notif_name = findViewById<TextView>(R.id.notif_name)
        val notif_title = findViewById<TextView>(R.id.notif_title)
        val create_notif = findViewById<Button>(R.id.create_notif)
        val del_notif_ch = findViewById<Button>(R.id.del_ch)
        val notif = findViewById<Button>(R.id.Notify)
        val text = findViewById<TextView>(R.id.textView)
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
                text.setText(e.toString())
            }
        }

        del_notif_ch.setOnClickListener {
                val intr = Intent(this, DeleteChannel::class.java)
                startActivity(intr)
        }


        notif.setOnClickListener {
            try {
                createNotificationChannel(
                    id = id_ch.text.toString(),
                    name = findViewById<TextView>(R.id.name_ch).text.toString(),
                    activity = this,
                    descriptionText = findViewById<TextView>(R.id.disc_ch).text.toString()
                )
                fill_drop_down_list(this, spinner, this)
            } catch (e: Exception) {
                text.setText(e.toString())
            }
        }
    }
}