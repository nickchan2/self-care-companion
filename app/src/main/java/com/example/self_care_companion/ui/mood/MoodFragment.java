package com.example.self_care_companion.ui.mood;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.self_care_companion.databinding.FragmentMoodBinding;

public class MoodFragment extends Fragment {

    private FragmentMoodBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        MoodViewModel MoodViewModel =
                new ViewModelProvider(this).get(MoodViewModel.class);

        binding = FragmentMoodBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textMood;
        MoodViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}