package com.coelho.motorsport;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class UserprofileActivity extends AppCompatActivity {

    // VARIABLES
    TextView fullName, userName, location, motorcycle;
    TextInputLayout fullNameInput, emailInput, motorcycleInput, passwordInput;

    // GLOBAL VARIABLES TO HOLD USER DATA INSIDE THIS ACTIVITY
    String _NAME, _USERNAME, _EMAIL, _LOCATION, _MOTORCYCLE, _PASSWORD;

    // FIREBASE
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);

        reference = FirebaseDatabase.getInstance().getReference("users");

        // HOOKS
        fullName = findViewById(R.id.profileFullName);
        userName = findViewById(R.id.profileUsername);
        location = findViewById(R.id.profileLocalization);
        motorcycle = findViewById(R.id.profileMotorcycle);
        fullNameInput = findViewById(R.id.profile_FullName);
        emailInput = findViewById(R.id.profile_Email);
        motorcycleInput = findViewById(R.id.profile_Motorcycle);
        passwordInput = findViewById(R.id.profile_Password);

        // SHOW ALL DATA
        showAllUserData();
    }

    private void showAllUserData() {

        Intent intent = getIntent();
        _NAME = intent.getStringExtra("name");
        _USERNAME = intent.getStringExtra("username");
        _EMAIL = intent.getStringExtra("email");
        _LOCATION = intent.getStringExtra("location");
        _MOTORCYCLE = intent.getStringExtra("motorcycle");
        _PASSWORD = intent.getStringExtra("password");

        fullName.setText(_NAME);
        userName.setText(_USERNAME);
        location.setText(_LOCATION);
        motorcycle.setText(_MOTORCYCLE);
        Objects.requireNonNull(fullNameInput.getEditText()).setText(_NAME);
        Objects.requireNonNull(emailInput.getEditText()).setText(_EMAIL);
        Objects.requireNonNull(motorcycleInput.getEditText()).setText(_MOTORCYCLE);
        Objects.requireNonNull(passwordInput.getEditText()).setText(_PASSWORD);
    }

    public void update(View view) {
        if (isNameChanged() || isEmailChanged() || isMotorcycleChanged() || isPasswordChanged()) {
            Toast.makeText(this, "Data has been Updated Successfully", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Data is the Same and therefore Cannot be Updated", Toast.LENGTH_LONG).show();
        }
    }

    private boolean isNameChanged() {
        if (!_NAME.equals(Objects.requireNonNull(fullNameInput.getEditText()).getText().toString())) {
            reference.child(_USERNAME).child("name").setValue(fullNameInput.getEditText().getText().toString());
            fullName.setText(_NAME);
            return true;
        } else {
            return false;
        }
    }

    private boolean isEmailChanged() {
        if (!_EMAIL.equals(Objects.requireNonNull(emailInput.getEditText()).getText().toString())) {
            reference.child(_USERNAME).child("email").setValue(emailInput.getEditText().getText().toString());
            return true;
        } else {
            return false;
        }
    }

    private boolean isMotorcycleChanged() {
        if (!_MOTORCYCLE.equals(Objects.requireNonNull(motorcycleInput.getEditText()).getText().toString())) {
            reference.child(_USERNAME).child("motorcycle").setValue(motorcycleInput.getEditText().getText().toString());
            motorcycle.setText(_MOTORCYCLE);
            return true;
        } else {
            return false;
        }
    }

    private boolean isPasswordChanged() {
        if (!_PASSWORD.equals(Objects.requireNonNull(passwordInput.getEditText()).getText().toString())) {
            reference.child(_USERNAME).child("password").setValue(passwordInput.getEditText().getText().toString());
            _PASSWORD = passwordInput.getEditText().getText().toString();
            return true;
        } else {
            return false;
        }
    }
}