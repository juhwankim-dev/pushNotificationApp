package com.juhwan.anyang_yi.ui.notice

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.juhwan.anyang_yi.databinding.FragmentNoticeBinding
import com.juhwan.anyang_yi.ui.notice.menu.AriNoticeFragment
import com.juhwan.anyang_yi.ui.notice.menu.EntireFragment
import com.juhwan.anyang_yi.ui.notice.menu.UnivFragment

class NoticeFragment : Fragment() {

    private var binding: FragmentNoticeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoticeBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initTabLayout()
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    private fun initTabLayout() {
        val tabTextList = arrayListOf("전체", "대학교", "비교과")

        binding!!.viewPagerNotice.adapter = CustomFragmentStateAdapter(requireActivity())
        TabLayoutMediator(binding!!.tabLayout, binding!!.viewPagerNotice) {
                tab, position ->
            tab.text = tabTextList[position]
        }.attach()
    }

    inner class CustomFragmentStateAdapter(fragmentActivity: FragmentActivity):
        FragmentStateAdapter(fragmentActivity) {
        override fun getItemCount(): Int {
            return 3
        }

        override fun createFragment(position: Int): Fragment {
            return when(position) {
                0 -> EntireFragment()
                1 -> UnivFragment()
                else -> AriNoticeFragment()
            }
        }
    }
}
