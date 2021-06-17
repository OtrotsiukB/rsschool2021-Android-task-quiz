package com.rsschool.quiz

import com.rsschool.quiz.data.Question

interface IRouterFragmentAndQuestionWithAnswer {
    fun openQuestion(numberQuestion:Int)
    fun getQuestion(numberQuestion:Int):Question
    fun writeChoseAnswer(numberQuestion:Int,choseAnswer:Int)


}