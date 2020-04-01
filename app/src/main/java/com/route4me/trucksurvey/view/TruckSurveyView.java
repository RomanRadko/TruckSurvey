package com.route4me.trucksurvey.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;

import com.route4me.trucksurvey.R;
import com.route4me.trucksurvey.model.HazardousGood;
import com.route4me.trucksurvey.model.TruckParams;
import com.route4me.trucksurvey.model.TruckSurveySubmitCallback;

import java.util.ArrayList;
import java.util.List;

public class TruckSurveyView extends LinearLayout implements MultiSpinner.MultiSpinnerListener {

    private TextView trailersCount;
    private TextView height;
    private TextView width;
    private TextView length;
    private TextView weight;
    private TextView weightPerAxle;
    private TextView maxAllowedWeight;
    private TextView isTunnelsAllowedLbl;
    private TextView isDifTurnsAllowedLbl;
    private SwitchCompat isTunnelsAllowed;
    private SwitchCompat isDifTurnsAllowed;
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
            height.setText(getResources().getString(R.string.truckHeight, params.getHeight()));
            length.setText(getResources().getString(R.string.truckLength, params.getLength()));
            weight.setText(getResources().getString(R.string.truckWeight, params.getWeight()));
            width.setText(getResources().getString(R.string.truckWidth, params.getWidth()));
            weightPerAxle.setText(getResources().getString(R.string.truckWeightPerAxle, params.getWeightPerAxle()));
            maxAllowedWeight.setText(getResources().getString(R.string.maxTruckWeight, params.getMaxAllowedWeight()));
            isTunnelsAllowed.setActivated(params.isTunnelsAllowed());
            isTunnelsAllowedLbl.setText(params.isTunnelsAllowed() ? "Yes" : "No");
            isDifTurnsAllowed.setChecked(params.isDifficultTurnsAllowed());
            isDifTurnsAllowedLbl.setText(params.isDifficultTurnsAllowed() ? "Yes" : "No");
            selectedHazardousGoods = params.getHazardousGoods();
//            hazardousGoodsSpinner.setSelectedItems(params.getHazardousGoods());
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
        trailersCount = findViewById(R.id.trailersCountValue);
        height = findViewById(R.id.truckHeightValue);
        width = findViewById(R.id.truckWidthValue);
        length = findViewById(R.id.truckLengthValue);
        weight = findViewById(R.id.truckWeightValue);
        weightPerAxle = findViewById(R.id.truckWeightPerAxleValue);
        maxAllowedWeight = findViewById(R.id.maxAllowedWeightValue);
        isTunnelsAllowed = findViewById(R.id.isTunnelsAllowed);
        isTunnelsAllowedLbl = findViewById(R.id.tunnelsAllowedValue);
        isDifTurnsAllowed = findViewById(R.id.isDifficultTurnsAllowed);
        isDifTurnsAllowedLbl = findViewById(R.id.difficultTurnsAllowedValue);
        initHazardousGoodsSpinner();
        initSubmitBtn();
    }

    private void initHazardousGoodsSpinner() {
        View hazardousGoodsBtn = findViewById(R.id.hazardousGoodsBtn);
//        List<String> items = new ArrayList<>();
//        for (HazardousGood item : HazardousGood.values()) {
//            items.add(item.name());
//        }
//        hazardousGoodsSpinner.setItems(items, "", this);
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
