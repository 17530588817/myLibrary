package com.heng.myLibrary.database.bean;

/**
 * @author : HengZhang
 * @date : 2021/11/21 14:30
 *
 * me页面下午listview的行
 */

public class MeItemBean {
    private String itemName;
    private String UserName;
    private String userAccount;

    public MeItemBean(String itemName, String userName, String userAccount) {
        this.itemName = itemName;
        UserName = userName;
        this.userAccount = userAccount;
    }

    public MeItemBean() {
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    @Override
    public String toString() {
        return "MeItemBean{" +
                "itemName='" + itemName + '\'' +
                ", UserName='" + UserName + '\'' +
                ", userAccount='" + userAccount + '\'' +
                '}';
    }
}
