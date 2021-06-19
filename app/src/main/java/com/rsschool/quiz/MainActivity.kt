package com.rsschool.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.rsschool.quiz.data.Question
import com.rsschool.quiz.data.Questions
import com.rsschool.quiz.ui.Quiz
import com.rsschool.quiz.ui.Result

class MainActivity : AppCompatActivity(),IRouterFragmentAndQuestionWithAnswer,IWorkWithResult {
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
        if(numberQuestion<arrayQuestions.size) {
            supportFragmentManager.beginTransaction().apply {
                addToBackStack(null)
                add(R.id.fragmentContainerView, Quiz.newInstance(numberQuestion).apply { })
                commit()

            }
        }else{
            //открыть результа результата
            supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainerView,Result.newInstance(showYourResultText()).apply {  })
                commit()

            }
        }
    }

    fun showYourResultText():String{
        var result = 0
        for(x in arrayQuestions){
            if (x.choiceAnswer==x.trueAnswer){
                result++
            }
        }
        return "Ваш результат: "+result+" из "+arrayQuestions.size
    }

    override fun getQuestion(numberQuestion: Int): Question {
        return arrayQuestions[numberQuestion]
    }

    override fun writeChoseAnswer(numberQuestion: Int, choiseAnswer: Int) {
        arrayQuestions[numberQuestion].choiceAnswer=choiseAnswer
    }

    override fun getCountQuestions(): Int {
       return arrayQuestions.count()
    }

    override fun clearChoiceAnswers() {
        for (x in arrayQuestions){
            x.choiceAnswer=null
        }
    }

    override fun textForShare(): String {
        var outText = showYourResultText()
        for (x in arrayQuestions){
            outText+="\n\n"+x.textQuestion+"\n Ваш ответ:\n"+answerInArray(x,x.choiceAnswer?:0)
        }
        return outText
    }
    private fun answerInArray(question: Question,numberAnsver:Int):String{
        when(numberAnsver){
            1 -> return question.firstAnswer
            2 -> return question.twoAnswer
            3 -> return question.threeAnswer
            4 -> return question.fourAnswer
            5 -> return question.fiveAnswer
            else -> {}
        }
        return "error"
    }

}