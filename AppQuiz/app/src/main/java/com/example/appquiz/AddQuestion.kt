package com.example.appquiz

import Question
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AddQuestion : AppCompatActivity() {
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_question)
        val idQuestion = findViewById<EditText>(R.id.idQuestion)
        val tvQuestion = findViewById<EditText>(R.id.tvQuestion)
        val optionOne = findViewById<EditText>(R.id.optionOne)
        val optionTwo = findViewById<EditText>(R.id.optionTwo)
        val optionThree = findViewById<EditText>(R.id.optionThree)
        val optionFour = findViewById<EditText>(R.id.optionFour)
        val correctAnswer = findViewById<EditText>(R.id.correctAnswer)
        val btnSave = findViewById<Button>(R.id.btnSave)

        btnSave.setOnClickListener {
            correctAnswer.inputType = InputType.TYPE_CLASS_NUMBER
            idQuestion.inputType = InputType.TYPE_CLASS_NUMBER
            val idQuestionValue = idQuestion.text.toString().toIntOrNull()
            val correctAnswerValue = correctAnswer.text.toString().toIntOrNull()

            if (idQuestionValue != null && correctAnswerValue != null) {
                val questionKey = "question_$idQuestionValue" // Tạo key dựa trên ID câu hỏi
                val QuestionText = tvQuestion.text.toString()
                val Option1 = optionOne.text.toString()
                val Option2 = optionTwo.text.toString()
                val Option3 = optionThree.text.toString()
                val Option4 = optionFour.text.toString()
                val question = Question(idQuestionValue, QuestionText, Option1, Option2, Option3, Option4, correctAnswerValue)

                database = FirebaseDatabase.getInstance().getReference("Question")
                database.child(questionKey).setValue(question)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            tvQuestion.text?.clear()
                            optionOne.text?.clear()
                            optionTwo.text?.clear()
                            optionThree.text?.clear()
                            optionFour.text?.clear()
                            correctAnswer.text?.clear()
                            Toast.makeText(this, "Saved", Toast.LENGTH_LONG).show()
                            val intent = Intent(this@AddQuestion, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this, "Failed", Toast.LENGTH_LONG).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Invalid input for idQuestion or correctAnswer", Toast.LENGTH_LONG).show()
            }
        }
    }
}

