package com.heng.myLibrary.database.bean;

/**
 * @author : HengZhang
 * @date : 2021/12/21 21:29
 *
 * 积分活动的bean
 */

public class CodePartyItemBean {
    private String codePartyItemBeanCategory;
    private String codePartyItemBeanTitle;
    private String codePartyItemBeanContent;

    public CodePartyItemBean(String codePartyItemBeanCategory, String codePartyItemBeanTitle, String codePartyItemBeanContent) {
        this.codePartyItemBeanCategory = codePartyItemBeanCategory;
        this.codePartyItemBeanTitle = codePartyItemBeanTitle;
        this.codePartyItemBeanContent = codePartyItemBeanContent;
    }

    public String getCodePartyItemBeanCategory() {
        return codePartyItemBeanCategory;
    }

    public void setCodePartyItemBeanCategory(String codePartyItemBeanCategory) {
        this.codePartyItemBeanCategory = codePartyItemBeanCategory;
    }

    public String getCodePartyItemBeanTitle() {
        return codePartyItemBeanTitle;
    }

    public void setCodePartyItemBeanTitle(String codePartyItemBeanTitle) {
        this.codePartyItemBeanTitle = codePartyItemBeanTitle;
    }

    public String getCodePartyItemBeanContent() {
        return codePartyItemBeanContent;
    }

    public void setCodePartyItemBeanContent(String codePartyItemBeanContent) {
        this.codePartyItemBeanContent = codePartyItemBeanContent;
    }

    @Override
    public String toString() {
        return "CodePartyItemBean{" +
                "codePartyItemBeanCategory='" + codePartyItemBeanCategory + '\'' +
                ", codePartyItemBeanTitle='" + codePartyItemBeanTitle + '\'' +
                ", codePartyItemBeanContent='" + codePartyItemBeanContent + '\'' +
                '}';
    }
}
