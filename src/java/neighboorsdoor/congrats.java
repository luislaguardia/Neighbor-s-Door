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

public class congrats extends AppCompatActivity {

    private VideoView videoView;
    private Button goBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congrats);

        videoView = findViewById(R.id.videoView1);
        goBackButton = findViewById(R.id.buttonGoBack3);

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

            }
        });

        showCongrats();

    }

    // Method to play the jump scare video
    private void playCongrats() {
        videoView.setVisibility(View.VISIBLE);
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.congrats);
        videoView.setVideoURI(videoUri);
        videoView.start();
    }

    // Call this method when you want to play the jump scare video
    // For example, when the last attempt picks the right door
    private void showCongrats() {
        playCongrats();
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "you can't run...", Toast.LENGTH_SHORT).show();
    }
}
