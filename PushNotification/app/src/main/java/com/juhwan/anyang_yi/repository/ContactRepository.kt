package com.juhwan.anyang_yi.repository

import androidx.lifecycle.MutableLiveData
import com.juhwan.anyang_yi.data.Contact
import com.juhwan.anyang_yi.network.ContactNetwork
import okhttp3.ResponseBody
import org.jsoup.Jsoup
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object ContactRepository {

    var contact = ArrayList<Contact>()
    var departmentList = ArrayList<String>()

    var isFinished = MutableLiveData<Boolean>()

    fun requestPost() {
        val call = ContactNetwork.getJsonApi().boardListPost()
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
                if (response.isSuccessful) {
                    try {
                        arrangeHtml(response.body()!!.string())
                        getDepartmentList()
                    } catch (e: Exception) {

                    }
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

            }
        })
    }

    private fun arrangeHtml(html: String) {
        var lClass = "" // 대분류
        var mClass = "" // 중분류
        var sClass = "" // 소분류
        var tel = "" // 번호

        var doc = Jsoup.parse(html)

        var elements = doc.select("tr")

        for (i in 1 until elements.size) {
            var td = elements[i].select("td")

            when {
                td.attr("colspan") != "" -> {
                    lClass = td[0].text()
                    mClass = lClass
                    sClass = td[2].text()
                    tel = td[3].text()
                }
                td.attr("rowspan") != "" -> {
                    when (td.size) {
                        6 -> {
                            lClass = td[0].text()
                            mClass = td[1].text()
                            sClass = td[3].text()
                            tel = td[4].text()
                        }
                        5 -> {
                            mClass = td[0].text()
                            sClass = td[2].text()
                            tel = td[3].text()
                        }
                        else -> {
                            mClass = td[0].text()
                            sClass = td[2].text()
                            tel = td[3].text()
                        }
                    }
                }
                else -> {
                    when (td.size) {
                        2, 3 -> {
                            sClass = td[td.size - 2].text()
                            tel = td[td.size - 1].text()
                        }

                        4, 5 -> {
                            if (td[0].text() == "아 504-1" || td[0].text() == "봉 1407") {
                                sClass = td[1].text()
                                tel = td[2].text()
                            } else {
                                mClass = td[0].text()
                                sClass = td[2].text()
                                tel = td[3].text()
                            }
                        }

                        6 -> {
                            lClass = td[0].text()
                            mClass = td[0].text()
                            tel = td[4].text()
                        }
                    }
                }
            }

            contact.add(
                Contact(
                    lClass.replace(
                        " ",
                        ""
                    ), mClass.replace(" ", ""), sClass, tel
                )
            )
        }
    }

    private fun getDepartmentList() {
        var contactSize = contact.size

        departmentList.add("") // 맨 첫 아이템은 '전체'를 넣을거임

        for (i in 1 until contactSize) {
            if (contact[i].lClass != contact[i - 1].lClass) {
                departmentList.add(contact[i - 1].lClass)
            }
        }

        departmentList.add(contact[contactSize - 1].lClass)

        isFinished.value = true
    }
}