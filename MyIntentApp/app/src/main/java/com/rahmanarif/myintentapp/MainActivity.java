package com.rahmanarif.myintentapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rahmanarif.myintentapp.model.Person;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnMoveActivity, btnMoveWithDataActivity, btnMoveWithObject, btnDialNumber, btnMoveForResult;
    private TextView tvResult;
    private int REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMoveActivity = findViewById(R.id.btn_move_activity);
        btnMoveWithDataActivity = findViewById(R.id.btn_move_activity_data);
        btnMoveWithObject = findViewById(R.id.btn_move_activity_object);
        btnDialNumber = findViewById(R.id.btn_dial_number);
        btnMoveForResult = findViewById(R.id.btn_move_for_result);
        tvResult = findViewById(R.id.tv_result);

        btnMoveActivity.setOnClickListener(this);
        btnMoveWithDataActivity.setOnClickListener(this);
        btnMoveWithObject.setOnClickListener(this);
        btnDialNumber.setOnClickListener(this);
        btnMoveForResult.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE){
            if (resultCode == MoveForResultActivity.RESULT_CODE){
                int selectedValue = data.getIntExtra(MoveForResultActivity.EXTRA_SELECTED_VALUE, 0);
                tvResult.setText(String.format("Hasil : %s", selectedValue));
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_move_activity:
                startActivity(new Intent(this, MoveActivity.class));
                break;
            case R.id.btn_move_activity_data:
                Intent moveWithDataIntent = new Intent(MainActivity.this, MoveWithDataActivity.class);
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_NAME, "Arif Rahman");
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_AGE, 20);
                startActivity(moveWithDataIntent);
                break;
            case R.id.btn_move_activity_object:
                Person person = new Person();
                person.setName("Arif Rahman");
                person.setAge(21);
                person.setEmail("rahmanarifofficial@gmail.com");
                person.setCity("Malang");

                Intent moveWithObjectIntent = new Intent(this, MoveWithObjectActivity.class);
                moveWithObjectIntent.putExtra(MoveWithObjectActivity.EXTRA_PERSON, person);
                startActivity(moveWithObjectIntent);
                break;
            case R.id.btn_dial_number:
                String phoneNumber= "089630420175";
                Intent dialPhoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phoneNumber));
                startActivity(dialPhoneIntent);
                break;
            case R.id.btn_move_for_result:
                Intent moveForResultIntent = new Intent(MainActivity.this, MoveForResultActivity.class);
                startActivityForResult(moveForResultIntent, REQUEST_CODE);
                break;
        }
    }
}
