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
import com.route4me.trucksurvey.view.ParameterItemView;

public class TruckSizeFragment extends Fragment {

    static final String HEIGHT_KEY = "HEIGHT_KEY";
    static final String LENGTH_KEY = "LENGTH_KEY";
    static final String WIDTH_KEY = "WIDTH_KEY";
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
            checkFields();
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
            heightInput.setText(String.valueOf(getArguments().getFloat(HEIGHT_KEY, 0f)));
            heightInput.addTextChangedListener(mTextWatcher);
            lengthInput = getView().findViewById(R.id.length).findViewById(R.id.paramInput);
            lengthInput.setText(String.valueOf(getArguments().getFloat(LENGTH_KEY, 0f)));
            lengthInput.addTextChangedListener(mTextWatcher);
            widthInput = getView().findViewById(R.id.width).findViewById(R.id.paramInput);
            widthInput.setText(String.valueOf(getArguments().getFloat(WIDTH_KEY, 0f)));
            widthInput.addTextChangedListener(mTextWatcher);
            checkFields();
        }
    }

    private void checkFields() {
        String widthInputStr = widthInput.getText().toString();
        ParameterItemView widthView = getView().findViewById(R.id.width);
        //todo: maybe validation for other params should be also added?
        boolean isWidthRight;
        if (!widthInputStr.equals("")) {
            isWidthRight = Float.parseFloat(widthInputStr) <= 32;
            if (!isWidthRight) {
                widthView.setErrorState(getString(R.string.wrong_width));
                return;
            }
        }
        widthView.hideErrorLbl();
        Intent intent = new Intent();
        getTargetFragment().onActivityResult(
                getTargetRequestCode(),
                Activity.RESULT_OK,
                intent.putExtra(HEIGHT_KEY, heightInput.getText())
                        .putExtra(LENGTH_KEY, lengthInput.getText())
                        .putExtra(WIDTH_KEY, widthInput.getText())
        );
    }


}
