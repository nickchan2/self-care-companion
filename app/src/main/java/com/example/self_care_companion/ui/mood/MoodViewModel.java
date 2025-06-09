package com.example.self_care_companion.ui.mood;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MoodViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public MoodViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Check-In");
    }

    public LiveData<String> getText() {
        return mText;
    }

}

