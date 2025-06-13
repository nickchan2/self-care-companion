package com.example.self_care_companion.ui.splash;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.self_care_companion.R;

public class SplashFragment extends Fragment {

    public SplashFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splash, container, false);

        Button signUpBtn = view.findViewById(R.id.btn_sign_up);
        Button loginBtn = view.findViewById(R.id.btn_log_in);

        SharedPreferences prefs = requireContext().getSharedPreferences("prefs", 0);
        boolean isFirstTimeUser = prefs.getBoolean("first_time", true);

        if (isFirstTimeUser) {
            signUpBtn.setVisibility(View.VISIBLE);
            loginBtn.setVisibility(View.GONE);
        } else {
            signUpBtn.setVisibility(View.GONE);
            loginBtn.setVisibility(View.VISIBLE);
        }

        signUpBtn.setOnClickListener(v -> {
            prefs.edit().putBoolean("first_time", false).apply();
            Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_mainFragment);
        });

        loginBtn.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_loginFragment);
        });

        return view;
    }
}
