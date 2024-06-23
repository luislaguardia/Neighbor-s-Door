package com.luis.neighboorsdoor;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class cutscene_lvl1 extends AppCompatActivity {

    private VideoView videoView;
    private Button skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cutscene_lvl1);

        videoView = findViewById(R.id.cutscene1);
        skip = findViewById(R.id.skip);

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Intent i = new Intent(getApplicationContext(), CorrectActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        showscene1();

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), CorrectActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
    }

    // Method to play the jump scare video
    private void playscene1() {
        videoView.setVisibility(View.VISIBLE);
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.scene1);
        videoView.setVideoURI(videoUri);
        videoView.start();
    }

    // Call this method when you want to play the jump scare video
    // For example, when the last attempt picks the right door
    private void showscene1() {
        playscene1();
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "you can't run...", Toast.LENGTH_SHORT).show();
    }

}
