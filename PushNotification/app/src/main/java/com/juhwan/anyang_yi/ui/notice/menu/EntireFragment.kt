package com.juhwan.anyang_yi.ui.notice.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.juhwan.anyang_yi.databinding.FragmentEntireBinding

class EntireFragment : Fragment() {

    private var binding: FragmentEntireBinding? = null
    private val modelMain: MainNoticeViewModel by viewModels()
    private lateinit var adapterMain: MainNoticeAdapter
    private var page = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEntireBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        modelMain.getMainNotices(page, "전체")

        modelMain.getAll("전체").observe(viewLifecycleOwner, Observer {
            adapterMain.setList(it.resultList)

            when (page) {
                1 -> adapterMain.notifyDataSetChanged()
                else -> {
                    adapterMain.notifyItemRangeInserted((page - 1) * 15, 15)
                }
            }
        })

        binding!!.rvEntireNotice.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val lastVisibleItemPosition =
                    (recyclerView.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition()
                val itemTotalCount = recyclerView.adapter!!.itemCount - 1

                if (!binding!!.rvEntireNotice.canScrollVertically(1) &&
                    lastVisibleItemPosition == itemTotalCount
                ) {
                    adapterMain.deleteLoding()
                    modelMain.getMainNotices(++page, "전체")
                }

            }
        })
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    private fun initRecyclerView() {
        binding!!.rvEntireNotice.layoutManager = LinearLayoutManager(context)
        adapterMain = MainNoticeAdapter()
        binding!!.rvEntireNotice.adapter = adapterMain
    }
}