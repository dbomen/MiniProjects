package com.example.firstandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
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
}