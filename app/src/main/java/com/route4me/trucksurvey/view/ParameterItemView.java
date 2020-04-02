package com.route4me.trucksurvey.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.route4me.trucksurvey.R;

public class ParameterItemView extends RelativeLayout {

    private TextView titleView;
    private TextView labelView;
    private String title;
    private String label;

    public ParameterItemView(Context context) {
        super(context);
        init();
    }

    public ParameterItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        readParams(context, attrs);
        init();
    }

    public ParameterItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        readParams(context, attrs);
        init();
    }

    private void readParams(Context context, @Nullable AttributeSet attrs) {
        TypedArray attributes = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.ParameterItemView,
                0, 0);
        try {
            title = attributes.getString(R.styleable.ParameterItemView_title);
            label = attributes.getString(R.styleable.ParameterItemView_label);
        } finally {
            attributes.recycle();
        }
    }

    private void init() {
        inflate(getContext(), R.layout.params_item_view_layout, this);
        titleView = findViewById(R.id.title);
        labelView = findViewById(R.id.label);
        initEditForm();
        applyParams();
    }

    private void applyParams() {
        titleView.setText(title);
        labelView.setText(label);
    }

    private void initEditForm() {
        final EditText editText = findViewById(R.id.paramInput);
        LayerDrawable drawable = (LayerDrawable) ContextCompat.getDrawable(editText.getContext(),
                R.drawable.layer_bg_edit_text);
        GradientDrawable gradientDrawableNormal = (GradientDrawable) drawable
                .findDrawableByLayerId(R.id.normal_layer);
        gradientDrawableNormal.setStroke(2, ContextCompat.getColor(editText.getContext(), R.color.colorPrimaryDark));

        LayerDrawable drawableFocused = (LayerDrawable) ContextCompat.getDrawable(editText.getContext(),
                R.drawable.layer_bg_focused_edit_text);
        GradientDrawable gradientDrawableFocused = (GradientDrawable) drawableFocused
                .findDrawableByLayerId(R.id.focused_layer);
        gradientDrawableFocused.setStroke(5, ContextCompat.getColor(editText.getContext(), R.color.colorPrimary));

        StateListDrawable states = new StateListDrawable();
        states.addState(new int[]{android.R.attr.state_focused}, drawableFocused);
        states.addState(new int[]{}, drawable);
        editText.setBackground(states);
        editText.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    titleView.setTextColor(getResources().getColor(R.color.colorPrimary));
                    editText.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                } else {
                    titleView.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                    editText.setTextColor(getResources().getColor(R.color.colorSecondaryDark));
                }
            }
        });
    }
}
