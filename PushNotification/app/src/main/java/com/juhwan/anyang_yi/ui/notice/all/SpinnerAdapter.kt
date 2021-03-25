package com.juhwan.anyang_yi.ui.notice.all

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.juhwan.anyang_yi.R

class SpinnerAdapter(context: Context, items: Array<String>) : ArrayAdapter<String>(context, 0, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, convertView, parent)
    }

    private fun initView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_spinner, parent, false)
        val tvSpinnerOption = view.findViewById<TextView>(R.id.tv_spinner_option)
        val item = getItem(position)

        tvSpinnerOption.text = item

        return view
    }
}