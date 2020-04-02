package com.route4me.trucksurvey.fragment;

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

import com.route4me.trucksurvey.R;
import com.route4me.trucksurvey.TruckSurveyActivity;

public class TruckWeightFragment extends Fragment {

    static final String WEIGHT_KEY = "WEIGHT_KEY";
    static final String WEIGHT_PER_AXLE_KEY = "WEIGHT_PER_AXLE_KEY";
    private TextView saveBtn;

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
            weightInput.addTextChangedListener(mTextWatcher);
            weightPerAxleInput = getView().findViewById(R.id.weightPerAxle).findViewById(R.id.paramInput);
            weightPerAxleInput.addTextChangedListener(mTextWatcher);
            saveBtn = getView().findViewById(R.id.saveWeightBtn);
            saveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    getTargetFragment().onActivityResult(
                            getTargetRequestCode(),
                            Activity.RESULT_OK,
                            intent.putExtra(WEIGHT_KEY, weightInput.getText())
                                    .putExtra(WEIGHT_PER_AXLE_KEY, weightPerAxleInput.getText())
                    );
                    TruckSurveyActivity.hideKeyboard(getActivity());
                    getActivity().getSupportFragmentManager().popBackStack();
                }
            });
            checkFields();
        }
    }

    private void checkFields() {
        saveBtn = getView().findViewById(R.id.saveWeightBtn);

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
