package com.juhwan.anyang_yi.ui.notice.menu

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.juhwan.anyang_yi.databinding.FragmentMainNoticeBinding
import com.juhwan.anyang_yi.ui.notice.NoticeViewModel

class MainNoticeFragment : Fragment() {

    private var binding: FragmentMainNoticeBinding? = null
    private lateinit var model: NoticeViewModel
    private lateinit var adapterMain: MainNoticeAdapter
    private var page = 1
    private var recyclerViewState: Parcelable? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainNoticeBinding.inflate(inflater, container, false)
        model = ViewModelProvider(requireActivity()).get(NoticeViewModel::class.java)

        Log.v("만들어짐", "만들어짐")

        return binding?.root
    }

    override fun onResume() {
        super.onResume()
        Log.v("재개됨", "재개됨")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        model.getAll().observe(viewLifecycleOwner, Observer {
            adapterMain.setList(it.resultList)
            Log.v("데이터 변화가 있음", "데이터변화가있음")
            when (page) {
                1 -> adapterMain.notifyDataSetChanged()
                else -> {
                    adapterMain.notifyItemRangeInserted((page - 1) * 15, 15)
                }
            }
        })

        binding!!.rvEntireNotice.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val lastVisibleItemPosition =
                    (recyclerView.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition()
                val itemTotalCount = recyclerView.adapter!!.itemCount - 1

                if (!binding!!.rvEntireNotice.canScrollVertically(1) &&
                    lastVisibleItemPosition == itemTotalCount
                ) {
                    adapterMain.deleteLoding()
                    model.getMainNotices(++page)
                }

            }
        })
    }


    override fun onPause() {
        super.onPause()
        Log.v("이렇게 되자나", "222222222222")
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    private fun initRecyclerView() {
        binding!!.rvEntireNotice.layoutManager = LinearLayoutManager(context)
        adapterMain = MainNoticeAdapter()
        binding!!.rvEntireNotice.adapter = adapterMain
    }

/*    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.v("000000000000", "000000000000000")
    }

    private fun saveRecyclerViewState() {
        recyclerViewState = binding!!.rvEntireNotice.layoutManager!!.onSaveInstanceState()
        Log.v("111111111111", "1111111111111")
    }

    private fun setSavedRecyclerViewState() {
        binding!!.rvEntireNotice.layoutManager!!.onRestoreInstanceState(recyclerViewState)
        Log.v("22222222222", "22222222222222222")
    }*/
}