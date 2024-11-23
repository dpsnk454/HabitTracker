package com.example.assignment

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val habitList = mutableListOf<Habit>() // List of habits
    private lateinit var habitAdapter: HabitAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Ensures that the UI layout extends into the system's edge (status bar, navigation bar)
        setContentView(R.layout.activity_main)

        // Applying padding for edge-to-edge UI
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Setting up RecyclerView to display habits
        val recyclerView = findViewById<RecyclerView>(R.id.habitRecyclerView)
        habitAdapter = HabitAdapter(habitList)
        recyclerView.adapter = habitAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Set up Add New Habit button to navigate to AddHabitActivity
        findViewById<Button>(R.id.newhabit).setOnClickListener {
            val intent = Intent(this, AddHabitActivity::class.java)
            startActivityForResult(intent, ADD_HABIT_REQUEST_CODE)
        }

        // Set up Reset Habits button to clear the habit list
        findViewById<Button>(R.id.resetHabits).setOnClickListener {
            habitList.clear()
            habitAdapter.notifyDataSetChanged() // Refresh RecyclerView
        }

        // Setup BottomNavigationView with an item selected listener
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    // Home selected, no action needed as we're already on the Home screen
                    true
                }
                R.id.nav_statistics -> {
                    // Navigate to the Statistics Activity
                    startActivity(Intent(this, StatisticsActivity::class.java))
                    true
                }
                R.id.nav_settings -> {
                    // Navigate to the Settings Activity
                    startActivity(Intent(this, SettingsActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }

    // Handle the result from AddHabitActivity
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_HABIT_REQUEST_CODE && resultCode == RESULT_OK) {
            val habitName = data?.getStringExtra("habitName")
            val habitDescription = data?.getStringExtra("habitDescription")
            if (!habitName.isNullOrEmpty()) {
                habitList.add(Habit(habitName, habitDescription ?: ""))
                habitAdapter.notifyDataSetChanged() // Refresh RecyclerView
            }
        }
    }

    companion object {
        const val ADD_HABIT_REQUEST_CODE = 1
    }
}

