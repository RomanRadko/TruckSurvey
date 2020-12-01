package com.route4me.survey.model;

public class HazardousGoodItem {

    private HazardousGood hazardousGood;
    private boolean isSelected;
    private int position;

    public HazardousGoodItem(HazardousGood hazardousGood, boolean isSelected, int position) {
        this.hazardousGood = hazardousGood;
        this.isSelected = isSelected;
        this.position = position;
    }

    public HazardousGood getHazardousGood() {
        return hazardousGood;
    }

    public void setHazardousGood(HazardousGood hazardousGood) {
        this.hazardousGood = hazardousGood;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

}
