package com.juhwan.anyang_yi.present.views.contact

import android.Manifest
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.juhwan.anyang_yi.R
import com.juhwan.anyang_yi.data.mapper.ContactMapper
import com.juhwan.anyang_yi.data.model.ContactEntity
import com.juhwan.anyang_yi.databinding.FragmentContactBinding
import com.juhwan.anyang_yi.domain.model.Contact
import com.juhwan.anyang_yi.present.config.ApplicationClass.Companion.databaseReference
import com.juhwan.anyang_yi.present.config.BaseFragment

class ContactFragment : BaseFragment<FragmentContactBinding>(R.layout.fragment_contact) {
    private val requiredPermissions = arrayOf(Manifest.permission.CALL_PHONE)
    private lateinit var contactAdapter: ContactAdapter
    private lateinit var filterAdapter: ContactFilterAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requestPermissions(requiredPermissions, 0) // 전화 권한 동의
        initView()
        initEvent()
    }

    private fun initView() {
        binding!!.searchViewContact.maxWidth = Int.MAX_VALUE

        binding!!.rvContactLClass.layoutManager = LinearLayoutManager(context)
        contactAdapter = ContactAdapter()
        binding!!.rvContactLClass.adapter = contactAdapter

        binding!!.rvContactMClass.layoutManager = LinearLayoutManager(context)
        filterAdapter = ContactFilterAdapter()
        binding!!.rvContactMClass.adapter = filterAdapter

        binding!!.lottieSheep.visibility = View.VISIBLE
        binding!!.lottieSheep.playAnimation()
    }

    private fun initEvent() {
        contactAdapter.setItemClickListener(object : ContactAdapter.ItemClickListener{
            override fun onClick(category: String) {
                filterAdapter.selectDepartment(category)
            }
        })

        filterAdapter.setItemClickListener(object : ContactFilterAdapter.ItemClickListener{
            override fun onClick(contact: Contact) {
                ContactDialog(requireActivity()).createDialog(contact)
            }
        })

        binding!!.searchViewContact.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterAdapter.search(getArrangedKeyword(newText))
                return true
            }
        })

        databaseReference.child("contact").addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val contactList = ArrayList<Contact>()

                for(child in snapshot.children) {
                    var map = child.value as HashMap<String, String>

                    contactList.add(
                        ContactMapper(
                            ContactEntity(
                                category = map["대분류"] ?: "",
                                department = map["부서명"] ?: "",
                                job = map["업무직책"] ?: "",
                                location = map["위치"] ?: "",
                                tel = map["전화번호"] ?: "",
                            )
                        )
                    )
                }

                contactAdapter.setList(contactList)
                filterAdapter.setList(contactList)

                binding!!.lottieSheep.visibility = View.GONE
            }

            override fun onCancelled(error: DatabaseError) {
                binding!!.lottieSheep.visibility = View.GONE
                showToastMessage(resources.getString(R.string.network_error))
            }
        })
    }

    private fun getArrangedKeyword(str: String?): String {
        return str.toString().replace(" ","").replace("-", "")
    }
}