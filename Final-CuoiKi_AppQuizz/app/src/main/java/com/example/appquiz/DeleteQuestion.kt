package com.example.appquiz

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import com.google.firebase.database.*

class DeleteQuestion : AppCompatActivity() {
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete)

        database = FirebaseDatabase.getInstance().reference.child("Question")

        val etIdToDelete = findViewById<EditText>(R.id.etIdToDelete)
        val btnDeleteQuestion = findViewById<Button>(R.id.btnDeleteQuestion)

        btnDeleteQuestion.setOnClickListener {
            val idToDelete = etIdToDelete.text.toString().toIntOrNull()
            if (idToDelete != null) {
                val query = database.orderByChild("id").equalTo(idToDelete.toDouble())
                query.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        for (snapshot in dataSnapshot.children) {
                            snapshot.ref.removeValue()
                        }
                        Toast.makeText(applicationContext, "Câu hỏi đã được xóa", Toast.LENGTH_LONG).show()
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        Toast.makeText(applicationContext, "Lỗi khi xóa câu hỏi", Toast.LENGTH_LONG).show()
                    }
                })
            } else {
                Toast.makeText(applicationContext, "ID không hợp lệ", Toast.LENGTH_LONG).show()
            }
        }
    }
}
