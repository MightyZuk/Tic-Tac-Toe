package com.example.tictactoe;

import android.content.Intent;
//import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button button,gameName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameName = findViewById(R.id.gameName);
        button = findViewById(R.id.button);
    }
        // on clicking button takes to gamePage
    public void onClick(View view) {
//        MediaPlayer player = MediaPlayer.create(this,R.raw.sound);
//        player.start();
        Intent intent = new Intent(MainActivity.this, gamePage.class);
        startActivity(intent);
    }

    public void onTap(View view) {
        Toast.makeText(this,"developed by MightyZuk",Toast.LENGTH_SHORT).show();
    }

    public void onBackPressed() {
        showAlertDialog();
    }

    private void showAlertDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.WelcomeStyle);
        builder.setCancelable(false);
        builder.setTitle("Exit");
        builder.setMessage("Do you really wanna exit ?");
        builder.setPositiveButton("YES", (dialog, which) -> finish());
        builder.setNegativeButton("No", (dialog, which) -> dialog.dismiss());
        builder.create().show();
    }
}