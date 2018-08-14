package com.anishmistry.glocal;

import java.util.List;

public class AddSeller {
    public String sellerName;
    public String sellerPhone;
    public String sellerEmail;
    public String shopName;
    public String shopLocation;
    public String bidPrice;
    public List selectedCategory;
    public String selectedExtraOption;

    public AddSeller() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public AddSeller(String sellerName, String sellerPhone, String sellerEmail, String shopName, String shopLocation, String bidPrice,
                     List selectedCategory, String selectedExtraOption) {
        this.sellerName = sellerName;
        this.sellerPhone = sellerPhone;
        this.sellerEmail = sellerEmail;
        this.shopName = shopName;
        this.shopLocation = shopLocation;
        this.bidPrice = bidPrice;
        this.selectedCategory = selectedCategory;
        this.selectedExtraOption = selectedExtraOption;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerPhone() {
        return sellerPhone;
    }

    public void setSellerPhone(String sellerPhone) {
        this.sellerPhone = sellerPhone;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public void setSellerEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopLocation() {
        return shopLocation;
    }

    public void setShopLocation(String shopLocation) {
        this.shopLocation = shopLocation;
    }

    public String getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(String bidPrice) {
        this.bidPrice = bidPrice;
    }

    public List getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(List selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

    public String getSelectedExtraOption() {
        return selectedExtraOption;
    }

    public void setSelectedExtraOption(String selectedExtraOption) {
        this.selectedExtraOption = selectedExtraOption;
    }
}
