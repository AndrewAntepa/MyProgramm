package com.example.myprogramm;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.HashMap;
import java.util.LinkedList;

public class FirstPage extends Fragment {
    Button addButton;
    Intent intent;
    ListView pillsList;

    SQLiteDatabase sdb;
    MyOpenHelper myOpenHelper;
    SimpleAdapter simpleAdapter;
    LinkedList<HashMap<String, Object>> mapPills = new LinkedList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_page, container, false);

        addButton = view.findViewById(R.id.addButton);
        pillsList = view.findViewById(R.id.listview);
        intent = new Intent(getActivity(), AddPage.class);

        myOpenHelper = new MyOpenHelper(view.getContext());
        sdb = myOpenHelper.getWritableDatabase();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
                myOpenHelper.close();
                sdb.close();
            }
        });

        String[] keyFrom = {"tittle", "start", "next", "amount", "image"};
        int [] idTo = {R.id.tittleExam, R.id.startExam, R.id.nextExam, R.id.amountExam, R.id.imageExam};
        String[] keyQuery = {MyOpenHelper.COLUMN_TITLE, MyOpenHelper.COLUMN_START, MyOpenHelper.COLUMN_INTERVAL, MyOpenHelper.COLUMN_AMOUNT_TIME};
        simpleAdapter = new SimpleAdapter(getContext(), mapPills, R.layout.pils_example, keyFrom, idTo);
        pillsList.setAdapter(simpleAdapter);
        Cursor cursor = sdb.query(MyOpenHelper.TABLE_NAME, keyQuery, null, null, null, null, null);

//        cursor.moveToFirst();
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

        return view;
    }
}
