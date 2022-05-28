package com.juhwan.anyang_yi.present.views.contact

import android.Manifest
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.juhwan.anyang_yi.databinding.FragmentContactBinding
import com.juhwan.anyang_yi.data.repository.ContactRepository

class ContactFragment : Fragment(),
    SelectDepartmentListener  {

    private val requiredPermissions = arrayOf(Manifest.permission.CALL_PHONE)

    private var binding: FragmentContactBinding? = null
    private lateinit var adapter: ContactAdapter
    private lateinit var filterAdapter: ContactFilterAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContactBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(ContactRepository.isFinished.value == null){
            ContactRepository.requestPost()
            binding!!.lottieSheep.visibility = View.VISIBLE
            binding!!.lottieSheep.playAnimation()
        }

        ContactRepository.isFinished.observe(viewLifecycleOwner, Observer{
            binding!!.lottieSheep.visibility = View.GONE
            initRecyclerView()
        })

        requestPermissions(requiredPermissions, 0) // 전화 권한 동의

        binding!!.searchViewContact.maxWidth = Int.MAX_VALUE

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

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    private fun initRecyclerView() {
        binding!!.rvContactLClass.layoutManager = LinearLayoutManager(context)
        adapter = ContactAdapter(this)
        binding!!.rvContactLClass.adapter = adapter

        binding!!.rvContactMClass.layoutManager = LinearLayoutManager(context)
        filterAdapter = ContactFilterAdapter()
        binding!!.rvContactMClass.adapter = filterAdapter
    }

    override fun selectDepartment(department: String) {
        filterAdapter.selectDepartment(department)
    }

    private fun getCleanKeyword(str: String?): String {
        return str.toString().replace(" ","").replace("-", "").replace("031", "")
    }
}