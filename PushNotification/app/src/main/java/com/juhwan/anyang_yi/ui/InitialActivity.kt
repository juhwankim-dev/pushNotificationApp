package com.juhwan.anyang_yi.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.github.kimcore.inko.Inko
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.messaging.FirebaseMessaging
import com.juhwan.anyang_yi.KeywordSelectListener
import com.juhwan.anyang_yi.R
import com.juhwan.anyang_yi.database.Keyword
import com.juhwan.anyang_yi.repository.InitialKeywordRepository
import com.juhwan.anyang_yi.ui.notice.keyword.KeywordViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
/*
class InitialActivity : AppCompatActivity(){

    private val databaseReference = FirebaseDatabase.getInstance().reference
    private val model: KeywordViewModel by viewModels()
    private val inko = Inko()
    private lateinit var map: Map<String, String>// 서버에 있는 키워드를 가져와서 저장할 변수

    private lateinit var binding: ActivityInitialBinding
    private lateinit var internalUnivAdapter: InitialKeywordAdapter
    private lateinit var externalUnivAdapter: InitialKeywordAdapter
    private lateinit var univAdapter: InitialKeywordAdapter

    private val selectedKeyword = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInitialBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        loadKeywordFromFirebase()

        binding.btnSkip.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.btnNext.setOnClickListener {
            if(selectedKeyword.size > 10){
                Snackbar.make(binding.layout, "키워드는 최대 10개까지 설정할 수 있습니다.", Snackbar.LENGTH_SHORT).show()
            } else if(selectedKeyword.size != 0) {
                for(i in selectedKeyword){
                    subscribe(i)
                }
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
    }

    private fun initRecyclerView() {
        binding.rvInternalUniv.layoutManager = GridLayoutManager(this, 3)
        internalUnivAdapter = InitialKeywordAdapter(InitialKeywordRepository.internalUnivKeyword, this)
        binding.rvInternalUniv.adapter = internalUnivAdapter

        binding.rvExternalUniv.layoutManager = GridLayoutManager(this, 3)
        externalUnivAdapter = InitialKeywordAdapter(InitialKeywordRepository.externalUnivKeyword, this)
        binding.rvExternalUniv.adapter = externalUnivAdapter

        binding.rvUniv.layoutManager = GridLayoutManager(this, 3)
        univAdapter = InitialKeywordAdapter(InitialKeywordRepository.univKeyword, this)
        binding.rvUniv.adapter = univAdapter
    }

    private fun loadKeywordFromFirebase(){
        FirebaseDatabase.getInstance().reference
            .child("keywords")
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {

                }

                override fun onDataChange(p0: DataSnapshot) {
                    map = p0.value as Map<String, String>
                }
            })
    }

    override fun keywordSelect(keyword: String) {
        selectedKeyword.add(keyword)

        if(selectedKeyword.size == 1){
            binding.btnNext.setBackgroundResource(R.drawable.button_blue_round_fill)
        }
    }

    override fun keywordUnSelect(keyword: String) {
        selectedKeyword.remove(keyword)

        if(selectedKeyword.size == 0 ){
            binding.btnNext.setBackgroundResource(R.drawable.button_gray_round_fill)
        }
    }

    private fun subscribe(keyword: String) {
        var englishKeyword = inko.ko2en(keyword)

        FirebaseMessaging.getInstance().subscribeToTopic(englishKeyword)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    var num = (map.getValue(englishKeyword).toInt() + 1).toString() // 구독자 수 +1

                    lifecycleScope.launch(Dispatchers.IO){
                        databaseReference.child("keywords").child(englishKeyword).setValue(num)
                        model.insert(Keyword(englishKeyword))
                    }

                } else {
                    Snackbar.make(binding.layout, "네트워크 상태를 확인하세요", Snackbar.LENGTH_SHORT).show()
                }
            }
    }
}
*/