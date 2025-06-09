package com.example.self_care_companion.ui.insights;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.self_care_companion.databinding.FragmentInsightsBinding;
import com.example.self_care_companion.R;

public class InsightsFragment extends Fragment {
    private FragmentInsightsBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        InsightsViewModel insightsViewModel =
                new ViewModelProvider(this).get(InsightsViewModel.class);

        binding = FragmentInsightsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.imageButton.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.navigation_home);
        });


        final TextView moodReport = binding.moodReport;
        insightsViewModel.getMoodReport().observe(getViewLifecycleOwner(), moodReport::setText);
        final TextView suggestionText1 = binding.suggestionText1;
        insightsViewModel.getSuggestionText1().observe(getViewLifecycleOwner(), suggestionText1::setText);
        final TextView suggestionText2 = binding.suggestionText2;
        insightsViewModel.getSuggestionText2().observe(getViewLifecycleOwner(), suggestionText2::setText);
        final TextView suggestionText3 = binding.suggestionText3;
        insightsViewModel.getSuggestionText3().observe(getViewLifecycleOwner(), suggestionText3::setText);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}