package com.example.mydemoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Order_Adapter extends RecyclerView.Adapter<Order_Adapter.MyViewHolder> {
    Context context;
    ArrayList<OrderModel> orderModels;

    public interface OnClickListener{
        void onDeleteButtonClick(OrderModel orderModel, int position);
        void onEditButtonClick(OrderModel orderModel, int position);
    }
    private Order_Adapter.OnClickListener onClickListener;

    public Order_Adapter(Context context, ArrayList<OrderModel> orderModels, Order_Adapter.OnClickListener onClickListener){
        this.context = context;
        this.orderModels = orderModels;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public Order_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.each_order, parent, false);
        return new Order_Adapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Order_Adapter.MyViewHolder holder, int position) {

        holder.tvOrderId.setText(String.valueOf(orderModels.get(position).getOrderId()));
        holder.tvEventName.setText(orderModels.get(position).getEventName());
        holder.tvTicketCategoryName.setText(orderModels.get(position).getTicketCategoryName());
        holder.tvPrice.setText(String.valueOf(orderModels.get(position).getTotalPrice()));
        holder.tvDate.setText(orderModels.get(position).getOrderDate());
        holder.tvNumberTickets.setText(String.valueOf(orderModels.get(position).getNumberOfTickets()));

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    if (onClickListener != null) {
                        onClickListener.onDeleteButtonClick(orderModels.get(adapterPosition), adapterPosition);
                    }
                }
            }
        });

        /*holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    if (onClickListener != null) {
                        onClickListener.onEditButtonClick(orderModels.get(adapterPosition), adapterPosition);
                    }
                }
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return orderModels.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        Button deleteButton;
        Button editButton;
        TextView tvEventName, tvTicketCategoryName, tvDate, tvPrice, tvOrderId, tvNumberTickets;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            deleteButton = itemView.findViewById(R.id.delete_button);
            editButton = itemView.findViewById(R.id.cancelButton);
            tvOrderId = itemView.findViewById(R.id.order_id);
            tvEventName = itemView.findViewById(R.id.eventName);
            tvTicketCategoryName = itemView.findViewById(R.id.ticketCategory);
            tvPrice = itemView.findViewById(R.id.total_price);
            tvDate = itemView.findViewById(R.id.date);
            tvNumberTickets = itemView.findViewById(R.id.number_tickets);

        }
    }
}
