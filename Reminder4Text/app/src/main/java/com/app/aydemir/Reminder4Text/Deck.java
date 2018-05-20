package com.app.aydemir.Reminder4Text;

import java.io.Serializable;
import java.util.ArrayList;

public class Deck implements Serializable{
    private int id;
    private String deskName;
    private ArrayList<String> arrayListFront;
    private ArrayList<String> arrayListBack;
    private int repeatingTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeskName() {
        return deskName;
    }

    public void setDeskName(String deskName) {
        this.deskName = deskName;
    }

    public ArrayList<String> getArrayListFront() {
        return arrayListFront;
    }

    public void setArrayListFront(ArrayList<String> arrayListFront) {
        this.arrayListFront = arrayListFront;
    }

    public ArrayList<String> getArrayListBack() {
        return arrayListBack;
    }

    public void setArrayListBack(ArrayList<String> arrayListBack) {
        this.arrayListBack = arrayListBack;
    }

    public int getRepeatingTime() {
        return repeatingTime;
    }

    public void setRepeatingTime(int repeatingTime) {
        this.repeatingTime = repeatingTime;
    }
}
