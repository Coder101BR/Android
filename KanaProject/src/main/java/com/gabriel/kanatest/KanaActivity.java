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
    long delay = 1250; // tempo de delay em millisegundos
    int RandomOption;
    int Escolhido;
    int Resposta;
    int Cont =0;
    boolean HabilitaNovoKana = true;

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


    String[][] KanaArray = new String [][] {
            { "a", "あ"},
            { "i", "い"},
            { "u", "う"},
            { "e", "え"},
            /*         { "o", "お"},

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
                  */
            { "n", "ん"}

    };

    int IndexRespostaUnica(int KanaListaUtilizados[])
    {

        Toast.makeText(getApplicationContext(), Integer.toString(Cont), Toast.LENGTH_SHORT).show();
        int NovaResposta = rand.nextInt(KanaArray.length);
        int i;
        for(i = 0; i < KanaListaUtilizados.length; i++)
        {
            if(NovaResposta == KanaListaUtilizados[i])
            {
                NovaResposta = rand.nextInt(KanaArray.length);
                i = 0;
            }
        }

        return NovaResposta;
    }

    int[] KanaJaUtilizado = new int[KanaArray.length];



    String[][] NovoKanaArray = novoKanaArray(Escolhido, KanaArray);

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

        OptionA = (RadioButton) findViewById(R.id.OptionA);
        OptionB = (RadioButton) findViewById(R.id.OptionB);
        OptionC = (RadioButton) findViewById(R.id.OptionC);
        OptionD = (RadioButton) findViewById(R.id.OptionD);
        textView = (TextView) findViewById(R.id.text);



        final TextView finalTextView = textView;
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {


            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //radioGroup.clearCheck();
                // find which radio button is selected



                if(HabilitaNovoKana == true)
                {

                    if(checkedId == R.id.OptionA)
                    {
                        if(OptionA.getText() == KanaArray[Resposta][0])
                        {
                            Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else if(checkedId == R.id.OptionB)
                    {
                        if(OptionB.getText() == KanaArray[Resposta][0])
                        {
                            Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else if(checkedId == R.id.OptionC)
                    {
                        if(OptionC.getText() == KanaArray[Resposta][0])
                        {
                            Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else if(checkedId == R.id.OptionD)
                    {
                        if(OptionD.getText() == KanaArray[Resposta][0])
                        {
                            Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_SHORT).show();
                        }
                    }



                    // Inserir uma trava estilo mutex
                    HabilitaNovoKana = false;
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            // código a ser executado após o tempo de delay
                            radioGroup.clearCheck(); // // Radio button checked

                            RandomOption = rand.nextInt(4);
                            if(RandomOption == 0)
                            {
                                //Resposta = rand.nextInt(KanaArray.length);  // Resposta
                                //Escolhido = Resposta;
                                Escolhido = IndexRespostaUnica(KanaJaUtilizado);
                                KanaJaUtilizado[Cont] = Escolhido;
                                Cont++;
                                OptionA.setText(KanaArray[Escolhido][0]);

                                /* Print a random hiragana question */
                                finalTextView.setText(KanaArray[Escolhido][1]);

                                NovoKanaArray = novoKanaArray(Escolhido, KanaArray);
                                Escolhido = rand.nextInt(NovoKanaArray.length); // Opção1
                                OptionB.setText(NovoKanaArray[Escolhido][0]);

                                NovoKanaArray = novoKanaArray(Escolhido, NovoKanaArray);
                                Escolhido = rand.nextInt(NovoKanaArray.length); // Opção2
                                OptionC.setText(NovoKanaArray[Escolhido][0]);

                                NovoKanaArray = novoKanaArray(Escolhido, NovoKanaArray);
                                Escolhido = rand.nextInt(NovoKanaArray.length); // Opção1
                                OptionD.setText(NovoKanaArray[Escolhido][0]);
                            }
                            else if(RandomOption == 1)
                            {
                                //Resposta = rand.nextInt(KanaArray.length);  // Resposta
                                //Escolhido = Resposta;
                                Escolhido = IndexRespostaUnica(KanaJaUtilizado);
                                KanaJaUtilizado[Cont] = Escolhido;
                                Cont++;
                                OptionB.setText(KanaArray[Escolhido][0]);

                                /* Print a random hiragana question */
                                finalTextView.setText(KanaArray[Escolhido][1]);

                                NovoKanaArray = novoKanaArray(Escolhido, KanaArray);
                                Escolhido = rand.nextInt(NovoKanaArray.length); // Opção1
                                OptionA.setText(NovoKanaArray[Escolhido][0]);

                                NovoKanaArray = novoKanaArray(Escolhido, NovoKanaArray);
                                Escolhido = rand.nextInt(NovoKanaArray.length); // Opção2
                                OptionC.setText(NovoKanaArray[Escolhido][0]);

                                NovoKanaArray = novoKanaArray(Escolhido, NovoKanaArray);
                                Escolhido = rand.nextInt(NovoKanaArray.length); // Opção1
                                OptionD.setText(NovoKanaArray[Escolhido][0]);
                            }
                            else if(RandomOption == 2)
                            {
                                //Resposta = rand.nextInt(KanaArray.length);  // Resposta
                                //Escolhido = Resposta;
                                Escolhido = IndexRespostaUnica(KanaJaUtilizado);
                                KanaJaUtilizado[Cont] = Escolhido;
                                Cont++;
                                OptionC.setText(KanaArray[Escolhido][0]);

                                /* Print a random hiragana question */
                                finalTextView.setText(KanaArray[Escolhido][1]);

                                NovoKanaArray = novoKanaArray(Escolhido, KanaArray);
                                Escolhido = rand.nextInt(NovoKanaArray.length); // Opção1
                                OptionB.setText(NovoKanaArray[Escolhido][0]);

                                NovoKanaArray = novoKanaArray(Escolhido, NovoKanaArray);
                                Escolhido = rand.nextInt(NovoKanaArray.length); // Opção2
                                OptionA.setText(NovoKanaArray[Escolhido][0]);

                                NovoKanaArray = novoKanaArray(Escolhido, NovoKanaArray);
                                Escolhido = rand.nextInt(NovoKanaArray.length); // Opção1
                                OptionD.setText(NovoKanaArray[Escolhido][0]);
                            }
                            else if(RandomOption == 3)
                            {
                                //Resposta = rand.nextInt(KanaArray.length);  // Resposta
                                //Escolhido = Resposta;
                                Escolhido = IndexRespostaUnica(KanaJaUtilizado);
                                KanaJaUtilizado[Cont] = Escolhido;
                                Cont++;
                                OptionD.setText(KanaArray[Escolhido][0]);

                                /* Print a random hiragana question */
                                finalTextView.setText(KanaArray[Escolhido][1]);

                                NovoKanaArray = novoKanaArray(Escolhido, KanaArray);
                                Escolhido = rand.nextInt(NovoKanaArray.length); // Opção1
                                OptionB.setText(NovoKanaArray[Escolhido][0]);

                                NovoKanaArray = novoKanaArray(Escolhido, NovoKanaArray);
                                Escolhido = rand.nextInt(NovoKanaArray.length); // Opção2
                                OptionC.setText(NovoKanaArray[Escolhido][0]);

                                NovoKanaArray = novoKanaArray(Escolhido, NovoKanaArray);
                                Escolhido = rand.nextInt(NovoKanaArray.length); // Opção1
                                OptionA.setText(NovoKanaArray[Escolhido][0]);
                            }

                            HabilitaNovoKana = true;
                        }
                    }, delay);
                }

                if(Cont >= KanaArray.length)
                {
                    Toast.makeText(getApplicationContext(), "BUGADO", Toast.LENGTH_SHORT).show();
                }


            }

        });


        /* Initial random Kana */
        RandomOption = rand.nextInt(4);
        if(RandomOption == 0)
        {
            Resposta = rand.nextInt(KanaArray.length);  // Resposta
            Escolhido = Resposta;
            OptionA.setText(KanaArray[Escolhido][0]);

            /* Print a random hiragana question */
            finalTextView.setText(KanaArray[Escolhido][1]);

            NovoKanaArray = novoKanaArray(Escolhido, KanaArray);
            Escolhido = rand.nextInt(NovoKanaArray.length); // Opção1
            OptionB.setText(NovoKanaArray[Escolhido][0]);

            NovoKanaArray = novoKanaArray(Escolhido, NovoKanaArray);
            Escolhido = rand.nextInt(NovoKanaArray.length); // Opção2
            OptionC.setText(NovoKanaArray[Escolhido][0]);

            NovoKanaArray = novoKanaArray(Escolhido, NovoKanaArray);
            Escolhido = rand.nextInt(NovoKanaArray.length); // Opção1
            OptionD.setText(NovoKanaArray[Escolhido][0]);
        }
        else if(RandomOption == 1)
        {
            Resposta = rand.nextInt(KanaArray.length);  // Resposta
            Escolhido = Resposta;
            OptionB.setText(KanaArray[Escolhido][0]);

            /* Print a random hiragana question */
            finalTextView.setText(KanaArray[Escolhido][1]);

            NovoKanaArray = novoKanaArray(Escolhido, KanaArray);
            Escolhido = rand.nextInt(NovoKanaArray.length); // Opção1
            OptionA.setText(NovoKanaArray[Escolhido][0]);

            NovoKanaArray = novoKanaArray(Escolhido, NovoKanaArray);
            Escolhido = rand.nextInt(NovoKanaArray.length); // Opção2
            OptionC.setText(NovoKanaArray[Escolhido][0]);

            NovoKanaArray = novoKanaArray(Escolhido, NovoKanaArray);
            Escolhido = rand.nextInt(NovoKanaArray.length); // Opção1
            OptionD.setText(NovoKanaArray[Escolhido][0]);
        }
        else if(RandomOption == 2)
        {
            Resposta = rand.nextInt(KanaArray.length);  // Resposta
            Escolhido = Resposta;
            OptionC.setText(KanaArray[Escolhido][0]);

            /* Print a random hiragana question */
            finalTextView.setText(KanaArray[Escolhido][1]);

            NovoKanaArray = novoKanaArray(Escolhido, KanaArray);
            Escolhido = rand.nextInt(NovoKanaArray.length); // Opção1
            OptionB.setText(NovoKanaArray[Escolhido][0]);

            NovoKanaArray = novoKanaArray(Escolhido, NovoKanaArray);
            Escolhido = rand.nextInt(NovoKanaArray.length); // Opção2
            OptionA.setText(NovoKanaArray[Escolhido][0]);

            NovoKanaArray = novoKanaArray(Escolhido, NovoKanaArray);
            Escolhido = rand.nextInt(NovoKanaArray.length); // Opção1
            OptionD.setText(NovoKanaArray[Escolhido][0]);
        }
        else if(RandomOption == 3)
        {
            Resposta = rand.nextInt(KanaArray.length);  // Resposta
            Escolhido = Resposta;
            OptionD.setText(KanaArray[Escolhido][0]);

            /* Print a random hiragana question */
            finalTextView.setText(KanaArray[Escolhido][1]);

            NovoKanaArray = novoKanaArray(Escolhido, KanaArray);
            Escolhido = rand.nextInt(NovoKanaArray.length); // Opção1
            OptionB.setText(NovoKanaArray[Escolhido][0]);

            NovoKanaArray = novoKanaArray(Escolhido, NovoKanaArray);
            Escolhido = rand.nextInt(NovoKanaArray.length); // Opção2
            OptionC.setText(NovoKanaArray[Escolhido][0]);

            NovoKanaArray = novoKanaArray(Escolhido, NovoKanaArray);
            Escolhido = rand.nextInt(NovoKanaArray.length); // Opção1
            OptionA.setText(NovoKanaArray[Escolhido][0]);
        }

        /* Initial value */
        for(int i = 0; i < KanaJaUtilizado.length; i++)
        {
            KanaJaUtilizado[i] = Escolhido;
        }
        Cont++;



    }









}
