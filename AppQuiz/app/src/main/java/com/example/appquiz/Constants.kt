package com.example.appquiz

object Constants {


    val questionsList = ArrayList<Question>()


    fun addQuestion(question: Question) {
        questionsList.add(question)
    }


    fun getQuestions(): ArrayList<Question> {
        return questionsList
    }


    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANSWERS: String = "correct_answers"
}
