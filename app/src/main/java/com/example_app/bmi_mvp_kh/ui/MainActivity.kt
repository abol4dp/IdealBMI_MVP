package com.example_app.bmi_mvp_kh.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example_app.bmi_mvp_kh.MVP.model.ModelMainActivity
import com.example_app.bmi_mvp_kh.MVP.presenter.PresenterMainActivity
import com.example_app.bmi_mvp_kh.MVP.view.ViewMainActivity
import com.example_app.bmi_mvp_kh.R
class MainActivity : AppCompatActivity() {

    // استفاده از متغیر presenter که در سطح کلاس اعلام شده است.
    private lateinit var presenter: PresenterMainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ایجاد ViewMainActivity
        val view = ViewMainActivity(this)

        // ست کردن View به عنوان محتوا
        setContentView(view.binding.root)

        // مقداردهی presenter که در سطح کلاس اعلام شده است
        presenter = PresenterMainActivity(view, ModelMainActivity(this))

        // صدا زدن متد onCreate از Presenter
        presenter.onCreate()




    }
}