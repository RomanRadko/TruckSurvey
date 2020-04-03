package com.route4me.trucksurvey.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

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
                //here is a final place where form is submitted
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
            ((EditText) truckSurveyView.findViewById(R.id.trailersCountInput)).setCursorVisible(false);
            switch (requestCode) {
                case SIZE_DATA_CODE: {
                    TruckSize.Builder builder = TruckSize.newBuilder();
                    String heightStr = data.getExtras().get(TruckSizeFragment.HEIGHT_KEY).toString();
                    if (!heightStr.equals("")) {
                        builder.setHeight(Float.parseFloat(heightStr));
                    }
                    String lengthStr = data.getExtras().get(TruckSizeFragment.LENGTH_KEY).toString();
                    if (!lengthStr.equals("")) {
                        builder.setLength(Float.parseFloat(lengthStr));
                    }
                    String widthStr = data.getExtras().get(TruckSizeFragment.WIDTH_KEY).toString();
                    if (!widthStr.equals("")) {
                        builder.setWidth(Float.parseFloat(widthStr));
                    }
                    truckSurveyView.updateSize(builder.build());
                    break;
                }
                case WEIGHT_DATA_CODE: {
                    TruckWeight.Builder builder = TruckWeight.newBuilder();
                    String weightStr = data.getExtras().get(TruckWeightFragment.WEIGHT_KEY).toString();
                    if (!weightStr.equals("")) {
                        builder.setWeight(Float.parseFloat(weightStr));
                    }
                    String weightPerAxleStr = data.getExtras().get(TruckWeightFragment.WEIGHT_PER_AXLE_KEY).toString();
                    if (!weightPerAxleStr.equals("")) {
                        builder.setWeightPerAxle(Float.parseFloat(weightPerAxleStr));
                    }
                    truckSurveyView.updateWeight(builder.build());
                    break;
                }
                case HAZARDOUS_GOODS_DATA_CODE: {
                    truckSurveyView.updateHazardousGoods((boolean[]) data.getExtras().get(HazardousGoodsFragment.ITEMS_SELECTION_LIST_KEY));
                    break;
                }
            }
        }
    }

    private void navigateTo(Screen screen) {
        Fragment fragment;
        String tag;
        String headerTitle;
        Bundle bundle;
        int requestCode = 0;
        switch (screen) {
            case SIZE:
                fragment = new TruckSizeFragment();
                tag = TruckSizeFragment.class.getSimpleName();
                bundle = new Bundle();
                bundle.putFloat(TruckSizeFragment.HEIGHT_KEY, truckSurveyView.getTruckHeight());
                bundle.putFloat(TruckSizeFragment.WIDTH_KEY, truckSurveyView.getTruckWidth());
                bundle.putFloat(TruckSizeFragment.LENGTH_KEY, truckSurveyView.getTruckLength());
                fragment.setArguments(bundle);
                headerTitle = getResources().getString(R.string.truck_size_title);
                requestCode = SIZE_DATA_CODE;
                break;
            case WEIGHT:
                fragment = new TruckWeightFragment();
                bundle = new Bundle();
                bundle.putFloat(TruckWeightFragment.WEIGHT_KEY, truckSurveyView.getTruckWeight());
                bundle.putFloat(TruckWeightFragment.WEIGHT_PER_AXLE_KEY, truckSurveyView.getTruckWeightPerAxle());
                fragment.setArguments(bundle);
                tag = TruckWeightFragment.class.getSimpleName();
                headerTitle = getResources().getString(R.string.truck_width_title);
                requestCode = WEIGHT_DATA_CODE;
                break;
            case HAZARDOUS_GOODS:
                fragment = new HazardousGoodsFragment();
                bundle = new Bundle();
                bundle.putBooleanArray(HazardousGoodsFragment.ITEMS_SELECTION_LIST_KEY, truckSurveyView.getHazardousGoodsSelections());
                fragment.setArguments(bundle);
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
