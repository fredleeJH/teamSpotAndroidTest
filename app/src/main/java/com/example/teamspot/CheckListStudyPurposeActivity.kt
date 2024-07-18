package com.example.teamspot

import android.content.Intent
import android.os.Bundle
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import com.example.teamspot.databinding.ActivityCheckListStudyPurposeBinding
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.flexbox.FlexboxLayout

class CheckListStudyPurposeActivity : AppCompatActivity() {
    lateinit var binding: ActivityCheckListStudyPurposeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckListStudyPurposeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = ""
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        val studypurposeintent = Intent(this, CheckListLocationActivity::class.java)
        binding.checklistspotNextBt.setOnClickListener { startActivity(studypurposeintent) }

        // Chip 상태를 설정하는 메서드 호출
        setChipGroup()
    }

    private fun setChipGroup() {
        // 초기 버튼 비활성화
        binding.checklistspotNextBt.isEnabled = false

        // chip 선택 상태 리스너
        val chipCheckedChangeListener = CompoundButton.OnCheckedChangeListener { _, _ ->
            val isAnyChipChecked = isAnyChipChecked()
            binding.checklistspotNextBt.isEnabled = isAnyChipChecked
        }

        // FlexboxLayout의 각 칩에 리스너 설정
        for (i in 0 until binding.flexboxLayout.childCount) {
            val chip = binding.flexboxLayout.getChildAt(i) as? CompoundButton
            chip?.setOnCheckedChangeListener(chipCheckedChangeListener)
        }
    }

    private fun isAnyChipChecked(): Boolean {
        for (i in 0 until binding.flexboxLayout.childCount) {
            val chip = binding.flexboxLayout.getChildAt(i) as? Chip
            if (chip?.isChecked == true) {
                return true
            }
        }
        return false
    }
}
