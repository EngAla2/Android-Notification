package com.example.tryandroidnotif

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {
    val sharedPrefFile = "kotlinsharedpreference"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val client_side = findViewById<Button>(R.id.client)
        val server_side = findViewById<Button>(R.id.server)
        val text = findViewById<TextView>(R.id.text)
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(
            sharedPrefFile,
            Context.MODE_PRIVATE
        )


        client_side.setOnClickListener {
            startActivity(Intent(this, Client::class.java))
        }
        server_side.setOnClickListener {
            startActivity( Intent(this, ServerSide::class.java))
        }
//        text.setText(
//            sharedPreferences.getInt("id_key", 0).toString() + NotificationManagerCompat.from(
//                this
//            ).areNotificationsEnabled()
//        )


    }

}
