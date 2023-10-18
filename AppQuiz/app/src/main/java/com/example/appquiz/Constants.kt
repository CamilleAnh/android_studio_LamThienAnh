package com.example.appquiz

object Constants {

    // Định nghĩa danh sách câu hỏi
    val questionsList = ArrayList<Question>()

    // Phương thức để thêm câu hỏi mới vào danh sách
    fun addQuestion(question: Question) {
        questionsList.add(question)
    }

    // Phương thức để lấy danh sách câu hỏi
    fun getQuestions(): ArrayList<Question> {
        return questionsList
    }

    // Các hằng số khác
    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANSWERS: String = "correct_answers"
}
