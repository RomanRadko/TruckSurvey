package com.route4me.trucksurvey.fragment;

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

import com.route4me.trucksurvey.R;
import com.route4me.trucksurvey.view.BaseTextView;

public class TruckWeightFragment extends Fragment {

    private static final String TAG = TruckWeightFragment.class.getSimpleName();

    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            checkFieldsForEmptyValues();
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
            weightInput.addTextChangedListener(mTextWatcher);
            weightPerAxleInput = getView().findViewById(R.id.weightPerAxle).findViewById(R.id.paramInput);
            weightPerAxleInput.addTextChangedListener(mTextWatcher);
            checkFieldsForEmptyValues();
        }
    }

    private void checkFieldsForEmptyValues() {
        BaseTextView saveBtn = getView().findViewById(R.id.saveWeightBtn);

        String weightStr = weightInput.getText().toString();
        String weightPerAxleStr = weightPerAxleInput.getText().toString();

        if (weightStr.equals("") || weightPerAxleStr.equals("")) {
            saveBtn.setAlpha(0.6f);
            saveBtn.setEnabled(false);
        } else {
            saveBtn.setAlpha(1f);
            saveBtn.setEnabled(true);
        }
    }

}
