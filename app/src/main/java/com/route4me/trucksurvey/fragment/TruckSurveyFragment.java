package com.route4me.trucksurvey.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.route4me.trucksurvey.R;
import com.route4me.trucksurvey.model.TruckParams;
import com.route4me.trucksurvey.model.TruckSize;
import com.route4me.trucksurvey.model.TruckSurveySubmitCallback;
import com.route4me.trucksurvey.model.TruckWeight;
import com.route4me.trucksurvey.view.TruckSurveyView;

import java.util.Arrays;

import static com.route4me.trucksurvey.model.HazardousGood.Explosive;
import static com.route4me.trucksurvey.model.HazardousGood.Flammable;
import static com.route4me.trucksurvey.model.HazardousGood.Gas;

public class TruckSurveyFragment extends Fragment {

    private static final String TAG = TruckSurveyFragment.class.getSimpleName();
    private static final int SIZE_DATA_CODE = 11;
    private static final int WEIGHT_DATA_CODE = 22;
    private static final int HAZARDOUS_GOODS_DATA_CODE = 33;
    private TruckSurveyView truckSurveyView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.truck_survey_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        truckSurveyView = view.findViewById(R.id.truckSurveyView);
        View weightBtn = truckSurveyView.findViewById(R.id.truckWeightBtn);
        View sizeBtn = truckSurveyView.findViewById(R.id.truckSizeBtn);
        View hazardousBtn = truckSurveyView.findViewById(R.id.hazardousGoodsBtn);
        truckSurveyView.bindData(TruckParams.newBuilder()
                .setTrailersCount(2)
                .setSize(TruckSize.newBuilder()
                        .setWidth(4.7f)
                        .setHeight(5.6f)
                        .setLength(6.5f)
                        .build())
                .setWeight(TruckWeight.newBuilder()
                        .setWeight(45.8f)
                        .setWeightPerAxle(3.5f)
                        .build())
                .setMaxAllowedWeight(89.8f)
                .setIsDifficultTurnsAllowed(true)
                .setIsTunnelsAllowed(false)
                .setHazardousGoods(Arrays.asList(Explosive, Gas, Flammable))
                .build());
        truckSurveyView.setSubmitCallback(new TruckSurveySubmitCallback() {
            @Override
            public void onSubmit(TruckParams params) {
                Log.d(TAG, "TruckParams ::: " + params);
            }
        });
        weightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateTo(Screen.WEIGHT);
            }
        });
        sizeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateTo(Screen.SIZE);
            }
        });
        hazardousBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateTo(Screen.HAZARDOUS_GOODS);
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case SIZE_DATA_CODE: {
                    truckSurveyView.updateSize(TruckSize.newBuilder()
                            .setHeight(Float.parseFloat(data.getExtras().get(TruckSizeFragment.HEIGHT_KEY).toString()))
                            .setLength(Float.parseFloat(data.getExtras().get(TruckSizeFragment.LENGTH_KEY).toString()))
                            .setWidth(Float.parseFloat(data.getExtras().get(TruckSizeFragment.WIDTH_KEY).toString())).build());
                    break;
                }
                case WEIGHT_DATA_CODE: {
                    truckSurveyView.updateWeight(TruckWeight.newBuilder()
                            .setWeight(Float.parseFloat(data.getExtras().get(TruckWeightFragment.WEIGHT_KEY).toString()))
                            .setWeightPerAxle(Float.parseFloat(data.getExtras().get(TruckWeightFragment.WEIGHT_PER_AXLE_KEY).toString())).build());
                    break;
                }
                case HAZARDOUS_GOODS_DATA_CODE: {

                    break;
                }
            }
        }
    }

    private void navigateTo(Screen screen) {
        Fragment fragment;
        String tag;
        String headerTitle;
        int requestCode = 0;
        switch (screen) {
            case SIZE:
                fragment = new TruckSizeFragment();
                tag = TruckSizeFragment.class.getSimpleName();
                headerTitle = getResources().getString(R.string.truck_size_title);
                requestCode = SIZE_DATA_CODE;
                break;
            case WEIGHT:
                fragment = new TruckWeightFragment();
                tag = TruckWeightFragment.class.getSimpleName();
                headerTitle = getResources().getString(R.string.truck_width_title);
                requestCode = WEIGHT_DATA_CODE;
                break;
            case HAZARDOUS_GOODS:
                fragment = new HazardousGoodsFragment();
                tag = HazardousGoodsFragment.class.getSimpleName();
                headerTitle = getResources().getString(R.string.hazardous_goods_title);
                requestCode = HAZARDOUS_GOODS_DATA_CODE;
                break;
            default:
                fragment = this;
                tag = TruckSurveyFragment.class.getSimpleName();
                headerTitle = getResources().getString(R.string.truck_options);
        }
        if (getActivity() != null) {
            getActivity().setTitle(headerTitle);
            fragment.setTargetFragment(TruckSurveyFragment.this, requestCode);
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.container, fragment);
            transaction.addToBackStack(tag);
            transaction.commit();
        }
    }
}
