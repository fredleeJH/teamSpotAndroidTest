package com.example.teamspot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.teamspot.databinding.ActivityCheckListLocationBinding

class CheckListLocationActivity : AppCompatActivity() {
    lateinit var binding: ActivityCheckListLocationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCheckListLocationBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}