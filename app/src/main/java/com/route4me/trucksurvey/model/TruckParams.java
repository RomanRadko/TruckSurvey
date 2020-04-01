package com.route4me.trucksurvey.model;

import androidx.annotation.NonNull;

import java.util.List;

public class TruckParams {

    private int trailersCount;
    private float height;
    private float width;
    private float length;
    private float weight;
    private float weightPerAxle;
    private float maxAllowedWeight;
    private List<HazardousGood> hazardousGoods;
    private boolean isTunnelsAllowed;
    private boolean isDifficultTurnsAllowed;


    private TruckParams() {
        // private constructor
    }

    public int getTrailersCount() {
        return trailersCount;
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }

    public float getWeight() {
        return weight;
    }

    public float getLength() {
        return length;
    }

    public float getWeightPerAxle() {
        return weightPerAxle;
    }

    public float getMaxAllowedWeight() {
        return maxAllowedWeight;
    }

    public List<HazardousGood> getHazardousGoods() {
        return hazardousGoods;
    }

    public boolean isTunnelsAllowed() {
        return isTunnelsAllowed;
    }

    public boolean isDifficultTurnsAllowed() {
        return isDifficultTurnsAllowed;
    }

    public static Builder newBuilder() {
        return new TruckParams().new Builder();
    }

    public class Builder {

        private Builder() {
            // private constructor
        }

        public Builder setTrailersCount(int trailersCount) {
            TruckParams.this.trailersCount = trailersCount;

            return this;
        }

        public Builder setHeight(float height) {
            TruckParams.this.height = height;

            return this;
        }

        public Builder setWidth(float width) {
            TruckParams.this.width = width;

            return this;
        }

        public Builder setLength(float length) {
            TruckParams.this.length = length;

            return this;
        }

        public Builder setWeight(float weight) {
            TruckParams.this.weight = weight;

            return this;
        }

        public Builder setWeightPerAxle(float weightPerAxle) {
            TruckParams.this.weightPerAxle = weightPerAxle;

            return this;
        }

        public Builder setMaxAllowedWeight(float maxAllowedWeight) {
            TruckParams.this.maxAllowedWeight = maxAllowedWeight;

            return this;
        }

        public Builder setHazardousGoods(List<HazardousGood> hazardousGoods) {
            TruckParams.this.hazardousGoods = hazardousGoods;

            return this;
        }

        public Builder setIsTunnelsAllowed(boolean isTunnelsAllowed) {
            TruckParams.this.isTunnelsAllowed = isTunnelsAllowed;

            return this;
        }

        public Builder setIsDifficultTurnsAllowed(boolean isDifficultTurnsAllowed) {
            TruckParams.this.isDifficultTurnsAllowed = isDifficultTurnsAllowed;

            return this;
        }

        public TruckParams build() {
            return TruckParams.this;
        }

    }

    @NonNull
    @Override
    public String toString() {
        String string = "Truck Params \n trailersCount:" + trailersCount + "\n" +
                "height: " + height + "\n" +
                "width: " + width + "\n" +
                "length: " + length + "\n" +
                "weightPerAxle: " + weightPerAxle + "\n" +
                "maxAllowedWeight: " + maxAllowedWeight + "\n" +
                "isTunnelsAllowed: " + isTunnelsAllowed + "\n" +
                "isDifficultTurnsAllowed: " + isDifficultTurnsAllowed + "\n" +
                "hazardousGoods: ";
        StringBuilder hazardousGoodStr = new StringBuilder();
        for (HazardousGood hazardousGood : hazardousGoods) {
            hazardousGoodStr.append(hazardousGood).append(" ");
        }
        return string + hazardousGoodStr;
    }
}