package com.juhwan.anyang_yi.present.views.home.social

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.juhwan.anyang_yi.R
import com.juhwan.anyang_yi.databinding.FragmentSocialBinding
import com.juhwan.anyang_yi.present.config.BaseFragment
import com.juhwan.anyang_yi.present.views.home.WebViewActivity

class SocialFragment : BaseFragment<FragmentSocialBinding>(R.layout.fragment_social) {
    private lateinit var eduAdapter: KakaoAdapter
    private lateinit var jobAdapter: KakaoAdapter
    private lateinit var ariPanelAdapter: KakaoAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(KakaoRepository_.isFinished.value == null){
            KakaoRepository_.loadInitialData()
            binding!!.lottieSheep.visibility = View.VISIBLE
            binding!!.lottieSheep.playAnimation()
        }

        KakaoRepository_.isFinished.observe(viewLifecycleOwner, Observer{
            binding!!.lottieSheep.visibility = View.GONE
            initRecyclerView()
        })

        binding!!.tvSeeAllEdu.setOnClickListener {
            //startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://plus.kakao.com/home/@jxehRd")))
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://pf.kakao.com/_jxehRd")))
        }
        binding!!.tvSeeAllJob.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://pf.kakao.com/_iMxaFC")))
        }
        binding!!.tvSeeAllAriPanel.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://pf.kakao.com/_lNmNd")))
        }
    }

    private fun initRecyclerView(){
        binding!!.rvEdu.layoutManager = LinearLayoutManager(context).also {
            it.orientation = LinearLayoutManager.HORIZONTAL
        }
        eduAdapter = KakaoAdapter(KakaoRepository_.eduNotice)
        binding!!.rvEdu.adapter = eduAdapter

        binding!!.rvJob.layoutManager = LinearLayoutManager(context).also {
            it.orientation = LinearLayoutManager.HORIZONTAL
        }
        jobAdapter = KakaoAdapter(KakaoRepository_.jobNotice)
        binding!!.rvJob.adapter = jobAdapter

        binding!!.rvAriPanel.layoutManager = LinearLayoutManager(context).also {
            it.orientation = LinearLayoutManager.HORIZONTAL
        }
        ariPanelAdapter = KakaoAdapter(KakaoRepository_.ariPanelNotice)
        binding!!.rvAriPanel.adapter = ariPanelAdapter
    }

    private fun goPage(url: String){
        var goPage = Intent(context, WebViewActivity::class.java)

        goPage.putExtra("url", url)
        startActivity(goPage)
    }
}