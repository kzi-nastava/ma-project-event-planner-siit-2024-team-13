package com.eventorium.presentation.adapters.service;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.eventorium.R;
import com.eventorium.data.models.Service;
import com.eventorium.presentation.fragments.service.EditServiceFragment;
import com.eventorium.presentation.fragments.service.ServiceDetailsFragment;

import java.util.List;

public class ManageableServiceAdapter extends BaseServiceAdapter<ManageableServiceAdapter.ManageableServiceViewHolder> {

    public ManageableServiceAdapter(List<Service> services) {
        super(services);
    }

    @NonNull
    @Override
    public ManageableServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.manageable_service_card, parent, false);
        return new ManageableServiceViewHolder(view);
    }

    public static class ManageableServiceViewHolder extends BaseServiceViewHolder {
        TextView nameTextView;
        TextView priceTextView;
        ImageView photoImageview;

        Button seeMoreButton;
        Button editButton;
        Button deleteButton;

        public ManageableServiceViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.service_name);
            priceTextView = itemView.findViewById(R.id.service_price);
            photoImageview = itemView.findViewById(R.id.service_photo);
            seeMoreButton = itemView.findViewById(R.id.see_more_button);
            editButton = itemView.findViewById(R.id.editButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void bind(Service service) {
            nameTextView.setText(service.getName());
            priceTextView.setText(service.getPrice().toString());
            photoImageview.setImageResource(service.getPhoto());
            seeMoreButton.setOnClickListener(v -> {
                NavController navController = Navigation.findNavController(itemView);
                navController.navigate(R.id.action_manageService_to_serviceDetailsFragment,
                        ServiceDetailsFragment.newInstance(service).getArguments());
            });
            editButton.setOnClickListener(v -> {
                NavController navController = Navigation.findNavController(itemView);
                navController.navigate(R.id.action_manageService_to_editService,
                        ServiceDetailsFragment.newInstance(service).getArguments());
            });

        }
    }
}
