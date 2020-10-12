package com.coelho.motorsport;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class SignupActivity extends AppCompatActivity {

    // Variables
    TextInputLayout regName, regUsername, regEmail, regPhoneNo, regPassword;
    Button regBtn, regToLoginBtn;

    // Firebase connection
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    // Fields validation
    private Boolean validateName() {
        String val = Objects.requireNonNull(regName.getEditText()).getText().toString();
        if (val.isEmpty()) {
            regName.setError("Field cannot be empty");
            return false;
        } else {
            regName.setError(null);
            regName.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateUsername() {
        String val = Objects.requireNonNull(regUsername.getEditText()).getText().toString();
        String noWhiteSpaces = "\\A\\w{4,20}\\z";

        if (val.isEmpty()) {
            regUsername.setError("Field cannot be empty");
            return false;
        } else if (val.length() >= 15) {
            regUsername.setError("Username too long");
            return false;
        } else if (!val.matches(noWhiteSpaces)) {
            regUsername.setError("White spaces are not allowed");
            return false;
        } else {
            regUsername.setError(null);
            return true;
        }
    }

    private Boolean validateEmail() {
        String val = Objects.requireNonNull(regEmail.getEditText()).getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            regEmail.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            regEmail.setError("Invalid email address");
            return false;
        } else {
            regEmail.setError(null);
            return true;
        }
    }

    private Boolean validatePhoneNo() {
        String val = Objects.requireNonNull(regPhoneNo.getEditText()).getText().toString();
        if (val.isEmpty()) {
            regPhoneNo.setError("Field cannot be empty");
            return false;
        } else {
            regPhoneNo.setError(null);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = Objects.requireNonNull(regPassword.getEditText()).getText().toString();
        String passwordVal = "^[a-zA-Z0-9@.#$%^&*_\\\\]+$";

        if (val.isEmpty()) {
            regPassword.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            regPassword.setError("Password is to weak");
            return false;
        } else {
            regPassword.setError(null);
            regPassword.setErrorEnabled(false);
            return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Hooks
        regName = findViewById(R.id.signupFullname);
        regUsername = findViewById(R.id.signupUsername);
        regEmail = findViewById(R.id.signupEmail);
        regPhoneNo = findViewById(R.id.signupPhone);
        regPassword = findViewById(R.id.signupPassword);
        regBtn = findViewById(R.id.btnSignup);
        regToLoginBtn = findViewById(R.id.btnLoginSignup);

        // Save data in Firebase on button click
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("users");

                if (!validateName() | !validateUsername() | !validateEmail() | !validatePhoneNo() | !validatePassword()) {
                    return;
                }

                // Get all the values
                String name = Objects.requireNonNull(regName.getEditText()).getText().toString();
                String username = Objects.requireNonNull(regUsername.getEditText()).getText().toString();
                String email = Objects.requireNonNull(regEmail.getEditText()).getText().toString();
                String phoneNo = Objects.requireNonNull(regPhoneNo.getEditText()).getText().toString();
                String password = Objects.requireNonNull(regPassword.getEditText()).getText().toString();

                UserHelperClass helperClass = new UserHelperClass(name, username, email, phoneNo, password);
                reference.child(name).setValue(helperClass);
            }
        });
    }
}