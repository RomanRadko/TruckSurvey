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

public class TruckSizeFragment extends Fragment {

    private static final String TAG = TruckSizeFragment.class.getSimpleName();
    private TextView heightInput;
    private TextView lengthInput;
    private TextView widthInput;

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.truck_size_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getView() != null) {
            heightInput = getView().findViewById(R.id.height).findViewById(R.id.paramInput);
            heightInput.addTextChangedListener(mTextWatcher);
            lengthInput = getView().findViewById(R.id.length).findViewById(R.id.paramInput);
            lengthInput.addTextChangedListener(mTextWatcher);
            widthInput = getView().findViewById(R.id.width).findViewById(R.id.paramInput);
            widthInput.addTextChangedListener(mTextWatcher);
            checkFieldsForEmptyValues();
        }
    }

    private void checkFieldsForEmptyValues() {
        BaseTextView saveBtn = getView().findViewById(R.id.saveSizeBtn);

        String heightInputStr = heightInput.getText().toString();
        String lengthInputStr = lengthInput.getText().toString();
        String widthInputStr = widthInput.getText().toString();

        if (heightInputStr.equals("") || lengthInputStr.equals("") || widthInputStr.equals("")) {
            saveBtn.setAlpha(0.6f);
            saveBtn.setEnabled(false);
        } else {
            saveBtn.setAlpha(1f);
            saveBtn.setEnabled(true);
        }
    }

}
