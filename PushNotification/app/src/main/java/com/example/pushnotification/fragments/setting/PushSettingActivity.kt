package com.example.pushnotification.fragments.setting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.pushnotification.R
import kotlinx.android.synthetic.main.activity_push_setting.*

class PushSettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_push_setting)

        switch_push_notification.setOnCheckedChangeListener { buttonView, isChecked ->
            Toast.makeText(this, "현재 상태는 $isChecked", Toast.LENGTH_SHORT).show()
        }
    }
}
