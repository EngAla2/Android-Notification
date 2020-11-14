package com.example.tryandroidnotif

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.icu.text.Transliterator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.view.ViewParent
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.app.NotificationManagerCompat
import java.lang.Exception
import java.text.FieldPosition

class Client : AppCompatActivity() {
    val sharedPrefFile = "kotlinsharedpreference"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client)
        val btnSave = findViewById<Button>(R.id.save)
        val text = findViewById<TextView>(R.id.textView4)


        val androidId = Settings.Secure.getString(
            contentResolver,
            Settings.Secure.ANDROID_ID
        )
        text.setText("***"+androidId.substring(6,9)+"****")

        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()


        btnSave.setOnClickListener(View.OnClickListener {
            startActivity( Intent(this, MainActivity::class.java))
        })

        if  (!NotificationManagerCompat.from(this).areNotificationsEnabled() and sharedPreferences.getBoolean("first_time", true)){
            val builder = AlertDialog.Builder(this)
            builder.setTitle("NOTIFICATIONS")
            builder.setMessage("Your NOTIFICATIONS are disabled, allow them from settings!")
            builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                Toast.makeText(applicationContext,
                    android.R.string.yes, Toast.LENGTH_SHORT).show()
                    val intent: Intent = Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS)
                    .putExtra(Settings.EXTRA_APP_PACKAGE, this.getPackageName())
                    .putExtra(Settings.EXTRA_CHANNEL_ID, "id")
                    startActivity(intent)
            }
            builder.setNegativeButton(android.R.string.no) { dialog, which ->
                Toast.makeText(applicationContext,
                    android.R.string.no, Toast.LENGTH_SHORT).show()
            }
            builder.setNeutralButton("Maybe") { dialog, which ->
                Toast.makeText(applicationContext,
                    "Maybe", Toast.LENGTH_SHORT).show()
            }
            builder.show()
            editor.putBoolean("first_time", false)
        }

        val listView = findViewById<ListView>(R.id.dynamic_list)

        var listViewAdapter = ListViewModelAdapter(this, getListViewModelList())
        listView.adapter = listViewAdapter
        listView.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, id ->
        }

     }

    fun <ArrayList> getListViewModelList(): ArrayList {
        var listViewModelArrayList = ArrayList<ListViewModel>()
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(
            sharedPrefFile,
            Context.MODE_PRIVATE
        )
        with(NotificationManagerCompat.from(this)) {
            var Notifications = getNotificationChannels()
            var notifications = mutableListOf<String>()
            for (a in Notifications) {
                notifications.add(a.id)
            }

            for (id in notifications)
                listViewModelArrayList.add(ListViewModel(1, id, sharedPreferences.getBoolean(id, false)))


        }
        return listViewModelArrayList as ArrayList
    }
}