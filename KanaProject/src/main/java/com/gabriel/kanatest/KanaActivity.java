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
    public static final String KANA_OK_RESULT = "qwer";
    public static final String KANA_NOK_RESULT = "asdf";

    private RadioGroup radioGroup;
    private RadioButton OptionA, OptionB, OptionC, OptionD;
    private TextView textView;
    Random rand = new Random();
    Handler handler = new Handler();
    long delay = 1250; // tempo de delay em millisegundos
    int RandomOption;
    int Escolhido;
    int Resposta;
    int Cont = 0;
    int Erros;
    int Acertos;
    int Coluna = 1; // coluna do hiragana ou katakana
    boolean HabilitaNovoKana = true;
    boolean MutexNovasRespostas = true;

    public static String[][] novoKanaArray(int Escolhido, String[][] KanaArray)
    {
        String[][] NovoKanaArray = new String [KanaArray.length-1][2*(KanaArray.length-1)];
        int i = 0;
        int j = 0;


        for(i = 0; i < KanaArray.length; i++){
            if(i != Escolhido){
                NovoKanaArray[j][0] = KanaArray[i][0];
                NovoKanaArray[j][1] = KanaArray[i][1];
                NovoKanaArray[j][2] = KanaArray[i][2];
                j++;
            }
        }

        return NovoKanaArray;
    }


    String[][] KanaArray = new String [][] {

        { "a", "あ", "ア"},
        { "i", "い", "イ"},
        { "u", "う", "ウ"},
        { "e", "え", "エ"},
        { "o", "お", "オ"},

        { "ka", "か", "カ"},
        { "ki", "き", "キ"},
        { "ku", "く", "ク"},
        { "ke", "け", "ケ"},
        { "ko", "こ", "コ"},

        { "sa", "さ", "サ"},
        { "shi", "し","シ"},
        { "su", "す", "ス"},
        { "se", "せ", "セ"},
        { "so", "そ", "ソ"},

        { "ta", "た", "タ"},
        { "chi", "ち", "チ"},
        { "tsu", "つ", "ツ"},
        { "te", "て", "テ"},
        { "to", "と", "ト"},

        { "na", "な", "ナ"},
        { "ni", "に", "ニ"},
        { "nu", "ぬ", "ヌ"},
        { "ne", "ね", "ネ"},
        { "no", "の", "ノ"},

        { "ha", "は", "ハ"},
        { "hi", "ひ", "ヒ"},
        { "fu", "ふ", "フ"},
        { "he", "へ", "ヘ"},
        { "ho", "ほ", "ホ"},

        { "ma", "ま", "マ"},
        { "mi", "み", "ミ"},
        { "mu", "む", "ム"},
        { "me", "め", "メ"},
        { "mo", "も", "モ"},

        { "ya", "や", "ヤ"},
        { "yu", "ゆ", "ユ"},
        { "yo", "よ", "ヨ"},

        { "ra", "ら", "ラ"},
        { "ri", "り", "リ"},
        { "ru", "る", "ル"},
        { "re", "れ", "レ"},
        { "ro", "ろ", "ロ"},

        { "wa", "わ", "ワ"},
        { "wo", "を", "ヲ"},

        { "n", "ん", "ン"}

    };

    int IndexRespostaUnica(int KanaListaUtilizados[])
    {

      //  Toast.makeText(getApplicationContext(), Integer.toString(Cont), Toast.LENGTH_SHORT).show();
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

    void NovaIntent(int Contador, int limite)
    {
        if(Contador >= limite)
        {
            HabilitaNovoKana = false;
            MutexNovasRespostas = false;

            /* Call next activity */
            Intent intent = new Intent(this, ResultActivity.class);

            intent.putExtra(KANA_OK_RESULT, Integer.toString(Acertos));
            intent.putExtra(KANA_NOK_RESULT, Integer.toString(Erros));

            startActivity(intent);
            /* Finish this activity */
            finish();
        }

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
            Coluna = 1;
        }
        else if(message.equals("Katakana"))
        {
            textView.setText("Katakana");
            Coluna = 2;
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


                // NovaIntent(Cont, 5);
                //NovaIntent(Cont, KanaJaUtilizado.length);

                if(HabilitaNovoKana == true)
                {


                    if(checkedId == R.id.OptionA)
                    {
                        if(OptionA.getText() == KanaArray[Resposta][0])
                        {
                            Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_SHORT).show();
                            Acertos = Acertos+ 1;
                        }
                        else
                        {
                            Erros = Erros + 1;
                        }
                    }
                    else if(checkedId == R.id.OptionB)
                    {
                        if(OptionB.getText() == KanaArray[Resposta][0])
                        {
                            Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_SHORT).show();
                            Acertos = Acertos+ 1;
                        }
                        else
                        {
                            Erros = Erros + 1;
                        }
                    }
                    else if(checkedId == R.id.OptionC)
                    {
                        if(OptionC.getText() == KanaArray[Resposta][0])
                        {
                            Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_SHORT).show();
                            Acertos = Acertos+ 1;
                        }
                        else
                        {
                            Erros = Erros + 1;
                        }
                    }
                    else if(checkedId == R.id.OptionD)
                    {
                        if(OptionD.getText() == KanaArray[Resposta][0])
                        {
                            Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_SHORT).show();
                            Acertos = Acertos+ 1;
                        }
                        else
                        {
                            Erros = Erros + 1;
                        }
                    }



                    // Inserir uma trava estilo mutex
                    NovaIntent(Cont, KanaJaUtilizado.length);

                    HabilitaNovoKana = false;
                    if(MutexNovasRespostas ==  true) {
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                // código a ser executado após o tempo de delay
                                radioGroup.clearCheck(); // // Radio button checked

                                RandomOption = rand.nextInt(4);
                                if (RandomOption == 0) {
                                    //Resposta = rand.nextInt(KanaArray.length);  // Resposta
                                    //Escolhido = Resposta;
                                    Escolhido = IndexRespostaUnica(KanaJaUtilizado);
                                    Resposta = Escolhido;
                                    KanaJaUtilizado[Cont] = Escolhido;
                                    Cont++;
                                    OptionA.setText(KanaArray[Escolhido][0]);

                                    /* Print a random kana question */
                                    finalTextView.setText(KanaArray[Escolhido][Coluna]);

                                    NovoKanaArray = novoKanaArray(Escolhido, KanaArray);
                                    Escolhido = rand.nextInt(NovoKanaArray.length); // Opção1
                                    OptionB.setText(NovoKanaArray[Escolhido][0]);

                                    NovoKanaArray = novoKanaArray(Escolhido, NovoKanaArray);
                                    Escolhido = rand.nextInt(NovoKanaArray.length); // Opção2
                                    OptionC.setText(NovoKanaArray[Escolhido][0]);

                                    NovoKanaArray = novoKanaArray(Escolhido, NovoKanaArray);
                                    Escolhido = rand.nextInt(NovoKanaArray.length); // Opção1
                                    OptionD.setText(NovoKanaArray[Escolhido][0]);
                                } else if (RandomOption == 1) {
                                    //Resposta = rand.nextInt(KanaArray.length);  // Resposta
                                    //Escolhido = Resposta;
                                    Escolhido = IndexRespostaUnica(KanaJaUtilizado);
                                    Resposta = Escolhido;
                                    KanaJaUtilizado[Cont] = Escolhido;
                                    Cont++;
                                    OptionB.setText(KanaArray[Escolhido][0]);

                                    /* Print a random kana question */
                                    finalTextView.setText(KanaArray[Escolhido][Coluna]);

                                    NovoKanaArray = novoKanaArray(Escolhido, KanaArray);
                                    Escolhido = rand.nextInt(NovoKanaArray.length); // Opção1
                                    OptionA.setText(NovoKanaArray[Escolhido][0]);

                                    NovoKanaArray = novoKanaArray(Escolhido, NovoKanaArray);
                                    Escolhido = rand.nextInt(NovoKanaArray.length); // Opção2
                                    OptionC.setText(NovoKanaArray[Escolhido][0]);

                                    NovoKanaArray = novoKanaArray(Escolhido, NovoKanaArray);
                                    Escolhido = rand.nextInt(NovoKanaArray.length); // Opção1
                                    OptionD.setText(NovoKanaArray[Escolhido][0]);
                                } else if (RandomOption == 2) {
                                    //Resposta = rand.nextInt(KanaArray.length);  // Resposta
                                    //Escolhido = Resposta;
                                    Escolhido = IndexRespostaUnica(KanaJaUtilizado);
                                    Resposta = Escolhido;
                                    KanaJaUtilizado[Cont] = Escolhido;
                                    Cont++;
                                    OptionC.setText(KanaArray[Escolhido][0]);

                                    /* Print a random kana question */
                                    finalTextView.setText(KanaArray[Escolhido][Coluna]);

                                    NovoKanaArray = novoKanaArray(Escolhido, KanaArray);
                                    Escolhido = rand.nextInt(NovoKanaArray.length); // Opção1
                                    OptionB.setText(NovoKanaArray[Escolhido][0]);

                                    NovoKanaArray = novoKanaArray(Escolhido, NovoKanaArray);
                                    Escolhido = rand.nextInt(NovoKanaArray.length); // Opção2
                                    OptionA.setText(NovoKanaArray[Escolhido][0]);

                                    NovoKanaArray = novoKanaArray(Escolhido, NovoKanaArray);
                                    Escolhido = rand.nextInt(NovoKanaArray.length); // Opção1
                                    OptionD.setText(NovoKanaArray[Escolhido][0]);
                                } else if (RandomOption == 3) {
                                    //Resposta = rand.nextInt(KanaArray.length);  // Resposta
                                    //Escolhido = Resposta;
                                    Escolhido = IndexRespostaUnica(KanaJaUtilizado);
                                    Resposta = Escolhido;
                                    KanaJaUtilizado[Cont] = Escolhido;
                                    Cont++;
                                    OptionD.setText(KanaArray[Escolhido][0]);

                                    /* Print a random kana question */
                                    finalTextView.setText(KanaArray[Escolhido][Coluna]);

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

            /* Print a random kana question */
            finalTextView.setText(KanaArray[Escolhido][Coluna]);

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

            /* Print a random kana question */
            finalTextView.setText(KanaArray[Escolhido][Coluna]);

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

            /* Print a random kana question */
            finalTextView.setText(KanaArray[Escolhido][Coluna]);

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

            /* Print a random kana question */
            finalTextView.setText(KanaArray[Escolhido][Coluna]);

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
