package com.example.self_care_companion.ui.journal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.self_care_companion.R;
import com.example.self_care_companion.databinding.FragmentJournalBinding;

public class JournalFragment extends Fragment {

    private FragmentJournalBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        JournalViewModel journalViewModel =
                new ViewModelProvider(this).get(JournalViewModel.class);

        Bundle args = getArguments();
        String moodParam;

        if (args != null) {
            moodParam = args.getString("moodParam");
        } else {
            moodParam = null;
        }

        binding = FragmentJournalBinding.inflate(inflater, container, false);

        binding.nextButton.setOnClickListener(v -> {
            if (moodParam != null && !moodParam.isEmpty()) {
                Bundle bundle = new Bundle();
                bundle.putString("moodParam", moodParam);
                Navigation.findNavController(v).navigate(R.id.navigation_insights, bundle);
            } else {
                Navigation.findNavController(v).navigate(R.id.navigation_insights);
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
