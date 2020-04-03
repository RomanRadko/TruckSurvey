package com.route4me.trucksurvey.model;

public class TruckWeight {

    private float weight;
    private float weightPerAxle;

    public float getWeight() {
        return weight;
    }

    public float getWeightPerAxle() {
        return weightPerAxle;
    }

    public static TruckWeight.Builder newBuilder() {
        return new TruckWeight().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Builder setWeight(float weight) {
            TruckWeight.this.weight = weight;

            return this;
        }

        public Builder setWeightPerAxle(float weightPerAxle) {
            TruckWeight.this.weightPerAxle = weightPerAxle;

            return this;
        }

        public TruckWeight build() {
            return TruckWeight.this;
        }
    }

    @Override
    public String toString() {
        return "TruckWeight{" +
                "weight=" + weight +
                ", weightPerAxle=" + weightPerAxle +
                '}';
    }
}
