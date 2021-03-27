package com.juhwan.anyang_yi.ui.setting.appnotice

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.juhwan.anyang_yi.databinding.ActivityAppNoticeBinding

class AppNoticeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAppNoticeBinding

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        binding = ActivityAppNoticeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lottieViewSheep.visibility = View.VISIBLE
        binding.lottieViewSheep.playAnimation()

        binding.rvAppNotice.layoutManager = LinearLayoutManager(applicationContext)

        FirebaseDatabase.getInstance().reference
            .child("notices")
            .addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {

                }

                override fun onDataChange(p0: DataSnapshot) {
                    var notices = arrayListOf<AppNotice>()

                    p0.children.forEach {
                        var content = " "
                        var date = " "
                        var title = " "
                        var isNew = " "
                        it.children.forEach {notice ->
                            when(notice.key){
                                "content" -> content = notice.value.toString()
                                "date" -> date = notice.value.toString()
                                "title" -> title = notice.value.toString()
                                "isNew" -> isNew = notice.value.toString()
                            }
                        }

                        notices.add(0, AppNotice(content, date, title, isNew))
                    }

                    binding.rvAppNotice.adapter = AppNoticeAdapter(notices)
                    binding.lottieViewSheep.visibility = View.GONE
                }
            })

        binding.ivBack.setOnClickListener {
            finish()
        }
    }
}
