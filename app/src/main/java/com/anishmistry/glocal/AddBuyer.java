package com.anishmistry.glocal;

import java.util.List;

public class AddBuyer {
    public String buyerName;
    public String buyerPhone;
    public String buyerEmail;
    public String buyerDOB;
    public String buyerGender;

    public AddBuyer() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public AddBuyer(String buyerName, String buyerPhone, String buyerEmail, String buyerDOB, String buyerGender) {
        this.buyerName = buyerName;
        this.buyerPhone = buyerPhone;
        this.buyerEmail = buyerEmail;
        this.buyerDOB = buyerDOB;
        this.buyerGender = buyerGender;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerPhone() {
        return buyerPhone;
    }

    public void setPhone(String buyerPhone) {
        this.buyerPhone = buyerPhone;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    public String getBuyerDOB() {
        return buyerDOB;
    }

    public void setBuyerDOB(String buyerDOB) {
        this.buyerDOB = buyerDOB;
    }

    public String getBuyerGender() {
        return buyerGender;
    }

    public void setBuyerGender(String buyerGender) {
        this.buyerGender = buyerGender;
    }
}
