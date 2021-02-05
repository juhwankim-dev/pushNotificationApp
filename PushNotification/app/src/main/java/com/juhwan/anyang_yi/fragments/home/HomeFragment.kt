package com.juhwan.anyang_yi.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.juhwan.anyang_yi.R
import com.juhwan.anyang_yi.fragments.home.HtmlCrawler.Companion.notices
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private var page = 1       // 현재 페이지
    private var crawler = HtmlCrawler()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView_notices.adapter =
            MyNoticeAdapter(context) // 어댑터 생성

        var keywordAdapter = recyclerView_notices.adapter
        recyclerView_notices.layoutManager = LinearLayoutManager(context)

        // 스크롤 리스너
        recyclerView_notices.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val lastVisibleItemPosition = (recyclerView.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition()
                val itemTotalCount = recyclerView.adapter!!.itemCount - 1

                // 스크롤이 끝에 도달했는지 확인
                if (!recyclerView_notices.canScrollVertically(1) &&
                    lastVisibleItemPosition == itemTotalCount
                    ) {
                    notices.removeAt(notices.lastIndex)
                    crawler.requestPost(++page)

                    // 너무 빨리 데이터가 로드되면 스크롤 되는 Ui 를 확인하기 어려우므로,
                    // Handler 를 사용하여 1초간 postDelayed 시켰다
                    val handler = android.os.Handler()
                    handler.postDelayed({
                        loadMorePage(keywordAdapter)
                    }, 1000)

                }
            }
        })
    }

    private fun loadMorePage(keywordAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>?) {
        // 한 페이지당 게시물이 15개씩 들어있음.
        // 새로운 게시물이 추가되었다는 것을 알려줌 (추가된 부분만 새로고침)
        keywordAdapter!!.notifyItemRangeInserted(page * 15, 15)
    }
}
