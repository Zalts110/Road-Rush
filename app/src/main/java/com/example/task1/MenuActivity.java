package com.example.task1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class MenuActivity extends AppCompatActivity {
    private AppCompatButton fastModeButton, slowModeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        fastModeButton = findViewById(R.id.fastModeButton);
        slowModeButton = findViewById(R.id.slowModeButton);

        fastModeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int gameSpeed = 500;
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("MY_INT_SPEED",gameSpeed);
                startActivity(intent);

            }
        });

        slowModeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int gameSpeed = 1000;
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("MY_INT_SPEED",gameSpeed);
                startActivity(intent);
            }
        });
    }

}

