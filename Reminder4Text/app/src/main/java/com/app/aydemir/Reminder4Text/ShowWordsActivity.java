package com.app.aydemir.Reminder4Text;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShowWordsActivity extends AppCompatActivity {

    TextView textViewFront, textViewBack;
    Button buttonShowTheAnswer, buttonNextWord;
    Deck DeckToShow;
    int page = 1;
    Toolbar toolbarShowWords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_words);
        DeckToShow = (Deck) getIntent().getSerializableExtra("MyObjectToShowWords");
        Log.v("gelen nesne:", "" + DeckToShow.getDeskName());
        buttonShowTheAnswer = findViewById(R.id.buttonShowTheAnswer);
        buttonNextWord = findViewById(R.id.buttonNextWord);
        buttonNextWord.setTransformationMethod(null);
        buttonShowTheAnswer.setTransformationMethod(null);
        textViewBack = findViewById(R.id.textViewBack);
        textViewFront = findViewById(R.id.textViewFront);
        toolbarShowWords=findViewById(R.id.toolbarShowWords);
        showFrontWord(page);
        buttonShowTheAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBackWord(page);
                page++;
                buttonShowTheAnswer.setVisibility(View.GONE);
                buttonNextWord.setVisibility(View.VISIBLE);
            }
        });
        buttonNextWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (page == 5) {
                    textViewFront.setText("");
                    Intent intent = new Intent(ShowWordsActivity.this, ListActivity.class);
                    startActivity(intent);
                }
                showFrontWord(page);
                buttonNextWord.setVisibility(View.GONE);
                buttonShowTheAnswer.setVisibility(View.VISIBLE);
                textViewBack.setVisibility(View.GONE);
            }
        });
        toolbarShowWords.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ShowWordsActivity.this,ListActivity.class);
                startActivity(intent);
            }
        });
    }

    public void showFrontWord(int page) {
        textViewFront.setVisibility(View.VISIBLE);
        if (page == 1) {
            textViewFront.setText(DeckToShow.getArrayListFront().get(0));
        }
        if (page == 2) {
            textViewFront.setText(DeckToShow.getArrayListFront().get(1));
        }
        if (page == 3) {
            textViewFront.setText(DeckToShow.getArrayListFront().get(2));
        }
        if (page == 4) {
            textViewFront.setText(DeckToShow.getArrayListFront().get(3));
        }
    }

    public void showBackWord(int page) {
        textViewBack.setVisibility(View.VISIBLE);
        if (page == 1) {
            textViewBack.setText(DeckToShow.getArrayListBack().get(0));
        }
        if (page == 2) {
            textViewBack.setText(DeckToShow.getArrayListBack().get(1));
        }
        if (page == 3) {
            textViewBack.setText(DeckToShow.getArrayListBack().get(2));
        }
        if (page == 4) {
            textViewBack.setText(DeckToShow.getArrayListBack().get(3));
        }
    }
}
