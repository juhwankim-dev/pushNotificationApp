package com.juhwan.anyang_yi.ui.notice.menu

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.juhwan.anyang_yi.databinding.FragmentAriNoticeBinding
import com.juhwan.anyang_yi.network.AriNotice

class AriNoticeFragment : Fragment(),
    AriUpdateListener {

    private var binding: FragmentAriNoticeBinding? = null
    private val model: AriNoticeViewModel by viewModels()
    private lateinit var adapter: AriNoticeAdapter
    private var page = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAriNoticeBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        model.getAriNotices(page, this)

    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    private fun initRecyclerView(){
        binding!!.rvAriNotice.layoutManager = LinearLayoutManager(context)
        adapter = AriNoticeAdapter()
        binding!!.rvAriNotice.adapter = adapter
    }

    override fun update(ariNotice: ArrayList<AriNotice>) {
        adapter.setList(ariNotice)
        adapter.notifyDataSetChanged()
    }
}