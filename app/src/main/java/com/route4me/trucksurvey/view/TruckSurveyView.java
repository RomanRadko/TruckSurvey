package com.route4me.trucksurvey.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;

import com.route4me.trucksurvey.R;
import com.route4me.trucksurvey.model.HazardousGood;
import com.route4me.trucksurvey.model.TruckParams;

public class TruckSurveyView extends LinearLayout {

    public TruckSurveyView(Context context) {
        super(context);
        init();
    }

    public TruckSurveyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TruckSurveyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.truck_survey_layout, this);
        AppCompatSpinner spinner = findViewById(R.id.hazardousGoodsSpinner);
        ArrayAdapter<HazardousGood> adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, HazardousGood.values());
        spinner.setAdapter(adapter);
    }

    public void bindData(TruckParams params) {

    }

}
