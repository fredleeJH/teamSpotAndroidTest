package com.example.teamspot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.CheckBox
import androidx.core.content.ContextCompat
import com.example.teamspot.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val certificationCode = "1234"
    private var isPhoneNumberValid = false // 휴대폰 번호 입력 유효성 체크용 변수
    private var isNameSelected = false // 이름 입력 여부 체크 변수
    private var isBirthdaySelected = false // 생년월일 선택 여부 체크 변수
    private var selectedNationality: String? = null // 선택된 국적 값 저장 변수
    private var selectedGender: String? = null // 선택된 성별 값 저장 변수
    private var selectedAgency: String? = null // 선택된 통신사 값 저장 변수
    private var isChecked1 = false // 체크박스1 체크 여부 변수
    private var isChecked2 = false // 체크박스2 체크 여부 변수

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = ""
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        val intent = Intent(this, RegisterInformation::class.java)
        binding.loginwithspotNextBt.setOnClickListener { startActivity(intent) }

        // 인증번호 요청 버튼 클릭 리스너
        binding.authenticationBt.setOnClickListener {
            if (isPhoneNumberValid && isNameSelected && isBirthdaySelected &&
                selectedNationality != null && selectedGender != null && selectedAgency != null) {
                binding.certificationnumberNumInputLl.visibility = View.VISIBLE
            }
        }

        // 인증번호 확인 버튼 클릭 리스너
        binding.authenticationResultBt.setOnClickListener {
            val input = binding.certificationnumberNumInputEt.text.toString()
            validateCode(input)
            updateButtonStateForCheckboxes()
        }

        // EditText 입력 감지 - 휴대폰 번호
        binding.phonenumberNumInputEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                // 입력 값이 휴대폰 번호인 경우에만 인증번호 요청 버튼의 상태를 업데이트
                isPhoneNumberValid = s?.isNotBlank() ?: false
                updateButtonState()
            }
        })

        // EditText 입력 감지 - 이름
        binding.idTextInputEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                // 입력 값이 있는 경우에만 이름 입력 완료로 처리
                isNameSelected = s?.isNotBlank() ?: false
                updateButtonState()
            }
        })

        // EditText 입력 감지 - 생년월일
        binding.birthTextInputEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                // 입력 값이 있는 경우에만 생년월일 선택 완료로 처리
                isBirthdaySelected = s?.isNotBlank() ?: false
                updateButtonState()
            }
        })

        // 체크박스1 상태 감지
        binding.checkBox1.setOnCheckedChangeListener { buttonView, isChecked ->
            isChecked1 = isChecked
            updateButtonStateForCheckboxes()
        }

        // 체크박스2 상태 감지
        binding.checkBox2.setOnCheckedChangeListener { buttonView, isChecked ->
            isChecked2 = isChecked
            updateButtonStateForCheckboxes()
        }

        // 국적 Spinner 설정
        val nationalityAdapter = ArrayAdapter(this, R.layout.item_spinner_home_register, listOf("내국인", "외국인"))
        binding.nationalitySp.adapter = nationalityAdapter

        binding.nationalitySp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedNationality = parent?.getItemAtPosition(position).toString()
                updateButtonState()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                selectedNationality = null
                updateButtonState()
            }
        }

        // 성별 Spinner 설정
        val genderAdapter = ArrayAdapter(this, R.layout.item_spinner_home_register, listOf("남자", "여자"))
        binding.spinnerMf.adapter = genderAdapter

        binding.spinnerMf.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedGender = parent?.getItemAtPosition(position).toString()
                updateButtonState()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                selectedGender = null
                updateButtonState()
            }
        }

        // 통신사 Spinner 설정
        val agencyAdapter = ArrayAdapter(this, R.layout.item_spinner_home_register, listOf("SKT", "LG", "KT"))
        binding.agencySp.adapter = agencyAdapter

        binding.agencySp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedAgency = parent?.getItemAtPosition(position).toString()
                updateButtonState()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                selectedAgency = null
                updateButtonState()
            }
        }
    }

    // 인증번호 유효성 검사
    private fun validateCode(input: String) {
        val drawable = if (input == certificationCode) {
            ContextCompat.getDrawable(this, R.drawable.true_check)
        } else {
            ContextCompat.getDrawable(this, R.drawable.img)
        }

        // 아이콘의 크기를 EditText의 글자 크기의 1.5배로 설정
        drawable?.let {
            val textSizeInPx = binding.certificationnumberNumInputEt.textSize.toInt()
            val iconSize = (textSizeInPx * 1.5).toInt() // EditText 글자 크기의 1.5배
            it.setBounds(0, 0, iconSize, iconSize)
            binding.certificationnumberNumInputEt.setCompoundDrawables(null, null, it, null)
        }

        // 인증 실패 시 오류 메시지 표시
        if (input != certificationCode) {
            binding.checkCertificationTv.visibility = View.VISIBLE
        } else {
            binding.checkCertificationTv.visibility = View.GONE
        }
    }

    // 인증번호 요청 버튼 상태 업데이트
    private fun updateButtonState() {
        binding.authenticationBt.isEnabled = isPhoneNumberValid &&
                isNameSelected && isBirthdaySelected && selectedNationality != null &&
                selectedGender != null && selectedAgency != null
    }

    // 체크박스 상태에 따른 Next 버튼 상태 업데이트
    private fun updateButtonStateForCheckboxes() {
        binding.loginwithspotNextBt.isEnabled = isChecked1 && isChecked2 &&
                binding.certificationnumberNumInputEt.text.toString() == certificationCode
    }
}
