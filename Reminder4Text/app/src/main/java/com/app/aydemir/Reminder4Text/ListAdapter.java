package com.app.aydemir.Reminder4Text;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by alican on 24.12.2017.
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {
<<<<<<< HEAD
    private Activity context;
=======
    private Context context;
>>>>>>> e0537c0e4e1329ed3c8e231e43b2402b433c51ca
    private ArrayList<Deck> mDataList;
    private LayoutInflater inflater;
    private SharedPreferences.Editor editor;
    private Database database;

    public ListAdapter(Context context, ArrayList<Deck> data) {
        inflater = LayoutInflater.from(context);
        this.mDataList = data;
        this.context = (Activity) context;
        Collections.reverse(this.mDataList);
        editor = context.getSharedPreferences("myTimePicked", context.MODE_PRIVATE).edit();
        database = new Database(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.list_item, parent, false);
        MyViewHolder holder = new MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textViewFront1.setText("" + mDataList.get(position).getArrayListFront().get(0));
        holder.textViewFront2.setText("" + mDataList.get(position).getArrayListFront().get(1));
        holder.textViewFront3.setText("" + mDataList.get(position).getArrayListFront().get(2));
        holder.textViewFront4.setText("" + mDataList.get(position).getArrayListFront().get(3));
        holder.clickedItemPosition = position;
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public void deleteItem(int position) {
        mDataList.remove(position);
        notifyItemRemoved(position);
        if (position == 0) {
            editor.putBoolean("myObjRepeatingTime", false);
            editor.apply();
        }
        notifyItemRangeChanged(position, mDataList.size());
    }

    public void showWordClicked(int position) {
        Intent intent = new Intent(context, ShowWordsActivity.class);
        intent.putExtra("MyObjectToShowWords", mDataList.get(position));
        context.startActivity(intent);
        context.overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);

    }

    public void ShowSetAlarmAgain(int position) {
        Intent intent = new Intent(context, SetAlarmAgainActivity.class);
        intent.putExtra("MyObjectToSetAlarmAgain", mDataList.get(position));
        context.startActivity(intent);
        context.overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
      //  deleteItem(position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewFront1, textViewFront2, textViewFront3, textViewFront4;
        ImageView imageViewDelete, imageViewSetAlarm;
        int clickedItemPosition = 0;
        LinearLayout linearClick;

        public MyViewHolder(View itemView) {
            super(itemView);
            final Alarm alarm = new Alarm(context);
            linearClick = itemView.findViewById(R.id.linearClick);
            textViewFront1 = itemView.findViewById(R.id.textViewFront1);
            textViewFront2 = itemView.findViewById(R.id.textViewFront2);
            textViewFront3 = itemView.findViewById(R.id.textViewFront3);
            textViewFront4 = itemView.findViewById(R.id.textViewFront4);
            imageViewSetAlarm = itemView.findViewById(R.id.imageViewSetAlarm);
            imageViewDelete = itemView.findViewById(R.id.imageViewDelete);
            imageViewDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(clickedItemPosition==0){alarm.cancelAlarm();}
                    database.deleteData(mDataList.get(clickedItemPosition));
                    deleteItem(clickedItemPosition);
                }
            });
            linearClick.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showWordClicked(clickedItemPosition);
                }
            });
            imageViewSetAlarm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    ShowSetAlarmAgain(clickedItemPosition);
                }
            });
        }
    }

    @Override
    public void onViewDetachedFromWindow(MyViewHolder holder) {
        super.onViewDetachedFromWindow(holder);

    }
}
