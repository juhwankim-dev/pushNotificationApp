package com.example.pushnotification.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pushnotification.R
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    private var page = 1       // 현재 페이지

    private var crawler = HtmlCrawler()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView_notices.adapter = MyNoticeAdapter() // 어댑터 생성
        var keywordAdapter = recyclerView_notices.adapter
        recyclerView_notices.layoutManager = LinearLayoutManager(context) as RecyclerView.LayoutManager?

        //스크롤 관련
        recyclerView_notices.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(@NonNull recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                //마지막 체크
                if (!recyclerView_notices.canScrollVertically(1)) {
                    crawler.setURLAPI()
                    crawler.activateBot(++page)

                    // 너무 빨리 데이터가 로드되면 스크롤 되는 Ui 를 확인하기 어려우므로,
                    // Handler 를 사용하여 1초간 postDelayed 시켰다
                    val handler = android.os.Handler()
                    handler.postDelayed({
                        refreshPage(keywordAdapter)
                    }, 1000)
                }
            }
        })
    }

    private fun refreshPage(keywordAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>?) {
        // 리사이클러뷰 새로고침
        //recyclerView_notices.adapter = MyNoticeAdapter()
        //var keywordsAdapter = recyclerView_notices.adapter
        //keywordsAdapter!!.notifyDataSetChanged()

        keywordAdapter!!.notifyItemRangeInserted(0,15)
        recyclerView_notices.layoutManager.scrollTo
    }
}


