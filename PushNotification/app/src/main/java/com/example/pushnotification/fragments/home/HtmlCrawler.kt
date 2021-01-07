package com.example.pushnotification.fragments.home

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.HashMap

class HtmlCrawler {
    companion object{
        var notices = arrayListOf<NoticeList>() // 공지사항 리스트
        var mainNotice = "Error: Page 404" // 홈 화면에서 보여줄 메인공지 1개짜리
    }

    private val BASEURL = "http://www.anyang.ac.kr/bbs/ajax/" // 베이스 URL
    private var jsonPlaceHolderApi: JsonPlaceHolderApi? = null // JSON 사용하려면 적어야하는거? API
    private var page = "1"

    fun setURLAPI(){
        /* ------------------------------ 게시글 POST 요청하기 -------------------------------*/
        /* 몰라 Retrofit을 사용하려고 선언한다는 느낌? */
        val retrofit = Retrofit.Builder()
            .baseUrl(BASEURL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        /* JSON API에 retrofit을 ... 연동?  */
        /* 안에 적어준 JsonPlaceHolderAPi는.. 따로 만들어준 자바 클래스인갑다. */
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi::class.java)
        // (= jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class); )
        /* ------------------------------ 게시글 POST 요청하기 -------------------------------*/
    }

    fun activateBot(page: Int) {
        Log.i("00000", page.toString() + "를 크롤링합니당@@@@@@")
        this.page = page.toString()
        val thread = ThreadClass();
        thread.start();
    }

    inner class ThreadClass() :Thread(){
        override fun run() {
            requestPost() // 메소드 호출
        }
    }

    private fun requestPost() {
        val data: MutableMap<String, String> = HashMap() // Map 선언
        /*
        * menuId: 아리커뮤니티
        * bsIdx: 게시판
        * searchCondition: 검색조건??
        * searchKeyword: 이게 검색어 키워드
        * */
        data["menuId"] = "23"
        data["bsIdx"] = "61"
        data["bcIdx"] = "20"
        data["page"] = page
        //data["searchCondition"] = "SUBJECT"
        //data["searchKeyword"] = "ACE"

        // Retrofit에서 만들어놓은 Call이라는 클래스를 사용해서 객체를 하나 만들꺼야.
        // JsonApi랑... map.... 을 이용해서 말이지... map에 .. 아..아니야... 포스트에보낼 데이터 같은게 들어있어...
        val call: Call<Result?>? = jsonPlaceHolderApi?.boardListPost(data)

        /* .. Call.enquere를.. 정의해줄..거야.. */
        if (call != null) {
            call.enqueue(object : Callback<Result?> {
                /* 응답을 한다면 */
                /* Result 자료형으로 response를 만들고. 그리고 요청해서 받아온것들을 여기다가 저장할거임. */
                override fun onResponse(
                    call: Call<Result?>,
                    response: Response<Result?>
                )

                {
                    /* 응답이 성공적이지 않을때 할 행동, 만약 응답이 없었다면 response에는 코드가 저장되게 된다. (에러 404)*/
                    if (!response.isSuccessful()) {
                        notices.add(0, NoticeList("페이지를 표시할 수 없습니다.", response.code().toString(), " "))
                        //txt.text = "code: " + response.code()
                        return
                    }

                    /* 응답이 잘 되었다면 response에는 body가 저장되어있는데. 요청한 결과가 저장되어있는거임 */
                    /* 그걸 Result형 객체를 하나 만들어서 저장한거임 아래 문장이 */
                    val postResponse: Result? = response.body()

                    // 메인에 띄울 가장 최신 공지사항 하나만 따옴
                    if (postResponse != null) {
                        mainNotice = postResponse.resultList.get(0).SUBJECT.toString()
                    }

                    /* for-each문 돌면서.. Result에 있는 데이터중에 ResultList에 있는거 가져옴. 그래서 한줄씩 검사할거임 */
                    if (postResponse != null) {
                        var position = 0

                        for (post in postResponse.resultList) {
                            var title = post.SUBJECT.toString()
                            var info = post.WRITER.toString() + "   |   " + post.WRITE_DATE2.toString()
                            var url = post.B_IDX.toString()
                            notices.add(NoticeList(title, info, url))
                            position++
                        }
                    }
                }

                /* 응답을 하지 않는다면 */
                override fun onFailure(call: Call<Result?>, t: Throwable) {
                    notices.add(0, NoticeList("페이지를 표시할 수 없습니다.", "사이트 관리자에게 문의하세요.", " "))
                    //txt.text = t.message
                }
            })
        }
    }
}