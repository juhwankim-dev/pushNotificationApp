package com.juhwan.anyang_yi.ui.notice.all

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.recyclerview.widget.GridLayoutManager
import com.juhwan.anyang_yi.R
import com.juhwan.anyang_yi.databinding.ActivityAllApplyBinding
import com.juhwan.anyang_yi.repository.InitialRepository

class AllApplyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAllApplyBinding
    private lateinit var allApplyAdapter: AllApplyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllApplyBinding.inflate(layoutInflater)
        setContentView(binding.root)

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