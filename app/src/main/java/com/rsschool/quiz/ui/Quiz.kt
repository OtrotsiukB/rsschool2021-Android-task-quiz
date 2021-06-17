package com.rsschool.quiz.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rsschool.quiz.IRouterFragmentAndQuestionWithAnswer
import com.rsschool.quiz.data.Question
import com.rsschool.quiz.databinding.FragmentQuizBinding


class Quiz : Fragment() {
    // TODO: Rename and change types of parameters
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


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
      //  return inflater.inflate(R.layout.fragment_quiz, container, false)
        _binding= FragmentQuizBinding.inflate(inflater,container,false)
        return binding.root
    }

    fun Init(){
        binding.question.text=question?.textQuestion
        binding.optionOne.text=question?.firstAnswer
        binding.optionTwo.text=question?.twoAnswer
        binding.optionThree.text=question?.threeAnswer
        binding.optionFour.text=question?.fourAnswer
        binding.optionFive.text=question?.fiveAnswer
        if(numberQuestion==0){
            binding.previousButton.isEnabled=false
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Init()

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