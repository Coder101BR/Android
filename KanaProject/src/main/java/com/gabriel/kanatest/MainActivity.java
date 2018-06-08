package com.gabriel.kanatest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.KanaTest.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user taps the Hiragana button */
    public void startHiragana(View view) {
        Intent intent = new Intent(this, KanaActivity.class);

        String KanaSelect = "Hiragana";
        intent.putExtra(EXTRA_MESSAGE, KanaSelect);
        startActivity(intent);
    }

    /** Called when the user taps the Katakana button */
    public void startKatakana(View view) {
        Intent intent = new Intent(this, KanaActivity.class);

        String KanaSelect = "Katakana";
        intent.putExtra(EXTRA_MESSAGE, KanaSelect);
        startActivity(intent);
    }


}


