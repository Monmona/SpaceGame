package com.ayman.android.spacebattleship.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;

import com.ayman.android.spacebattleship.managers.GameMusicManager;

public class GameActivity extends AppCompatActivity {

    private GameView gameView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_game);
        //Getting display object
//        Display display = getWindowManager().getDefaultDisplay();
//
//        //Getting the screen resolution into point object
//        Point size = new Point();
//        display.getSize(size);
        gameView=new GameView(this);
        //Initializing game view object

        //adding it to contentview
        setContentView(gameView);
    }
    //pausing the game when activity is paused
    @Override
    protected void onPause() {
        super.onPause();
        gameView.pause();
    }

    //running the game when activity is resumed
    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
//                .setNegativeButton()
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                            GameMusicManager.stopMusic();
                            Intent startMain = new Intent(Intent.ACTION_MAIN);
                            startMain.addCategory(Intent.CATEGORY_HOME);
                            startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(startMain);
                            finish();
                    }
                }) ;
        AlertDialog alert = builder.create();
        alert.show();

    }
}
