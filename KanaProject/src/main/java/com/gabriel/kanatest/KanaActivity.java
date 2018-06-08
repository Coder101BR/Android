package com.gabriel.kanatest;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

public class KanaActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton OptionA, OptionB, OptionC, OptionD;
    private TextView textView;
    Random rand = new Random();
    Handler handler = new Handler();
    long delay = 5000; // tempo de delay em millisegundos


    public static String[][] novoKanaArray(int Escolhido, String[][] KanaArray)
    {
        String[][] NovoKanaArray = new String [KanaArray.length-1][KanaArray.length-1];
        int i = 0;
        int j = 0;

        for(i = 0; i < KanaArray.length; i++){
            if(i != Escolhido){
                NovoKanaArray[j][0] = KanaArray[i][0];
                NovoKanaArray[j][1] = KanaArray[i][1];
                j++;
            }
        }

        return NovoKanaArray;
    }





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

        radioGroup = (RadioGroup) findViewById(R.id.myRadioGroup);
        radioGroup.clearCheck();  // clean any radio button checked
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //radioGroup.clearCheck();
                // find which radio button is selected
                if(checkedId == R.id.OptionA)
                {
                      Toast.makeText(getApplicationContext(), "OptionA",Toast.LENGTH_SHORT).show();

                }
                else if(checkedId == R.id.OptionB)
                {
                     Toast.makeText(getApplicationContext(), "OptionB", Toast.LENGTH_SHORT).show();
                }
                else if(checkedId == R.id.OptionC)
                {
                     Toast.makeText(getApplicationContext(), "OptionC", Toast.LENGTH_SHORT).show();
                }
                else if(checkedId == R.id.OptionD)
                {
                     Toast.makeText(getApplicationContext(), "OptionD", Toast.LENGTH_SHORT).show();
                }

                handler.postDelayed(new Runnable() {
                    public void run() {
                        // código a ser executado após o tempo de delay
                        radioGroup.clearCheck(); // // Radio button checked

                    }
                }, delay);


            }

        });


        OptionA = (RadioButton) findViewById(R.id.OptionA);
        OptionB = (RadioButton) findViewById(R.id.OptionB);
        OptionC = (RadioButton) findViewById(R.id.OptionC);
        OptionD = (RadioButton) findViewById(R.id.OptionD);
        textView = (TextView) findViewById(R.id.text);

        textView.setText("か");
    }




    String[][] KanaArray = new String [][] {
            { "a", "あ"},
            { "i", "い"},
            { "u", "う"},
            { "e", "え"},
            { "o", "お"},

            { "ka", "か"},
            { "ki", "き"},
            { "ku", "く"},
            { "ke", "け"},
            { "ko", "こ"},

            { "sa", "さ"},
            { "shi", "し"},
            { "su", "す"},
            { "se", "せ"},
            { "so", "そ"},

            { "ta", "た"},
            { "chi", "ち"},
            { "tsu", "つ"},
            { "te", "て"},
            { "to", "と"},

            { "na", "な"},
            { "ni", "に"},
            { "nu", "ぬ"},
            { "ne", "ね"},
            { "no", "の"},

            { "ha", "は"},
            { "hi", "ひ"},
            { "fu", "ふ"},
            { "he", "へ"},
            { "ho", "ほ"},

            { "ma", "ま"},
            { "mi", "み"},
            { "mu", "む"},
            { "me", "め"},
            { "mo", "も"},

            { "ya", "や"},
            { "yu", "ゆ"},
            { "yo", "よ"},

            { "ra", "ら"},
            { "ri", "り"},
            { "ru", "る"},
            { "re", "れ"},
            { "ro", "ろ"},

            { "wa", "わ"},
            { "wo", "を"},

            { "n", "ん"}

    };





}
