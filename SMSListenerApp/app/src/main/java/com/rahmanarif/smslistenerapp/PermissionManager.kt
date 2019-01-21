package com.rahmanarif.smslistenerapp

import android.support.v4.app.ActivityCompat
import android.content.pm.PackageManager
import android.app.Activity
import android.Manifest.permission





class PermissionManager {
    companion object {
        fun check(activity: Activity, permission: String, requestCode: Int){
            if (ActivityCompat.checkSelfPermission(activity, permission)!=PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(activity, arrayOf(permission), requestCode)
            }
        }
    }
}