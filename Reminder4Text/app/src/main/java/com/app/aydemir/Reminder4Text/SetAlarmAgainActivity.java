package com.app.aydemir.Reminder4Text;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class SetAlarmAgainActivity extends AppCompatActivity {

    EditText editText1, editText2;
    Button btn;
    Deck desk1;
    ArrayList<String> sample1, sample2;
    int buttonClickCount = 0;
    Deck Deck2;
    Toolbar toolbarSetAlarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm_again);
        btn = (Button) findViewById(R.id.button2);
        btn.setTransformationMethod(null);
        Deck2 = (Deck) getIntent().getSerializableExtra("MyObjectToSetAlarmAgain");
        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText1.setText(Deck2.getArrayListFront().get(0));
        editText2.setText(Deck2.getArrayListBack().get(0));
        toolbarSetAlarm = findViewById(R.id.toolbarSetAlarm);
        desk1 = new Deck();
        sample1 = new ArrayList<String>();
        sample2 = new ArrayList<String>();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isEmpty(editText1) && !isEmpty(editText2)) {
                    buttonClickAdd();
                } else {
                    if (editText1.getText().toString().trim().equals("")) {
                        editText1.setError("Can't be null");
                    }
                    if (editText2.getText().toString().trim().equals("")) {
                        editText2.setError("Can't be null");
                    }
                }
            }
        });
        toolbarSetAlarm.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SetAlarmAgainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(SetAlarmAgainActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void buttonClickAdd() {

        sample1.add(String.valueOf(editText1.getText()));
        sample2.add(String.valueOf(editText2.getText()));
        desk1.setArrayListFront(sample1);
        desk1.setArrayListBack(sample2);
        buttonClickCount++;
        switch (buttonClickCount){

            case 1:
                editText1.setText(Deck2.getArrayListFront().get(buttonClickCount));
                editText2.setText(Deck2.getArrayListBack().get(buttonClickCount));
                break;
            case 2:
                editText1.setText(Deck2.getArrayListFront().get(buttonClickCount));
                editText2.setText(Deck2.getArrayListBack().get(buttonClickCount));
                break;
            case 3:
                editText1.setText(Deck2.getArrayListFront().get(buttonClickCount));
                editText2.setText(Deck2.getArrayListBack().get(buttonClickCount));
                break;
        }

        if (buttonClickCount == 4) {
            Intent intent = new Intent(SetAlarmAgainActivity.this, SetAlarmAgain2Activity.class);
            intent.putExtra("MyClass", desk1);
            intent.putExtra("MyObjectToSetAlarmAgain", Deck2);
            startActivity(intent);
        }
    }

    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0)
            return false;

        return true;
    }
}
