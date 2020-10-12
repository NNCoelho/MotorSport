package com.coelho.motorsport;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

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
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

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
}