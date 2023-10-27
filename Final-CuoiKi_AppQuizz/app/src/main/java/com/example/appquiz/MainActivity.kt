package com.example.appquiz

import Question
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnStart: Button = findViewById(R.id.btnStart)
        val etName: EditText = findViewById(R.id.etName)
        val btnMenu: Button = findViewById(R.id.btnMenu)
        val btnGetData: Button = findViewById(R.id.btnGetData)
        btnGetData.setOnClickListener {
            fetchDataFromFirebase()
        }


        btnStart.setOnClickListener {
            if (etName.text.isEmpty()) {
                Toast.makeText(this, "Please Enter Your Name", Toast.LENGTH_LONG).show()
            } else {
                val intent = Intent(this, QuizQuestionsActivity::class.java)
                intent.putExtra(Constants.USER_NAME, etName.text.toString())
                val questionsArray = Constants.questionsList.toTypedArray()
                intent.putExtra(Constants.QUESTIONS_LIST, questionsArray)
                startActivity(intent)
            }
        }

        btnMenu.setOnClickListener {
            val intent = Intent(this, Admin_Cofing_Question::class.java)
            startActivity(intent)

        }

    }
    private fun fetchDataFromFirebase() {

        val databaseReference = FirebaseDatabase.getInstance().getReference("Question")

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                if (dataSnapshot.exists()) {

                    Constants.questionsList.clear()
                    Toast.makeText(this@MainActivity, "Lấy Data Thành Công", Toast.LENGTH_LONG).show()
                    for (questionSnapshot in dataSnapshot.children) {
                        val id = questionSnapshot.child("id").getValue(Int::class.java)
                        val question = questionSnapshot.child("question").getValue(String::class.java)
                        val optionOne = questionSnapshot.child("optionOne").getValue(String::class.java)
                        val optionTwo = questionSnapshot.child("optionTwo").getValue(String::class.java)
                        val optionThree = questionSnapshot.child("optionThree").getValue(String::class.java)
                        val optionFour = questionSnapshot.child("optionFour").getValue(String::class.java)
                        val correctAnswer = questionSnapshot.child("correctAnswer").getValue(Int::class.java)
                        val newQuestion = Question(id, question, optionOne, optionTwo, optionThree, optionFour, correctAnswer)
                        Constants.questionsList.add(newQuestion)
                    }
                } else {
                    Toast.makeText(this@MainActivity, "Lấy Thành Công Mà Không Có Data", Toast.LENGTH_LONG).show()
                }

            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@MainActivity, "Error: ${databaseError.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

    }




