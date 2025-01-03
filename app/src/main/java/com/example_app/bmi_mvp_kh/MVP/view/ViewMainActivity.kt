package com.example_app.bmi_mvp_kh.MVP.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import android.widget.Toast
import com.example_app.bmi_mvp_kh.databinding.ActivityMainBinding

class ViewMainActivity(
    contextInstance: Context
) : FrameLayout(contextInstance) {

    val binding =
        ActivityMainBinding.inflate(LayoutInflater.from(context)) //Connection to the main activity


    private val weightRanges = mapOf(
        140..143 to "38 تا 50 کیلوگرم", // بازه قد 140 تا 143، وزن 38 تا 50 کیلوگرم
        143..146 to "40 تا 52 کیلوگرم", // بازه قد 143 تا 146، وزن 40 تا 52 کیلوگرم
        146..149 to "42 تا 54 کیلوگرم", // بازه قد 146 تا 149، وزن 42 تا 54 کیلوگرم
        149..152 to "43 تا 56 کیلوگرم", // بازه قد 149 تا 152، وزن 43 تا 56 کیلوگرم
        152..155 to "45 تا 58 کیلوگرم", // بازه قد 152 تا 155، وزن 45 تا 58 کیلوگرم
        155..158 to "47 تا 60 کیلوگرم", // بازه قد 155 تا 158، وزن 47 تا 60 کیلوگرم
        158..161 to "48 تا 62 کیلوگرم", // بازه قد 158 تا 161، وزن 48 تا 62 کیلوگرم
        161..164 to "50 تا 64 کیلوگرم", // بازه قد 161 تا 164، وزن 50 تا 64 کیلوگرم
        164..167 to "52 تا 66 کیلوگرم", // بازه قد 164 تا 167، وزن 52 تا 66 کیلوگرم
        167..170 to "54 تا 69 کیلوگرم", // بازه قد 167 تا 170، وزن 54 تا 69 کیلوگرم
        170..173 to "56 تا 72 کیلوگرم", // بازه قد 170 تا 173، وزن 56 تا 72 کیلوگرم
        173..176 to "58 تا 74 کیلوگرم", // بازه قد 173 تا 176، وزن 58 تا 74 کیلوگرم
        176..179 to "60 تا 77 کیلوگرم", // بازه قد 176 تا 179، وزن 60 تا 77 کیلوگرم
        179..182 to "62 تا 80 کیلوگرم", // بازه قد 179 تا 182، وزن 62 تا 80 کیلوگرم
        182..185 to "64 تا 83 کیلوگرم", // بازه قد 182 تا 185، وزن 64 تا 83 کیلوگرم
        185..188 to "66 تا 85 کیلوگرم", // بازه قد 185 تا 188، وزن 66 تا 85 کیلوگرم
        188..191 to "68 تا 88 کیلوگرم", // بازه قد 188 تا 191، وزن 68 تا 88 کیلوگرم
        191..194 to "70 تا 91 کیلوگرم", // بازه قد 191 تا 194، وزن 70 تا 91 کیلوگرم
        194..197 to "72 تا 94 کیلوگرم", // بازه قد 194 تا 197، وزن 72 تا 94 کیلوگرم
        197..200 to "74 تا 97 کیلوگرم"  // بازه قد 197 تا 200، وزن 74 تا 97 کیلوگرم
    )

    private fun height(): Float {   //Height calculation
        if (binding.editTextGhad.text?.toString()?.isEmpty() == true) {
            Toast.makeText(context, "خالی است ", Toast.LENGTH_SHORT).show()
            return 0.0f
        }
        val heightus = binding.editTextGhad.text.toString().toFloat()
        val res = heightus / 100 //Height divided by 100
        return res * res


    }

    private fun weight(): String {
        val weightText = binding.editTextVazn.text.toString().trim() // حذف فاصله‌های اضافی
        if (weightText.isEmpty()) {

            return "" // بازگشت مقدار خالی در صورت خطا
        }
        val weightUs = weightText.toFloat()
        val heightValue = height()
        if (heightValue == 0.0f) { // بررسی جلوگیری از تقسیم بر صفر
            Toast.makeText(context, "لطفا قد معتبر وارد کنید", Toast.LENGTH_SHORT).show()
            return ""
        }
        val result = weightUs / heightValue
        return "%.1f".format(result)  // Return the result with one decimal place

        /* The BMI formula based on your code is
        1 height / 100 = after ( result height = height / height )
        2 ( weight / height ) = BMI   */

    }

    fun result() {

        val result = weight()

        if (result.isNotEmpty()) {
            binding.txtresult.visibility = View.VISIBLE
            binding.txtresult.text = result
        }
        binding.thrashImage.setOnClickListener {
            binding.editTextGhad.text?.clear()
            binding.editTextVazn.text?.clear()
            binding.txtresult.text = " "
            binding.textifbody.text = ""
            binding.bmirange.text = ""
        }
        binding.calculation.setOnClickListener {
            binding.root.closeKeyboard()
            val result = weight() // فقط یک بار محاسبه

            val heightValue = binding.editTextGhad.text.toString().toIntOrNull()
            val weightRange =
                heightValue?.let { h -> weightRanges.entries.find { h in it.key }?.value }

            if (weightRange != null) {
                binding.bmirange.text = ("وزن مناسب برای شما  : $weightRange")
            } else {

            }

            if (result.isNotEmpty()) {
                binding.txtresult.visibility = View.VISIBLE
                binding.txtresult.text = result
                val bmiValue = result.toFloatOrNull()
                if (bmiValue != null) {
                    when {
                        bmiValue <= 18.5 -> {
                            binding.textifbody.visibility = View.VISIBLE
                            binding.textifbody.text = "کمبود وزن"
                        }

                        bmiValue in 18.5..25.0 -> {
                            binding.textifbody.visibility = View.VISIBLE
                            binding.textifbody.text = "وزن سلامت"
                        }

                        bmiValue in 25.0..30.0 -> {
                            binding.textifbody.visibility = View.VISIBLE
                            binding.textifbody.text = "اضافه وزن"
                        }

                        bmiValue >= 30 -> {
                            binding.textifbody.visibility = View.VISIBLE
                            binding.textifbody.text = "وزن زیاد"
                        }


                    }
                }
            }
        }


    }

    fun View.closeKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}






