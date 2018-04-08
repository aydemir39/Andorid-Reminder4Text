package com.app.aydemir.Reminder4Text;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;

import java.util.ArrayList;

public class NewWordsActivity extends AppCompatActivity {

    EditText editText1, editText2;
    Button btn;
    Deck desk1;
    ArrayList<String> sample1, sample2;
    int buttonClickCount = 1;
    Toolbar toolbarNewWords;
    ScrollView scrollNewWords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_words);
        btn = findViewById(R.id.button2);
        toolbarNewWords = findViewById(R.id.toolbarNewWords);
        scrollNewWords=findViewById(R.id.scrollNewWords);

        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        desk1 = new Deck();
        sample1 = new ArrayList<String>();
        sample2 = new ArrayList<String>();
        btn.setTransformationMethod(null);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isEmpty(editText1) && !isEmpty(editText2)) {
                    buttonClickAdd();
                } else {
                    if (editText1.getText().toString().trim().equals("")) {
                        editText1.setError("Can not be null");
                    }
                    if (editText2.getText().toString().trim().equals("")) {
                        editText2.setError("Can not be null");
                    }
                }
            }
        });
        toolbarNewWords.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewWordsActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(NewWordsActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void buttonClickAdd() {
        sample1.add(String.valueOf(editText1.getText()));
        sample2.add(String.valueOf(editText2.getText()));
        desk1.setArrayListFront(sample1);
        desk1.setArrayListBack(sample2);
        if(buttonClickCount==4){scrollNewWords.setVisibility(View.INVISIBLE);}
        buttonClickCount++;
        editText1.setText("");
        editText2.setText("");
        if (buttonClickCount == 5) {
            Intent intent = new Intent(NewWordsActivity.this, NewDeckAndAlarmActivity.class);
            intent.putExtra("MyClass", desk1);
            startActivity(intent);
        }
    }

    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0)
            return false;

        return true;
    }
}
