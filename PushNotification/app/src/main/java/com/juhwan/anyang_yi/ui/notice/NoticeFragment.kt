package com.juhwan.anyang_yi.ui.notice

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.juhwan.anyang_yi.databinding.FragmentNoticeBinding

class NoticeFragment : Fragment(){


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

        model.itemPagedList.observe(viewLifecycleOwner, Observer{
            Log.v("왔다면", it.toString() + "뭐들어있냐")
            adapter.submitList(it)
            //adapter.setList(it)
        })

/*        model.getAll().observe(viewLifecycleOwner, Observer{
            adapter.setList(it.resultList)
            adapter.notifyDataSetChanged()
        })*/

/*        recyclerView_notices.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                Handler(Looper.getMainLooper()).postDelayed({
                    val lastVisibleItemPosition = (recyclerView.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition()
                    val itemTotalCount = recyclerView.adapter!!.itemCount - 1

                    if (!recyclerView_notices.canScrollVertically(1) &&
                        lastVisibleItemPosition == itemTotalCount
                    ) {
                        allNotices.removeAt(allNotices.lastIndex)
                        crawler.requestPost(++page)
                    }
                }, intervalTime)
            }
        })

        refresh_layout.setOnRefreshListener {
            intervalTime = 500
            page = 1
            crawler.requestPost(page)
            refresh_layout.isRefreshing = false
            Snackbar.make(home_linearlayout, "새로고침 하였습니다.", Snackbar.LENGTH_SHORT).show()
        }*/
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

/*    override fun loadPage(notices: ArrayList<NoticeList>, page: Int) {
        CoroutineScope(Dispatchers.Main).async {
            if (page == 1) allNotices.clear()
            allNotices.addAll(notices)
            var keywordAdapter = recyclerView_notices.adapter

            when (page) {
                1 -> keywordAdapter!!.notifyDataSetChanged()
                else -> {
                    keywordAdapter!!.notifyItemRangeInserted((page - 1) * 15, 15)
                }
            }
            intervalTime = 0
        }
    }*/

    private fun initRecyclerView() {
        binding!!.rvNotice.layoutManager = LinearLayoutManager(context)
        adapter = NoticeAdapter()
        binding!!.rvNotice.adapter = adapter
    }
}
