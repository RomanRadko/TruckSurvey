package com.route4me.survey.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.route4me.survey.R;
import com.route4me.survey.model.MultiHazardousGoodsListener;
import com.route4me.survey.view.HazardousGoodsView;

public class HazardousGoodsFragment extends Fragment implements MultiHazardousGoodsListener {

    static final String ITEMS_SELECTION_LIST_KEY = "ITEMS_SELECTION_LIST_KEY";
    private static final String TAG = HazardousGoodsFragment.class.getSimpleName();
    private HazardousGoodsView hazardousGoodsView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.hazardous_goods_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        hazardousGoodsView = view.findViewById(R.id.hazardousGoodsView);
        hazardousGoodsView.bindData(getArguments().getBooleanArray(ITEMS_SELECTION_LIST_KEY), this);
    }

    @Override
    public void onItemsSelected(boolean[] selected) {
        Intent intent = new Intent();
        getTargetFragment().onActivityResult(
                getTargetRequestCode(),
                Activity.RESULT_OK,
                intent.putExtra(ITEMS_SELECTION_LIST_KEY, selected)
        );
    }

}
