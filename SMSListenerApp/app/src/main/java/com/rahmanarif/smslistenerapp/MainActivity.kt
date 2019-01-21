package com.rahmanarif.smslistenerapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.jar.Manifest
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import org.jetbrains.anko.startService


class MainActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        val ACTION_DOWNLOAD_STATUS: String = "download_status"
    }

    private var downloadReceiver: BroadcastReceiver? = null

    val SMS_REQUEST_CODE: Int = 101

    override fun onClick(v: View?) {
        if (v?.id == R.id.btn_permission){
           PermissionManager.check(this, android.Manifest.permission.RECEIVE_SMS, SMS_REQUEST_CODE)
        } else if (v?.id == R.id.btn_download){
            val downloadServiceIntent = Intent(this, DownloadService::class.java)
            startService(downloadServiceIntent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (downloadReceiver!=null){
            unregisterReceiver(downloadReceiver)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == SMS_REQUEST_CODE){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Sms receiver permission diterima", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Sms receiver permission ditolak", Toast.LENGTH_SHORT).show();
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_permission.setOnClickListener(this)
        btn_download.setOnClickListener{this}

        downloadReceiver = object : BroadcastReceiver(){
            override fun onReceive(context: Context?, intent: Intent?) {
                Log.d(DownloadService.TAG,"Download Selesai")
                Toast.makeText(context, "Download Selesai", Toast.LENGTH_SHORT).show()
            }
        }
        val downloadIntentFilter = IntentFilter(ACTION_DOWNLOAD_STATUS)
        registerReceiver(downloadReceiver, downloadIntentFilter)
    }
}
