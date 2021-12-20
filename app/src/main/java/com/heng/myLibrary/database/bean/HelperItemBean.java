package com.heng.myLibrary.database.bean;

/**
 * @author : HengZhang
 * @date : 2021/12/20 19:58
 */

public class HelperItemBean {
    private Integer helperItemId;
    private String helperItemTitle;
    private String helperItemContent;

    public HelperItemBean() {
    }

    public HelperItemBean(Integer helperItemId, String helperItemTitle, String helperItemContent) {
        this.helperItemId = helperItemId;
        this.helperItemTitle = helperItemTitle;
        this.helperItemContent = helperItemContent;
    }

    public Integer getHelperItemId() {
        return helperItemId;
    }

    public void setHelperItemId(Integer helperItemId) {
        this.helperItemId = helperItemId;
    }

    public String getHelperItemTitle() {
        return helperItemTitle;
    }

    public void setHelperItemTitle(String helperItemTitle) {
        this.helperItemTitle = helperItemTitle;
    }

    public String getHelperItemContent() {
        return helperItemContent;
    }

    public void setHelperItemContent(String helperItemContent) {
        this.helperItemContent = helperItemContent;
    }

    @Override
    public String toString() {
        return "HelperItemBean{" +
                "helperItemId=" + helperItemId +
                ", helperItemTitle='" + helperItemTitle + '\'' +
                ", helperItemContent='" + helperItemContent + '\'' +
                '}';
    }
}
