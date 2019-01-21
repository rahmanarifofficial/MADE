package com.rahmanarif.myintentapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.rahmanarif.myintentapp.model.Person;

public class MoveWithObjectActivity extends AppCompatActivity {
    public static final String EXTRA_PERSON = "extra_person";

    private TextView tvObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_with_object);

        tvObject = findViewById(R.id.tv_object_received);

        Person person = getIntent().getParcelableExtra(EXTRA_PERSON);
        String text = "Nama: " + person.getName() + "\nUmur: " + person.getAge() +
                "\nEmail: " + person.getEmail() + "\nKota: " + person.getCity();
        tvObject.setText(text);
    }
}
