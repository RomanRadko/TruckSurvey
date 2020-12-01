package com.route4me.trucksurvey

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.route4me.survey.TruckSurveyActivity
import com.route4me.survey.TruckSurveyActivity.SUBMIT_DATA_CODE
import com.route4me.survey.TruckSurveyActivity.TRUCK_PARAMS_KEY
import com.route4me.survey.model.TruckParams

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar!!.hide()
        setContentView(R.layout.activity_main)
        navigateToAddressesList()
    }

    private fun navigateToAddressesList() {
        startActivityForResult(Intent(this, TruckSurveyActivity::class.java), SUBMIT_DATA_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SUBMIT_DATA_CODE) {
            val params = data!!.extras!!.getParcelable<TruckParams>(TRUCK_PARAMS_KEY)
            Log.d("TEST", "Parameters: $params")
        }
    }
}