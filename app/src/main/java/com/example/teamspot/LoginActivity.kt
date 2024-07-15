package com.example.teamspot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import com.example.teamspot.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val outCategoryList = listOf("내국인", "외국인")
        val inCategoryList = listOf("남자", "여자")
        val phoneCategoryList = listOf("SKT","LG","KT")

        // Spinner 설정
        val spinnerAdapter = CategorySpinnerAdapter(this, R.layout.item_spinner_home_register, outCategoryList)
        binding.nationalitySp.adapter = spinnerAdapter

        // Spinner 아이템 선택 리스너 설정
        binding.nationalitySp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val value = binding.nationalitySp.getItemAtPosition(p2).toString()
                // 선택된 값을 사용하여 필요한 작업 수행
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                // 선택되지 않은 경우
            }
        }
        val spinAdapter = CategorySpinnerAdapter(this, R.layout.item_spinner_home_register, inCategoryList)
        binding.spinnerMf.adapter = spinAdapter

        // Spinner 아이템 선택 리스너 설정
        binding.spinnerMf.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val value = binding.spinnerMf.getItemAtPosition(p2).toString()
                // 선택된 값을 사용하여 필요한 작업 수행
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        val phoneAdapter = CategorySpinnerAdapter(this, R.layout.item_spinner_home_register, phoneCategoryList)
        binding.agencySp.adapter = phoneAdapter

        // Spinner 아이템 선택 리스너 설정
        binding.agencySp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val value = binding.agencySp.getItemAtPosition(p2).toString()
                // 선택된 값을 사용하여 필요한 작업 수행
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }


    }

}