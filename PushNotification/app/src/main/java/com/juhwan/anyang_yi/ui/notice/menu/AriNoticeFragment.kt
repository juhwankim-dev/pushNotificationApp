package com.juhwan.anyang_yi.ui.notice.menu

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.juhwan.anyang_yi.databinding.FragmentAriNoticeBinding
import com.juhwan.anyang_yi.network.AriNotice
import com.juhwan.anyang_yi.ui.notice.NoticeViewModel

class AriNoticeFragment : Fragment(),
    AriUpdateListener {

    private var binding: FragmentAriNoticeBinding? = null
    private lateinit var model: NoticeViewModel
    private lateinit var adapter: AriNoticeAdapter
    private var page = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAriNoticeBinding.inflate(inflater, container, false)
        model = ViewModelProvider(this).get(NoticeViewModel::class.java)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

/*        model.getAriNotices(page, this)

        var test = model.getAriApplyNotices()*/
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