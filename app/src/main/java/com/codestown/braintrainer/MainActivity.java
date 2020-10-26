package com.codestown.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button gobutton, playAgainButton;
    TextView sumTextview, scoreTextView, timerTextview, resultTextView;
    Button button0, button1, button2, button3;
    ConstraintLayout gameLayout;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int score = 0;
    int numberOfQuestionsAttempted = 0;
    int locationOfCorrectAnswer;
    CountDownTimer countDownTimer;

    public void start(View view) {
        gobutton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.playAgainButton));
    }

    public void playAgain(View view) {
        playAgainButton.setVisibility(View.INVISIBLE);
        score = 0;
        numberOfQuestionsAttempted = 0;
        timerTextview.setText("30s");
        resultTextView.setText("");
        scoreTextView.setText(Integer.toString(score) + "/ " + Integer.toString(numberOfQuestionsAttempted));
        newQuestion();

        countDownTimer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextview.setText(String.valueOf(millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("Done!");
                playAgainButton.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    public void newQuestion() {
        Random random = new Random();
        int first = random.nextInt(21);
        int second = random.nextInt(21);
        sumTextview.setText(first + " + " + second);
        locationOfCorrectAnswer = random.nextInt(4);
        answers.clear();
        for (int i = 0; i < 4; i++) {
            if (i == locationOfCorrectAnswer) {
                answers.add(first + second);
            } else {

                int wrongAnswer = random.nextInt(41);

                while (wrongAnswer == first + second) {
                    wrongAnswer = random.nextInt(41);
                }

                answers.add(wrongAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }

    public void chooseAnswer(View view) {
        if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())) {
            Log.i("Correct", "Correct Answer");
            resultTextView.setText("Hey! You Got It.");
            score++;
        } else {
            Log.i("Wrong", "Wrong Answer");
            resultTextView.setText("Sorry! Wrong Answer.");
        }
        numberOfQuestionsAttempted++;
        scoreTextView.setText(Integer.toString(score) + "/ " + Integer.toString(numberOfQuestionsAttempted));
        newQuestion();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initListener();
        gobutton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);

    }

    private void initListener() {
        gobutton = findViewById(R.id.goButton);
        sumTextview = findViewById(R.id.sumTextView);
        timerTextview = findViewById(R.id.timerTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        resultTextView = findViewById(R.id.resultTextView);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        playAgainButton = findViewById(R.id.playAgainButton);
        gameLayout = findViewById(R.id.gameLayout);

    }
}