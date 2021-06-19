package com.rsschool.quiz.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rsschool.quiz.IRouterFragmentAndQuestionWithAnswer
import com.rsschool.quiz.R
import com.rsschool.quiz.data.Question
import com.rsschool.quiz.databinding.FragmentQuizBinding
import kotlin.random.Random


class Quiz : Fragment() {

    private var numberQuestion: Int? = null
    private var listener: IRouterFragmentAndQuestionWithAnswer?=null
    private var question:Question?=null
    private var _binding:FragmentQuizBinding?=null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            numberQuestion = it.getInt("numberQuestionGet")
            if (numberQuestion!=null){
            question=listener?.getQuestion(numberQuestion!!)
            }
        }
    }

    fun changeTheme(number:Int){
        when(number){
            0       -> {context?.theme?.applyStyle(R.style.Theme_Quiz_First, true);}
            1       -> {context?.theme?.applyStyle(R.style.Theme_Quiz_Second, true);}
            2       -> {context?.theme?.applyStyle(R.style.Theme_Quiz_Three, true);}
            3       -> {context?.theme?.applyStyle(R.style.Theme_Quiz_Four, true);}
            4       -> {context?.theme?.applyStyle(R.style.Theme_Quiz_Five, true);}
            else    -> {changeTheme(Random.nextInt(0, 4))}
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        numberQuestion?.let { changeTheme(it) }
        // Inflate the layout for this fragment
      //  return inflater.inflate(R.layout.fragment_quiz, container, false)
        _binding= FragmentQuizBinding.inflate(inflater,container,false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    fun init(){
        binding.question.text=question?.textQuestion
        binding.optionOne.text=question?.firstAnswer
        binding.optionTwo.text=question?.twoAnswer
        binding.optionThree.text=question?.threeAnswer
        binding.optionFour.text=question?.fourAnswer
        binding.optionFive.text=question?.fiveAnswer
        if(numberQuestion==0){
            binding.previousButton.isEnabled=false
            binding.toolbar.navigationIcon=null //.isVisible= View.GONE
        }
        if(numberQuestion== listener?.getCountQuestions()?.minus(1)){
            binding.nextButton.text="Submit"
        }
        binding.toolbar.title="Question "+numberQuestion?.plus(1)
    }

    fun radioButonCheck(){
        binding.optionOne.setOnClickListener {
            binding.nextButton.isEnabled=true
            numberQuestion?.let { it1 -> listener?.writeChoiceAnswer(it1,1) }
        }
        binding.optionTwo.setOnClickListener {
            binding.nextButton.isEnabled=true
            numberQuestion?.let { it1 -> listener?.writeChoiceAnswer(it1,2) }
        }
        binding.optionThree.setOnClickListener {
            binding.nextButton.isEnabled=true
            numberQuestion?.let { it1 -> listener?.writeChoiceAnswer(it1,3) }
        }
        binding.optionFour.setOnClickListener {
            binding.nextButton.isEnabled=true
            numberQuestion?.let { it1 -> listener?.writeChoiceAnswer(it1,4) }
        }
        binding.optionFive.setOnClickListener {
            binding.nextButton.isEnabled=true
            numberQuestion?.let { it1 -> listener?.writeChoiceAnswer(it1,5) }
        }
    }

    fun checkChouse(){
        if (question?.choiceAnswer!=null)
        {
            binding.nextButton.isEnabled=true
            when(question?.choiceAnswer){
                1 ->{binding.optionOne.isChecked=true}
                2 ->{binding.optionTwo.isChecked=true}
                3 ->{binding.optionThree.isChecked=true}
                4 ->{binding.optionFour.isChecked=true}
                5 ->{binding.optionFive.isChecked=true}
                else ->{}
            }
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        radioButonCheck()
        checkChouse()
        binding.nextButton.setOnClickListener {
            if (numberQuestion!=null){
                listener?.openQuestion(numberQuestion!! +1)
            }
        }
        binding.previousButton.setOnClickListener {
            if (numberQuestion!=null){
                listener?.openQuestion(numberQuestion!! -1)
            }
        }
        binding.toolbar.setNavigationOnClickListener {
            if (numberQuestion!=null){
                if (numberQuestion!!!=0) {
                    listener?.openQuestion(numberQuestion!! - 1)
                }
            }
        }


    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is IRouterFragmentAndQuestionWithAnswer){
            listener=context
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding=null
        listener=null
    }
    companion object {

        @JvmStatic
        fun newInstance(numberQuestion: Int) =
            Quiz().apply {
                arguments = Bundle().apply {
                    putInt("numberQuestionGet", numberQuestion)

                }
            }
    }
}