package com.route4me.trucksurvey;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.route4me.trucksurvey.model.TruckParams;
import com.route4me.trucksurvey.view.TruckSurveyView;

import java.util.Arrays;

import static com.route4me.trucksurvey.model.HazardousGood.Explosive;
import static com.route4me.trucksurvey.model.HazardousGood.Flammable;
import static com.route4me.trucksurvey.model.HazardousGood.Gas;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
    }
}
