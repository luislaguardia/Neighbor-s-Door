package com.luis.neighboorsdoor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class CorrectActivity4 extends AppCompatActivity {

    private static final int MAX_ATTEMPTS = 3;
    private int attempts = MAX_ATTEMPTS; // Initialize attempts to 3

    @Override
    protected void onResume() {
        super.onResume();
        resetAttempts();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        resetAttempts(); // Reset attempts when the activity is destroyed
    }

    private void resetAttempts() {
        SharedPreferences preferences = getSharedPreferences("attempts", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("attempts");
        editor.apply();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correct4);

        // Get attempts from Intent
        Intent intent = getIntent();
        attempts = intent.getIntExtra("attempts", MAX_ATTEMPTS); // Default to MAX_ATTEMPTS if not found

        MediaPlayer song = MediaPlayer.create(this, R.raw.scarrysound);
        song.start();

        TextView textViewAttempts = findViewById(R.id.textViewAttempts);
        updateAttemptsText(textViewAttempts);

        ImageView door1 = findViewById(R.id.imageView1);
        ImageView door2 = findViewById(R.id.imageView2);
        ImageView door3 = findViewById(R.id.imageView3);

        door1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleDoorClick(1, v, textViewAttempts);
            }
        });

        door2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleDoorClick(2, v, textViewAttempts);
            }
        });

        door3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleDoorClick(3, v, textViewAttempts);
            }
        });
    }

    public void onGoBackButtonClick(View view) {
        clearAttempts();
        Intent intent = new Intent(this, CorrectActivity4.class); // Navigate to CorrectActivity4
        startActivity(intent);
        finish();
    }


    private void handleDoorClick(int selectedDoor, View view, TextView textViewAttempts) {
        MediaPlayer door = MediaPlayer.create(this, R.raw.door_closing);
        door.start();

        final float originalAlpha = view.getAlpha();
        final float originalTranslationY = view.getTranslationY();

        view.animate()
                .alpha(0.5f)
                .translationYBy(-50)
                .setDuration(600)
                .setInterpolator(new AccelerateInterpolator())
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        view.animate()
                                .alpha(originalAlpha)
                                .translationY(originalTranslationY)
                                .setDuration(0)
                                .start();

                        navigateToActivity(selectedDoor, textViewAttempts);
                    }
                })
                .start();
    }

    private void saveAttempts(int attempts) {
        SharedPreferences preferences = getSharedPreferences("attempts", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("attempts", attempts);
        editor.apply();
    }

    private int getAttempts() {
        SharedPreferences preferences = getSharedPreferences("attempts", MODE_PRIVATE);
        return preferences.getInt("attempts", attempts);
    }


    private void navigateToActivity(int selectedDoor, TextView textViewAttempts) {
        final int correctDoor = new Random().nextInt(3);

        Class<?> activityClass;

        if (selectedDoor == correctDoor) {
            activityClass = cutscene_lvl5.class;
        } else {
            if (attempts == 0) {
                Intent intent = new Intent(this, jumpscare.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                return;
            }

            attempts--; // Decrement attempts only if the selected door is wrong
            saveAttempts(attempts);
            updateAttemptsText(textViewAttempts);

            int incorrectIndex;
            do {
                incorrectIndex = new Random().nextInt(3);
            } while (incorrectIndex == correctDoor);

            switch (incorrectIndex) {
                case 0:
                    activityClass = Incorrect1Activity.class;
                    break;
                case 1:
                    activityClass = Incorrect2Activity.class;
                    break;
                case 2:
                    activityClass = Incorrect3Activity.class;
                    break;
                default:
                    activityClass = jumpscare.class;
                    break;
            }
        }

        Intent intent = new Intent(this, activityClass);
        intent.putExtra("attempts", attempts); // Pass the number of attempts to the next activity
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

    }


    private void updateAttemptsText(TextView textViewAttempts) {
        attempts = getAttempts();
        textViewAttempts.setText("Lives: " + attempts + "/" + MAX_ATTEMPTS);
    }

    private void clearAttempts() {
        SharedPreferences preferences = getSharedPreferences("attempts", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("attempts");
        editor.apply();
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "you can't run...", Toast.LENGTH_SHORT).show();
    }
}

