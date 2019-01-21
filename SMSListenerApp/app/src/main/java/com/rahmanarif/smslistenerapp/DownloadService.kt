package com.rahmanarif.smslistenerapp

import android.app.IntentService
import android.content.Intent
import android.content.Context
import android.util.Log

class DownloadService : IntentService("DownloadService") {
    companion object {
        val TAG: String = "DownloadService"
    }
    override fun onHandleIntent(intent: Intent?) {
        Log.d(TAG, "Download Service dijalankan")
        if (intent != null){
            try {
                Thread.sleep(5000)
            }catch (e: InterruptedException){
                e.printStackTrace()
            }
            val notifyFinishIntent = Intent(MainActivity.ACTION_DOWNLOAD_STATUS)
            sendBroadcast(notifyFinishIntent)
        }
    }

}
