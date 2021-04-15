package com.example.myprogramm;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class AddPage extends AppCompatActivity {
    Button add;
    EditText editText;
    DatePicker datePicker;
    Spinner interval, dose;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_page);

        add = findViewById(R.id.addPill);
        editText = findViewById(R.id.editTittle);
        datePicker = findViewById(R.id.datePick);
        interval = findViewById(R.id.intervalSpinner);
        dose = findViewById(R.id.doseSpinner);
    }
//
//    public void Add() {
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(myOpenHelper.COLUMN_NAME, editText.getText().toString());
//        contentValues.put(MyOpenHelper.COLUMN_NEXT, "*Следующий прием*");
//        contentValues.put(MyOpenHelper.COLUMN_END, "*Осталось__раз*");
//        contentValues.put(MyOpenHelper.COLUMN_COVER, R.drawable.pill_example);
//        sdb.insert(MyOpenHelper.TABLE_NAME, null, contentValues);
//    }
}