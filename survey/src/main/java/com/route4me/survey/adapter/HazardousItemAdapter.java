package com.route4me.survey.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.route4me.survey.R;
import com.route4me.survey.model.HazardousGood;
import com.route4me.survey.model.HazardousGoodItem;
import com.route4me.survey.model.MultiHazardousGoodsListener;

import java.util.List;

public class HazardousItemAdapter extends ArrayAdapter<HazardousGoodItem> {

    private int resourceLayout;
    private Context context;
    private boolean [] selections = new boolean[HazardousGood.values().length];
    private MultiHazardousGoodsListener listener;

    public HazardousItemAdapter(Context context, int resource, List<HazardousGoodItem> items, MultiHazardousGoodsListener listener) {
        super(context, resource, items);
        this.resourceLayout = resource;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater layoutInflater;
            layoutInflater = LayoutInflater.from(context);
            view = layoutInflater.inflate(resourceLayout, null);
        }

        final HazardousGoodItem item = getItem(position);

        if (item != null) {
            TextView title = view.findViewById(R.id.hazardousTitle);
            final CheckBox checkBox = view.findViewById(R.id.hazardousCheckbox);

            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selections[position] = checkBox.isChecked();
                    listener.onItemsSelected(selections);
                }
            });

            if (title != null) {
                title.setText(item.getHazardousGood().name().replace("_", " "));
            }

            if (checkBox != null) {
                checkBox.setChecked(item.isSelected());
                selections[position] = item.isSelected();
            }
        }

        return view;
    }

}