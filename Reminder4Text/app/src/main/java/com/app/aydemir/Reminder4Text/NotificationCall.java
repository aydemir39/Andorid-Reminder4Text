package com.app.aydemir.Reminder4Text;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

public class NotificationCall {
    Context context;

    public NotificationCall(Context context) {
        this.context = context;
    }

    String id = "main_channel";

    public void NotificationCall() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
            CharSequence name = "Channel Name";
            String description = "Channel Description";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = new NotificationChannel(id, name, importance);
            notificationChannel.setDescription(description);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.WHITE);
            notificationChannel.enableVibration(false);
            if (notificationChannel != null) {
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }

        Intent intent = new Intent(context, ShowWordsActivity.class);
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        Database database = new Database(context);
        Deck deckNotToShow;
        deckNotToShow = database.getList().get(database.getList().size()-1);
        intent.putExtra("MyObjectToShowWords",deckNotToShow);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder b = new NotificationCompat.Builder(context, id);
        b.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.small_logo2)
                /*.setLargeIcon(BitmapFactory.decodeResource(context.getResources(),
                        R.drawable.alarm))*/
                .setTicker("Are you there ?")
                .setContentTitle("Reminder 4 Text")
                .setContentText("Let's practice")
                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_SOUND)
                .setContentIntent(contentIntent)
                .setPriority(NotificationManager.IMPORTANCE_HIGH)
                .setContentInfo("");
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(1, b.build());
    }
}
