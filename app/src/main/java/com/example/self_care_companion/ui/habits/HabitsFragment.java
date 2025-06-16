package com.example.self_care_companion.ui.habits;

import static com.example.self_care_companion.MainActivity.databaseHelper;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.self_care_companion.R;
import com.example.self_care_companion.databinding.FragmentHabitsBinding;

import java.util.Set;

public class HabitsFragment extends Fragment {

    private FragmentHabitsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HabitsViewModel HabitsViewModel =
                new ViewModelProvider(this).get(HabitsViewModel.class);

        binding = FragmentHabitsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHabits;
        HabitsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        Set<String> habits = databaseHelper.getUniqueHabits();
        habits.add("Water Intake|cups");
        habits.add("Exercise|hrs");
        habits.add("Sleep|hrs");

        LinearLayout habitContainer = root.findViewById(R.id.habitContainer);
        for (String habit : habits) {
            String[] habitParts = habit.split("\\|");

            if (habitParts.length < 2) {
                continue;
            }

            String labelValue = habitParts[0].trim();
            String unitValue = habitParts[1].trim();

            View habitRow = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_habit_row, habitContainer, false);

            TextView label = habitRow.findViewById(R.id.label_habit);
            TextView unit = habitRow.findViewById(R.id.unit_habit);

            label.setText(labelValue);
            unit.setText(unitValue);

            habitContainer.addView(habitRow);
        }

        binding.buttonSaveHabits.setOnClickListener(v -> {
            for (int i = 0; i < habitContainer.getChildCount(); i++) {
                View habitRow = habitContainer.getChildAt(i);
                TextView label = habitRow.findViewById(R.id.label_habit);
                TextView unit = habitRow.findViewById(R.id.unit_habit);
                EditText valueInput = habitRow.findViewById(R.id.input_habit);

                String labelValue = label.getText().toString();
                String unitValue = unit.getText().toString();
                String valueText = valueInput.getText().toString();

                if (!valueText.isEmpty()) {
                    int value = Integer.parseInt(valueText);
                    databaseHelper.addHabit(labelValue, value, unitValue);
                }
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
