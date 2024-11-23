package com.example.assignment

import android.os.Bundle
import android.widget.Switch
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.preference.PreferenceManager // Ensure this is the correct import
import com.example.assignment.R.id.main

class SettingsActivity : AppCompatActivity() {

    companion object {
        private const val PREF_DARK_THEME = "dark_theme"
        private const val PREF_NOTIFICATIONS_ENABLED = "notifications_enabled"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        // Apply the selected theme before setting the content view
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)

        // Load the saved theme preference
        val isDarkTheme = sharedPreferences.getBoolean(PREF_DARK_THEME, false)

        setTheme(
            if (isDarkTheme) {
                com.google.android.material.R.style.Theme_AppCompat_DayNight
            } else {
                com.google.android.material.R.style.Theme_AppCompat_Light
            }
        )

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_settings)

        // Apply padding for edge-to-edge UI
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Get references to the Switch components
        val notificationsSwitch: Switch? = findViewById(R.id.switchNotifications)
        val themeSwitch: Switch? = findViewById(R.id.switchTheme)

        // Set the initial state of switches based on saved preferences
        notificationsSwitch?.isChecked = sharedPreferences.getBoolean(PREF_NOTIFICATIONS_ENABLED, true)
        themeSwitch?.isChecked = isDarkTheme

        // Save preferences when switches are toggled
        notificationsSwitch?.setOnCheckedChangeListener { _, isChecked ->
            sharedPreferences.edit().putBoolean(PREF_NOTIFICATIONS_ENABLED, isChecked).apply()
            Toast.makeText(this, "Notifications ${if (isChecked) "Enabled" else "Disabled"}", Toast.LENGTH_SHORT).show()
        }

        themeSwitch?.setOnCheckedChangeListener { _, isChecked ->
            // Save the theme preference
            sharedPreferences.edit().putBoolean(PREF_DARK_THEME, isChecked).apply()

            // Recreate the activity to apply the new theme
            recreate()
        }
    }
}
