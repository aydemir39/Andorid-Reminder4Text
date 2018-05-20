package com.app.aydemir.Reminder4Text;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

public class NotificationCall {
    Context context;
    private NotificationChannel mChannel;
    private NotificationManager notifManager;

    public NotificationCall(Context context) {
        this.context = context;
    }

    String id = "main_channel";

    public void NotificationCallme() {
        if (notifManager == null) {
            notifManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            int importance = NotificationManager.IMPORTANCE_HIGH;
            if (mChannel == null) {
                mChannel = new NotificationChannel
                        ("0", "z", importance);
                mChannel.enableVibration(true);
                notifManager.createNotificationChannel(mChannel);
            }
        }
        Intent intent = new Intent(context, ShowWordsActivity.class);
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        Database database = new Database(context);
        Deck deckNotToShow;
        deckNotToShow = database.getList().get(database.getList().size() - 1);
        intent.putExtra("MyObjectToShowWords", deckNotToShow);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, "0")
                .setSmallIcon(R.drawable.ic_noti)
                .setContentTitle("Hey!")
                .setAutoCancel(true)
                .setShowWhen(true)
                .setContentIntent(contentIntent)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setContentText("Let's practise!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        notifManager.notify((int) System.currentTimeMillis(), mBuilder.build());
    }
}
