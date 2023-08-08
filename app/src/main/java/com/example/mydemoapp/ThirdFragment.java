package com.example.mydemoapp;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ThirdFragment extends Fragment implements Order_Adapter.OnClickListener {

    ArrayList<OrderModel> orderModels = new ArrayList<>();
    Order_Adapter adapter;
    public ThirdFragment(){
        // require a empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_third, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.ordersRecyclerView);
        setUpEventModels();
        adapter = new Order_Adapter(requireContext(), orderModels, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        return view;
    }

    private void setUpEventModels() {
        OrderModel orderModel1 = new OrderModel(
                1001,
                1000,
                "Untold",
                "VIP",
                1,
                "30 Jun 2023"
        );

        OrderModel orderModel2 = new OrderModel(
                1002,
                2000,
                "Untold",
                "VIP",
                2,
                "31 Jun 2023"
        );
        OrderModel orderModel3 = new OrderModel(
                1003,
                3700,
                "Electric Castle",
                "Standard",
                6,
                "10 Sept 2022"
        );
        orderModels.add(orderModel1);
        orderModels.add(orderModel2);
        orderModels.add(orderModel3);
    }

    @Override
    public void onDeleteButtonClick(OrderModel orderModel, int position) {
        deleteOrder(orderModel, position);
    }

    @Override
    public void onEditButtonClick(OrderModel orderModel, int position) {
        editOrder(orderModel, position);
    }

    public void deleteOrder(OrderModel orderModel, int position){
        /*TO DO: Delete an order*/
    }

    public void editOrder(OrderModel orderModel, int position){
        /*TO DO: Edit an order */
    }
}