package com.juhwan.anyang_yi.present.views.setting.inquiry

import android.app.Dialog
import android.content.Context
import android.graphics.Point
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowManager
import com.juhwan.anyang_yi.databinding.DialogInquiryBinding
import com.juhwan.anyang_yi.present.views.setting.SettingFragment

class InquiryDialog(
    val context: Context,
    val listener: SettingFragment
) {
    private val dialog = Dialog(context)

    fun createDialog() {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)

        val binding = DialogInquiryBinding.inflate(LayoutInflater.from(context))
        val params: ViewGroup.LayoutParams? = dialog.window!!.attributes
        val deviceWidth = size.x
        params!!.width = (deviceWidth * 0.9).toInt()
        dialog.window!!.attributes = params as WindowManager.LayoutParams

        dialog.setContentView(binding.root)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)

        binding.ivCancel.setOnClickListener {
            binding.etInquiry.setText("")
            dialog.hide()
        }

        binding.tvSend.setOnClickListener {
            listener.sendClickListener(binding.etInquiry.text.toString())
            binding.etInquiry.setText("")
            dialog.hide()
        }

        binding.etInquiry.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(str: CharSequence?, p1: Int, p2: Int, count: Int) {
                var input = str ?: ""

                if (input.length <= 150) {
                    binding.tvCount.text = "${input.length} / 150"
                } else {
                    binding.etInquiry.setText(binding.etInquiry.text.substring(0, 150))
                    binding.etInquiry.setSelection(binding.etInquiry.text.length)
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })

        dialog.show()
    }
}