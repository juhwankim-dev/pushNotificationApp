package com.example.pushnotification.fragments.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.baoyz.widget.PullRefreshLayout
import com.example.pushnotification.R
import com.example.pushnotification.fragments.home.HtmlCrawler.Companion.notices
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(), ManageData{
    private var page = 1       // 현재 페이지
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //val linearLayoutManagerWrapper = LinearLayoutManagerWrapper(context!!, LinearLayoutManager.VERTICAL, false)
        recyclerView_notices.adapter = MyNoticeAdapter(context, notices) // 어댑터 생성
        recyclerView_notices.layoutManager = LinearLayoutManager(context)

        val handler = android.os.Handler()

        // 스크롤 리스너
        recyclerView_notices.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                // 스크롤이 끝에 도달했는지 확인
                if (!recyclerView_notices.canScrollVertically(1)){
                    Log.v("안녕하세여", "하이하이0000000000")
                    handler.postDelayed({
                        Log.v("안녕하세여", "하이하이: {${notices.lastIndex}")
                        notices.removeAt(notices.lastIndex)
                        ++page
                        refreshPage()
                    }, 1000)

                }
            }
        })

        refresh_layout.setOnRefreshListener {
            // 목록을 싹 다 지우고 다시 크롤링을 해옴
            notices.clear()
            page = 1
            refreshPage()

            // 새로고침을 완료하면 아이콘을 없앤다.
            refresh_layout.setRefreshing(false)
        }
    }

    private fun refreshPage() {
        Log.v("테스트", "페이지는${page}")
        HtmlCrawler(this).requestPost(page)
    }

    override fun refreshAllData() {
        if(notices.isNotEmpty()){
            var keywordAdapter = recyclerView_notices.adapter
            if(page == 1){
                keywordAdapter!!.notifyDataSetChanged()
            } else if(page > 1){
                // 한 페이지당 게시물이 15개씩 들어있음.
                // 새로운 게시물이 추가되었다는 것을 알려줌 (추가된 부분만 새로고침)
                //keywordAdapter!!.notifyDataSetChanged()
                keywordAdapter!!.notifyItemRangeInserted(page*15,15)
            }
        }
    }
}