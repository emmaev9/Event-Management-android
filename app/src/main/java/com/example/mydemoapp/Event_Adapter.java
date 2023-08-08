package com.example.mydemoapp;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Event_Adapter extends RecyclerView.Adapter<Event_Adapter.MyViewHolder> {
    Context context;
    ArrayList<EventModel> eventModels;

    public interface OnBuyButtonClickListener{
        void onBuyButtonClick(EventModel eventModel, int position);
    }
    private OnBuyButtonClickListener onBuyButtonClickListener;

    public Event_Adapter(Context context, ArrayList<EventModel> eventModels, OnBuyButtonClickListener onBuyButtonClickListener){
        this.context = context;
        this.eventModels = eventModels;
        this.onBuyButtonClickListener = onBuyButtonClickListener;
    }

    @NonNull
    @Override
    public Event_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.each_event, parent, false);
        return new Event_Adapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Event_Adapter.MyViewHolder holder, int position) {
        holder.tvName.setText(eventModels.get(position).getEventName());
        holder.tvLocation.setText(eventModels.get(position).getLocation());
        holder.tvDate.setText(eventModels.get(position).getDate());
        holder.tvDescription.setText(eventModels.get(position).getEventDescription());
        holder.imageView.setImageResource(eventModels.get(position).getImage());

        holder.buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    if (onBuyButtonClickListener != null) {
                        onBuyButtonClickListener.onBuyButtonClick(eventModels.get(adapterPosition), adapterPosition);
                    }
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return eventModels.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;

        Button buyButton;

        TextView tvName, tvDescription, tvLocation, tvDate;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            tvName = itemView.findViewById(R.id.eventName);
            tvDescription = itemView.findViewById(R.id.description);
            tvLocation = itemView.findViewById(R.id.location);
            tvDate = itemView.findViewById(R.id.date);
            buyButton = itemView.findViewById(R.id.buy_button);
        }
    }
    public void setFilteredList(ArrayList<EventModel> filteredList){
        this.eventModels = filteredList;
        notifyDataSetChanged();
    }
}
