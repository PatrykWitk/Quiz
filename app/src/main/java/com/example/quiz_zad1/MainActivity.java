package com.example.quiz_zad1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private Button promptButton;
    private TextView questionTextView;
    private int currentIndex = 0;
    public boolean answerWasShown;
    public static final String KEY_EXTRA_ANSWER = "com.example.quiz_zad1.correctAnswer";
    private static final String KEY_CURRENT_INDEX = "currentIndex";
    public static final int REQUEST_CODE_PROMPT = 0;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK){ return;}
        if (requestCode == REQUEST_CODE_PROMPT){
            if (data == null){ return; }
            answerWasShown = data.getBooleanExtra(PromptActivity.KEY_EXTRA_ANSWER, false);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("MainActivity","Wywołano metodę onSaveInstanceState");
        outState.putInt(KEY_CURRENT_INDEX, currentIndex);
    }

    private Question[] questions = new Question[]{
            new Question(R.string.q_1, false),
            new Question(R.string.q_2, true),
            new Question(R.string.q_3, true),
            new Question(R.string.q_4, false),
            new Question(R.string.q_5, false),
    };

    private void checkAnswerCorrectness(boolean userAnswer){
        boolean correctAnswer = questions[currentIndex].isTrueAnswer();
        int resultMessageId;
        if (answerWasShown){
            resultMessageId = R.string.answer_was_shown;
        } else {
            if(userAnswer == correctAnswer){
                resultMessageId = R.string.correct_answer;
            } else{
                resultMessageId = R.string.incorrect_answer;
            }
        }
        Toast.makeText(this,resultMessageId,Toast.LENGTH_SHORT).show();
    }



        private void setNextQuestion(){
        questionTextView.setText(questions[currentIndex].getQuestionId());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("MainActivity","Wywołano metodę onCreate");

        setContentView(R.layout.activity_main);

         if (savedInstanceState != null) {
             currentIndex = savedInstanceState.getInt(KEY_CURRENT_INDEX);
         }

        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        nextButton = findViewById(R.id.next_button);
        promptButton = findViewById(R.id.answer_btn_main_activity);
        questionTextView = findViewById(R.id.question_text_view);

        trueButton.setOnClickListener(v -> checkAnswerCorrectness(true));
        falseButton.setOnClickListener(v -> checkAnswerCorrectness(false));

        promptButton.setOnClickListener((v) -> {
            Intent intent = new Intent(MainActivity.this, PromptActivity.class);
            boolean correctAnswer = questions[currentIndex].isTrueAnswer();
            intent.putExtra(KEY_EXTRA_ANSWER, correctAnswer);
            startActivityForResult(intent, REQUEST_CODE_PROMPT);
        });

        nextButton.setOnClickListener((v) -> {
            currentIndex = (currentIndex + 1)%questions.length;
            answerWasShown = false;
            setNextQuestion();
        });
        setNextQuestion();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MainActivity","Wywołano metodę onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MainActivity","Wywołano metodę onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MainActivity","Wywołano metodę onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MainActivity","Wywołano metodę onStop");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MainActivity","Wywołano metodę onDestroy");
    }

}