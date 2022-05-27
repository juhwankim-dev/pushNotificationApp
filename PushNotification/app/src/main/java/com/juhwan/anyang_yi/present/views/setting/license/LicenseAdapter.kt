package com.juhwan.anyang_yi.present.views.setting.license

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juhwan.anyang_yi.databinding.ItemLicenseBinding
import com.juhwan.anyang_yi.present.views.notice.WebViewActivity
import com.juhwan.anyang_yi.present.views.setting.license.explanation.ExplanationLicenseActivity

class LicenseAdapter(licenseList: List<License>) : RecyclerView.Adapter<LicenseAdapter.LicenseViewHolder>() {
    var items = licenseList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : LicenseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemLicenseBinding.inflate(layoutInflater, parent, false)
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
            binding.tvLibraryName.text = license.owner
            binding.layoutLibrary.setOnClickListener {
                if(license.type == "firebase"){
                    var intent = Intent(it.context, WebViewActivity::class.java)
                    intent.putExtra("url", "https://firebase.google.com/terms?hl=ko")
                    it.context.startActivity(intent)
                } else {
                    var intent = Intent(it.context, ExplanationLicenseActivity::class.java)
                    intent.putExtra("type", license.type)
                    intent.putExtra("copyright", license.copyright)
                    it.context.startActivity(intent)
                }
            }
        }
    }
}