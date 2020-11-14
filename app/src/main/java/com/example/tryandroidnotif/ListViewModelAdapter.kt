package com.example.tryandroidnotif

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.TextView
import com.example.tryandroidnotif.ListViewModel
import com.example.tryandroidnotif.R

class ListViewModelAdapter(val context: Context, val listModelArrayList: ArrayList<ListViewModel>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val view: View?
        val vh: ViewHolder

        if (convertView == null) {
            val layoutInflater = LayoutInflater.from(context)
            view = layoutInflater.inflate(R.layout.rows, parent, false)
            vh = ViewHolder(view)
            view.tag = vh
        } else {
            view = convertView
            vh = view.tag as ViewHolder
        }

        vh.tvTitle.text = listModelArrayList[position].title
        vh.tvContent.isChecked = listModelArrayList[position].content!!

        vh.tvContent.setOnClickListener {
            vh.tvTitle.text ="kmbkbjbbkbj" + "Row :$position"

        }

        return view
    }

    override fun getItem(position: Int): Any {
        return listModelArrayList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return listModelArrayList.size
    }
}

private class ViewHolder(view: View?) {
    val tvTitle: TextView = view?.findViewById<TextView>(R.id.tvTitle) as TextView
    val tvContent = view?.findViewById<CheckBox>(R.id.tvContent) as CheckBox
}