package com.route4me.trucksurvey.model;

import androidx.annotation.NonNull;

import java.util.List;

public class TruckParams {

    private int trailersCount;
    private TruckSize size;
    private TruckWeight weight;
    private float maxAllowedWeight;
    private List<HazardousGood> hazardousGoods;
    private boolean isTunnelsAllowed;
    private boolean isDifficultTurnsAllowed;

    private TruckParams() {
    }

    public int getTrailersCount() {
        return trailersCount;
    }

    public TruckSize getSize() {
        return size;
    }

    public TruckWeight getTruckWeight() {
        return weight;
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
        }

        public Builder setTrailersCount(int trailersCount) {
            TruckParams.this.trailersCount = trailersCount;

            return this;
        }

        public Builder setSize(TruckSize size) {
            TruckParams.this.size = size;

            return this;
        }

        public Builder setWeight(TruckWeight weight) {
            TruckParams.this.weight = weight;

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
                "size: " + size + "\n" +
                "weight: " + weight + "\n" +
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