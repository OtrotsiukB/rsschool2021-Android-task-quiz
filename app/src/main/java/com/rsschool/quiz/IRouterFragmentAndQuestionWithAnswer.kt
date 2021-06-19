package com.rsschool.quiz

import com.rsschool.quiz.data.Question

interface IRouterFragmentAndQuestionWithAnswer {
    fun openQuestion(numberQuestion:Int)
    fun getQuestion(numberQuestion:Int):Question
    fun writeChoiceAnswer(numberQuestion:Int, choiseAnswer:Int)
    fun getCountQuestions():Int

}