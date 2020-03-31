package com.route4me.trucksurvey.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.route4me.trucksurvey.R;
import com.route4me.trucksurvey.model.HazardousGood;
import com.route4me.trucksurvey.model.TruckParams;

import java.util.ArrayList;
import java.util.List;

public class TruckSurveyView extends LinearLayout implements MultiSpinner.MultiSpinnerListener {

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

    public void bindData(TruckParams params) {

    }

    @Override
    public void onItemsSelected(boolean[] selected) {

    }

    private void init() {
        inflate(getContext(), R.layout.truck_survey_layout, this);
        initHazardousGoodsSpinner();
    }

    private void initHazardousGoodsSpinner() {
        MultiSpinner spinner = findViewById(R.id.hazardousGoodsSpinner);
        List<String> items = new ArrayList<>();
        for (HazardousGood item : HazardousGood.values()) {
            items.add(item.name());
        }
        spinner.setItems(items, "", this);
    }
}
