package com.example.myadventure

import android.content.res.Resources
import androidx.lifecycle.MutableLiveData

var questionsSpace = listOf<String>("uno", "dos", "tres")
var anwsersSpace = listOf<String>("A", "B", "C")

fun getSpaceQuestion(): String {
    return questionsSpace.shuffled()[0]
}

fun getSpaceAnswers(): List<String> {
    return anwsersSpace.shuffled().take(3)
}

fun getSpaceScore(question: String?, answer: String?): Int {
    if(questionsSpace.indexOf(question).equals(anwsersSpace.indexOf(answer))){
        return 1
    }
    return 0
}

fun formatString(resources: Resources) {

}