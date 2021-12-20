package com.heng.myLibrary.database.bean;

/**
 * @author : HengZhang
 * @date : 2021/11/28 19:54
 * <p>
 * 查询用户的实体类
 */

public class SelectUserItem {
    private String userName;
    private String userSex;
    private String userPhone;
    private Integer userCode;

    public Integer getUserCode() {
        return userCode;
    }

    public void setUserCode(Integer userCode) {
        this.userCode = userCode;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    private String userEmail;
    private Integer userLevel;

    public SelectUserItem(String userName, String userSex, String userPhone, String userEmail) {
        this.userName = userName;
        this.userSex = userSex;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
    }

    public SelectUserItem() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public String toString() {
        return "SelectUserItem{" +
                "userName='" + userName + '\'' +
                ", userSex='" + userSex + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userCode='" + userCode + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userLevel=" + userLevel +
                '}';
    }
}
