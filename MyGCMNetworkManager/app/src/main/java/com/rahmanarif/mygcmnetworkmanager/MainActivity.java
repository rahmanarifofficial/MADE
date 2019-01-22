package com.rahmanarif.mygcmnetworkmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSetSchedular, btnCancelSchedular;
    private SchedulerTask schedulerTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSetSchedular = findViewById(R.id.btn_set_scheduler);
        btnCancelSchedular = findViewById(R.id.btn_cancel_scheduler);
        btnSetSchedular.setOnClickListener(this);
        btnCancelSchedular.setOnClickListener(this);
        schedulerTask = new SchedulerTask(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_set_scheduler){
            schedulerTask.createPeriodicTask();
            Toast.makeText(this, "Periodic Task Created", Toast.LENGTH_SHORT).show();
        }
        if (v.getId() == R.id.btn_cancel_scheduler){
            schedulerTask.cancelPeriodicTask();
            Toast.makeText(this, "Periodic Task Cancelled", Toast.LENGTH_SHORT).show();
        }
    }
}
