package com.example.u0151051.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btn;
    NotificationManager notificationManager;
    Notification.Builder notification;
    int notifyID = 0;//通知的編號 ,可能會有多個通知
    Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION); // 通知音效的URI,在這裡使用系統內建的通知音效

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findview();
    }

    void findview() {
        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(c);
    }

    void shownotification() {
        //宣告一個 NotificationManager,它負責管理整個與這個訊息提醒相關事務
        //getSystemService用來取得系統提供的各種功能
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //設計intent(當我們點下通知,會跳到另一個畫面)
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, Main2Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        //PendingIntent會把Intent包起來,讓使用者可以指定Intent執行時機
        PendingIntent pendingIntent = PendingIntent.getActivity(this, notifyID, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        //使用Notification.Builder建立通知
        notification = new Notification.Builder(this)
                .setSmallIcon(android.R.drawable.sym_def_app_icon)//顯示在螢幕上的圖示資源(必要)
                .setContentTitle("這是訊息通知")//訊息TITLE(必要)
                .setContentText("嗚嗚嗚")//訊息文字(必要)
                .setContentIntent(pendingIntent)//傳入pendingIntent,當我們按下訊息時,希望做什麼事
                .setSound(sound)//設置音效
                .setProgress(100, 50, true);//設置進度條(setProgress(進度最大值,目前進度,是否為不確定的進度))
        //發出通知
        notificationManager.notify(notifyID, notification.build());//Api版本最少為Leavle16

    }

    View.OnClickListener c = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            shownotification();
        }
    };
}
