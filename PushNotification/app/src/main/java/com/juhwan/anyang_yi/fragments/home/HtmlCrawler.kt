package com.juhwan.anyang_yi.fragments.home

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.HashMap

class HtmlCrawler(listener: CallbackPost){
    var mCallback = listener
    private val BASEURL = "http://www.anyang.ac.kr/bbs/ajax/" // 베이스 URL
    private var jsonPlaceHolderApi: JsonPlaceHolderApi? = null // JSON 사용하려면 적어야하는거? API

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASEURL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun requestPost(page: Int) {
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi::class.java)
        val data: MutableMap<String, String> = HashMap() // Map 선언
        /*
        * menuId: 아리커뮤니티
        * bsIdx: 게시판
        * searchCondition: 검색조건??
        * searchKeyword: 이게 검색어 키워드
        * */
        data["menuId"] = "23"
        data["bsIdx"] = "61"
        data["bcIdx"] = "0" // 20이 대학교 0이 전체
        data["page"] = page.toString()

        CoroutineScope(Dispatchers.Main).launch {
            val html = CoroutineScope(Dispatchers.IO).async {
                callAndReq(data, page)
            }.await()
        }
    }

    private fun callAndReq(
        data: MutableMap<String, String>,
        page: Int
    ) {
        var notices = arrayListOf<NoticeList>()

        // Retrofit에서 만들어놓은 Call이라는 클래스를 사용해서 객체를 하나 만들꺼야.
        // JsonApi랑... map.... 을 이용해서 말이지... map에 .. 아..아니야... 포스트에보낼 데이터 같은게 들어있어...
        val call: Call<Result?>? = jsonPlaceHolderApi?.boardListPost(data)
        /* .. Call.enquere를.. 정의해줄..거야.. */
        call?.enqueue(object : Callback<Result?> {
            /* 응답을 한다면 */
            /* Result 자료형으로 response를 만들고. 그리고 요청해서 받아온것들을 여기다가 저장할거임. */
            override fun onResponse(
                call: Call<Result?>,
                response: Response<Result?>
            ) {
                /* 응답이 잘 되었다면 response에는 body가 저장되어있는데. 요청한 결과가 저장되어있는거임 */
                /* 그걸 Result형 객체를 하나 만들어서 저장한거임 아래 문장이 */
                try {
                    var position = 0
                    val posts = response!!.body()!!.resultList
                    for (post in posts) {
                        var title = post.SUBJECT.toString()
                        var info = post.WRITE_DATE2.toString() + "   |   " + post.WRITER.toString()
                        var url = post.B_IDX.toString()
                        notices.add(NoticeList(title, info, url))
                        position++
                    }

                    notices.add(NoticeList("loading", " ", " ")) // 맨끝에 null을 넣고 싶은데 안돼서 이거를...
                } catch (e: Exception) {

                }

                mCallback.loadPage(notices, page)
            }

            /* 응답을 하지 않는다면 */
            override fun onFailure(call: Call<Result?>, t: Throwable) {

            }
        })
    }
}