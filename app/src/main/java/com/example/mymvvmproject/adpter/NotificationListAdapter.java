package com.example.mymvvmproject.adpter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mymvvmproject.R;
import com.example.mymvvmproject.models.ItemModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class NotificationListAdapter extends RecyclerView.Adapter<NotificationListAdapter.MyViewHolder> {
    Context _context;
    OnCardClickListner onCardClickListner;

    private List<ItemModel> fellowList;

    public NotificationListAdapter(Context context, List<ItemModel> data) {
        this._context = context;
        this.fellowList = data;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(NotificationListAdapter.MyViewHolder holder, final int listPosition) {
        TextView title = holder.title;
        TextView message = holder.message;
        final ItemModel vehicleTrackModel = fellowList.get(listPosition);
        try {
            title.setText(vehicleTrackModel.getName());
            message.setText(vehicleTrackModel.getAddress());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getDate(long time) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time);
        String date = DateFormat.format("MMM dd, yyyy hh:mm a", cal).toString();
        return date;
    }


    @Override
    public int getItemCount() {
        return fellowList.size();
    }

    public void setOnCardClickListner(OnCardClickListner onCardClickListner) {
        this.onCardClickListner = onCardClickListner;
    }

    public interface OnCardClickListner {
        void OnCardClicked();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView message;


        public MyViewHolder(View itemView) {
            super(itemView);
            this.title = (TextView) itemView.findViewById(R.id.name);
            this.message = (TextView) itemView.findViewById(R.id.address);


        }
    }


}