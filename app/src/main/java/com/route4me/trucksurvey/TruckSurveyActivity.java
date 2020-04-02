package com.route4me.trucksurvey;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.route4me.trucksurvey.fragment.TruckSurveyFragment;

public class TruckSurveyActivity extends AppCompatActivity {

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

    private void navigateToSurvey() {
        setTitle(getResources().getString(R.string.truck_options));
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment surveyFragment = new TruckSurveyFragment();
        transaction.replace(R.id.container, surveyFragment);
        transaction.commit();
    }
}
