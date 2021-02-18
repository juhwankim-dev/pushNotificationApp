package com.juhwan.anyang_yi.fragments.setting

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juhwan.anyang_yi.R
import com.juhwan.anyang_yi.fragments.home.WebViewActivity
import kotlinx.android.synthetic.main.license_list_item.view.*

class LicenseAdapter() : RecyclerView.Adapter<LicenseAdapter.LicenseViewHolder>() {
    var items = arrayListOf<License>(
        License("inko", "mit", "Copyright (c) 2020 kimcore"),
        License("lottie", "apache", "Copyright 2018 Airbnb, Inc."),
        License("firebase-database", "firebase", " "),
        License("firebase-messaging", "firebase", " "),
        License("jsoup", "mit", "Copyright © 2009 - 2021 Jonathan Hedley (https://jsoup.org/)"),
        License("retrofit2", "apache", "Copyright 2013 Square, Inc."),
        License("rxjava2", "apache", "Copyright (c) 2016-present, RxJava Contributors."),
        License("MaterialAbout+", "mit", "Copyright (c) 2016 Arleu Cezar Vansuita Júnior"),
        License("pullrefreshlayout", "mit", "Copyright (c) 2014 baoyongzhang"),
        License("material-calendar-view", "apache", "Copyright 2017, Applandeo sp. z o.o."),
        License("AwesomeDialog", "apache", "Copyright 2020 Muhammad Nouman"),
        License("StickyTimeLine", "apache", "Copyright 2019 Jeong Seok-Won"),
        License("lottie Love Explosion Animation", "lottie", "CC by Chris Gannon")
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LicenseViewHolder {
        return LicenseViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: LicenseViewHolder, position: Int) {
        holder.txtLibraryName.text = items[position].owner
        holder.layoutLibrary.setOnClickListener {
            if(items[position].type == "firebase"){
                var intent = Intent(it.context, WebViewActivity::class.java)
                intent.putExtra("url", "https://firebase.google.com/terms?hl=ko")
                it.context.startActivity(intent)
            } else {
                var intent = Intent(it.context, ExplanationLicenseActivity::class.java)
                intent.putExtra("type", items[position].type)
                intent.putExtra("copyright", items[position].copyright)
                it.context.startActivity(intent)
            }
        }
    }

    inner class LicenseViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder
        (LayoutInflater.from(parent.context).inflate(R.layout.license_list_item, parent, false)){
        var txtLibraryName = itemView.txt_library_name!!
        var layoutLibrary = itemView.layout_library!!
    }
}