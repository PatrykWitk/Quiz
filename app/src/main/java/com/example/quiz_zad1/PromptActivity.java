package com.example.quiz_zad1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.CollationElementIterator;

public class PromptActivity extends AppCompatActivity {
    public static final String KEY_EXTRA_ANSWER = "com.example.quiz_zad1.answerShown";
    private boolean correctAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prompt);

        Button showCorrectAnswerButton;
        TextView answerTextView;

        showCorrectAnswerButton = findViewById(R.id.answer_btn);
        answerTextView = findViewById(R.id.answer_text_view);

        correctAnswer = getIntent().getBooleanExtra(MainActivity.KEY_EXTRA_ANSWER, true);
        showCorrectAnswerButton.setOnClickListener(view -> {
            int answer = correctAnswer ? R.string.button_true : R.string.button_false;
            answerTextView.setText(answer);
            setAnswerShownResult(true);
        });
    }

    public void setAnswerShownResult(boolean answerWasShown){
        Intent resultIntent = new Intent();
        resultIntent.putExtra(KEY_EXTRA_ANSWER, answerWasShown);
        setResult(RESULT_OK, resultIntent);
    }

}