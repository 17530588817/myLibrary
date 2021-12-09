package com.heng.myLibrary.database.bean;

import android.graphics.Bitmap;

/**
 * @author : HengZhang
 * @date : 2021/11/21 16:21
 *
 * 主界面的辅助类
 */

public class DefaultGVItem {
    private String itemName;
    Bitmap ItemImg;
    private String userName;
    private String userAccount;

    public DefaultGVItem(String itemName, Bitmap itemImg, String userName, String userAccount) {
        this.itemName = itemName;
        ItemImg = itemImg;
        this.userName = userName;
        this.userAccount = userAccount;
    }

    public DefaultGVItem(String itemName, Bitmap itemImg) {
        this.itemName = itemName;
        ItemImg = itemImg;
    }

    public DefaultGVItem() {
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public String toString() {
        return "DefaultGVItem{" +
                "itemName='" + itemName + '\'' +
                ", ItemImg=" + ItemImg +
                ", userName='" + userName + '\'' +
                ", userAccount='" + userAccount + '\'' +
                '}';
    }
}
