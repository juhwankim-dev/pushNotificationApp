package com.juhwan.anyang_yi

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.juhwan.anyang_yi.fragments.home.WebViewActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    private val TAG = "FirebaseService"

    // FirebaseInstanceIdService는 이제 사라짐. 이제 이걸 사용함
    override fun onNewToken(token: String?) {
        // 토큰 값을 따로 저장해둔다.
        val pref = this.getSharedPreferences("token", Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString("token", token).apply()
        editor.commit()

        Log.v("토큰값: ", token.toString())
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        val pref = this.getSharedPreferences("pushNotificaiton", 0)
        val isPushOn = pref.getBoolean("isPushOn", true) // 푸시알림 On한 경우

        if(isPushOn){
            val currentTitle = remoteMessage!!.data["title"]
            val prefNotice = this.getSharedPreferences("lastTitle", Context.MODE_PRIVATE)
            val lastTitle = prefNotice.getString("lastTitle", "null")

            if(currentTitle != lastTitle){ // 연달아 중복으로 온 메시지가 아니라면
                if(remoteMessage!!.data.isNotEmpty()){
                    sendNotification(remoteMessage)
                }

                val editor = prefNotice.edit()
                editor.putString("lastTitle", remoteMessage!!.data["title"]).apply()
                editor.commit()
            }
            // 중복해서 왔으면 키워드가 여러개 포함돼서 그런거니까 그냥 무시 ..
        }
    }

    private fun sendNotification(remoteMessage: RemoteMessage) {
        // RequestCode, Id를 고유값으로 지정하여 알림이 개별 표시되도록 함
        val uniId: Int = (System.currentTimeMillis() / 7).toInt()

        // 일회용 PendingIntent
        // PendingIntent : Intent 의 실행 권한을 외부의 어플리케이션에게 위임한다.
        val intent = Intent(this, WebViewActivity::class.java)
        intent.putExtra("url", remoteMessage.data["url"])
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP) // Activity Stack 을 경로만 남긴다. A-B-C-D-B => A-B
        val pendingIntent = PendingIntent.getActivity(this, uniId, intent, PendingIntent.FLAG_ONE_SHOT)

        // 알림 채널 이름
        val channelId = getString(R.string.firebase_notification_channel_id)

        // 알림 소리
        val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        // 알림에 대한 UI 정보와 작업을 지정한다.
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.mipmap.ic_sheep_notification) // 아이콘 설정
            .setContentTitle("공지사항") // 제목
            .setContentText(remoteMessage.data["title"]) // 메시지 내용
            .setAutoCancel(true)
            .setSound(soundUri) // 알림 소리
            .setContentIntent(pendingIntent) // 알림 실행 시 Intent

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // 오레오 버전 이후에는 채널이 필요하다.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, "Notice", NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }

        // 알림 생성
        notificationManager.notify(uniId, notificationBuilder.build())
    }
}
