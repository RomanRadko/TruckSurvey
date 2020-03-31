package com.route4me.trucksurvey.model;

public class TruckParams {

    private int trailersCount;
    private float height;
    private float width;
    private float weight;
    private float weightPerAxle;
    private float maxAllowedWeight;
    private HazardousGoods hazardousGoods;
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

    public float getWeightPerAxle() {
        return weightPerAxle;
    }

    public float getMaxAllowedWeight() {
        return maxAllowedWeight;
    }

    public HazardousGoods getHazardousGoods() {
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

        public Builder setHazardousGoods(HazardousGoods hazardousGoods) {
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

}