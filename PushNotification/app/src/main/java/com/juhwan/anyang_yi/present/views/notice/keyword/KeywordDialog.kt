package com.juhwan.anyang_yi.present.views.notice.keyword

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import com.juhwan.anyang_yi.R

class KeywordDialog(
    context: Context,
    listener: KeywordActivity
) {

    private val mCallback = listener
    private val context = context
    private val dialog = Dialog(context)

    fun myDig(keyword: String) {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)

        val params: ViewGroup.LayoutParams? = dialog.window!!.attributes
        val deviceWidth = size.x
        params!!.width = (deviceWidth * 0.9).toInt()
        dialog.window!!.attributes = params as WindowManager.LayoutParams

        dialog.setContentView(R.layout.dialog_keyword)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)

        val tvInfo = dialog.findViewById<TextView>(R.id.tv_info)
        val btnCancel = dialog.findViewById<TextView>(R.id.btn_cancel)
        val btnSignUp = dialog.findViewById<TextView>(R.id.btn_sign_up)

        tvInfo.text = "\'$keyword\'(은)는\n공지에 등록된 이력이 없는 키워드입니다. \n그래도 등록하시겠습니까?"

        btnCancel.setOnClickListener {
            dialog.dismiss()
        }

        btnSignUp.setOnClickListener {
            mCallback.signUpListener(keyword)
            dialog.dismiss()
        }

        dialog.show()
    }
}