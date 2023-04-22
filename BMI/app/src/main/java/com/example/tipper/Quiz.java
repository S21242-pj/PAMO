package com.example.tipper;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


public class Quiz extends AppCompatActivity {
    private final String SCORE_KEY = "SCORE";
    private final String INDEX_KEY = "INDEX";
    private TextView mTxtQuestion;
    private Button btnF;
    private Button btnT;
    private int mQuestionIndex;
    private int mQuizQuestion;
    private ProgressBar mProgressBar;
    private TextView mQuizstatsTextView;
    private int mUserScore;


    private final QuizModel[] questionCollection = new QuizModel[]{
            new QuizModel(R.string.q1, false),
            new QuizModel(R.string.q2, true),
            new QuizModel(R.string.q3, true),
            new QuizModel(R.string.q4, true),
            new QuizModel(R.string.q5, true),
            new QuizModel(R.string.q6, true),



    };
    final int USER_PROGRESS = (int) Math.ceil(100.0 / questionCollection.length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz);
        mTxtQuestion = findViewById(R.id.txtQuestion);
        QuizModel q1 = questionCollection[mQuestionIndex];
        mQuizQuestion = q1.getmQuestion();
        mTxtQuestion.setText(mQuizQuestion);
        mProgressBar = findViewById(R.id.quizPB);
        mQuizstatsTextView = findViewById(R.id.txtQuizStats);


        btnT = findViewById(R.id.btnTrue);
        btnT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                evaluateUserAnswer(true);
                changeQuestionOnButtonClick();

            }
        });

        btnF = findViewById(R.id.btnFalse);
        btnF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                evaluateUserAnswer(false);
                changeQuestionOnButtonClick();

            }
        });

    }

    private void changeQuestionOnButtonClick() {
        mQuestionIndex = (mQuestionIndex + 1) % 6;
        if (mQuestionIndex == 0) {
            AlertDialog.Builder quizAlert = new AlertDialog.Builder(this);
            quizAlert.setCancelable(false);
            quizAlert.setTitle("Quiz is Finished");
            quizAlert.setMessage("Your Score is " + mUserScore);
            quizAlert.setPositiveButton("Finish the Quiz", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            quizAlert.show();
        }
        mQuizQuestion = questionCollection[mQuestionIndex].getmQuestion();
        mTxtQuestion.setText(mQuizQuestion);
        mProgressBar.incrementProgressBy(USER_PROGRESS);
        mQuizstatsTextView.setText(mUserScore + "" + "/ 6");
    }

    private void evaluateUserAnswer(boolean userGuess) {
        boolean currentQuestionAnswer = questionCollection[mQuestionIndex].ismAnswer();
        if (currentQuestionAnswer == userGuess) {

            mUserScore = mUserScore + 1;
        } else {
        }
    }

}