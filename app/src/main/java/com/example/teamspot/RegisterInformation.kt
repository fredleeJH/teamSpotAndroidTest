package com.example.teamspot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.teamspot.databinding.ActivityRegisterInformationBinding

class RegisterInformation : AppCompatActivity() {
    lateinit var binding: ActivityRegisterInformationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRegisterInformationBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}