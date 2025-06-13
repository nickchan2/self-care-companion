package com.example.self_care_companion.ui.journal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.self_care_companion.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class JournalNavFragment extends Fragment {

    public JournalNavFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_journal_nav, container, false);

        ImageButton homeButton = view.findViewById(R.id.homeButton);
        homeButton.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            BottomNavigationView navView = requireActivity().findViewById(R.id.nav_view);

            navController.popBackStack(R.id.navigation_home, false);
            navView.setSelectedItemId(R.id.navigation_home);  // triggers nav + syncs bottom nav UI
        });

        return view;
    }
}
