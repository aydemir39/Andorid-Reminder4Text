package com.app.aydemir.Reminder4Text;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.shawnlin.numberpicker.NumberPicker;

public class NewDeckAndAlarmActivity extends AppCompatActivity {

    Button btnDataSave;
    EditText editTextDeckName;
    Context context = this;
    TextView textViewNumber;
    NumberPicker numberPicker;
    Toolbar toolbarNewDeckAlarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_deck_and_alarm);
        final Deck Deck2 = (Deck) getIntent().getSerializableExtra("MyClass");
        btnDataSave = (Button) findViewById(R.id.buttonData);
        btnDataSave.setTransformationMethod(null);
        //editTextDeckName = (EditText) findViewById(R.id.editTextDeckName);
        textViewNumber = (TextView) findViewById(R.id.textViewNumber);
        textViewNumber.setText("Hourly notifications \ntotally will run " + 3 + " times");
        toolbarNewDeckAlarm = findViewById(R.id.toolbarNewDeckAlarm);
        numberPicker = (NumberPicker) findViewById(R.id.number_picker);
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

                textViewNumber.setText("Hourly notifications \ntotally will run " + newVal + " times");

            }
        });
        btnDataSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    //Deck2.setDeskName(String.valueOf(editTextDeckName.getText()));
                    Deck2.setRepeatingTime(numberPicker.getValue());
                    Database database = new Database(getApplicationContext());
                    long id = 0;
                    id = database.addData(Deck2);
                    Intent intent = new Intent(NewDeckAndAlarmActivity.this, MainActivity.class);
                    SharedPreferences.Editor editor = getSharedPreferences("myTimePicked", MODE_PRIVATE).edit();
                    editor.putBoolean("myObjRepeatingTime", true);
                    editor.apply();
                    final Alarm alarm = new Alarm(context);
                    alarm.setAlarm(Deck2.getRepeatingTime());
                    startActivity(intent);

            }
        });
        toolbarNewDeckAlarm.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewDeckAndAlarmActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(NewDeckAndAlarmActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0)
            return false;

        return true;
    }
}
