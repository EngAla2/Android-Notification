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


        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()

        btnSave.setOnClickListener(View.OnClickListener {
//            val id:Int = Integer.parseInt(inputId.text.toString())
//            val name:String = inputName.text.toString()
            editor.putInt("id_key",123456)
            editor.putString("id",androidId)
            var listViewModelArrayList = ArrayList<ListViewModel>()
            text.setText(listViewModelArrayList.toString())
            editor.apply()
            editor.commit()

//            startActivity( Intent(this, MainActivity::class.java))
        })

        if  (!NotificationManagerCompat.from(this).areNotificationsEnabled() and sharedPreferences.getBoolean("first_time", true)){

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Androidly Alert")
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
        try{
        listView.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, id ->
//            val item = parent.getItemAtPosition(position) as ListViewModel
            text.setText("jhgjkf"+text.text.toString() + view.toString()+position.toString() + adapterView.toString())
        }}catch (e :Exception){
            text.setText("jjkcljkvcflnk"+e.toString())
        }

//        listView.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, id ->
//            val productListToDetail = Intent(this, ProductDetailActivity::class.java)
//            productListToDetail.putExtra("title",platoList[position].nombre)
//            productListToDetail.putExtra("descripcion",platoList[position].descripcion)
//            startActivity(productListToDetail)
//        }
        }

    fun <ArrayList> getListViewModelList(): ArrayList {
        var listViewModelArrayList = ArrayList<ListViewModel>()
        listViewModelArrayList.add(ListViewModel(1, "Afghanistan", true))
        listViewModelArrayList.add(ListViewModel(2, "Australia", false))

        return listViewModelArrayList as ArrayList
    }
//    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//        var item:String = parent?.getItemAtPosition(position) as String
//        text.setText("jhgjkf"+text.text.toString() + view.toString()+position.toString() + adapterView.toString())
//
//    }

}