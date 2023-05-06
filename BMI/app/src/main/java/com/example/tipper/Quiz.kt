package com.example.tipper

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class Quiz : AppCompatActivity() {
    private val SCORE_KEY = "SCORE"
    private val INDEX_KEY = "INDEX"
    private var mTxtQuestion: TextView? = null
    private var btnF: Button? = null
    private var btnT: Button? = null
    private var mQuestionIndex = 0
    private var mQuizQuestion = 0
    private var mProgressBar: ProgressBar? = null
    private var mQuizstatsTextView: TextView? = null
    private var mUserScore = 0
    private val questionCollection = arrayOf(
            QuizModel(R.string.q1, false),
            QuizModel(R.string.q2, true),
            QuizModel(R.string.q3, true),
            QuizModel(R.string.q4, true),
            QuizModel(R.string.q5, true),
            QuizModel(R.string.q6, true))
    val USER_PROGRESS = Math.ceil(100.0 / questionCollection.size).toInt()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quiz)
        mTxtQuestion = findViewById(R.id.txtQuestion)
        val q1 = questionCollection[mQuestionIndex]
        mQuizQuestion = q1.getmQuestion()
        mTxtQuestion?.run { setText(mQuizQuestion) }
        mProgressBar = findViewById(R.id.quizPB)
        mQuizstatsTextView = findViewById(R.id.txtQuizStats)
        btnT = findViewById(R.id.btnTrue)
        btnT?.run {
            setOnClickListener(View.OnClickListener {
                evaluateUserAnswer(true)
                changeQuestionOnButtonClick()
            })
        }
        btnF = findViewById(R.id.btnFalse)
        btnF?.run {
            setOnClickListener(View.OnClickListener {
                evaluateUserAnswer(false)
                changeQuestionOnButtonClick()
            })
        }
    }

    private fun changeQuestionOnButtonClick() {
        mQuestionIndex = (mQuestionIndex + 1) % 6
        if (mQuestionIndex == 0) {
            val quizAlert = AlertDialog.Builder(this)
            quizAlert.setCancelable(false)
            quizAlert.setTitle("Quiz is Finished")
            quizAlert.setMessage("Your Score is $mUserScore")
            quizAlert.setPositiveButton("Finish the Quiz") { dialogInterface, i -> finish() }
            quizAlert.show()
        }
        mQuizQuestion = questionCollection[mQuestionIndex].getmQuestion()
        mTxtQuestion!!.setText(mQuizQuestion)
        mProgressBar!!.incrementProgressBy(USER_PROGRESS)
        mQuizstatsTextView!!.text = "$mUserScore/ 6"
    }

    private fun evaluateUserAnswer(userGuess: Boolean) {
        val currentQuestionAnswer = questionCollection[mQuestionIndex].ismAnswer()
        if (currentQuestionAnswer == userGuess) {
            mUserScore = mUserScore + 1
        } else {
        }
    }
}