package com.mala.newsapp.Adapter

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.mala.newsapp.R

class SpinnerAdapter(context: Context, private val data: List<Triple<String, Int, Int>>) :
    ArrayAdapter<Triple<String, Int, Int>>(context, R.layout.my_dropdown_item, data) {

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createItemView(position, convertView, parent)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createItemView(position, convertView, parent)
    }

    private fun createItemView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rowView = inflater.inflate(R.layout.my_dropdown_item, parent, false)

        val textView = rowView.findViewById<TextView>(R.id.name_of_country)
        val imageView = rowView.findViewById<ImageView>(R.id.image_of_country)

        textView.text = data[position].first
        imageView.setImageResource(data[position].second)

        return rowView
    }
}
