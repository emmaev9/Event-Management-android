package com.example.mydemoapp;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class FirstFragment extends Fragment implements Event_Adapter.OnBuyButtonClickListener, AdapterView.OnItemSelectedListener {
    private SearchView searchView;
    Event_Adapter adapter;
    ArrayList<EventModel> eventModels = new ArrayList<>();
    Button buyButton;
    AppCompatImageButton sortButton;


    public FirstFragment() {
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        searchView = view.findViewById(R.id.searchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filterList(s);
                return false;
            }
        });

        RecyclerView recyclerView = view.findViewById(R.id.mRecyclerView);
        setUpEventModels();

        sortButton = view.findViewById(R.id.btnSort);
        sortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sortByPriceDialog();
            }
        });

        adapter = new Event_Adapter(requireContext(), eventModels, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        return view;

    }

    private void setUpEventModels() {
        EventModel event1 = new EventModel(
                "Untold",
                "Best music experience",
                "Cluj-Napoca, Romania",
                "03 Aug - 06 Aug 2023",
                R.drawable.untold,
                1000);
        EventModel event5 = new EventModel(
                "Tomorrowland",
                "A large-scale annual electronic dance music festival",
                "Boom, Belgium",
                "25 Jul - 30 Aug 2023",
                R.drawable.tm,
                600);
        EventModel event2 = new EventModel(
                "Electric Castle",
                "Dance in the rain",
                "Bontida, Romania",
                "25 Jul - 30 Aug 2023",
                R.drawable.ecc,300);
        EventModel event3 = new EventModel(
                "Saga",
                "The biggest music festival in Bucharest",
                "Bucharest, Romania",
                "1 Sept - 4 Sept 2023",
                R.drawable.saga,
                900);
        EventModel event4 = new EventModel(
                "Jazz in the park",
                "Build bridges between festival goers and jazz music.",
                "Cluj-Napoca, Romania",
                "5 Jul - 10 Jul 2023",
                R.drawable.jazz,
                50);
        eventModels.add(event1);
        eventModels.add(event5);
        eventModels.add(event2);
        eventModels.add(event3);
        eventModels.add(event4);
    }

    private void filterList(String text) {
        ArrayList<EventModel> filteredList = new ArrayList<>();
        for (EventModel eventModel : eventModels) {
            if (eventModel.getEventName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(eventModel);
            }
        }
        if (filteredList.isEmpty()) {
            Toast.makeText(getContext(), "No event found", Toast.LENGTH_SHORT).show();
        } else {
            adapter.setFilteredList(filteredList);
        }
    }

    private void showOrderDialog(EventModel eventModel) {
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.success_dialog, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext(), R.style.CustomDialogStyle);
        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();

        Button cancelButton = dialogView.findViewById(R.id.cancelButton);
        Button orderButton = dialogView.findViewById(R.id.orderButton);

        Spinner spinnerNumbers=dialogView.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence>adapter= ArrayAdapter.createFromResource(requireContext(), R.array.numbers, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerNumbers.setAdapter(adapter);
        spinnerNumbers.setOnItemSelectedListener(this);
        spinnerNumbers.setSelection(0);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss(); // Close the dialog when Cancel is clicked
            }
        });

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Perform actions when Order is clicked
                // Example: You can handle the order logic here
                alertDialog.dismiss(); // Close the dialog after processing the order
            }
        });

        alertDialog.show();

    }

    @Override
    public void onBuyButtonClick(EventModel eventModel, int position) {
        showOrderDialog(eventModel);
    }

    private void sortByPriceDialog() {
        RadioButton radioAsc, radioDesc;
        Button btnApply;

        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.sort_dialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext(), R.style.CustomDialogStyle);
        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();

        radioAsc = dialogView.findViewById(R.id.radioAsc);
        radioDesc = dialogView.findViewById(R.id.radioDesc);
        btnApply = dialogView.findViewById(R.id.btnApply);

        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle apply button click
                boolean ascending = radioAsc.isChecked();
                boolean descending = radioDesc.isChecked();

                if (ascending) {
                    sortByPriceAsc();
                } else if (descending) {
                    sortByPriceDesc();
                }

                adapter.notifyDataSetChanged(); // Notify the adapter that the data has changed
                alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }

    private void sortByPriceAsc() {
        Collections.sort(eventModels, new Comparator<EventModel>() {
            @Override
            public int compare(EventModel event1, EventModel event2) {
                return Float.compare(event1.getPrice(), event2.getPrice());
            }
        });
    }

    private void sortByPriceDesc() {
        Collections.sort(eventModels, new Comparator<EventModel>() {
            @Override
            public int compare(EventModel event1, EventModel event2) {
                return Float.compare(event2.getPrice(), event1.getPrice());
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}