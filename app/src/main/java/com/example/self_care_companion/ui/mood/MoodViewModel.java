package com.example.self_care_companion.ui.mood;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MoodViewModel extends ViewModel {
    public static final MutableLiveData<List<String>> selectedMoods = new MutableLiveData<>(new ArrayList<>());
    public LiveData<List<String>> getSelectedMoods() {
        return selectedMoods;
    }

    public static void toggleMood(String mood) {
        List<String> current = selectedMoods.getValue();
        if (current == null) current = new ArrayList<>();
        if (current.contains(mood)) {
            current.remove(mood);
        } else {
            current.add(mood);
        }
        selectedMoods.setValue(new ArrayList<>(current)); // trigger observers
    }

    private final MutableLiveData<String> mText;

    public MoodViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Check-In");
    }

    public LiveData<String> getText() {
        return mText;
    }
}

