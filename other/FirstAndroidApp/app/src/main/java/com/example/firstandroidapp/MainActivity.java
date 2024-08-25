package com.example.firstandroidapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // references
    Button mainButton;
    TextView mainText;
    TextView mainName;
    User user;

    DataBaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mainButton = findViewById(R.id.button);
        mainText = findViewById(R.id.mainText);
        mainName = findViewById(R.id.mainName);
        db = new DataBaseHelper(MainActivity.this);

        Intent i = getIntent();
        user = (User) i.getSerializableExtra("user");

        mainText.setText(String.format("%d", user.getVal()));
        mainName.setText(String.format("HELLO %s", user.getName()));
    }

    public void incrementVal(View v) {

        try {

            // v db povecamo COLUMN_VAL za 1
            boolean status = db.incrementUserValueById(user.getId());
            if (!status)  throw new Exception();

            // iz db-ja new user object
            // (lahko bi samo tukaj naredili val++, vendar nam ta extra step zagotovi, da se je val res incrementiral)
            User userFromDb = db.getUserById(user.getId());
            if (userFromDb == null)  throw new Exception();
            user = userFromDb;

            // prikazemo new value
            mainText.setText(String.format("%d", user.getVal()));

        } catch (Exception e) {

            e.printStackTrace();
            HelperFunctions.showToast(MainActivity.this, "ERROR, DID NOT INCREMENT");
        }
    }

    public void LogOutAction(View v) {

        Intent i = new Intent(this, LoginPage.class);
        startActivity(i);
        finish();
    }

    // action = ACTION_DOWN (zacetek actiona)
    //          ACTION_UP   (konec actiona)
    // ko je ACTION_DOWN si shranimo X in Y, potem ko je pa ACTION_UP pogledamo
    // nova X in Y. Glede na X-e in Y-e vemo ali je swipe desno ali levo
    // ter tudi vemo ali za velikost swipa. S tem lahko dolocimo min. velikosti swipe
    // da se steje za swipe to new activity
    private float initialX;
    @Override
    public boolean onTouchEvent(MotionEvent e) {

        // zanimajo nas samo initial ACTION_DOWN in ending ACTION_UP
        if (e.getAction() == MotionEvent.ACTION_MOVE)  return true;

        switch (e.getAction()) {

            case MotionEvent.ACTION_DOWN: // starting action

                this.initialX = e.getX();

                break;

            case MotionEvent.ACTION_UP: // ending action

                // interpretiramo action
                // action se steje ce je X difference >= 200

                float x = e.getX();
                float y = e.getY();

                if (Math.abs(initialX - x) >= 200) { // stejemo swipe action

                    if (initialX > x) { // left swipe action

                        // move activities
                        Intent i = new Intent(MainActivity.this, ApiActivity.class);
                        i.putExtra("user", this.user);
                        MainActivity.this.startActivity(i);
                        MainActivity.this.finish();
                    }
                }

                break;
        }

        return true;
    }
}