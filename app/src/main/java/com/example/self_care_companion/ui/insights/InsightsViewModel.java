package com.example.self_care_companion.ui.insights;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class InsightsViewModel extends ViewModel {
    private final MutableLiveData<String> moodReport = new MutableLiveData<>();
    private final MutableLiveData<String> suggestionText1 = new MutableLiveData<>();
    private final MutableLiveData<String> suggestionText2 = new MutableLiveData<>();
    private final MutableLiveData<String> suggestionText3 = new MutableLiveData<>();

    String loading = "Loading...";

    public InsightsViewModel() {
        moodReport.setValue(loading);
        suggestionText1.setValue(loading);
        suggestionText2.setValue(loading);
        suggestionText3.setValue(loading);
    }

    public LiveData<String> getMoodReport() {
        return moodReport;
    }

    public LiveData<String> getSuggestionText1() {
        return suggestionText1;
    }

    public LiveData<String> getSuggestionText2() {
        return suggestionText2;
    }

    public LiveData<String> getSuggestionText3() {
        return suggestionText3;
    }

    public void setMoodReport(String text) {
        moodReport.setValue(text);
    }

    public void setSuggestionText1(String text) {
        suggestionText1.setValue(text);
    }

    public void setSuggestionText2(String text) {
        suggestionText2.setValue(text);
    }

    public void setSuggestionText3(String text) {
        suggestionText3.setValue(text);
    }
}
