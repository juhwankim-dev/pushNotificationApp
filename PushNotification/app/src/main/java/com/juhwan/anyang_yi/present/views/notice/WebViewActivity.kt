package com.juhwan.anyang_yi.present.views.notice

import android.Manifest
import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.webkit.*
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.juhwan.anyang_yi.databinding.ActivityWebViewBinding
import java.net.URLDecoder

class WebViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebViewBinding
    private lateinit var url: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        url = intent.getStringExtra("url").toString()

        binding.webView.webViewClient = WebViewClient()
        binding.webView.settings.javaScriptEnabled = true // to load mobile version

        binding.webView.setDownloadListener(DownloadListener { _, userAgent, contentDisposition, mimeType, _ ->
            setDownloadLogic(userAgent, contentDisposition, mimeType)
        })

        binding.webView.loadUrl(url)

        binding.btnBrowser.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

        binding.btnShare.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "아냥이")
            shareIntent.putExtra(Intent.EXTRA_TEXT, url);
            startActivity(Intent.createChooser(shareIntent, "choose one"));
        }
    }

    private fun setDownloadLogic(
        userAgent: String,
        contentDisposition: String,
        mimeType: String
    ) {
        var contentDisposition = contentDisposition
        try {
            val request = DownloadManager.Request(Uri.parse(url))
            val dm = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            contentDisposition = URLDecoder.decode(contentDisposition, "UTF-8") // 파일명이 한글일때 깨지는 현상을 위해 디코딩을 함.
            val fileName = contentDisposition.replace("attachment; filename=", "") // 파일명 앞에 attachment; filename*=UTF-8'' 이런 쓰잘데기 없는 문구가 붙음. 그래서 삭제하는 거임.
            request.setMimeType(mimeType) // mimetype은 파일의 확장자를 뜻함. 이걸 안드로이드에게 안 알려주면 전부 bin 확장자로 다운로드 받아짐.
            request.addRequestHeader("User-Agent", userAgent)
            request.setDescription("Downloading File")
            request.setAllowedOverMetered(true)
            request.setAllowedOverRoaming(true)
            request.setTitle(fileName)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                request.setRequiresCharging(false)
            }
            request.setAllowedOverMetered(true)
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            request.setDestinationInExternalPublicDir(
                Environment.DIRECTORY_DOWNLOADS,
                fileName
            )
            dm.enqueue(request)
            Toast.makeText(applicationContext, "파일이 다운로드됩니다.", Toast.LENGTH_LONG).show()
        } catch (e: Exception) { // 권한이 없어서 실패하는 경우
            if (ContextCompat.checkSelfPermission(
                    this@WebViewActivity,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        this@WebViewActivity,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )
                ) {
                    Toast.makeText(
                        baseContext,
                        "다운로드를 위해\n권한이 필요합니다.",
                        Toast.LENGTH_LONG
                    ).show()
                    ActivityCompat.requestPermissions(
                        this@WebViewActivity,
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        1004
                    )
                } else {
                    Toast.makeText(
                        baseContext,
                        "다운로드를 위해\n권한이 필요합니다.",
                        Toast.LENGTH_LONG
                    ).show()
                    ActivityCompat.requestPermissions(
                        this@WebViewActivity,
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        1004
                    )
                }
            }
        }
    }
}
