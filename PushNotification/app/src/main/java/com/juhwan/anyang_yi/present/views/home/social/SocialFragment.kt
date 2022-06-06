package com.juhwan.anyang_yi.present.views.home.social

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.juhwan.anyang_yi.R
import com.juhwan.anyang_yi.databinding.FragmentSocialBinding
import com.juhwan.anyang_yi.present.config.BaseFragment
import com.juhwan.anyang_yi.present.views.home.WebViewActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SocialFragment : BaseFragment<FragmentSocialBinding>(R.layout.fragment_social) {
    private val viewModel: SocialViewModel by viewModels()
    private lateinit var eduAdapter: KakaoAdapter
    private lateinit var jobAdapter: KakaoAdapter
    private lateinit var ariPanelAdapter: KakaoAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getEduNoticeList()
        viewModel.getJobNoticeList()
        viewModel.getAriPanelNoticeList()
        initView()
        initEvent()
    }

    private fun initView(){
        binding!!.rvEdu.layoutManager = LinearLayoutManager(context).also {
            it.orientation = LinearLayoutManager.HORIZONTAL
        }
        eduAdapter = KakaoAdapter()
        binding!!.rvEdu.adapter = eduAdapter

        binding!!.rvJob.layoutManager = LinearLayoutManager(context).also {
            it.orientation = LinearLayoutManager.HORIZONTAL
        }
        jobAdapter = KakaoAdapter()
        binding!!.rvJob.adapter = jobAdapter

        binding!!.rvAriPanel.layoutManager = LinearLayoutManager(context).also {
            it.orientation = LinearLayoutManager.HORIZONTAL
        }
        ariPanelAdapter = KakaoAdapter()
        binding!!.rvAriPanel.adapter = ariPanelAdapter
    }

    private fun initEvent() {
        viewModel.eduNoticeList.observe(viewLifecycleOwner) {
            eduAdapter.setList(it)
        }

        viewModel.jobNoticeList.observe(viewLifecycleOwner) {
            jobAdapter.setList(it)
        }

        viewModel.ariPanelNoticeList.observe(viewLifecycleOwner) {
            ariPanelAdapter.setList(it)
        }

        viewModel.problem.observe(viewLifecycleOwner) {
            showToastMessage(resources.getString(R.string.network_error))
        }

        binding!!.tvSeeAllEdu.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://pf.kakao.com/_jxehRd")))
        }
        binding!!.tvSeeAllJob.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://pf.kakao.com/_iMxaFC")))
        }
        binding!!.tvSeeAllAriPanel.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://pf.kakao.com/_lNmNd")))
        }
    }

    private fun goPage(url: String){
        var goPage = Intent(context, WebViewActivity::class.java)

        goPage.putExtra("url", url)
        startActivity(goPage)
    }
}