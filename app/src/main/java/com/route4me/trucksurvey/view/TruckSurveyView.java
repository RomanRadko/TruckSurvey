package com.route4me.trucksurvey.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
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

public class TruckSurveyView extends LinearLayout {

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
    private boolean[] hazardousGoodsSelections = new boolean[HazardousGood.values().length];
    private TruckSurveySubmitCallback submitCallback;
    private TruckParams data;

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
        data = params;
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
            hazardousGoodsSelections = getHazardousSelections(params.getHazardousGoods());
            updateHazardousGoods(hazardousGoodsSelections);
        }
    }

    private boolean[] getHazardousSelections(List<HazardousGood> hazardousGoods) {
        boolean[] selections = new boolean[HazardousGood.values().length];
        for (int i = 0; i < selections.length; i++) {
            for (HazardousGood item : hazardousGoods) {
                if (HazardousGood.values()[i] == item) {
                    selections[i] = true;
                    break;
                }
            }
        }
        return selections;
    }

    public TruckParams getData() {
        return data;
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

    public void updateHazardousGoods(boolean[] selected) {
        hazardousGoodsSelections = selected;
        TextView hazardousGoodsValue = findViewById(R.id.hazardousGoodsValue);
        List<String> goodsNames = new ArrayList<>();
        for (int i = 0; i < hazardousGoodsSelections.length; i++) {
            if (hazardousGoodsSelections[i]) {
                goodsNames.add(HazardousGood.values()[i].name().replace("_", " "));
            }
        }
        hazardousGoodsValue.setText(TextUtils.join(", ", goodsNames));
    }

    public boolean[] getHazardousGoodsSelections() {
        return hazardousGoodsSelections;
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
        initSubmitBtn();
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
                        .setHazardousGoods(getHazardousGoods())
                        .build();
            }

            private List<HazardousGood> getHazardousGoods() {
                List<HazardousGood> result = new ArrayList<>();
                for (int i = 0; i < hazardousGoodsSelections.length; i++) {
                    if (hazardousGoodsSelections[i]) {
                        result.add(HazardousGood.values()[i]);
                    }
                }
                return result;
            }
        });
    }

}
