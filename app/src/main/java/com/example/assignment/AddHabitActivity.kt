package com.example.assignment


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AddHabitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_habit)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val habitNameInput = findViewById<EditText>(R.id.habitNameInput)
        val habitDescriptionInput = findViewById<EditText>(R.id.habitDescriptionInput)

        findViewById<Button>(R.id.addHabitButton).setOnClickListener {
            val intent = Intent()
            intent.putExtra("habitName", habitNameInput.text.toString())
            intent.putExtra("habitDescription", habitDescriptionInput.text.toString())
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

    }
}