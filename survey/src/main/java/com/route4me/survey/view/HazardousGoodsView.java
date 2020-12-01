package com.route4me.survey.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.route4me.survey.R;
import com.route4me.survey.adapter.HazardousItemAdapter;
import com.route4me.survey.model.HazardousGood;
import com.route4me.survey.model.HazardousGoodItem;
import com.route4me.survey.model.MultiHazardousGoodsListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HazardousGoodsView extends FrameLayout {

    private List<HazardousGoodItem> items;

    public HazardousGoodsView(Context context) {
        super(context);
        init();
    }

    public HazardousGoodsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HazardousGoodsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.hazardous_goods_view_layout, this);
    }

    public void bindData(boolean[] selections, MultiHazardousGoodsListener listener) {
        List<HazardousGood> goodsList = Arrays.asList(HazardousGood.values());
        List<HazardousGoodItem> itemsList = new ArrayList<>();
        for (int i = 0; i < selections.length; i++) {
            itemsList.add(new HazardousGoodItem(goodsList.get(i), selections[i], i));
        }
        items = itemsList;
        ListView list = findViewById(R.id.hazardousList);
        final ListAdapter adapter = new HazardousItemAdapter(getContext(), R.layout.hazardous_goods_item_layout, items, listener);
        list.setAdapter(adapter);
    }

}
