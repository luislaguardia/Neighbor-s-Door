package com.luis.neighboorsdoor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Incorrect2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incorrect2);

        Button goBackButton = findViewById(R.id.buttonGoBack2);
        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the current activity and return to the previous activity
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

            }
        });
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "you can't run...", Toast.LENGTH_SHORT).show();
    }
}

