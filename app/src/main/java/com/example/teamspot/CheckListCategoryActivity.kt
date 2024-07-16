package com.example.teamspot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.teamspot.databinding.ActivityCheckListCategoryBinding

class CheckListCategoryActivity : AppCompatActivity() {
    lateinit var binding: ActivityCheckListCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCheckListCategoryBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = ""
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}