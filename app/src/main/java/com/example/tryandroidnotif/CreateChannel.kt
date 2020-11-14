package com.example.tryandroidnotif

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class CreateChannel : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_channel)
        val id_ch = findViewById<TextView>(R.id.id_ch)
        val create_ch = findViewById<Button>(R.id.create_channel)

        create_ch.setOnClickListener {
            try {
                createNotificationChannel(
                    id = id_ch.text.toString(),
                    name = findViewById<TextView>(R.id.name_ch).text.toString(),
                    activity = this,
                    descriptionText = findViewById<TextView>(R.id.disc_ch).text.toString()
                )
            } catch (e: Exception) {
//                text.setText(e.toString())
            }

        }
    }
}