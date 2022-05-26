package com.juhwan.anyang_yi.data.repository

import androidx.lifecycle.MutableLiveData
import com.juhwan.anyang_yi.data.model.Item
import com.juhwan.anyang_yi.data.model.KakaoNotice
import com.juhwan.anyang_yi.data.api.KakaoNoticeApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object KakaoRepository {

    const val EDU = 1
    const val JOB = 2
    const val ARI = 3

    var eduNotice = ArrayList<Item>()
    var jobNotice = ArrayList<Item>()
    var ariPanelNotice = ArrayList<Item>()

    var isFinished = MutableLiveData<Boolean>()

    fun loadInitialData(){
        loadKakaoNotice(KakaoNoticeApi.createApi().getEduNotice(), EDU)
        loadKakaoNotice(KakaoNoticeApi.createApi().getJobNotice(), JOB)
        loadKakaoNotice(KakaoNoticeApi.createApi().getAriPanelNotice(), ARI)
    }

    private fun loadKakaoNotice(call: Call<KakaoNotice>, channel: Int) {
        call.enqueue(object : Callback<KakaoNotice> {
            override fun onResponse(
                call: Call<KakaoNotice>,
                response: Response<KakaoNotice>
            ) {
                if (response.isSuccessful) {
                    try {
                        when(channel){
                            EDU -> eduNotice = response.body()!!.posts.items
                            JOB -> jobNotice = response.body()!!.posts.items
                            ARI -> ariPanelNotice = response.body()!!.posts.items
                        }
                        isFinished.value = true
                    } catch (e: Exception) {

                    }
                }
            }

            override fun onFailure(call: Call<KakaoNotice>, t: Throwable) {

            }
        })
    }
}