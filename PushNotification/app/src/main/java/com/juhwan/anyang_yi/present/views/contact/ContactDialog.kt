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
import android.widget.TextView
import android.widget.Toast
import com.juhwan.anyang_yi.R
import com.juhwan.anyang_yi.databinding.DialogContactBinding
import java.lang.Exception

class ContactDialog(context: Context) {

    private val context = context
    private val dialog = Dialog(context)

    fun myDig(
        lClass: String,
        msClass: String,
        tel: String
    ) {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)

        val params: ViewGroup.LayoutParams? = dialog.window!!.attributes
        val deviceWidth = size.x
        params!!.width = (deviceWidth * 0.9).toInt()
        dialog.window!!.attributes = params as WindowManager.LayoutParams

        dialog.setContentView(R.layout.dialog_contact)
        //dialog.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)

        val binding = DialogContactBinding.inflate(LayoutInflater.from(context))

        binding.tvLClass.text = lClass
        binding.tvMClass.text = msClass

        if(tel.length == 3){
            binding.tvTel.text = "031-467-0700\n+ 내선번호($tel)"
        } else {
            binding.tvTel.text = tel.replace(",", ", ")
        }

        binding.tvCall.setOnClickListener {
            var fullTel = parsingTel(tel)

            try{
                context.startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:$fullTel")))
            } catch (e: Exception){
                Toast.makeText(context, "사용하시는 모델은 통화를 지원하지 않습니다.", Toast.LENGTH_SHORT).show()
            }

        }

        binding.tvClose.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun parsingTel(tel: String): String {
        var cleanTel = tel.replace("[ㄱ-힣]", "").replace("-","")

        if(cleanTel[0].equals("0")){ // 서해안발전연구소
            return cleanTel.substring(0, 10)
        } else if(cleanTel.length == 3){
            return "0314670700"
        }
        return "031" + cleanTel.substring(0, 7)
    }
}