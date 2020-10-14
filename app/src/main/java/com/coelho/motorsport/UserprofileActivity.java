package com.coelho.motorsport;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class UserprofileActivity extends AppCompatActivity {

    TextView fullName, userName, location, motorcycle;
    TextInputLayout fullNameInput, emailInput, localizationInput, motorcycleInput, passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);

        fullName = findViewById(R.id.profileFullName);
        userName = findViewById(R.id.profileUsername);
        location = findViewById(R.id.profileLocalization);
        motorcycle = findViewById(R.id.profileMotorcycle);
        fullNameInput = findViewById(R.id.profile_FullName);
        emailInput = findViewById(R.id.profile_Email);
        localizationInput = findViewById(R.id.profile_Localization);
        motorcycleInput = findViewById(R.id.profile_Motorcycle);
        passwordInput = findViewById(R.id.profile_Password);
    }
}