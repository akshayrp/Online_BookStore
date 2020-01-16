package com.thoughtworks.onlinebookstore.service;

import org.springframework.stereotype.Service;


public enum CountryType {
    INDIA(50), OTHER_COUNTRY(200);

    public int shippingCharges;

    CountryType(int shippingCharges) {
        this.shippingCharges = shippingCharges;
    }

    CountryType() {
    }
}
