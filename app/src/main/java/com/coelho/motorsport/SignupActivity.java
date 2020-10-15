package com.coelho.motorsport;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class SignupActivity extends AppCompatActivity {

    // VARIABLES
    TextInputLayout regName, regUsername, regEmail, regLocation, regMoto, regPassword;
    Button regBtn, regToLoginBtn;

    // FIREABSE CONNECTION
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    // FIELDS VALIDATION
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

    private Boolean validateLocation() {
        String val = Objects.requireNonNull(regLocation.getEditText()).getText().toString();
        if (val.isEmpty()) {
            regLocation.setError("Field cannot be empty");
            return false;
        } else {
            regLocation.setError(null);
            return true;
        }
    }

    private Boolean validateMotorcycle() {
        String val = Objects.requireNonNull(regMoto.getEditText()).getText().toString();
        if (val.isEmpty()) {
            regMoto.setError("Field cannot be empty");
            return false;
        } else {
            regMoto.setError(null);
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

        // HOOKS
        regName = findViewById(R.id.signupFullname);
        regUsername = findViewById(R.id.signupUsername);
        regEmail = findViewById(R.id.signupEmail);
        regLocation = findViewById(R.id.signupLocation);
        regMoto = findViewById(R.id.signupMotorcycle);
        regPassword = findViewById(R.id.signupPassword);
        regBtn = findViewById(R.id.btnSignup);
        regToLoginBtn = findViewById(R.id.btnToLoginFromSignup);

        // SAVE DATA IN FIREBASE ON BUTTON CLICK
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("users");

                if (!validateName() | !validateUsername() | !validateEmail() | !validateLocation() | !validateMotorcycle() | !validatePassword()) {
                    return;
                } else {
                    Toast.makeText(SignupActivity.this, "User Successfully Registered on the Database!\n Please proceed to Login!", Toast.LENGTH_SHORT).show();
                }

                // Get all the values
                String name = Objects.requireNonNull(regName.getEditText()).getText().toString();
                String username = Objects.requireNonNull(regUsername.getEditText()).getText().toString();
                String email = Objects.requireNonNull(regEmail.getEditText()).getText().toString();
                String location = Objects.requireNonNull(regLocation.getEditText()).getText().toString();
                String motorcycle = Objects.requireNonNull(regMoto.getEditText()).getText().toString();
                String password = Objects.requireNonNull(regPassword.getEditText()).getText().toString();

                UserHelperClass helperClass = new UserHelperClass(name, username, email, location, motorcycle, password);
                reference.child(username).setValue(helperClass);
            }
        });

        // RETURN TO LOGIN
        regToLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}