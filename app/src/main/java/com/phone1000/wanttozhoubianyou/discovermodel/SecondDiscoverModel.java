package com.phone1000.wanttozhoubianyou.discovermodel;

/**
 * Created by Administrator on 2016/11/28.
 */
public class SecondDiscoverModel {
    private String cityName;
    private String mImageUrl;
    private String productName;
    private String productTitleContent;
    private int saledCount;
    private int price;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductTitleContent() {
        return productTitleContent;
    }

    public void setProductTitleContent(String productTitleContent) {
        this.productTitleContent = productTitleContent;
    }

    public int getSaledCount() {
        return saledCount;
    }

    public void setSaledCount(int saledCount) {
        this.saledCount = saledCount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
