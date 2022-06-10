package com.juhwan.anyang_yi.present.views.setting.appnotice

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.juhwan.anyang_yi.R
import com.juhwan.anyang_yi.databinding.ActivityAppNoticeBinding
import com.juhwan.anyang_yi.domain.model.AppNotice
import com.juhwan.anyang_yi.present.config.ApplicationClass.Companion.databaseReference
import com.juhwan.anyang_yi.present.config.BaseActivity

class AppNoticeActivity : BaseActivity<ActivityAppNoticeBinding>(R.layout.activity_app_notice) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        initEvent()
    }

    private fun initView() {
        binding.lottieViewSheep.visibility = View.VISIBLE
        binding.lottieViewSheep.playAnimation()

        binding.rvAppNotice.layoutManager = LinearLayoutManager(applicationContext)
    }

    private fun initEvent() {
        databaseReference
            .child("notices")
            .addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {

                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    var appNoticeList = arrayListOf<AppNotice>()

                    for(child in snapshot.children) {
                        var map = child.value as HashMap<String, String>

                        appNoticeList.add(
                            AppNotice(
                                content = map["content"] ?: "",
                                date = map["date"] ?: "",
                                title = map["title"] ?: "",
                                isNew = map["isNew"] == "true",
                            )
                        )
                    }

                    val appNoticeAdapter = AppNoticeAdapter()
                    binding.rvAppNotice.adapter = appNoticeAdapter
                    appNoticeAdapter.setList(appNoticeList)
                    binding.lottieViewSheep.visibility = View.GONE
                }
            })

        binding.ivBack.setOnClickListener {
            finish()
        }
    }
}
