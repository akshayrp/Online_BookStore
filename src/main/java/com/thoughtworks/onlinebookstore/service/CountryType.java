package com.thoughtworks.onlinebookstore.service;

public enum
CountryType {
    INDIA(50), OTHER_COUNTRY(200);

    public int shippingCharges;

    CountryType(int shippingCharges) {
        this.shippingCharges = shippingCharges;
    }
}
