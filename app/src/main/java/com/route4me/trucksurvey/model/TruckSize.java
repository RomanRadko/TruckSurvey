package com.route4me.trucksurvey.model;

public class TruckSize {

    private float height;
    private float width;
    private float length;

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }

    public float getLength() {
        return length;
    }

    public static TruckSize.Builder newBuilder() {
        return new TruckSize().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Builder setHeight(float height) {
            TruckSize.this.height = height;

            return this;
        }

        public Builder setWidth(float width) {
            TruckSize.this.width = width;

            return this;
        }

        public Builder setLength(float length) {
            TruckSize.this.length = length;

            return this;
        }

        public TruckSize build() {
            return TruckSize.this;
        }
    }
}