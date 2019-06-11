package com.coffee.myadventure

var questionsSpace = listOf<String>("6", "12", "2", "42", "74")
var anwsersSpace = mutableListOf<String>("6", "12", "2", "42", "74")


var questionsDetective = listOf<String>("cellphone", "camera", "map")
var anwsersDetective = mutableListOf<String>("cellphone", "camera", "map")

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

fun getDetectiveQuestion(): String {
    currentQuestion = questionsDetective.shuffled()[0]
    return currentQuestion
}

fun getDetectiveAnswers(): List<String> {
    var correctAnswer = anwsersDetective[questionsDetective.indexOf(currentQuestion)]
    var currentAnswers = anwsersDetective.shuffled().take(3)
    if(currentAnswers.indexOf(correctAnswer) < 0)
        return currentAnswers.take(2).plus(correctAnswer).shuffled()
    return currentAnswers
}

fun getDetectiveScore(question: String?, answer: String?): Int {
    if(questionsDetective.indexOf(question).equals(anwsersDetective.indexOf(answer))){
        return 2
    }
    return 0
}