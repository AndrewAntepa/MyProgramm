package com.example.myprogramm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    Button addButton;
    Intent intent;
    ListView pillsList;

    SQLiteDatabase sdb;
    MyOpenHelper myOpenHelper;
    SimpleAdapter simpleAdapter;
    LinkedList<HashMap<String, Object>> mapPills = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addButton = findViewById(R.id.addButton);
        pillsList = findViewById(R.id.listview);
        intent = new Intent(MainActivity.this, AddPage.class);

        myOpenHelper = new MyOpenHelper(getApplicationContext());
        sdb = myOpenHelper.getWritableDatabase();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
        addDataBase();
        Intent serv = new Intent(MainActivity.this, NotifyService.class);
        startService(intent);
//        alarmNotifyStart(mapPills);
    }

    public void addDataBase(){
        String[] keyFrom = {"tittle", "start", "next", "amount", "image"};
        int [] idTo = {R.id.tittleExam, R.id.startExam, R.id.nextExam, R.id.amountExam, R.id.imageExam};
        String[] keyQuery = {MyOpenHelper.COLUMN_TITLE, MyOpenHelper.COLUMN_START, MyOpenHelper.COLUMN_INTERVAL, MyOpenHelper.COLUMN_AMOUNT_TIME};
        simpleAdapter = new SimpleAdapter(getApplicationContext(), mapPills, R.layout.pils_example, keyFrom, idTo);
        pillsList.setAdapter(simpleAdapter);
        Cursor cursor = sdb.query(MyOpenHelper.TABLE_NAME, keyQuery, null, null, null, null, null);

        while (cursor.moveToNext()) {
            String tittle = cursor.getString(cursor.getColumnIndex(MyOpenHelper.COLUMN_TITLE));
            String start = cursor.getString(cursor.getColumnIndex(MyOpenHelper.COLUMN_START));
            String next = cursor.getString(cursor.getColumnIndex(MyOpenHelper.COLUMN_INTERVAL));
            String amount = cursor.getString(cursor.getColumnIndex(MyOpenHelper.COLUMN_AMOUNT_TIME));

            HashMap<String, Object> map = new HashMap<>();
            map.put("tittle", tittle);
            map.put("start", start);
            map.put("next", next);
            map.put("amount", amount);
            map.put("image", R.drawable.pill_example);
            mapPills.add(map);
        }
        simpleAdapter.notifyDataSetChanged();
        cursor.close();
        myOpenHelper.close();
        sdb.close();
    }

//    public void alarmNotifyStart(LinkedList<HashMap<String, Object>> o){
//        Intent intent = new Intent(getApplicationContext(), NotifyService.class);
//        intent.putExtra("linedList", o);
//        Objects.requireNonNull(getApplicationContext()).startService(intent);
//        /*????????????????????????????????????????????*/
//    }
}