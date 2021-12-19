package com.heng.myLibrary.database.DB;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.util.Log;

import com.heng.myLibrary.database.entity.Book;
import com.heng.myLibrary.database.entity.User;

/**
 * @author : HengZhang
 * @date : 2021/11/24 16:47
 * <p>
 * 数据库的Cursor转换成实体类
 */

public class DBTools {

    private static final String TAG = "DBTools";

    //检验游标 cursor 的可用性
    public boolean checkCursor(Cursor cursor) {
        int resultCounts = cursor.getCount();
        if (resultCounts == 0 || !cursor.moveToFirst()) {
            return false;
        }
        return true;
    }

    //Cursor数据转换Users
    @SuppressLint("Range")
    public User convertToUser(Cursor cursor, String userName, String userAccount, String userPassword, String userPhone,
                              String userEmail, String userSex, String userCode, String userBook) {
        if (!checkCursor(cursor)) {
            return null;
        }

        User user = new User();
        user.setUserId(cursor.getInt(0));
        user.setAccount(cursor.getString(cursor.getColumnIndex(userAccount)));
        user.setUserName(cursor.getString(cursor.getColumnIndex(userName)));
        user.setPassword(cursor.getString(cursor.getColumnIndex(userPassword)));
        user.setPhone(cursor.getString(cursor.getColumnIndex(userPhone)));
        user.setEmail(cursor.getString(cursor.getColumnIndex(userEmail)));
        user.setSex(cursor.getString(cursor.getColumnIndex(userSex)));
        user.setUserCode(cursor.getInt(cursor.getColumnIndex(userCode)));
        user.setBookName(cursor.getString(cursor.getColumnIndex(userBook)));

        return user;
    }

    //Cursor数据转换Book
    @SuppressLint("Range")
    public Book convertToBook(Cursor cursor, String bookName, String bookNumber, String bookAuthor,
                              String bookPrice, String bookStatus) {
        if (!checkCursor(cursor)) {
            return null;
        }
        //  users[i].ID = cursor.getInt(0);
        //  users[i].Name = cursor.getString(cursor.getColumnIndex(KEY_NAME));
        //  users[i].Sex = cursor.getString(cursor.getColumnIndex(KEY_SEX));
        //  users[i].Totalcredits = cursor.getInt(cursor.getColumnIndex(KEY_TOTALCREDITS));

        Book book = new Book();
        book.setBookId(cursor.getInt(0));
        book.setBookName(cursor.getString(cursor.getColumnIndex(bookName)));
        book.setAuthor(cursor.getString(cursor.getColumnIndex(bookAuthor)));
        book.setBookNumber(cursor.getInt(cursor.getColumnIndex(bookNumber)));
        book.setPrice(cursor.getDouble(cursor.getColumnIndex(bookPrice)));
        book.setStatus(cursor.getInt(cursor.getColumnIndex(bookStatus)));

        return book;
    }

    //核查user合法性
    @SuppressLint("Range")
    public Integer checkUserPwd(Cursor cursor, String userPassword, String userLevel,
                                String password, Integer level) {
        if (!checkCursor(cursor)) {
            return 0;
        }

        Log.d(TAG, "checkUserPwd: level = " + cursor.getInt(cursor.getColumnIndex(userLevel)));

        if (cursor.getString(cursor.getColumnIndex(userPassword)).equals(password)
                && cursor.getInt(cursor.getColumnIndex(userLevel)) <= level) {
            return level;
        }
        return 0;
    }


    //Cursor数据转换Users
    @SuppressLint("Range")
    public User[] convertToUsers(Cursor cursor, String userName, String userAccount, String userPassword, String userPhone,
                                 String userEmail, String userSex, String userCode, String userBook,String userLevel) {
        int resultCounts = cursor.getCount();
        if (resultCounts == 0 || !cursor.moveToFirst()) {
            return null;
        }
        User[] users = new User[resultCounts];
        if (cursor.moveToFirst()) {
            for (int i = 0; i < resultCounts; i++) {
//            users[i].ID = cursor.getInt(0);
//            users[i].Name = cursor.getString(cursor.getColumnIndex(KEY_NAME));
//            users[i].Sex = cursor.getString(cursor.getColumnIndex(KEY_SEX));
//            users[i].Totalcredits = cursor.getInt(cursor.getColumnIndex(KEY_TOTALCREDITS));

                users[i] = new User();
                users[i].setUserId(cursor.getInt(0));
                users[i].setUserName(cursor.getString(cursor.getColumnIndex(userName)));
                users[i].setAccount(cursor.getString(cursor.getColumnIndex(userAccount)));
                users[i].setPassword(cursor.getString(cursor.getColumnIndex(userPassword)));
                users[i].setPhone(cursor.getString(cursor.getColumnIndex(userPhone)));
                users[i].setEmail(cursor.getString(cursor.getColumnIndex(userEmail)));
                users[i].setSex(cursor.getString(cursor.getColumnIndex(userSex)));
                users[i].setUserCode(cursor.getInt(cursor.getColumnIndex(userCode)));
                users[i].setBookName(cursor.getString(cursor.getColumnIndex(userBook)));
                users[i].setUserLevel(cursor.getInt(cursor.getColumnIndex(userLevel)));

                cursor.moveToNext();
            }
        }
        return users;
    }

    //Cursor数据转换Books
    @SuppressLint("Range")
    public Book[] convertToBooks(Cursor cursor, String bookName, String bookAuthor, String bookNumber,
                                 String bookPrice, String bookStatus) {
        int resultCounts = cursor.getCount();
        if (resultCounts == 0 || !cursor.moveToFirst()) {
            return null;
        }
        Book[] books = new Book[resultCounts];
        if (cursor.moveToFirst()) {
            for (int i = 0; i < resultCounts; i++) {
//            users[i].ID = cursor.getInt(0);
//            users[i].Name = cursor.getString(cursor.getColumnIndex(KEY_NAME));
//            users[i].Sex = cursor.getString(cursor.getColumnIndex(KEY_SEX));
//            users[i].Totalcredits = cursor.getInt(cursor.getColumnIndex(KEY_TOTALCREDITS));

                books[i] = new Book();
                books[i].setBookId(cursor.getInt(0));
                books[i].setBookName(cursor.getString(cursor.getColumnIndex(bookName)));
                books[i].setAuthor(cursor.getString(cursor.getColumnIndex(bookAuthor)));
                books[i].setBookNumber(cursor.getInt(cursor.getColumnIndex(bookNumber)));
                books[i].setPrice(cursor.getDouble(cursor.getColumnIndex(bookPrice)));
                books[i].setStatus(cursor.getInt(cursor.getColumnIndex(bookStatus)));

                cursor.moveToNext();
            }
        }

        return books;
    }
}
