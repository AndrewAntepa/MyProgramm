package com.example.myprogramm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
//    Button b1, b2, b3, b4;
    FragmentManager fragmentManager;
    FirstPage firstPage;
    SecondPage secondPage = new SecondPage();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
//        b1 = findViewById(R.id.button1);
//        b2 = findViewById(R.id.button2);
//        b3 = findViewById(R.id.button3);
//        b4 = findViewById(R.id.button4);
        firstPage = new FirstPage();

        if(savedInstanceState == null){
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.container, firstPage);
            fragmentTransaction.commit();
        }

//        b1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                if(fragmentManager != null){
//                    fragmentTransaction.replace(R.id.container, firstPage);
//                    fragmentTransaction.commit();
//                }
//            }
//        });
    }
}