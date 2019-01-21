package com.rahmanarif.myservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;

public class BoundService extends Service {

    final String TAG = BoundService.class.getSimpleName();
    MyBinder myBinder = new MyBinder();
    final long startTime = System.currentTimeMillis();

    CountDownTimer countDownTimer = new CountDownTimer(10000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            long elapsedTime = System.currentTimeMillis() - startTime;
            Log.d(TAG, "onTick " + elapsedTime);
        }

        @Override
        public void onFinish() {

        }
    };

    public BoundService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind....");
        countDownTimer.start();
        return myBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind");
        countDownTimer.cancel();
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.d(TAG, "onRebind");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate.....");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    class MyBinder extends Binder {
        BoundService getService() {
            return BoundService.this;
        }
    }
}
