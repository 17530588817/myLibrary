package com.heng.myLibrary.database.entity;

import java.io.Serializable;

/**
 * @author : HengZhang
 * @date : 2021/11/24 14:58
 *
 * 图书实体类
 */

public class Book implements Serializable {
    private Integer bookId;
    private String bookName;
    private Integer bookNumber;
    private String author;
    private Double price;
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Book() {
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Integer getBookNumber() {
        return bookNumber;
    }

    public void setBookNumber(Integer bookNumber) {
        this.bookNumber = bookNumber;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", bookNumber=" + bookNumber +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", status=" + status +
                '}';
    }
}
