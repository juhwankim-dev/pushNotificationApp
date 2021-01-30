package com.example.pushnotification.fragments.home

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
    }

    private val BASEURL = "http://www.anyang.ac.kr/bbs/ajax/" // 베이스 URL
    private var jsonPlaceHolderApi: JsonPlaceHolderApi? = null // JSON 사용하려면 적어야하는거? API

    val retrofit = Retrofit.Builder()
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
                    if (!response.isSuccessful) {
                        errorMessage()
                        return
                    }

                    /* 응답이 잘 되었다면 response에는 body가 저장되어있는데. 요청한 결과가 저장되어있는거임 */
                    /* 그걸 Result형 객체를 하나 만들어서 저장한거임 아래 문장이 */
                    val postResponse: Result? = response.body()

                    // TODO Attempt to read from null array: 사이트와의 연결이 불안정할때 이런 에러가 뜸
                    try{
                        /* for-each문 돌면서.. Result에 있는 데이터중에 ResultList에 있는거 가져옴. 그래서 한줄씩 검사할거임 */
                        if (postResponse != null) {
                            var position = 0

                            for (post in postResponse.resultList) {
                                var title = post.SUBJECT.toString()
                                var info = post.WRITE_DATE2.toString() + "   |   " + post.WRITER.toString()
                                var url = post.B_IDX.toString()
                                notices.add(NoticeList(title, info, url))
                                position++
                            }
                        }

                        notices.add(NoticeList("loading", " ", " ")) // 맨끝에 null을 넣고 싶은데 안돼서 이거를...
                    } catch (e: Exception){
                        errorMessage()
                    }
                }

                /* 응답을 하지 않는다면 */
                override fun onFailure(call: Call<Result?>, t: Throwable) {
                    errorMessage()
                }
            })
        }
    }

    private fun errorMessage(){
        notices.add(0, NoticeList("네트워크가 불안정 합니다. ", "새로고침을 해주세요.", " "))
    }
}