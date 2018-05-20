package com.app.aydemir.Reminder4Text;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
    FloatingActionButton fabMain, fabNew, fabList;
    LinearLayout linearLayoutFabNew, linearLayoutFabList;
    TextView  textView1Front1, textView1Front2, textView1Front3, textView1Front4;
    LinearLayout  linearLayoutNull;
    CardView linearLayoutData;
<<<<<<< HEAD
    SharedPreferences prefs,prefNumberRepeat;
=======
    SharedPreferences prefs;
>>>>>>> e0537c0e4e1329ed3c8e231e43b2402b433c51ca
    ImageView imageViewDataRemove;
    Toolbar toolbar;
    boolean doubleBackToExitPressedOnce=false;
    TextView textViewChangedNumber;
    int ChangedNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fabMain = findViewById(R.id.floatingActionMain);
        fabNew = findViewById(R.id.floatingActionButtonNew);
        fabNew.setEnabled(false);
        fabList = findViewById(R.id.floatingActionButtonList);
        fabList.setEnabled(false);

        linearLayoutFabNew = findViewById(R.id.linearLayoutNew);
        linearLayoutFabList = findViewById(R.id.linearLayoutList);

        textView1Front1 = findViewById(R.id.textView1Front1);
        textView1Front2 = findViewById(R.id.textView1Front2);
        textView1Front3 = findViewById(R.id.textView1Front3);
        textView1Front4 = findViewById(R.id.textView1Front4);
        textViewChangedNumber=findViewById(R.id.textViewChangedNumber);
        linearLayoutData = findViewById(R.id.LinearLayoutData);
        linearLayoutNull = findViewById(R.id.LinearLayoutNull);
        imageViewDataRemove = findViewById(R.id.imageViewDataRemove);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Reminder 4 Text");
        toolbar.setTitleTextColor(Color.parseColor("#fafafa"));
        toolbar.inflateMenu(R.menu.menu_main);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu1:
                        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                        sharingIntent.putExtra(Intent.EXTRA_TEXT, "http://play.google.com/store/apps/details?id=com.app.aydemir.Reminder4Text");
                        sharingIntent.setType("text/plain");
                        startActivity(Intent.createChooser(sharingIntent, "links"));
                        break;
                    case R.id.menu2:
                        alertButtonCustom();
                        break;
                    case R.id.menu3:
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.app.aydemir.Reminder4Text"));
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });
        prefNumberRepeat = getSharedPreferences("TIMER", MODE_PRIVATE);

        ChangedNumber = prefNumberRepeat.getInt("alarmCount", 0);
        prefNumberRepeat.registerOnSharedPreferenceChangeListener(this);
        textViewChangedNumber.setText(""+ChangedNumber);
        prefs = getSharedPreferences("myTimePicked", MODE_PRIVATE);
        prefs.registerOnSharedPreferenceChangeListener(this);
        if (!prefs.getBoolean("myObjRepeatingTime", false)) {
            DataNotExist();
        } else {
            DataExist();
        }
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(getApplicationContext(), "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    public void DataExist() {
        linearLayoutNull.setVisibility(View.GONE);
        linearLayoutData.setVisibility(View.VISIBLE);
        Database database = new Database(getApplicationContext());
        Deck deckList;
        deckList = database.getList().get(database.getList().size() - 1);
        textView1Front1.setText(deckList.getArrayListFront().get(0));
        textView1Front2.setText(deckList.getArrayListFront().get(1));
        textView1Front3.setText(deckList.getArrayListFront().get(2));
        textView1Front4.setText(deckList.getArrayListFront().get(3));
        final Alarm alarm = new Alarm(getApplicationContext());
        imageViewDataRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = getSharedPreferences("myTimePicked", MODE_PRIVATE).edit();
                editor.putBoolean("myObjRepeatingTime", false);
                editor.apply();
                alarm.cancelAlarm();
            }
        });
        database.close();
    }

    public void DataNotExist() {
        linearLayoutData.setVisibility(View.GONE);
        linearLayoutNull.setVisibility(View.VISIBLE);
    }

    public void fabClickMain(View view) {
        Animation animShowOn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_layout_show_on);
        Animation animShowOff = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_layout_show_off);
        if (linearLayoutFabList.getVisibility() == View.VISIBLE && linearLayoutFabNew.getVisibility() == View.VISIBLE) {
            fabNew.setEnabled(false);
            fabList.setEnabled(false);
            rotateFabForward();
            linearLayoutFabList.startAnimation(animShowOff);
            linearLayoutFabNew.startAnimation(animShowOff);
            linearLayoutFabNew.setVisibility(View.GONE);
            linearLayoutFabList.setVisibility(View.GONE);
        } else {

            rotateFabBackward();
            linearLayoutFabList.startAnimation(animShowOn);
            linearLayoutFabNew.startAnimation(animShowOn);
            linearLayoutFabNew.setVisibility(View.VISIBLE);
            linearLayoutFabList.setVisibility(View.VISIBLE);
            fabNew.setEnabled(true);
            fabList.setEnabled(true);
        }
    }

    public void rotateFabForward() {
        ViewCompat.animate(fabMain)
                .rotation(0.0F)
                .withLayer()
                .setDuration(300L)
                .setInterpolator(new OvershootInterpolator(10.0F))
                .start();
    }

    public void rotateFabBackward() {
        ViewCompat.animate(fabMain)
                .rotation(135.0F)
                .withLayer()
                .setDuration(300L)
                .setInterpolator(new OvershootInterpolator(10.0F))
                .start();
    }

    public void fabClickList(View view) {
        Intent intent = new Intent(getApplicationContext(), ListActivity.class);
        startActivity(intent);
        finish();
<<<<<<< HEAD
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
=======
>>>>>>> e0537c0e4e1329ed3c8e231e43b2402b433c51ca
    }

    public void fabClicNew(View view) {
        Intent intent = new Intent(getApplicationContext(), NewWordsActivity.class);
        startActivity(intent);
        finish();
<<<<<<< HEAD
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
=======
>>>>>>> e0537c0e4e1329ed3c8e231e43b2402b433c51ca
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        if (!prefs.getBoolean("myObjRepeatingTime", false)) {
            DataNotExist();
        } else {
            DataExist();
        }
        ChangedNumber--;
        textViewChangedNumber.setText(""+ChangedNumber);
    }

    public void alertButtonCustom() {
        final Dialog dlg = new Dialog(this);
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dlg.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//köşeleri yok etti!!!
        dlg.setCancelable(true);
        dlg.setContentView(R.layout.custom_dialog);
        final TextView txtCustomDialog = dlg.findViewById(R.id.textViewCustomDialog);
        txtCustomDialog.setText("Version 1.3");
        dlg.show();
    }
}
