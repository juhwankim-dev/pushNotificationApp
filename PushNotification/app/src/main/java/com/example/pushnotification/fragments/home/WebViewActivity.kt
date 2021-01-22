package com.example.pushnotification.fragments.home

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
import com.example.pushnotification.R
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : AppCompatActivity() {

    var lastTimeBackPressed = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        val url = intent.getStringExtra("url")

        webView.webViewClient = WebViewClient()

        webView.setDownloadListener(DownloadListener { url, userAgent, contentDisposition, mimeType, contentLength ->
            try {
                val request =
                    DownloadManager.Request(Uri.parse(url))
                request.setMimeType(mimeType)
                request.addRequestHeader("User-Agent", userAgent)
                request.setDescription("Downloading file")
                var fileName =
                    contentDisposition.replace("inline; filename=", "")
                fileName = fileName.replace("\"".toRegex(), "")
                request.setTitle(fileName)
                request.allowScanningByMediaScanner()
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                request.setDestinationInExternalPublicDir(
                    Environment.DIRECTORY_DOWNLOADS,
                    fileName
                )
                val dm =
                    getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                dm.enqueue(request)
                Toast.makeText(applicationContext, "Downloading File", Toast.LENGTH_LONG)
                    .show()
            } catch (e: Exception) {
                if (ContextCompat.checkSelfPermission(
                        this@WebViewActivity,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )
                    != PackageManager.PERMISSION_GRANTED
                ) { // Should we show an explanation?
                    if (ActivityCompat.shouldShowRequestPermissionRationale(
                            this@WebViewActivity,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                        )
                    ) {
                        Toast.makeText(
                            baseContext,
                            "다운로드를 위해\n동의가 필요합니다.",
                            Toast.LENGTH_LONG
                        ).show()
                        ActivityCompat.requestPermissions(
                            this@WebViewActivity,
                            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                            110
                        )
                    } else {
                        Toast.makeText(
                            baseContext,
                            "다운로드를 위해\n동의가 필요합니다.",
                            Toast.LENGTH_LONG
                        ).show()
                        ActivityCompat.requestPermissions(
                            this@WebViewActivity,
                            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                            110
                        )
                    }
                }
            }
        })

        if (url != null) {
            webView.loadUrl(url)
        }

        btn_browser.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

        btn_share.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "아냥이")
            shareIntent.putExtra(Intent.EXTRA_TEXT, url);
            startActivity(Intent.createChooser(shareIntent, "choose one"));
        }
    }
}

/*override fun onBackPressed() {
    if(webView.canGoBack()) webView.goBack()
    else {
        if(System.currentTimeMillis() - lastTimeBackPressed < 1500){
            finish()
            return
        }
        lastTimeBackPressed = System.currentTimeMillis()
        Toast.makeText(this,"'뒤로' 버튼을 한 번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
    }
}*/

