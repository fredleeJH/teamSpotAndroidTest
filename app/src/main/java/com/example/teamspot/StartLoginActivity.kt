package com.example.teamspot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.teamspot.databinding.ActivityStartLoginBinding

class StartLoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityStartLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = Intent(this, LoginActivity::class.java)
        binding.loginwithspotBt.setOnClickListener {startActivity(intent)}

        val intent2 = Intent(this, CheckListCategoryActivity::class.java)
        binding.loginwithkakaoBt.setOnClickListener { startActivity(intent2) }
    }
}