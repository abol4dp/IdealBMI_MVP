package com.example_app.bmi_mvp_kh.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example_app.bmi_mvp_kh.databinding.FirstPageBinding

class FirstPage : AppCompatActivity() {
    private lateinit var binding: FirstPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FirstPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.appCompatImageView.alpha = 0f // تنظیم شفافیت به صفر
        binding.appCompatImageView.animate().alpha(1f).duration = 4000


        binding.textView.translationX = 1000f
        binding.textView.animate().translationX(0f).duration = 300
        binding.textView2.translationX = -1000f
        binding.textView2.animate().translationX(0f).duration = 3000

        binding.button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }

    }
}