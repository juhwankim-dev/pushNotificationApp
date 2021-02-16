package com.juhwan.anyang_yi.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.juhwan.anyang_yi.R
import com.juhwan.anyang_yi.SplashActivity.Companion.initialPost
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class HomeFragment : Fragment(), PostListener {

    private var page = 1       // 현재 페이지
    private var crawler = HtmlCrawler(this)
    var allNotices = arrayListOf<NoticeList>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        allNotices.addAll(initialPost) // 스플래시에서 가져온 공지사항 1페이지
        if(allNotices.isEmpty()) crawler.requestPost(1) // 스플래시에서 페이지를 가져오 못했다면 다시 시도

        recyclerView_notices.adapter = MyNoticeAdapter(context, allNotices) // 어댑터 생성
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
                    allNotices.removeAt(allNotices.lastIndex)
                    crawler.requestPost(++page)
                }
            }
        })

        refresh_layout.setOnRefreshListener {
            allNotices.clear()
            page = 1
            crawler.requestPost(page)
            refresh_layout.isRefreshing = false // 새로고침을 완료하면 아이콘을 없앤다.
            Snackbar.make(home_linearlayout, "새로고침 하였습니다.", Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun loadPage(notices: ArrayList<NoticeList>, page: Int) {
        CoroutineScope(Dispatchers.Main).async {
            allNotices.addAll(notices)
            var keywordAdapter = recyclerView_notices.adapter

            when(page){
                1 -> keywordAdapter!!.notifyDataSetChanged()
                else -> {
                    // 한 페이지당 게시물이 15개씩 들어있음.
                    // 새로운 게시물이 추가되었다는 것을 알려줌 (추가된 부분만 새로고침)
                    keywordAdapter!!.notifyItemRangeInserted((page-1) * 15, 15)
                }
            }
        }
    }
    
    // 다른페이지갓다가 오면 맨 마지막 인덱스가 사라지지 않고 그대로 있는거... 기존꺼에 1페이지가 또 그대로추가되는거같아
}
