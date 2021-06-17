package com.rsschool.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rsschool.quiz.data.Question
import com.rsschool.quiz.data.Questions
import com.rsschool.quiz.ui.Quiz

class MainActivity : AppCompatActivity(),IRouterFragmentAndQuestionWithAnswer {
    val arrayQuestions = Questions.getQuestions()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainerView,Quiz.newInstance(0).apply {  })
            commit()

        }
    }

    override fun openQuestion(numberQuestion: Int) {
        supportFragmentManager.beginTransaction().apply {
            addToBackStack(null)
            add(R.id.fragmentContainerView,Quiz.newInstance(numberQuestion).apply {  })
            commit()

        }
    }

    override fun getQuestion(numberQuestion: Int): Question {
        return arrayQuestions[numberQuestion]
    }

    override fun writeChoseAnswer(numberQuestion: Int, choseAnswer: Int) {
        arrayQuestions[numberQuestion].choseAnswer=choseAnswer
    }

}