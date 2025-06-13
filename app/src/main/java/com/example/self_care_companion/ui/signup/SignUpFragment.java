package com.example.self_care_companion.ui.signup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.self_care_companion.DatabaseHelper;
import com.example.self_care_companion.MainActivity;
import com.example.self_care_companion.R;

public class SignUpFragment extends Fragment {

    public SignUpFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        EditText usernameInput = view.findViewById(R.id.et_username);
        EditText emailInput = view.findViewById(R.id.et_email);
        EditText passwordInput = view.findViewById(R.id.et_password);
        Button signUpButton = view.findViewById(R.id.btn_create_account);

        signUpButton.setOnClickListener(v -> {
            String name = usernameInput.getText().toString().trim();
            String email = emailInput.getText().toString().trim();
            String pin = passwordInput.getText().toString().trim();

            if (name.isEmpty() || email.isEmpty() || pin.isEmpty()) {
                Toast.makeText(getContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            DatabaseHelper db = MainActivity.databaseHelper;
            if (db.checkifUserExists()) {
                Toast.makeText(getContext(), "User already exists. Please log in.", Toast.LENGTH_SHORT).show();
                return;
            }

            String hashedPin = DatabaseHelper.hashPin(pin);
            db.addUser(name, email, hashedPin);
            Toast.makeText(getContext(), "Account created!", Toast.LENGTH_SHORT).show();

            Navigation.findNavController(v).navigate(R.id.action_signUpFragment_to_mainFragment);
        });

        return view;
    }
}
