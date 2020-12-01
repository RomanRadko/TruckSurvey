package com.route4me.survey;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.route4me.survey.fragment.TruckSurveyFragment;

public class TruckSurveyActivity extends AppCompatActivity {

    public static final int SUBMIT_DATA_CODE = 8323;
    public static final String TRUCK_PARAMS_KEY = "TRUCK_PARAMS_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.truck_survey_activity);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        navigateToSurvey();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            setTitle(getResources().getString(R.string.truck_options));
            getSupportFragmentManager().popBackStack();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void navigateToSurvey() {
        setTitle(getResources().getString(R.string.truck_options));
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment surveyFragment = new TruckSurveyFragment();
        transaction.replace(R.id.container, surveyFragment);
        transaction.commit();
    }
}
