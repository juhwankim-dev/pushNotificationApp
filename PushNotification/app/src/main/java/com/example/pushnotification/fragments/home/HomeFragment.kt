package com.example.pushnotification.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.baoyz.widget.PullRefreshLayout
import com.example.pushnotification.R
import com.example.pushnotification.fragments.home.HtmlCrawler.Companion.notices
import kotlinx.android.synthetic.main.fragment_home.*
import java.lang.Exception


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

        recyclerView_notices.adapter = MyNoticeAdapter(context) // 어댑터 생성

        var keywordAdapter = recyclerView_notices.adapter
        recyclerView_notices.layoutManager = LinearLayoutManager(context)

        // 스크롤 리스너
        recyclerView_notices.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                // 스크롤이 끝에 도달했는지 확인
                if (!recyclerView_notices.canScrollVertically(1)) {
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

        refresh_layout.setOnRefreshListener(PullRefreshLayout.OnRefreshListener {
            activity!!.window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            refreshPage(keywordAdapter)
        })
    }

    private fun refreshPage(keywordAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>?) {
        // 목록을 싹 다 지우고 다시 크롤링을 해옴
        notices.clear()
        page = 1
        crawler.requestPost(page)

        // 크롤링 해오는 속도가 UI 업데이트(notifyDataSetChanged)하는 속도보다 느리기 때문에 핸들러를 사용함
        val handler = android.os.Handler()
        handler.postDelayed({
            keywordAdapter!!.notifyDataSetChanged()
            refresh_layout.setRefreshing(false) // 새로고침을 완료하면 아이콘을 없앤다.
            activity!!.window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        }, 1000)
    }

    private fun loadMorePage(keywordAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>?) {
        // 한 페이지당 게시물이 15개씩 들어있음.
        // 새로운 게시물이 추가되었다는 것을 알려줌 (추가된 부분만 새로고침)
        keywordAdapter!!.notifyItemRangeInserted(page * 15, 15)
    }
}
