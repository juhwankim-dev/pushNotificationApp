package com.juhwan.anyang_yi.present.views.home.keyword

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.kimcore.inko.Inko
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.juhwan.anyang_yi.R
import com.juhwan.anyang_yi.data.model.KeywordEntity
import com.juhwan.anyang_yi.databinding.ActivityKeywordBinding
import com.juhwan.anyang_yi.present.config.ApplicationClass.Companion.databaseReference
import com.juhwan.anyang_yi.present.config.ApplicationClass.Companion.fcmReference
import com.juhwan.anyang_yi.present.config.BaseActivity
import com.juhwan.anyang_yi.present.utils.KeywordChecker
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class KeywordActivity : BaseActivity<ActivityKeywordBinding>(R.layout.activity_keyword), DeleteButtonListener, SignUpListener {
    private lateinit var map: Map<String, String>// 서버에 있는 키워드를 가져와서 저장할 변수
    private val viewModel: KeywordViewModel by viewModels()
    private lateinit var adapter: KeywordAdapter
    private var registeredKeywordList = arrayListOf<KeywordEntity>()
    private val inko = Inko()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        initEvent()
    }

    private fun initView() {
        binding.rvKeyword.layoutManager = LinearLayoutManager(this)
        adapter = KeywordAdapter(this)
        binding.rvKeyword.adapter = adapter
    }

    private fun initEvent() {
        databaseReference
            .child("keywords")
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {

                }

                override fun onDataChange(p0: DataSnapshot) {
                    map = p0.value as Map<String, String>
                }
            })

        binding.etKeyword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.toString().trim().isNotEmpty()) {
                    binding.btnSubscribe.isEnabled = true
                    binding.btnSubscribe.setTextColor(ContextCompat.getColor(applicationContext, R.color.mainBlue))
                } else {
                    binding.btnSubscribe.isEnabled = false
                    binding.btnSubscribe.setTextColor(ContextCompat.getColor(applicationContext, R.color.colorBlackDisabled2))
                }
            }
        })

        viewModel.readKeywordList().observe(this) {
            adapter.setList(it)
            binding.tvRegisteredKeyword.text = it.size.toString()
            registeredKeywordList.clear()
            registeredKeywordList.addAll(it)
        }

        binding.btnSubscribe.setOnClickListener {
            var keyword = binding.etKeyword.text.toString()
            try {
                KeywordChecker.check(keyword, registeredKeywordList)
                viewModel.getSearchResultList(keyword, 0)
            } catch (e: Exception) {
                showToastMessage(e.message.toString())
            }
        }

        viewModel.searchResultList.observe(this) {
            var keyword = binding.etKeyword.text.toString()

            if(it.isEmpty()){
                KeywordDialog(this, this).createDialog(keyword)
            } else {
                subscribe(keyword)
            }
        }

        viewModel.problem.observe(this) {
            showToastMessage(resources.getString(R.string.database_error))
            Log.d("주환", "initEvent: ${it.message}")
        }

        binding.ivBack.setOnClickListener {
            finish()
        }
    }

    private fun subscribe(keyword: String) {
        showProgress()
        var englishKeyword = inko.ko2en(keyword)

        fcmReference.subscribeToTopic(englishKeyword)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    var num = "1" // 기본값 1
                    if (map.containsKey(keyword)) {
                        num = (map.getValue(keyword).toInt() + 1).toString() // 구독자 수 +1
                    }

                    databaseReference.child("keywords").child(keyword).setValue(num)
                    viewModel.writeKeyword(keyword)
                } else {
                    showToastMessage(resources.getString(R.string.network_error))
                }
                hideProgress()
            }
        binding.etKeyword.text = null
    }

    override fun unSubscribe(keyword: String) {
        showProgress()
        var englishKeyword = inko.ko2en(keyword)

        fcmReference.unsubscribeFromTopic(englishKeyword)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    var num = map.getValue(keyword).toInt() - 1 // 구독자 수 -1
                    databaseReference.child("keywords").child(keyword).setValue(num.toString())
                    viewModel.deleteKeyword(keyword)
                } else {
                    showToastMessage(resources.getString(R.string.network_error))
                }
                hideProgress()
            }
    }

    override fun signUpListener(keyword: String) {
        subscribe(keyword)
        binding.etKeyword.text = null
    }

    private fun showProgress() {
        this.window.addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        binding.lottieViewSheep.visibility = View.VISIBLE
        binding.lottieViewSheep.playAnimation()
    }

    private fun hideProgress() {
        this.window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        binding.lottieViewSheep.visibility = View.GONE
    }
}