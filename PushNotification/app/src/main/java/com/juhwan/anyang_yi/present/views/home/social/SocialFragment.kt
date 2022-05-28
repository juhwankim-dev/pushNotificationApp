package com.juhwan.anyang_yi.present.views.home.social

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.juhwan.anyang_yi.R
import com.juhwan.anyang_yi.data.repository.KakaoRepository
import com.juhwan.anyang_yi.databinding.FragmentSocialBinding
import com.juhwan.anyang_yi.present.config.BaseFragment
import com.juhwan.anyang_yi.present.views.home.WebViewActivity

class SocialFragment : BaseFragment<FragmentSocialBinding>(R.layout.fragment_social) {
    private lateinit var eduAdapter: KakaoAdapter
    private lateinit var jobAdapter: KakaoAdapter
    private lateinit var ariPanelAdapter: KakaoAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(KakaoRepository.isFinished.value == null){
            KakaoRepository.loadInitialData()
            binding!!.lottieViewSheep.visibility = View.VISIBLE
            binding!!.lottieViewSheep.playAnimation()
        }

        KakaoRepository.isFinished.observe(viewLifecycleOwner, Observer{
            binding!!.lottieViewSheep.visibility = View.GONE
            initRecyclerView()
        })

        binding!!.seeAllEdu.setOnClickListener {
            //startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://plus.kakao.com/home/@jxehRd")))
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://pf.kakao.com/_jxehRd")))
        }
        binding!!.seeAllJob.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://pf.kakao.com/_iMxaFC")))
        }
        binding!!.seeAllAriPanel.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://pf.kakao.com/_lNmNd")))
        }
    }

    private fun initRecyclerView(){
        binding!!.rvEdu.layoutManager = LinearLayoutManager(context).also {
            it.orientation = LinearLayoutManager.HORIZONTAL
        }
        eduAdapter = KakaoAdapter(KakaoRepository.eduNotice)
        binding!!.rvEdu.adapter = eduAdapter

        binding!!.rvJob.layoutManager = LinearLayoutManager(context).also {
            it.orientation = LinearLayoutManager.HORIZONTAL
        }
        jobAdapter = KakaoAdapter(KakaoRepository.jobNotice)
        binding!!.rvJob.adapter = jobAdapter

        binding!!.rvAriPanel.layoutManager = LinearLayoutManager(context).also {
            it.orientation = LinearLayoutManager.HORIZONTAL
        }
        ariPanelAdapter = KakaoAdapter(KakaoRepository.ariPanelNotice)
        binding!!.rvAriPanel.adapter = ariPanelAdapter
    }

    private fun goPage(url: String){
        var goPage = Intent(context, WebViewActivity::class.java)

        goPage.putExtra("url", url)
        startActivity(goPage)
    }
}