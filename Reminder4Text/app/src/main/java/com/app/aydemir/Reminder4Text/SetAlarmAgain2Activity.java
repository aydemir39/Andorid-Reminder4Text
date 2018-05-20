package com.app.aydemir.Reminder4Text;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.shawnlin.numberpicker.NumberPicker;

public class SetAlarmAgain2Activity extends AppCompatActivity {

    Button btnDataSave;
    Context context = this;
    TextView textViewNumber;
    NumberPicker numberPicker;
    Toolbar toolbarSetAlarm2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm_again2);
        final Deck Deck2 = (Deck) getIntent().getSerializableExtra("MyClass");
        final Deck Deck3 = (Deck) getIntent().getSerializableExtra("MyObjectToSetAlarmAgain");
        btnDataSave = findViewById(R.id.buttonData);
        btnDataSave.setTransformationMethod(null);
        // editTextDeckName = findViewById(R.id.editTextDeckName);
        //editTextDeckName.setText(Deck3.getDeskName());
        textViewNumber = findViewById(R.id.textViewNumber);
        textViewNumber.setText("Hourly notifications \ntotally will run " + 3 + " times");
        toolbarSetAlarm2 = findViewById(R.id.toolbarSetAlarm2);
        numberPicker = findViewById(R.id.number_picker);
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

                textViewNumber.setText("Hourly notifications \ntotally will run " + newVal + " times");

            }
        });

        btnDataSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Deck2.setDeskName(String.valueOf(editTextDeckName.getText()));
                Deck2.setRepeatingTime(numberPicker.getValue());
                Database database1 = new Database(context);
                database1.deleteData(Deck3);
                Database database = new Database(getApplicationContext());
                long id = 0;
                id = database.addData(Deck2);
                Intent intent = new Intent(SetAlarmAgain2Activity.this, MainActivity.class);
                SharedPreferences.Editor editor = getSharedPreferences("myTimePicked", MODE_PRIVATE).edit();
                editor.putBoolean("myObjRepeatingTime", true);
                editor.apply();
                final Alarm alarm = new Alarm(context);
                alarm.setAlarm(Deck2.getRepeatingTime());
                startActivity(intent);
                overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);

            }
        });
        toolbarSetAlarm2.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SetAlarmAgain2Activity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SetAlarmAgain2Activity.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
    }
}
