package com.example.appquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class EditQuestion : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {


    super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_question)

        database = FirebaseDatabase.getInstance().reference.child("Question")

        val etIdToEdit = findViewById<EditText>(R.id.etIdToEdit)
        val etNewQuestion = findViewById<EditText>(R.id.etNewQuestion)
        val etNewOption1 = findViewById<EditText>(R.id.etNewOption1)
        val etNewOption2 = findViewById<EditText>(R.id.etNewOption2)
        val etNewOption3 = findViewById<EditText>(R.id.etNewOption3)
        val etNewOption4 = findViewById<EditText>(R.id.etNewOption4)
        val etNewCorrectAnswer = findViewById<EditText>(R.id.etNewCorrectAnswer)
        val btnEditQuestion = findViewById<Button>(R.id.btnEditQuestion)

        btnEditQuestion.setOnClickListener {
            val idToEdit = etIdToEdit.text.toString().toIntOrNull()
            if (idToEdit != null) {
                val newQuestionText = etNewQuestion.text.toString()
                val newOption1 = etNewOption1.text.toString()
                val newOption2 = etNewOption2.text.toString()
                val newOption3 = etNewOption3.text.toString()
                val newOption4 = etNewOption4.text.toString()
                val newCorrectAnswer = etNewCorrectAnswer.text.toString().toIntOrNull()

                if (newCorrectAnswer != null) {
                    val query = database.orderByChild("id").equalTo(idToEdit.toDouble())
                    query.addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onDataChange(dataSnapshot: DataSnapshot) {
                            for (snapshot in dataSnapshot.children) {
                                val questionKey = snapshot.key
                                val updatedQuestion = Question(idToEdit, newQuestionText, newOption1, newOption2, newOption3, newOption4, newCorrectAnswer)
                                snapshot.ref.setValue(updatedQuestion)
                                Toast.makeText(applicationContext, "Câu hỏi đã được chỉnh sửa", Toast.LENGTH_LONG).show()
                            }
                        }

                        override fun onCancelled(databaseError: DatabaseError) {
                            Toast.makeText(applicationContext, "Lỗi khi chỉnh sửa câu hỏi", Toast.LENGTH_LONG).show()
                        }
                    })
                } else {
                    Toast.makeText(applicationContext, "Sai định dạng cho Câu trả lời đúng", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(applicationContext, "ID không hợp lệ", Toast.LENGTH_LONG).show()
            }
    }
}
}
