package com.route4me.survey.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.route4me.survey.R;

public class TruckWeightFragment extends Fragment {

    static final String WEIGHT_KEY = "WEIGHT_KEY";
    static final String WEIGHT_PER_AXLE_KEY = "WEIGHT_PER_AXLE_KEY";

    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            checkFields();
        }
    };
    private TextView weightInput;
    private TextView weightPerAxleInput;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.truck_weight_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getView() != null) {
            weightInput = getView().findViewById(R.id.weight).findViewById(R.id.paramInput);
            weightInput.setText(String.valueOf(getArguments().getFloat(WEIGHT_KEY, 0f)));
            weightInput.addTextChangedListener(mTextWatcher);
            weightPerAxleInput = getView().findViewById(R.id.weightPerAxle).findViewById(R.id.paramInput);
            weightPerAxleInput.setText(String.valueOf(getArguments().getFloat(WEIGHT_PER_AXLE_KEY, 0f)));
            weightPerAxleInput.addTextChangedListener(mTextWatcher);
            checkFields();
        }
    }

    private void checkFields() {
        String weightStr = weightInput.getText().toString();
        String weightPerAxleStr = weightPerAxleInput.getText().toString();

        Intent intent = new Intent();
        getTargetFragment().onActivityResult(
                getTargetRequestCode(),
                Activity.RESULT_OK,
                intent.putExtra(WEIGHT_KEY, weightStr)
                        .putExtra(WEIGHT_PER_AXLE_KEY, weightPerAxleStr)
        );
    }

}
