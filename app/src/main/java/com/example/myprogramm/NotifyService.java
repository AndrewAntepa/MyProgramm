package com.example.myprogramm;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.widget.SimpleAdapter;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.UiThread;
import androidx.core.app.NotificationCompat;

import java.text.BreakIterator;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadPoolExecutor;

public class NotifyService extends Service {
    private int NOTIFY_ID = 101;
    private static String CHANNEL_ID = "Cat channel";
    LinkedList<HashMap<String, Object>> list = new LinkedList<>();
//    SQLiteDatabase sdb;
//    MyOpenHelper myOpenHelper;

    public NotifyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        myOpenHelper = new MyOpenHelper(getApplicationContext());
//        sdb = myOpenHelper.getWritableDatabase();
//        readDataBase();

        for (int i = 0; i < list.size(); i++) {

        }

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationChannel nChannel = new NotificationChannel(CHANNEL_ID, "Пора принять лекарство!", NotificationManager.IMPORTANCE_DEFAULT);
        notificationManager.createNotificationChannel(nChannel);
        Intent clickIntent = new Intent(this, MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, clickIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        Intent doneIntent = new Intent(this, GoodJog.class);
        PendingIntent donePend = PendingIntent.getActivity(this, 0, doneIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Прими лекарство")
                .setContentText("ВРемя принять *Название*")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setChannelId(CHANNEL_ID)
                .setContentIntent(pIntent)
                .addAction(R.drawable.ic_launcher_background, "Выполнено", donePend)
                .setAutoCancel(true);
        Notification notification = nBuilder.build();
        notificationManager.notify(NOTIFY_ID++, notification);
        alarmNotify(getApplicationContext(), 3600000);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static void alarmNotify(Context context, long inter) {
        Intent aintent = new Intent(context, NotifyService.class);
        PendingIntent alarmPend = PendingIntent.getService(context, 0, aintent, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC, inter, alarmPend);
    }

//    public void readDataBase(){
//
//        String[] keyFrom = {"tittle", "start", "next", "amount", "image"};
//        String[] keyQuery = {MyOpenHelper.COLUMN_TITLE, MyOpenHelper.COLUMN_START, MyOpenHelper.COLUMN_INTERVAL, MyOpenHelper.COLUMN_AMOUNT_TIME};
//        Cursor cursor = sdb.query(MyOpenHelper.TABLE_NAME, keyQuery, null, null, null, null, null);
//
//        while (cursor.moveToNext()) {
//            String tittle = cursor.getString(cursor.getColumnIndex(MyOpenHelper.COLUMN_TITLE));
//            String start = cursor.getString(cursor.getColumnIndex(MyOpenHelper.COLUMN_START));
//            String next = cursor.getString(cursor.getColumnIndex(MyOpenHelper.COLUMN_INTERVAL));
//            String amount = cursor.getString(cursor.getColumnIndex(MyOpenHelper.COLUMN_AMOUNT_TIME));
//
//            HashMap<String, Object> map = new HashMap<>();
//            map.put("tittle", tittle);
//            map.put("start", start);
//            map.put("next", next);
//            map.put("amount", amount);
//            map.put("image", R.drawable.pill_example);
//            list.add(map);
//        }
//        cursor.close();
//        myOpenHelper.close();
//        sdb.close();
//    }
}