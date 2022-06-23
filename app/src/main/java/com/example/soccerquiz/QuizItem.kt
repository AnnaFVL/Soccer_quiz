package com.example.soccerquiz

class QuizItem {

    var question: String
    var answersList: List<String>

    constructor(question: String, answers: List<String>) {
        this.question = question
        this.answersList = answers
    }
}