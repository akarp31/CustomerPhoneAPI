package com.phonenumber.restapi.model;

public class CustomerPhone {
    private long id;
    private long customerId;

    public CustomerPhone(long id, long customerId, String phoneNumber, boolean isActivated) {
        this.id = id;
        this.customerId = customerId;
        this.phoneNumber = phoneNumber;
        this.isActivated = isActivated;
    }

    public long getId() {
        return id;
    }

    private String phoneNumber;
    private boolean isActivated;

    public void setId(long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }

    public long getCustomerId() {
        return customerId;
    }

}
