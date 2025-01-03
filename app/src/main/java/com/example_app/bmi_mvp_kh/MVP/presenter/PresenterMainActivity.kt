package com.example_app.bmi_mvp_kh.MVP.presenter

import com.example_app.bmi_mvp_kh.MVP.ext.BaseLifeCycle
import com.example_app.bmi_mvp_kh.MVP.model.ModelMainActivity
import com.example_app.bmi_mvp_kh.MVP.view.ViewMainActivity

class PresenterMainActivity(
    private val view : ViewMainActivity,
    private val model : ModelMainActivity
):BaseLifeCycle {

    override fun onCreate() {
getBmi()
    
}

    fun getBmi(){
        view.result()


    }

}