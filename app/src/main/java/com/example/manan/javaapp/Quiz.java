package com.example.manan.javaapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class Quiz extends AppCompatActivity {

    private int page;
    private int score;

    TextView qTV;
    RadioGroup answersRG;
    RadioButton A1Btn;
    RadioButton A2Btn;
    RadioButton A3Btn;
    RadioButton A4Btn;

    Button nextBtn;
    Button checkBtn;

    TextView messageTV;
    TextView scoreTV;

    private String lecture;
    private int lectureNum;

    private JSONObject quiz;
    private JSONObject quizObj;
    private JSONObject questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        page = 0;

        qTV = findViewById(R.id.qTV);
        answersRG = findViewById(R.id.answersRG);
        A1Btn = findViewById(R.id.A1Btn);
        A2Btn = findViewById(R.id.A2Btn);
        A3Btn = findViewById(R.id.A3Btn);
        A4Btn = findViewById(R.id.A4Btn);

        nextBtn = findViewById(R.id.nextBtn);
        checkBtn = findViewById(R.id.checkBtn);

        messageTV = findViewById(R.id.messageTV);
        scoreTV = findViewById(R.id.scoreTV);
        scoreTV.setVisibility(View.GONE);

        lecture = getIntent().getStringExtra("lecture");
        lectureNum = Integer.parseInt(lecture.substring(lecture.length()-1));
        quiz = getQuiz();

        try {
            quizObj = (JSONObject) quiz.get("quiz");
            questions = (JSONObject) quizObj.get(lecture);
            setNextQuestion(questions);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setNextQuestion(questions);
            }
        });

        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(questions);
            }
        });
    }

    // function that reads the quiz from the JSON file into a JSONObject
    public JSONObject getQuiz() {
        String jsonStr = null;
        JSONObject jsonObj =null;
        try {
            InputStream is = getApplicationContext().getAssets().open("Quiz.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            jsonStr = new String(buffer, "UTF-8");

            jsonObj = new JSONObject(jsonStr);

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObj;

    }

    // function to set the next question or to display the results page
    public void setNextQuestion(JSONObject questions) {

        messageTV.setText("");

        if (page != 6) {
            page += 1;

            if (page < 6) {

                answersRG.clearCheck();

                disableNextButton();
                enableCheckButton();

                try {
                    JSONObject questionObj = (JSONObject) questions.get("q" + page);
                    String question = questionObj.getString("q");
                    String a1 = questionObj.getString("a1");
                    String a2 = questionObj.getString("a2");
                    String a3 = questionObj.getString("a3");
                    String a4 = questionObj.getString("a4");

                    qTV.setText(question);
                    A1Btn.setText(a1);
                    A2Btn.setText(a2);
                    A3Btn.setText(a3);
                    A4Btn.setText(a4);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                displayResults();

            }
        }
    }

    // function that displays the results on the results page
    public void displayResults() {
        checkBtn.setVisibility(View.GONE);
        nextBtn.setText("Home");
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Home.class);
                startActivity(intent);
            }
        });
        answersRG.setVisibility(View.GONE);
        qTV.setText("Your final score was:");
        qTV.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        scoreTV.setText(score + "/5");
        if (score > 2) {
            scoreTV.setTextColor(Color.GREEN);
             if (DatabaseHelpers.getCurrentUserWeek() < lectureNum) {
                 System.out.println("++ Updating the user's week to the lecture: " + lectureNum);
                 DatabaseHelpers.updateCurrentUserWeek(lectureNum);
             }
        } else {
            scoreTV.setTextColor(Color.RED);
        }
        scoreTV.setVisibility(View.VISIBLE);
    }

    // function that checks the entered answer with the corrent one and adds(?) to the score
    public void checkAnswer(JSONObject questions) {

        int id = answersRG.getCheckedRadioButtonId();

        if (id == -1) {
            messageTV.setText("Please choose an answer first");
        } else {

            disableCheckButton();
            enableNextButton();

            try {
                JSONObject questionObj = (JSONObject) questions.get("q" + page);
                String answer = questionObj.getString("answer");

                RadioButton selectedBtn = findViewById(id);
                String selectedAnswer = selectedBtn.getText().toString();
                if (answer.equals(selectedAnswer)) {
                    score++;
                    messageTV.setText("Correct! Well done.");
                    messageTV.setTextColor(Color.GREEN);
                } else {
                    messageTV.setText("Sorry, that is the incorrect answer!");
                    messageTV.setTextColor(Color.RED);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    // functions to enable and disable the checkButton and the nextButton

    public void enableCheckButton() {
         checkBtn.setEnabled(true);
         checkBtn.setBackgroundColor(getResources().getColor(R.color.design_default_color_primary));
    }

    public void disableCheckButton() {
        checkBtn.setEnabled(false);
        checkBtn.setBackgroundColor(Color.GRAY);
    }

    public void enableNextButton() {
        nextBtn.setEnabled(true);
        nextBtn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
    }

    public void disableNextButton() {
        nextBtn.setEnabled(false);
        nextBtn.setBackgroundColor(Color.GRAY);
    }

}
