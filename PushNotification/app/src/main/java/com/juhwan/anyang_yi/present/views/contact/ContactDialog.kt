package com.juhwan.anyang_yi.present.views.contact

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import com.juhwan.anyang_yi.R
import com.juhwan.anyang_yi.databinding.DialogContactBinding
import com.juhwan.anyang_yi.domain.model.Contact

class ContactDialog(context: Context) {
    private val context = context
    private val dialog = Dialog(context)

    fun createDialog(contact: Contact) {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)

        val binding = DialogContactBinding.inflate(LayoutInflater.from(context))
        binding.contact = contact

        val params: ViewGroup.LayoutParams? = dialog.window!!.attributes
        val deviceWidth = size.x
        params!!.width = (deviceWidth * 0.9).toInt()
        dialog.window!!.attributes = params as WindowManager.LayoutParams

        dialog.setContentView(binding.root)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)

        binding.tvCall.setOnClickListener {
            try {
                context.startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:${contact.representTel}")))
            } catch (e: Exception){
                Toast.makeText(context, context.resources.getText(R.string.call_error), Toast.LENGTH_SHORT).show()
            }
        }

        binding.tvClose.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
}