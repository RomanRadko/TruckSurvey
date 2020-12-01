package com.route4me.survey.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.List;

public class TruckParams implements Parcelable {

    private int trailersCount;
    private TruckSize size;
    private TruckWeight weight;
    private float maxAllowedWeight;
    private List<HazardousGood> hazardousGoods;
    private boolean isTunnelsAllowed;
    private boolean isDifficultTurnsAllowed;

    private TruckParams() {
    }

    protected TruckParams(Parcel in) {
        trailersCount = in.readInt();
        maxAllowedWeight = in.readFloat();
        isTunnelsAllowed = in.readByte() != 0;
        isDifficultTurnsAllowed = in.readByte() != 0;
    }

    public static final Creator<TruckParams> CREATOR = new Creator<TruckParams>() {
        @Override
        public TruckParams createFromParcel(Parcel in) {
            return new TruckParams(in);
        }

        @Override
        public TruckParams[] newArray(int size) {
            return new TruckParams[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(trailersCount);
        parcel.writeFloat(maxAllowedWeight);
        parcel.writeByte((byte) (isTunnelsAllowed ? 1 : 0));
        parcel.writeByte((byte) (isDifficultTurnsAllowed ? 1 : 0));
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

}