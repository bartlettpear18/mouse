package com.bartlettpear18gmail.mouse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Presentation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentation);
    }

    public void next(View view) {
        System.out.println("Next slide");
    }

    public void back (View view) {
        System.out.println("Previous Slide");
    }

}
