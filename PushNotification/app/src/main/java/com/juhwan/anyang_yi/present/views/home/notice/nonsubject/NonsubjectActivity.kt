package com.juhwan.anyang_yi.present.views.home.notice.nonsubject

import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.juhwan.anyang_yi.R
import com.juhwan.anyang_yi.databinding.ActivityNonsubjectBinding
import com.juhwan.anyang_yi.present.config.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NonsubjectActivity : BaseActivity<ActivityNonsubjectBinding>(R.layout.activity_nonsubject) {
    private val viewModel: NonsubjectViewModel by viewModels()
    private lateinit var nonsubjectAdapter: NonsubjectAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        initEvent()
        viewModel.getNonsubjectNoticeList()
    }

    private fun initView(){
        binding.rvNonsubject.layoutManager = GridLayoutManager(this, 2)
        nonsubjectAdapter = NonsubjectAdapter()
        binding.rvNonsubject.adapter = nonsubjectAdapter

    }

    private fun initEvent() {
        binding.radioGroupFilter.setOnCheckedChangeListener { _, checkedId ->
            when(checkedId){
                R.id.rb_new -> nonsubjectAdapter.arrangeList(0)
                R.id.rb_deadline -> nonsubjectAdapter.arrangeList(1)
            }
        }

        viewModel.nonsubjectNoticeList.observe(this) {
            nonsubjectAdapter.setList(it)
            binding.tvNumber.text = it.size.toString()
        }

        viewModel.problem.observe(this) {
            showToastMessage(resources.getString(R.string.network_error))
        }

        binding.ivBack.setOnClickListener {
            finish()
        }
    }
}