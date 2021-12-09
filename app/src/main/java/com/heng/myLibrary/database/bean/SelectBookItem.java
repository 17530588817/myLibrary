package com.heng.myLibrary.database.bean;

/**
 * @author : HengZhang
 * @date : 2021/11/28 19:08
 *
 * 查询图书的实体类
 */

public class SelectBookItem {
    private String bookName;
    private String bookAuthor;
    private Integer bookStatus;
    private Integer bookNumber;

    public SelectBookItem(String bookName, String bookAuthor, Integer bookStatus, Integer bookNumber) {
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookStatus = bookStatus;
        this.bookNumber = bookNumber;
    }

    public SelectBookItem() {
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public Integer getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(Integer bookStatus) {
        this.bookStatus = bookStatus;
    }

    public Integer getBookNumber() {
        return bookNumber;
    }

    public void setBookNumber(Integer bookNumber) {
        this.bookNumber = bookNumber;
    }

    @Override
    public String toString() {
        return "SelectBookItem{" +
                "bookName='" + bookName + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", bookStatus='" + bookStatus + '\'' +
                ", bookNumber=" + bookNumber +
                '}';
    }
}
