package com.imdevil.remoteviews;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private NotificationManager nm;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        context = getBaseContext();
        /**创建一个NotificationChannel**/
        NotificationChannel channel = new NotificationChannel("myChannel","myChannel", NotificationManager.IMPORTANCE_DEFAULT);
        channel.setBypassDnd(true);
        channel.canBypassDnd();
        channel.setLockscreenVisibility(Notification.VISIBILITY_SECRET);
        channel.setDescription("my own channel");
        channel.setLightColor(Color.RED);
        channel.setName("name of my channel");
        channel.setShowBadge(true);
        channel.setVibrationPattern(new long[]{100,200,300,400,500,400,300,200,400});
        channel.enableVibration(true);
        /**将channel设置给NotificationManager**/
        nm.createNotificationChannel(channel);


        findViewById(R.id.default_notification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**创建点播时发送的广播**/
                Intent intent = new Intent(context,RecevierActivity.class);
                intent.setAction("Action_SIMPLE_OPEN");
                PendingIntent pi = PendingIntent.getActivity(context,0,intent,0);
                /**创建删除时发送的广播**/
                Intent delIntent = new Intent(context, RecevierActivity.class);
                delIntent.setAction("Action_SIMPLE_DELETE");
                PendingIntent delPI = PendingIntent.getActivity(context, 1, delIntent, 0);
                /**创建一个Notification**/
                Notification.Builder builder = new Notification.Builder(context, "myChannel")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Content Title")
                        .setContentText("Content Text")
                        .setAutoCancel(true)
                        .setShowWhen(true)
                        .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher_round))
                        .setContentIntent(pi)
                        .setDeleteIntent(delPI);
                /**发送一个通知**/
                nm.notify(1,builder.build());
            }
        });
    }
}
