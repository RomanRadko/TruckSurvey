package com.route4me.trucksurvey.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;

import com.route4me.trucksurvey.R;
import com.route4me.trucksurvey.model.HazardousGood;
import com.route4me.trucksurvey.model.TruckParams;
import com.route4me.trucksurvey.model.TruckSize;
import com.route4me.trucksurvey.model.TruckSurveySubmitCallback;
import com.route4me.trucksurvey.model.TruckWeight;

import java.util.ArrayList;
import java.util.List;

public class TruckSurveyView extends LinearLayout implements MultiSpinner.MultiSpinnerListener {

    private TextView trailersCount;
    private EditText trailersCountInput;
    private TextView height;
    private TextView width;
    private TextView length;
    private TextView weight;
    private TextView weightPerAxle;
    private TextView maxAllowedWeight;
    private EditText maxAllowedWeightInput;
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
            trailersCountInput.setText(String.valueOf(params.getTrailersCount()));
            height.setText(getResources().getString(R.string.truckHeight, params.getSize().getHeight()));
            length.setText(getResources().getString(R.string.truckLength, params.getSize().getLength()));
            width.setText(getResources().getString(R.string.truckWidth, params.getSize().getWidth()));
            weight.setText(getResources().getString(R.string.truckWeightValue, params.getTruckWeight().getWeight()));
            weightPerAxle.setText(getResources().getString(R.string.truckWeightPerAxle, params.getTruckWeight().getWeightPerAxle()));
            maxAllowedWeight.setText(getResources().getString(R.string.maxTruckWeight, params.getMaxAllowedWeight()));
            maxAllowedWeightInput.setText(String.valueOf(params.getMaxAllowedWeight()));
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

    public void updateSize(TruckSize sizeParams) {
        height.setText(getResources().getString(R.string.truckHeight, sizeParams.getHeight()));
        length.setText(getResources().getString(R.string.truckLength, sizeParams.getLength()));
        width.setText(getResources().getString(R.string.truckWidth, sizeParams.getWidth()));
    }

    public void updateWeight(TruckWeight weightParams) {
        weight.setText(getResources().getString(R.string.truckWeightValue, weightParams.getWeight()));
        weightPerAxle.setText(getResources().getString(R.string.truckWeightPerAxle, weightParams.getWeightPerAxle()));
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
        trailersCountInput = findViewById(R.id.trailersCountInput);
        trailersCountInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence text, int start, int before, int count) {
                trailersCount.setText(text);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        trailersCountInput.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                trailersCountInput.setCursorVisible(true);
            }
        });
        height = findViewById(R.id.truckHeightValue);
        width = findViewById(R.id.truckWidthValue);
        length = findViewById(R.id.truckLengthValue);
        weight = findViewById(R.id.truckWeightValue);
        weightPerAxle = findViewById(R.id.truckWeightPerAxleValue);
        maxAllowedWeight = findViewById(R.id.maxAllowedWeightValue);
        maxAllowedWeightInput = findViewById(R.id.maxAllowedWeightInput);
        maxAllowedWeightInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence text, int start, int before, int count) {
                maxAllowedWeight.setText(getResources().getString(R.string.maxTruckWeight, Float.valueOf(text.toString())));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        isTunnelsAllowed = findViewById(R.id.isTunnelsAllowed);
        isTunnelsAllowedLbl = findViewById(R.id.tunnelsAllowedValue);
        isTunnelsAllowed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isTunnelsAllowedLbl.setText(isChecked ? "Yes" : "No");
            }
        });
        isDifTurnsAllowed = findViewById(R.id.isDifficultTurnsAllowed);
        isDifTurnsAllowedLbl = findViewById(R.id.difficultTurnsAllowedValue);
        isDifTurnsAllowed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isDifTurnsAllowedLbl.setText(isChecked ? "Yes" : "No");
            }
        });
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
                        .setSize(TruckSize.newBuilder()
                                .setHeight(Float.parseFloat(height.getText().toString()))
                                .setLength(Float.parseFloat(length.getText().toString()))
                                .setWidth(Float.parseFloat(width.getText().toString()))
                                .build())
                        .setWeight(TruckWeight.newBuilder()
                                .setWeight(Float.parseFloat(weight.getText().toString()))
                                .setWeightPerAxle(Float.parseFloat(weightPerAxle.getText().toString()))
                                .build())
                        .setMaxAllowedWeight(Float.parseFloat(maxAllowedWeight.getText().toString()))
                        .setIsTunnelsAllowed(isTunnelsAllowed.isChecked())
                        .setIsDifficultTurnsAllowed(isDifTurnsAllowed.isChecked())
                        .setHazardousGoods(selectedHazardousGoods)
                        .build();
            }
        });
    }
}
