package com.luis.neighboorsdoor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Instructions1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions1);
        Button instructionButton = (Button) findViewById(R.id.nextbutton);
        instructionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start InstructionActivity
                Intent intent = new Intent(Instructions1.this, Instructions2.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "you can't run...", Toast.LENGTH_SHORT).show();
    }

}