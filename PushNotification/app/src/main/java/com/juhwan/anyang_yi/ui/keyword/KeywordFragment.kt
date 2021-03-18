package com.juhwan.anyang_yi.ui.keyword

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.juhwan.anyang_yi.R
import com.github.kimcore.inko.Inko
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.messaging.FirebaseMessaging
import com.juhwan.anyang_yi.database.Keyword
import com.juhwan.anyang_yi.databinding.FragmentKeywordBinding
import com.juhwan.anyang_yi.repository.MainNoticeRepository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.regex.Pattern

const val KEYWORD_LIMIT = 10

class KeywordFragment : Fragment(), DeleteButtonListener {

    private lateinit var map: Map<String, String>// 서버에 있는 키워드를 가져와서 저장할 변수

    private val databaseReference = FirebaseDatabase.getInstance().reference
    private var binding: FragmentKeywordBinding? = null
    private val model: KeywordViewModel by viewModels()
    private lateinit var adapter: KeywordAdapter
    private var myKeywordList = arrayListOf<Keyword>()
    private val inko = Inko()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        FirebaseDatabase.getInstance().reference
            .child("keywords")
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {

                }

                override fun onDataChange(p0: DataSnapshot) {
                    map = p0.value as Map<String, String>
                }
            })

        binding = FragmentKeywordBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        model.getAll().observe(viewLifecycleOwner, Observer {
            adapter.setList(it)
            adapter.notifyDataSetChanged()
            binding!!.tvRegisteredKeyword.text = it.size.toString()
            myKeywordList.clear()
            myKeywordList.addAll(it)
        })


        binding!!.btnSubscribe.setOnClickListener {
            var enteredKeyword = binding!!.etKeyword.text.toString()
            if (isValidKeyword(enteredKeyword)) {
                subscribe(enteredKeyword)
            }
        }

        binding!!.etKeyword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.toString().trim().isNotEmpty()) {
                    binding!!.btnSubscribe.isEnabled = true
                    binding!!.btnSubscribe.setTextColor(resources.getColor(R.color.mainBlue))
                } else {
                    binding!!.btnSubscribe.isEnabled = false
                    binding!!.btnSubscribe.setTextColor(resources.getColor(R.color.colorBlackDisabled2))
                }
            }
        })
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    private fun initRecyclerView() {
        binding!!.rvKeyword.layoutManager = LinearLayoutManager(context)
        adapter = KeywordAdapter(this)
        binding!!.rvKeyword.adapter = adapter
    }

    private fun isValidKeyword(enteredKeyword: String): Boolean {
        return isNotExceed() && isCorrectType(enteredKeyword) && isNotRegistered(enteredKeyword)
    }

    private fun isNotExceed(): Boolean {
        if (KEYWORD_LIMIT > binding!!.tvRegisteredKeyword.text.toString().toInt()) return true
        showMessage("키워드는 10개까지 등록 가능합니다.")
        return false
    }

    private fun isCorrectType(enteredKeyword: String): Boolean {
        var ps = Pattern.compile("^[0-9ㄱ-ㅎ가-힣]+$");
        if (ps.matcher(enteredKeyword).matches()) return true
        showMessage("한글과 숫자만 등록 가능합니다.")
        return false
    }

    private fun isNotRegistered(enteredKeyword: String): Boolean {
        if (!myKeywordList.contains(Keyword(enteredKeyword))) return true
        showMessage("이미 등록된 키워드입니다.")
        return false
    }

    private fun subscribe(enteredKeyword: String) {
        var englishKeyword = inko.ko2en(enteredKeyword)
        showProgress()
        FirebaseMessaging.getInstance().subscribeToTopic(englishKeyword)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    var num = "1" // 기본값 1
                    if (map.containsKey(enteredKeyword)) {
                        num = (map.getValue(enteredKeyword).toInt() + 1).toString() // 구독자 수 +1
                    }

                    lifecycleScope.launch(Dispatchers.IO){
                        databaseReference.child("keywords").child(enteredKeyword).setValue(num)
                        model.insert(Keyword(enteredKeyword))
                    }

                } else {
                    showMessage("네트워크 상태가 불안정 합니다.")
                }
                hideProgress()
            }
        binding!!.etKeyword.text = null
    }

    override fun unSubscribe(keyword: String) {
        showProgress()
        var englishKeyword = inko.ko2en(keyword)

        FirebaseMessaging.getInstance().unsubscribeFromTopic(englishKeyword)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    lifecycleScope.launch(Dispatchers.IO){
                        var num = map.getValue(keyword).toInt() - 1 // 구독자 수 -1
                        databaseReference.child("keywords").child(keyword).setValue(num.toString())
                        model.deleteKeywordByTitle(keyword)
                    }
                } else {
                    showMessage("네트워크 상태가 불안정 합니다.")
                }
                hideProgress()
            }
    }

    private fun showProgress() {
        binding!!.progressBarKeyword.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        //requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        binding!!.progressBarKeyword.visibility = View.GONE // TODO 이슈 해결하기
    }

    private fun showMessage(msg: String) {
        Snackbar.make(binding!!.keywordFragment, msg, Snackbar.LENGTH_SHORT).show();
    }
}