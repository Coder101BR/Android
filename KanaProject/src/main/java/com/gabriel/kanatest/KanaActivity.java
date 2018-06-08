package com.gabriel.kanatest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class KanaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kana);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textView);


        if(message.equals("Hiragana"))
        {
            textView.setText("Hiragana");
        }
        else if(message.equals("Katakana"))
        {
            textView.setText("Katakana");
        }
        else
        {
            textView.setText("Error");
        }

    }



}
