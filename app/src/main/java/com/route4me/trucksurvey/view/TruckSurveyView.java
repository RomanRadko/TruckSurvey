package com.route4me.trucksurvey.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.route4me.trucksurvey.R;
import com.route4me.trucksurvey.model.HazardousGood;
import com.route4me.trucksurvey.model.TruckSurveySubmitCallback;
import com.route4me.trucksurvey.model.TruckParams;

import java.util.ArrayList;
import java.util.List;

public class TruckSurveyView extends LinearLayout implements MultiSpinner.MultiSpinnerListener {

    private TextView trailersCount;
    private TextView height;
    private TextView width;
    private TextView weight;
    private TextView weightPerAxle;
    private TextView maxAllowedWeight;
    private CheckBox isTunnelsAllowed;
    private CheckBox isDifTurnsAllowed;
    private MultiSpinner hazardousGoodsSpinner;
    private List<HazardousGood> selectedHazardousGoods = new ArrayList<>();
    private TruckSurveySubmitCallback submitCallback;

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
        if (params != null) {
            trailersCount.setText(String.valueOf(params.getTrailersCount()));
            height.setText(String.valueOf(params.getHeight()));
            width.setText(String.valueOf(params.getWidth()));
            weight.setText(String.valueOf(params.getWeight()));
            weightPerAxle.setText(String.valueOf(params.getWeightPerAxle()));
            maxAllowedWeight.setText(String.valueOf(params.getMaxAllowedWeight()));
            isTunnelsAllowed.setActivated(params.isTunnelsAllowed());
            isDifTurnsAllowed.setChecked(params.isDifficultTurnsAllowed());
            selectedHazardousGoods = params.getHazardousGoods();
            hazardousGoodsSpinner.setSelectedItems(params.getHazardousGoods());
        }
    }

    public void setSubmitCallback(TruckSurveySubmitCallback callback) {
        submitCallback = callback;
    }

    @Override
    public void onItemsSelected(boolean[] selected) {
        selectedHazardousGoods.clear();
        for (boolean selection : selected) {
            for (HazardousGood hazardousGood : HazardousGood.values()) {
                if (selection) {
                    selectedHazardousGoods.add(hazardousGood);
                    break;
                }
            }
        }
    }

    private void init() {
        inflate(getContext(), R.layout.truck_survey_layout, this);
        //get all fields
        trailersCount = findViewById(R.id.trailersCount);
        height = findViewById(R.id.height);
        width = findViewById(R.id.width);
        weight = findViewById(R.id.weight);
        weightPerAxle = findViewById(R.id.weightPerAxle);
        maxAllowedWeight = findViewById(R.id.maxAllowedWeight);
        isTunnelsAllowed = findViewById(R.id.isTunnelsAllowed);
        isDifTurnsAllowed = findViewById(R.id.isDifTurnsAllowed);
        initHazardousGoodsSpinner();
        initSubmitBtn();
    }

    private void initHazardousGoodsSpinner() {
        hazardousGoodsSpinner = findViewById(R.id.hazardousGoodsSpinner);
        List<String> items = new ArrayList<>();
        for (HazardousGood item : HazardousGood.values()) {
            items.add(item.name());
        }
        hazardousGoodsSpinner.setItems(items, "", this);
    }

    private void initSubmitBtn() {
        TextView submitBtn = findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                submitCallback.onSubmit(readParams());
            }

            private TruckParams readParams() {
                return TruckParams.newBuilder()
                        .setTrailersCount(Integer.parseInt(trailersCount.getText().toString()))
                        .setHeight(Float.parseFloat(height.getText().toString()))
                        .setWidth(Float.parseFloat(width.getText().toString()))
                        .setWeight(Float.parseFloat(weight.getText().toString()))
                        .setWeightPerAxle(Float.parseFloat(weightPerAxle.getText().toString()))
                        .setMaxAllowedWeight(Float.parseFloat(maxAllowedWeight.getText().toString()))
                        .setIsTunnelsAllowed(isTunnelsAllowed.isChecked())
                        .setIsDifficultTurnsAllowed(isDifTurnsAllowed.isChecked())
                        .setHazardousGoods(selectedHazardousGoods)
                        .build();
            }
        });
    }
}
