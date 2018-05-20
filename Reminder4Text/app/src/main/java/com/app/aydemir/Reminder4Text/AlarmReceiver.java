package com.app.aydemir.Reminder4Text;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent ıntent) {

        SharedPreferences sharedPreferences = context.getSharedPreferences("TIMER", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        int alarmCount = sharedPreferences.getInt("alarmCount", 0);
        if (alarmCount == 0) {
        } else {
            Alarm alarm = new Alarm(context);
            alarm.setAlarm(alarmCount - 1);
            editor.putInt("alarmCount", alarmCount - 1);
            editor.apply();
            NotificationCall notificationCall = new NotificationCall(context);
<<<<<<< HEAD
            notificationCall.NotificationCallme();
=======
            notificationCall.NotificationCall();
>>>>>>> e0537c0e4e1329ed3c8e231e43b2402b433c51ca
            if (alarmCount == 1) {
                alarm.cancelAlarm();
                SharedPreferences.Editor editor2 = context.getSharedPreferences("myTimePicked", Context.MODE_PRIVATE).edit();
                editor2.putBoolean("myObjRepeatingTime", false);
                editor2.apply();
            }
        }


    }
}