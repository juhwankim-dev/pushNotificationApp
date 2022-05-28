package com.juhwan.anyang_yi.present.views.home.notice.ari

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.juhwan.anyang_yi.R
import com.juhwan.anyang_yi.databinding.ActivityAriBinding
import com.juhwan.anyang_yi.present.config.BaseActivity

class AriActivity : BaseActivity<ActivityAriBinding>(R.layout.activity_ari) {

    private val model: AriViewModel by viewModels()
    private lateinit var ariAdapter: AriAdapter
    private var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initRecyclerView()
        model.loadAriNotice(page)

        binding.ivBack.setOnClickListener {
            finish()
        }

        model.getAll().observe(this, Observer {
            ariAdapter.setList(it.ariNotice)
            ariAdapter.notifyItemRangeInserted((page - 1) * 10, 10)
        })

        binding.rvAllAriNotice.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val lastVisibleItemPosition =
                    (recyclerView.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition()
                val itemTotalCount = recyclerView.adapter!!.itemCount-1

                if (!binding.rvAllAriNotice.canScrollVertically(1) &&
                    lastVisibleItemPosition == itemTotalCount
                ) {
                    ariAdapter.deleteLoading()
                    model.loadAriNotice(++page)
                }

            }
        })
    }

    private fun initRecyclerView(){
        binding.rvAllAriNotice.layoutManager = LinearLayoutManager(this)
        ariAdapter = AriAdapter()
        binding.rvAllAriNotice.adapter = ariAdapter
    }
}