package com.route4me.trucksurvey.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.route4me.trucksurvey.R;
import com.route4me.trucksurvey.model.TruckParams;

public class TruckSurveyView extends LinearLayout {

    public TruckSurveyView(Context context) {
        super(context);
        init(null);
    }

    public TruckSurveyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public TruckSurveyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(@Nullable AttributeSet set) {
        inflate(getContext(), R.layout.truck_survey_layout, this);
    }

    public void bindData(TruckParams params) {

    }

}
