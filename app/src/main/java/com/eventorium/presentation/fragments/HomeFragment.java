package com.eventorium.presentation.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.SnapHelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eventorium.R;
import com.eventorium.data.models.Event;
import com.eventorium.databinding.FragmentHomeBinding;
import com.eventorium.presentation.adapters.EventsAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private static List<Event> events = new ArrayList<>();


    public HomeFragment() { }

    public static HomeFragment newInstance() { return new HomeFragment(); }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        prepareEventData();

        attachSnapHelpers();

        binding.eventsRecycleView.setAdapter(new EventsAdapter(events));
    }

    public void attachSnapHelpers() {
        SnapHelper snapHelperEvents = new LinearSnapHelper();
        snapHelperEvents.attachToRecyclerView(binding.eventsRecycleView);
    }

    public void prepareEventData() {
        events.clear();
        events.add(new Event("Concert",  "Novi Sad", R.drawable.conference));
        events.add(new Event("Conference",  "Novi Sad", R.drawable.conference));
        events.add(new Event("Workshop",  "Novi Sad", R.drawable.conference));
        events.add(new Event("Festival",  "Novi Sad", R.drawable.conference));
        events.add(new Event("Webinar", "Novi Sad", R.drawable.conference));
    }
}