package com.luis.neighboorsdoor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class cutscene_lvl4 extends AppCompatActivity {

    private static final int MAX_ATTEMPTS = 3;
    private int attempts = MAX_ATTEMPTS;

    private VideoView videoView;
    private Button skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cutscene_lvl4);

        Intent intent = getIntent();
        attempts = intent.getIntExtra("attempts", MAX_ATTEMPTS);

        videoView = findViewById(R.id.cutscene4);
        skip = findViewById(R.id.skip);

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Intent i = new Intent(getApplicationContext(), CorrectActivity4.class);
                startActivity(i);
                saveAttempts(attempts);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        showscene4();

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), CorrectActivity4.class);
                startActivity(i);
                i.putExtra("attempts", attempts);
                startActivity(i);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
    }

    private void saveAttempts(int attempts) {
        SharedPreferences preferences = getSharedPreferences("attempts", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("attempts", attempts);
        editor.apply();
    }

    private void playscene4() {
        videoView.setVisibility(View.VISIBLE);
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.scene4);
        videoView.setVideoURI(videoUri);
        videoView.start();
    }

    private void showscene4() {
        playscene4();
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "you can't run...", Toast.LENGTH_SHORT).show();
    }
}