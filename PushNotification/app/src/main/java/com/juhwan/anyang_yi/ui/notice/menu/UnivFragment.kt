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
import com.juhwan.anyang_yi.databinding.FragmentUnivBinding

class UnivFragment : Fragment() {

    private var binding: FragmentUnivBinding? = null
    private val modelMain: MainNoticeViewModel by viewModels()
    private lateinit var adapterMain: MainNoticeAdapter
    private var page = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUnivBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        modelMain.getMainNotices(page, "대학교")

        modelMain.getAll("대학교").observe(viewLifecycleOwner, Observer {
            adapterMain.setList(it.resultList)

            when (page) {
                1 -> adapterMain.notifyDataSetChanged()
                else -> {
                    adapterMain.notifyItemRangeInserted((page - 1) * 15, 15)
                }
            }
        })

        binding!!.rvUnivNotice.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val lastVisibleItemPosition =
                    (recyclerView.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition()
                val itemTotalCount = recyclerView.adapter!!.itemCount - 1

                if (!binding!!.rvUnivNotice.canScrollVertically(1) &&
                    lastVisibleItemPosition == itemTotalCount
                ) {
                    adapterMain.deleteLoding()
                    modelMain.getMainNotices(++page, "대학교")
                }

            }
        })
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    private fun initRecyclerView() {
        binding!!.rvUnivNotice.layoutManager = LinearLayoutManager(context)
        adapterMain = MainNoticeAdapter()
        binding!!.rvUnivNotice.adapter = adapterMain
    }
}