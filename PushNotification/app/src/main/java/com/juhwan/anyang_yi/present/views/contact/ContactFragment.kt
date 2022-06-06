package com.juhwan.anyang_yi.present.views.contact

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.juhwan.anyang_yi.R
import com.juhwan.anyang_yi.databinding.FragmentContactBinding
import com.juhwan.anyang_yi.present.config.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactFragment : BaseFragment<FragmentContactBinding>(R.layout.fragment_contact),
    SelectDepartmentListener  {
    private val viewModel: ContactViewModel by viewModels()
    //private val requiredPermissions = arrayOf(Manifest.permission.CALL_PHONE)
    private lateinit var adapter: ContactAdapter
    private lateinit var filterAdapter: ContactFilterAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        if(ContactRepository_.isFinished.value == null){
//            ContactRepository_.requestPost()
//            binding!!.lottieSheep.visibility = View.VISIBLE
//            binding!!.lottieSheep.playAnimation()
//        }
//
//        ContactRepository_.isFinished.observe(viewLifecycleOwner, Observer{
//            binding!!.lottieSheep.visibility = View.GONE
//            initRecyclerView()
//        })

        //requestPermissions(requiredPermissions, 0) // 전화 권한 동의
        viewModel.getContact()
        initView()
        initEvent()
    }

    private fun initView() {
        binding!!.searchViewContact.maxWidth = Int.MAX_VALUE

        binding!!.rvContactLClass.layoutManager = LinearLayoutManager(context)
        adapter = ContactAdapter(this)
        binding!!.rvContactLClass.adapter = adapter

        binding!!.rvContactMClass.layoutManager = LinearLayoutManager(context)
        filterAdapter = ContactFilterAdapter()
        binding!!.rvContactMClass.adapter = filterAdapter
    }

    private fun initEvent() {
        viewModel.contact.observe(viewLifecycleOwner) {
            adapter.setList(it.category)
            filterAdapter.setList(it.departmentList)
        }

        binding!!.searchViewContact.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterAdapter.filter(getCleanKeyword(newText))
                return true
            }
        })
    }

    override fun selectDepartment(department: String) {
        filterAdapter.selectDepartment(department)
    }

    private fun getCleanKeyword(str: String?): String {
        return str.toString().replace(" ","").replace("-", "").replace("031", "")
    }
}