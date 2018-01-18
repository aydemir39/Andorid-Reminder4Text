package com.app.aydemir.Reminder4Text;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alican on 24.12.2017.
 */

public class Database extends SQLiteOpenHelper {
    private  static final String database_name="database1";
    private  static final String deck_table="tbl_deck1";

    private  static final int database_version=1;

    public Database(Context context) {
        super(context, database_name, null, database_version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String s = "create table " + deck_table + " (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, words_front1 TEXT, words_back1 TEXT, words_front2 TEXT, words_back2 TEXT, words_front3 TEXT, words_back3 TEXT, words_front4 TEXT, words_back4 TEXT);";
        sqLiteDatabase.execSQL(s);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + deck_table);

    }

    public long addData(Deck deck2) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("name",deck2.getDeskName());
        cv.put("words_front1",deck2.getArrayListFront().get(0));
        cv.put("words_front2",deck2.getArrayListFront().get(1));
        cv.put("words_front3",deck2.getArrayListFront().get(2));
        cv.put("words_front4",deck2.getArrayListFront().get(3));
        cv.put("words_back1",deck2.getArrayListBack().get(0));
        cv.put("words_back2",deck2.getArrayListBack().get(1));
        cv.put("words_back3",deck2.getArrayListBack().get(2));
        cv.put("words_back4",deck2.getArrayListBack().get(3));
        long id=db.insert(deck_table,null,cv);
        return id;
    }

    public List<Deck> getList(){
        List<Deck> deck_list=new ArrayList<Deck>();
        SQLiteDatabase db=this.getReadableDatabase();
        String sorgu="Select * from " + deck_table;
        Cursor c=db.rawQuery(sorgu,null);
        int sıraNoId=c.getColumnIndex("id");
        int sıraNoName=c.getColumnIndex("name");
        int sıraNoWordsFront1=c.getColumnIndex("words_front1");
        int sıraNoWordBack1=c.getColumnIndex("words_back1");
        int sıraNoWordsFront2=c.getColumnIndex("words_front2");
        int sıraNoWordBack2=c.getColumnIndex("words_back2");
        int sıraNoWordsFront3=c.getColumnIndex("words_front3");
        int sıraNoWordBack3=c.getColumnIndex("words_back3");
        int sıraNoWordsFront4=c.getColumnIndex("words_front4");
        int sıraNoWordBack4=c.getColumnIndex("words_back4");

        while(c.moveToNext())
        {
            ArrayList<String> arrayListBack=new ArrayList<>();
            ArrayList<String> arrayListFront=new ArrayList<>();
            arrayListBack.add(c.getString(sıraNoWordBack1));
            arrayListBack.add(c.getString(sıraNoWordBack2));
            arrayListBack.add(c.getString(sıraNoWordBack3));
            arrayListBack.add(c.getString(sıraNoWordBack4));
            arrayListFront.add(c.getString(sıraNoWordsFront1));
            arrayListFront.add(c.getString(sıraNoWordsFront2));
            arrayListFront.add(c.getString(sıraNoWordsFront3));
            arrayListFront.add(c.getString(sıraNoWordsFront4));

            Deck deck=new Deck();
            deck.setDeskName(c.getString(sıraNoName));
            deck.setArrayListFront(arrayListFront);
            deck.setArrayListBack(arrayListBack);
            deck.setId(c.getInt(sıraNoId));
            deck_list.add(deck);
        }
        return  deck_list;
    }
    public void deleteData(Deck deck){

        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(deck_table,"id"+" = ?",new String[]{String.valueOf(deck.getId())});
        db.close();
    }
}
