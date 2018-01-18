package com.app.aydemir.Reminder4Text;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent ıntent) {


        NotificationCall notificationCall = new NotificationCall(context);
        notificationCall.NotificationCall();


        /*PendingIntent alarmIntent;
        alarmIntent = PendingIntent.getBroadcast(context, 0, new Intent(context, AlarmReceiver.class), 0);*/
        SharedPreferences sharedPreferences = context.getSharedPreferences("TIMER", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        int alarmCount = sharedPreferences.getInt("alarmCount", 1);
        editor.putInt("alarmCount", alarmCount - 1);
        editor.apply();
        Log.e("alarmCount ", alarmCount + "");
        Alarm alarm = new Alarm(context);
        alarm.setAlarm(alarmCount - 1);
        if (alarmCount == 1) {
           /* AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            alarmManager.cancel(alarmIntent);*/
           Alarm alarm1=new Alarm(context);
            alarm1.cancelAlarm();
            Log.e("Alarm", "Cancelled");
            SharedPreferences.Editor editor2 = context.getSharedPreferences("myTimePicked", Context.MODE_PRIVATE).edit();
            editor2.putBoolean("myObjRepeatingTime", false);
            editor2.apply();
        }


    }
}