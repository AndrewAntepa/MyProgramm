package com.example.myprogramm;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

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
    private static String LIST = "linedList";
    LinkedList<HashMap<String, Object>> list = new LinkedList<>();
    Timer timer;
    MyTimerTask myTimerTask;

    public NotifyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
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
        final Notification notification = nBuilder.build();
//        notificationManager.notify(NOTIFY_ID++, notification);

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

//    public static void alarmNotify(Context context, int inter){
//        Intent aintent = new Intent(context, NotifyService.class);
//        PendingIntent alarmPend = PendingIntent.getService(context, 0, aintent, PendingIntent.FLAG_CANCEL_CURRENT);
//        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
//        alarmManager.setRepeating(AlarmManager.RTC, System.currentTimeMillis()+5000, 1000*3, alarmPend);
//
//    }

    class MyTimerTask extends TimerTask{
        Intent aintent = new Intent(getApplicationContext(), NotifyService.class);
        PendingIntent alarmPend = PendingIntent.getService(getApplicationContext(), 0, aintent, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getApplicationContext().getSystemService(ALARM_SERVICE);
        @Override
        public void run() {
            Handler handler = new Handler();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    alarmManager.set(AlarmManager.RTC, System.currentTimeMillis(), alarmPend);
                    handler.postDelayed(this, 5000);
                }
            });
        }
    }
}