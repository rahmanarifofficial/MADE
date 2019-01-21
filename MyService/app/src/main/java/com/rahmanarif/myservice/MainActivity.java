package com.rahmanarif.myservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnStartService, btnStartIntentService, btnStartBoundService, btnStopBoundService;
    boolean mServiceBound = false;
    BoundService boundService;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            BoundService.MyBinder myBinder = (BoundService.MyBinder) service;
            boundService = myBinder.getService();
            mServiceBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mServiceBound = false;
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mServiceBound){
            unbindService(serviceConnection);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStartService = findViewById(R.id.btn_start_service);
        btnStartIntentService = findViewById(R.id.btn_start_intent_service);
        btnStartBoundService = findViewById(R.id.btn_start_bound_service);
        btnStopBoundService = findViewById(R.id.btn_stop_bound_service);

        btnStartService.setOnClickListener(this);
        btnStartIntentService.setOnClickListener(this);
        btnStartBoundService.setOnClickListener(this);
        btnStopBoundService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start_service:
                startService(new Intent(MainActivity.this, OriginService.class));
                break;
            case R.id.btn_start_intent_service:
                startService(new Intent(MainActivity.this, DicodingIntentService.class)
                        .putExtra(DicodingIntentService.EXTRA_DURATION, 5000));
                break;
            case R.id.btn_start_bound_service:
                bindService(new Intent(MainActivity.this, BoundService.class), serviceConnection, BIND_AUTO_CREATE);
                break;
            case R.id.btn_stop_bound_service:
                unbindService(serviceConnection);
                break;
        }
    }
}
