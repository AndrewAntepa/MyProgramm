package com.example.myprogramm;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class AddPage extends AppCompatActivity {
    Button byDate, byTime;
    AddByDays addByDays;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_page);

        byDate = findViewById(R.id.byDays);
        byTime = findViewById(R.id.ByTime);
        addByDays = new AddByDays();
        fragmentManager = getSupportFragmentManager();


        byDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.addFrame, addByDays);
                fragmentTransaction.commit();
            }
        });
    }
}
