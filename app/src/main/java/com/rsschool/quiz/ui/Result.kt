package com.rsschool.quiz.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rsschool.quiz.IWorkWithResult
import com.rsschool.quiz.R
import com.rsschool.quiz.databinding.FragmentResultBinding


class Result : Fragment() {

    private var resultText: String? = null
    private var listener: IWorkWithResult?=null
    private var _binding: FragmentResultBinding?=null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            resultText = it.getString("ResultText")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_result, container, false)
        _binding= FragmentResultBinding.inflate(inflater,container,false)
        return binding.root
    }

    @SuppressLint("IntentReset")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvYourResult.text=resultText
        binding.iShare.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SEND)
            emailIntent.data = Uri.parse("mailto:")
            emailIntent.type = "text/plain"
            emailIntent.putExtra(Intent.EXTRA_TEXT   , listener?.textForShare())
            startActivity(Intent.createChooser(emailIntent, "Send mail..."))
        }
        binding.iBackOnStart.setOnClickListener {
            listener?.clearChoiceAnswers()
            requireActivity().supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainerView,Quiz.newInstance(0).apply {  })
                commit()
            }
        }
        binding.iExit.setOnClickListener {
            requireActivity().finish()
        }
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is IWorkWithResult){
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
        fun newInstance(ResultText: String) =
            Result().apply {
                arguments = Bundle().apply {
                    putString("ResultText", ResultText)
                }
            }
    }
}