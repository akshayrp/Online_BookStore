package com.thoughtworks.onlinebookstore.service;

public enum CountryType implements ICountryType{
    INDIA(50), OTHER_COUNTRY(200);

    private final int shippingCharges;

    CountryType(int shippingCharges) {
        this.shippingCharges = shippingCharges;
    }

    @Override
    public int shippingAmount() {
        return this.shippingCharges;
    }
}
