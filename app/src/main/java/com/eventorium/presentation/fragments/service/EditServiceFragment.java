package com.eventorium.presentation.fragments.service;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eventorium.R;
import com.eventorium.data.models.Service;
import com.eventorium.databinding.FragmentEditServiceBinding;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EditServiceFragment extends Fragment {

    private FragmentEditServiceBinding binding;

    private static final String ARG_SERVICE = "service";
    private Service service;

    public EditServiceFragment() {
    }

    public static EditServiceFragment newInstance(Service service) {
        EditServiceFragment fragment = new EditServiceFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_SERVICE, service);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            service = getArguments().getParcelable(ARG_SERVICE);
        }
    }


    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEditServiceBinding.inflate(inflater, container, false);

        fillForm();
        createDatePickers();

        return binding.getRoot();
    }

    private void fillForm() {
        binding.serviceNameEditText.setText(service.getName());
        binding.serviceDescriptionText.setText("Description");
        binding.serviceSpecificitiesText.setText("Specificities");
        binding.servicePriceText.setText(service.getPrice().toString());
        binding.serviceDiscountText.setText("0.0");
        binding.serviceCancellationDeadlineText.setText("30.4.2024.");
        binding.serviceReservationDeadlineText.setText("30.4.2025.");
    }

    private TextInputEditText reservationDate;
    private TextInputEditText cancellationDate;
    private void createDatePickers() {
        reservationDate = binding.serviceReservationDeadlineText;
        cancellationDate = binding.serviceCancellationDeadlineText;

        MaterialDatePicker<Long> reservationPicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select a Date")
                .build();
        MaterialDatePicker<Long> cancellationPicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select a Date")
                .build();


        reservationDate.setOnClickListener(v ->
                reservationPicker.show(requireActivity().getSupportFragmentManager(), "DATE_PICKER"));

        cancellationDate.setOnClickListener(v ->
                cancellationPicker.show(requireActivity().getSupportFragmentManager(), "DATE_PICKER"));

        reservationPicker.addOnPositiveButtonClickListener(selection -> {
            String selectedDate = new SimpleDateFormat("dd.MM.yyyy")
                    .format(new Date(selection));
            reservationDate.setText(selectedDate);
        });

        cancellationPicker.addOnPositiveButtonClickListener(selection -> {
            String selectedDate = new SimpleDateFormat("dd.MM.yyyy")
                    .format(new Date(selection));
            cancellationDate.setText(selectedDate);
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}