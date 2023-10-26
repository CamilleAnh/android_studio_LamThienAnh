

object Constants {
    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANSWERS: String = "correct_answers"

    // Hằng số cho danh sách câu hỏi
    const val QUESTIONS_LIST: String = "questions_list"

    // Chú ý rằng đây là mảng của đối tượng Question
    val questionsList = ArrayList<Question>()

    fun addQuestion(question: Question) {
        questionsList.add(question)
    }
}
