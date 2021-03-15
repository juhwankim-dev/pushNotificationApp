package com.juhwan.anyang_yi.ui.notice

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.juhwan.anyang_yi.databinding.FragmentNoticeBinding

class NoticeFragment : Fragment() {


    private var binding: FragmentNoticeBinding? = null
    private val model: NoticeViewModel by viewModels()
    private lateinit var adapter: NoticeAdapter
    private var page = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoticeBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        model.requestPost(page)

        model.getAll().observe(viewLifecycleOwner, Observer {
            adapter.setList(it.resultList)

            when (page) {
                1 -> adapter.notifyDataSetChanged()
                else -> {
                    adapter.notifyItemRangeInserted((page - 1) * 15, 15)
                }
            }
        })

        binding!!.rvNotice.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val lastVisibleItemPosition =
                    (recyclerView.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition()
                val itemTotalCount = recyclerView.adapter!!.itemCount - 1

                if (!binding!!.rvNotice.canScrollVertically(1) &&
                    lastVisibleItemPosition == itemTotalCount
                ) {
                    adapter.deleteLoding()
                    model.requestPost(++page)
                }

            }
        })
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    private fun initRecyclerView() {
        binding!!.rvNotice.layoutManager = LinearLayoutManager(context)
        adapter = NoticeAdapter()
        binding!!.rvNotice.adapter = adapter
    }
}
