package com.juhwan.anyang_yi.present.views.setting.license

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juhwan.anyang_yi.databinding.ItemLicenseBinding
import com.juhwan.anyang_yi.domain.model.License
import com.juhwan.anyang_yi.present.views.home.WebViewActivity

class LicenseAdapter(licenseList: List<License>) : RecyclerView.Adapter<LicenseAdapter.LicenseViewHolder>() {
    var items = licenseList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : LicenseViewHolder {
        val binding = ItemLicenseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LicenseViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: LicenseViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class LicenseViewHolder(private val binding: ItemLicenseBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(license: License) {
            binding.license = license
            binding.layoutLibrary.setOnClickListener {
                if(license.webLink != null) {
                    var intent = Intent(it.context, WebViewActivity::class.java)
                    intent.putExtra("url", license.webLink)
                    it.context.startActivity(intent)
                }
            }
        }
    }
}