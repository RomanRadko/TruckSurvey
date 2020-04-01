package com.route4me.trucksurvey;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.route4me.trucksurvey.model.TruckSurveySubmitCallback;
import com.route4me.trucksurvey.model.TruckParams;
import com.route4me.trucksurvey.view.TruckSurveyView;

import java.util.Arrays;

import static com.route4me.trucksurvey.model.HazardousGood.Explosive;
import static com.route4me.trucksurvey.model.HazardousGood.Flammable;
import static com.route4me.trucksurvey.model.HazardousGood.Gas;

public class TruckSurveyActivity extends AppCompatActivity {

    private static final String TAG = TruckSurveyActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        TruckSurveyView truckSurveyView = findViewById(R.id.truckSurveyView);
        truckSurveyView.bindData(TruckParams.newBuilder()
                .setTrailersCount(2)
                .setHeight(4.5f)
                .setWidth(5.6f)
                .setWeight(45.8f)
                .setWeightPerAxle(3.2f)
                .setMaxAllowedWeight(89.8f)
                .setIsDifficultTurnsAllowed(true)
                .setIsTunnelsAllowed(false)
                .setHazardousGoods(Arrays.asList(Explosive, Gas, Flammable))
                .build());
        truckSurveyView.setSubmitCallback(new TruckSurveySubmitCallback() {
            @Override
            public void onSubmit(TruckParams params) {
                Log.d(TAG, "TruckParams ::: " + params);
            }
        });
    }
}
