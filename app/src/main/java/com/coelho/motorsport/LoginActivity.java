package com.coelho.motorsport;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    // VARIABLES
    Button btnNewUser, btnLogin;
    ImageView imageMoto;
    TextView welcomeText, loginMessage;
    TextInputLayout username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // HOOKS
        btnNewUser = findViewById(R.id.btnNewUser);
        btnLogin = findViewById(R.id.btnLogin);
        imageMoto = findViewById(R.id.imageMoto);
        welcomeText = findViewById(R.id.welcomeBack);
        loginMessage = findViewById(R.id.loginContinue);
        username = findViewById(R.id.usernameLogin);
        password = findViewById(R.id.passwordLogin);

        btnNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);

                Pair[] pairs = new Pair[7];

                pairs[0] = new Pair<View, String>(imageMoto, "logo_image");
                pairs[1] = new Pair<View, String>(welcomeText, "welcome_text");
                pairs[2] = new Pair<View, String>(loginMessage, "user_message");
                pairs[3] = new Pair<View, String>(username, "username");
                pairs[4] = new Pair<View, String>(password, "password");
                pairs[5] = new Pair<View, String>(btnLogin, "btn_login_signup");
                pairs[6] = new Pair<View, String>(btnNewUser, "login_signup_new_user");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this, pairs);
                    startActivity(intent, options.toBundle());
                }
            }
        });
    }

    // Login event
    private Boolean validateUsername() {
        String val = Objects.requireNonNull(username.getEditText()).getText().toString();

        if (val.isEmpty()) {
            username.setError("Field cannot be empty");
            return false;
        } else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = Objects.requireNonNull(password.getEditText()).getText().toString();

        if (val.isEmpty()) {
            password.setError("Field cannot be empty");
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }

    public void loginUser(View view) {
        if (!validateUsername() | !validatePassword()) {
            return;
        } else {
            isUser();
        }
    }

    private void isUser() {

        final String usernameInput = Objects.requireNonNull(username.getEditText()).getText().toString().trim();
        final String passwordInput = Objects.requireNonNull(password.getEditText()).getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");

        Query checkUser = reference.orderByChild("username").equalTo(usernameInput);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    username.setError(null);
                    username.setErrorEnabled(false);
                    String passwordFromDB = snapshot.child(usernameInput).child("password").getValue(String.class);

                    if (Objects.equals(passwordFromDB, passwordInput)) {
                        password.setError(null);
                        password.setErrorEnabled(false);

                        String nameFromDB = snapshot.child(usernameInput).child("name").getValue(String.class);
                        String usernameFromDB = snapshot.child(usernameInput).child("username").getValue(String.class);
                        String emailFromDB = snapshot.child(usernameInput).child("email").getValue(String.class);
                        String locationFromDB = snapshot.child(usernameInput).child("location").getValue(String.class);
                        String motorcycleFromDB = snapshot.child(usernameInput).child("motorcycle").getValue(String.class);

                        Intent intent = new Intent(getApplicationContext(), UserprofileActivity.class);
                        intent.putExtra("name", nameFromDB);
                        intent.putExtra("username", usernameFromDB);
                        intent.putExtra("email", emailFromDB);
                        intent.putExtra("location", locationFromDB);
                        intent.putExtra("motorcycle", motorcycleFromDB);
                        intent.putExtra("password", passwordFromDB);
                        startActivity(intent);
                    } else {
                        password.setError("Wrong password");
                        password.requestFocus();
                    }
                } else {
                    username.setError("No such user exist in our database");
                    username.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}