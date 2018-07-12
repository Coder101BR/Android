package com.gabriel.kanatest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message_OK = intent.getStringExtra(KanaActivity.KANA_OK_RESULT);
        String message_NOK = intent.getStringExtra(KanaActivity.KANA_NOK_RESULT);
        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textView);

        String text_OK = message_OK;
        String text_NOK = message_NOK;

        textView.setText(text_OK+"\n"+text_NOK);

    }
}
