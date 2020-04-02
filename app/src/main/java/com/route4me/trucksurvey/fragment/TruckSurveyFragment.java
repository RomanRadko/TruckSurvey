package com.route4me.trucksurvey.fragment;

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.truck_survey_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        TruckSurveyView truckSurveyView = view.findViewById(R.id.truckSurveyView);
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

    private void navigateTo(Screen screen) {
        Fragment fragment;
        String tag;
        switch (screen) {
            case SIZE:
                fragment = new TruckSizeFragment();
                tag = TruckSizeFragment.class.getSimpleName();
                break;
            case WEIGHT:
                fragment = new TruckWeightFragment();
                tag = TruckWeightFragment.class.getSimpleName();
                break;
            case HAZARDOUS_GOODS:
                fragment = new HazardousGoodsFragment();
                tag = HazardousGoodsFragment.class.getSimpleName();
                break;
            default:
                fragment = this;
                tag = TruckSurveyFragment.class.getSimpleName();
        }
        if (getActivity() != null) {
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.container, fragment);
            transaction.addToBackStack(tag);
            transaction.commit();
        }
    }
}
