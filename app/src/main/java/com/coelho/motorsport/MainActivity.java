package com.coelho.motorsport;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Pair;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final int SPLASH_SCREEN = 3600;

    // VARIABLES
    Animation topAnim, botttomAnim;
    ImageView motoCaferacer;
    TextView logo, slogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // ANIMATIONS
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        botttomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        // HOOKS
        motoCaferacer = findViewById(R.id.imageMoto);
        logo = findViewById(R.id.motorsportTitle);
        slogan = findViewById(R.id.motorsportSlogan);

        motoCaferacer.setAnimation(topAnim);
        logo.setAnimation(botttomAnim);
        slogan.setAnimation(botttomAnim);

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);

                Pair[] pairs = new Pair[2];
                pairs[0] = new Pair<View, String>(motoCaferacer, "logo_image");
                pairs[1] = new Pair<View, String>(logo, "welcome_text");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, pairs);
                    startActivity(intent, options.toBundle());
                }
            }
        }, SPLASH_SCREEN);
    }
}