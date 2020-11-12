package com.example.tryandroidnotif

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView

class DeleteChannel : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_channel)

        val del_notif_ch = findViewById<Button>(R.id.delete)
        val spinner = findViewById<Spinner>(R.id.spinner)
        val text = findViewById<TextView>(R.id.textView)
        fill_drop_down_list(this, spinner, this)

        del_notif_ch.setOnClickListener {
            try{
            delete_nitif_channel(id=spinner.getSelectedItem().toString(), activity=this)
            startActivity(Intent(this, MainActivity::class.java))
            }
            catch (e: Exception){
                text.setText(e.toString())
            }
        }
    }
}