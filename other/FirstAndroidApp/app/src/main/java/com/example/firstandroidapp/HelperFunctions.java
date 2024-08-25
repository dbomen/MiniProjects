package com.example.firstandroidapp;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HelperFunctions {

    public static void showToast(AppCompatActivity activity, String text) {

        Toast.makeText(activity, text, Toast.LENGTH_SHORT).show();
    }
}
