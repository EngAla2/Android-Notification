package com.example.tryandroidnotif

import android.widget.*
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.android.material.textfield.TextInputEditText
import kotlin.collections.listOf as listOf1
import kotlin.collections.mutableListOf as mutableListOf

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val id_ch = findViewById<TextView>(R.id.id_ch)
        val notif_name = findViewById<TextView>(R.id.notif_name)
        val notif_title = findViewById<TextView>(R.id.notif_title)
        val create_notif = findViewById<Button>(R.id.create_notif)
        val del_notif_ch = findViewById<Button>(R.id.del_ch)
        val notif = findViewById<Button>(R.id.Notify)
        val text = findViewById<TextView>(R.id.textView)
        val spinner = findViewById<Spinner>(R.id.spinner)
        fill_drop_down_list(this, spinner, this@MainActivity)
        create_notif.setOnClickListener {
            try{
            create_notif(
                this,
//                id= spinner.getSelectedItem().toString(),
                id=id_ch.text.toString(),
                Title=notif_title.text.toString(),
                Text=notif_name.text.toString(),
                name_ch=findViewById<TextView>(R.id.name_ch).text.toString(),
                disc_ch =findViewById<TextView>(R.id.disc_ch).text.toString(),
                activity=this@MainActivity
            )
                fill_drop_down_list(this, spinner, this@MainActivity)

            }
            catch (e: Exception){
                text.setText(e.toString())
            }
        }

        del_notif_ch.setOnClickListener {
            try{
            val intr =Intent(this@MainActivity, DeleteChannel::class.java)
                startActivity(intr)
            }
            catch (e: Exception){
                text.setText(e.toString())
            }
        }
//            delete_nitif_channel(id=spinner.getSelectedItem().toString(), activity=this@MainActivity)



        notif.setOnClickListener{
            createNotificationChannel(
                    id=spinner.getSelectedItem().toString(),
                    name=findViewById<TextView>(R.id.name_ch).text.toString(),
                    activity=this@MainActivity,
                    descriptionText =findViewById<TextView>(R.id.disc_ch).text.toString()
            )
        }
    }

    }
