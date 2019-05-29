package com.example.myadventure

import android.content.res.Resources

var questionsSpace = listOf<String>("6", "12", "2", "42")
var anwsersSpace = mutableListOf<String>("6", "12", "2", "42")

var currentQuestion: String = ""

fun getSpaceQuestion(): String {
    currentQuestion = questionsSpace.shuffled()[0]
    return currentQuestion
}

fun getSpaceAnswers(): List<String> {
    var correctAnswer = anwsersSpace[questionsSpace.indexOf(currentQuestion)]
    var currentAnswers = anwsersSpace.shuffled().take(3)
    if(currentAnswers.indexOf(correctAnswer) < 0)
        return currentAnswers.take(2).plus(correctAnswer).shuffled()
    return currentAnswers
}

fun getSpaceScore(question: String?, answer: String?): Int {
    if(questionsSpace.indexOf(question).equals(anwsersSpace.indexOf(answer))){
        return 1
    }
    return 0
}

fun formatString(gameType: String, resources: Resources) {
    when(gameType) {
        "game_space" -> println("Invalid number")
        "game_detective" -> println("Number too low")
    }
}