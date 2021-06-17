package com.rsschool.quiz.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Question(val textQuestion:String,
                    val firstAnswer:String,
                    val twoAnswer: String,
                    val threeAnswer:String,
                    val fourAnswer:String,
                    val fiveAnswer:String,
                    val trueAnswer:Int,
                    var choseAnswer:Int?
                    ): Parcelable
