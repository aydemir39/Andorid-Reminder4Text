package com.app.aydemir.Reminder4Text;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Toolbar toolbarList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        recyclerView = findViewById(R.id.recyclerView);
        toolbarList=findViewById(R.id.toolbarList);
        Database database = new Database(getApplicationContext());
        List<Deck> deckList;
        deckList = database.getList();
        database.close();
        ArrayList<Deck> arld = (ArrayList<Deck>) deckList;
        ListAdapter listAdapter = new ListAdapter(this, arld);
        recyclerView.setAdapter(listAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        toolbarList.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ListActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
<<<<<<< HEAD
                overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
=======
>>>>>>> e0537c0e4e1329ed3c8e231e43b2402b433c51ca
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(ListActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
<<<<<<< HEAD
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
=======
        //overridePendingTransition(R,R.anim.splashfadeout);
>>>>>>> e0537c0e4e1329ed3c8e231e43b2402b433c51ca
    }
}
