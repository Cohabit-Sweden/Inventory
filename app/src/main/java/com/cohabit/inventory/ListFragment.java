package com.cohabit.inventory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class ListFragment extends Fragment {

    private RecyclerView recyclerViewItems;
    private DatabaseReference itemsDatabase;
    private ItemsAdapter itemsAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Database
        itemsDatabase = FirebaseDatabase.getInstance("https://cohabit-inventory-default-rtdb.europe-west1.firebasedatabase.app").getReference().child("items");

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.items_list, container, false);

        // Add the following lines to create RecyclerView
        recyclerViewItems = rootView.findViewById(R.id.recyclerview_items);
        recyclerViewItems.setLayoutManager(new LinearLayoutManager(getActivity()));
        FirebaseRecyclerOptions<Item> options = new FirebaseRecyclerOptions.Builder<Item>().setQuery(itemsDatabase, Item.class).build();
        itemsAdapter = new ItemsAdapter(options);
        recyclerViewItems.setAdapter(itemsAdapter);

        return rootView;
    }

    @Override public void onStart()
    {
        super.onStart();
        itemsAdapter.startListening();
    }

    // Function to tell the app to stop getting
    // data from database on stopping of the activity
    @Override public void onStop()
    {
        super.onStop();
        itemsAdapter.stopListening();
    }
}
