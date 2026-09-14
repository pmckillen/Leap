package com.training.portfolio;

/**
 * A single holding inside a client's portfolio - e.g. one bond, one block of
 * shares, one property. Deliberately just a plain class: no inheritance, no
 * subclasses per asset type. The assetType field is what tells two assets
 * apart, not the Java type.
 */
public class Asset {

    private final String assetId;
    private final String assetType;
    private final double value;

    public Asset(String assetId, String assetType, double value) {
        this.assetId = assetId;
        this.assetType = assetType;
        this.value = value;
    }

    public String getAssetId() {
        return assetId;
    }

    public String getAssetType() {
        return assetType;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Asset{assetId='" + assetId + "', assetType='" + assetType + "', value=" + value + "}";
    }
}
