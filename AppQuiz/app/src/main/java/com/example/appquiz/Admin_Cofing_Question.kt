package com.example.appquiz


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Admin_Cofing_Question : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_cofing_question)
        val btnAddQuestion: Button = findViewById(R.id.btnAdd)
        val btnEditQuestion: Button = findViewById(R.id.btnEdit)
        val btnDelete: Button = findViewById(R.id.btnDelete)

        btnAddQuestion.setOnClickListener{
            val intent = Intent (this, AddQuestion::class.java)
            startActivity(intent)
            finish()
        }
        btnDelete.setOnClickListener{
            val intent = Intent (this, DeleteQuestion::class.java)
            startActivity(intent)
            finish()
        }
        btnEditQuestion.setOnClickListener{
            val intent = Intent (this, EditQuestion::class.java)
            startActivity(intent)
            finish()
        }
    }
}