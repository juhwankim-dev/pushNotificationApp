package com.juhwan.anyang_yi.present.views.home.notice.nonsubject

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.juhwan.anyang_yi.R
import com.juhwan.anyang_yi.data.repository.InitialRepository
import com.juhwan.anyang_yi.databinding.ActivityNonsubjectBinding
import com.juhwan.anyang_yi.present.config.BaseActivity

class NonsubjectActivity : BaseActivity<ActivityNonsubjectBinding>(R.layout.activity_nonsubject) {
    private lateinit var nonsubjectAdapter: NonsubjectAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRecyclerView()

        binding.radioGroupFilter.setOnCheckedChangeListener { _, checkedId ->
            when(checkedId){
                R.id.radio_btn_new -> nonsubjectAdapter.arrangeList(0)
                R.id.radio_btn_deadline -> nonsubjectAdapter.arrangeList(1)
            }
        }

        binding.tvNumber.text = InitialRepository.apply.size.toString()
        binding.ivBack.setOnClickListener {
            finish()
        }
    }

    private fun initRecyclerView(){
        binding.rvAllApply.layoutManager = GridLayoutManager(this, 2)
        nonsubjectAdapter = NonsubjectAdapter()
        binding.rvAllApply.adapter = nonsubjectAdapter
        nonsubjectAdapter.setList(InitialRepository.apply)
    }
}