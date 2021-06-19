package com.rsschool.quiz.data

data class Question(val textQuestion:String,
                    val firstAnswer:String,
                    val twoAnswer: String,
                    val threeAnswer:String,
                    val fourAnswer:String,
                    val fiveAnswer:String,
                    val trueAnswer:Int,
                    var choiceAnswer:Int?
                    )
