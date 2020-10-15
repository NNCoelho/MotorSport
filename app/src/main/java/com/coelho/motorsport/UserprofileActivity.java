package com.coelho.motorsport;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class UserprofileActivity extends AppCompatActivity {

    // Variables
    TextView fullName, userName, location, motorcycle;
    TextInputLayout fullNameInput, emailInput, motorcycleInput, passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);

        // Hooks
        fullName = findViewById(R.id.profileFullName);
        userName = findViewById(R.id.profileUsername);
        location = findViewById(R.id.profileLocalization);
        motorcycle = findViewById(R.id.profileMotorcycle);
        fullNameInput = findViewById(R.id.profile_FullName);
        emailInput = findViewById(R.id.profile_Email);
        motorcycleInput = findViewById(R.id.profile_Motorcycle);
        passwordInput = findViewById(R.id.profile_Password);

        // Show all data
        showAllUserData();
    }

    private void showAllUserData() {

        Intent intent = getIntent();
        String user_name = intent.getStringExtra("name");
        String user_username = intent.getStringExtra("username");
        String user_email = intent.getStringExtra("email");
        String user_location = intent.getStringExtra("location");
        String user_motorcycle = intent.getStringExtra("motorcycle");
        String user_password = intent.getStringExtra("password");

        fullName.setText(user_name);
        userName.setText(user_username);
        location.setText(user_location);
        motorcycle.setText(user_motorcycle);
        Objects.requireNonNull(fullNameInput.getEditText()).setText(user_name);
        Objects.requireNonNull(emailInput.getEditText()).setText(user_email);
        Objects.requireNonNull(motorcycleInput.getEditText()).setText(user_motorcycle);
        Objects.requireNonNull(passwordInput.getEditText()).setText(user_password);

    }
}