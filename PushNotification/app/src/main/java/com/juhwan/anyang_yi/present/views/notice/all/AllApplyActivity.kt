package com.juhwan.anyang_yi.present.views.notice.all

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.juhwan.anyang_yi.R
import com.juhwan.anyang_yi.databinding.ActivityAllApplyBinding
import com.juhwan.anyang_yi.data.repository.InitialRepository
import com.juhwan.anyang_yi.present.config.BaseActivity

class AllApplyActivity : BaseActivity<ActivityAllApplyBinding>(R.layout.activity_all_apply) {
    private lateinit var allApplyAdapter: AllApplyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRecyclerView()

        binding.radioGroupFilter.setOnCheckedChangeListener { _, checkedId ->
            when(checkedId){
                R.id.radio_btn_new -> allApplyAdapter.arrangeList(0)
                R.id.radio_btn_deadline -> allApplyAdapter.arrangeList(1)
            }
        }

        binding.tvNumber.text = InitialRepository.apply.size.toString()
        binding.ivBack.setOnClickListener {
            finish()
        }
    }

    private fun initRecyclerView(){
        binding.rvAllApply.layoutManager = GridLayoutManager(this, 2)
        allApplyAdapter = AllApplyAdapter()
        binding.rvAllApply.adapter = allApplyAdapter
        allApplyAdapter.setList(InitialRepository.apply)
    }
}