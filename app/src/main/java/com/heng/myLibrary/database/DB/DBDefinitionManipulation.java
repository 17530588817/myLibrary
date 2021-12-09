package com.heng.myLibrary.database.DB;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.heng.myLibrary.database.entity.Book;
import com.heng.myLibrary.database.entity.User;

/**
 * @author : HengZhang
 * @date : 2021/11/24 15:29
 * <p>
 * 数据库操作类
 */

public class DBDefinitionManipulation {
    private static final String TAG = "DBDefinitionManipulation";
    private final DBTools DBTools = new DBTools();
    private static final String DB_NAME = "mylibrary.db";
    private static final String USER_TABLE = "t_user";
    private static final String BOOK_TABLE = "t_book";
    private static final int DB_VERSION = 1;

    //user表的列名/////////////////////////////////////////////////////////////////
    public static final String USER_ID = "_id";
    public static final String USER_NAME = "username";
    public static final String USER_ACCOUNT = "account";
    public static final String USER_PASSWORD = "password";
    public static final String USER_PHONE = "phone";
    public static final String USER_EMAIL = "email";
    public static final String USER_SEX = "sex";
    public static final String USER_CODE = "usercode";
    public static final String USER_BOOK = "userbookname";
    public static final String USER_LEVEL = "userlevel";
    ////////////////////////////////////////////////////////////////////
    // book表的列名  ///////////////////////////////////////////////////////////////
    public static final String BOOK_ID = "_id";
    public static final String BOOK_NAME = "bookname";
    public static final String BOOK_NUMBER = "booknumber";
    public static final String BOOK_AUTHOR = "bookauthor";
    public static final String BOOK_PRICE = "bookprice";
    private static final String BOOK_STATUS = "status";

    private SQLiteDatabase db;
    private final Context context;
    private DBOpenHelper dbOpenHelper;

    // 构造器
    public DBDefinitionManipulation(Context _context) {
        context = _context;
    }

    /******************************************************************************/
    // 添加用户
    public long addUser(User user) {
        ContentValues newValues = new ContentValues();
        newValues.put(USER_NAME, user.getUserName());
        newValues.put(USER_ACCOUNT, user.getAccount());
        newValues.put(USER_PASSWORD, user.getPassword());
        newValues.put(USER_PHONE, user.getPhone());
        newValues.put(USER_EMAIL, user.getEmail());
        newValues.put(USER_SEX, user.getSex());
        newValues.put(USER_CODE, user.getUserCode());
        newValues.put(USER_BOOK, user.getBookName());
        newValues.put(USER_LEVEL, user.getUserLevel());

        return db.insert(USER_TABLE, null, newValues);
    }

    //添加图书
    public long addBook(Book book) {
        ContentValues newValues = new ContentValues();
        newValues.put(BOOK_NAME, book.getBookName());
        newValues.put(BOOK_NUMBER, book.getBookNumber());
        newValues.put(BOOK_AUTHOR, book.getAuthor());
        newValues.put(BOOK_PRICE, book.getPrice());
        newValues.put(BOOK_STATUS, book.getStatus());

        return db.insert(BOOK_TABLE, null, newValues);
    }

    /**
     * == 借书 ==
     * 1.书的状态
     * 2.用户和书绑定
     * 3.积分
     */
    @SuppressLint({"Range", "LongLogTag"})
    public boolean lendBook(String userName, String userBook) {
        //检验此书是否借出
        Cursor cursor = db.query(BOOK_TABLE, new String[]{BOOK_STATUS}, BOOK_NAME + "=?",
                new String[]{userBook}, null, null, null);
        if (!DBTools.checkCursor(cursor) || cursor.getInt(cursor.getColumnIndex(BOOK_STATUS)) == 1) {
            return false;
        }

        //获取用户的积分
        cursor = db.query(USER_TABLE, new String[]{USER_CODE}, USER_NAME + "=?",
                new String[]{userName}, null, null, null);
        if (!DBTools.checkCursor(cursor)) {
            return false;
        }
        ContentValues values = new ContentValues();
        values.put(USER_BOOK, userBook);
        values.put(USER_CODE, cursor.getInt(cursor.getColumnIndex(USER_CODE) + 1));
        db.update(USER_TABLE, values, USER_NAME + "=?", new String[]{userName});

        values.remove(USER_BOOK);
        values.remove(USER_CODE);
        values.put(BOOK_STATUS, 1);
        db.update(BOOK_TABLE, values, BOOK_NAME + "=?", new String[]{userBook});
        cursor.close();
        return true;
    }

    /**
     * 还书
     */
    @SuppressLint("Range")
    public boolean backBook(String userName, String userBook) {
        //检验此书是否借出
        Cursor cursor = db.query(BOOK_TABLE, new String[]{BOOK_STATUS}, BOOK_NAME + "=?",
                new String[]{userBook}, null, null, null);
        if (!DBTools.checkCursor(cursor) || cursor.getInt(cursor.getColumnIndex(BOOK_STATUS)) == 0) {
            return false;
        }

        ContentValues values = new ContentValues();
        values.put(USER_BOOK, "");
        db.update(USER_TABLE, values, USER_NAME + "=?", new String[]{userName});
        values.remove(USER_BOOK);
        values.put(BOOK_STATUS, 0);
        db.update(BOOK_TABLE, values, BOOK_NAME + "=?", new String[]{userBook});

        return true;
    }

    //todo:查询指定user
    public User queryUser(String account) {
        Cursor cursor = db.query(USER_TABLE,
                new String[]{USER_NAME, USER_ACCOUNT, USER_PASSWORD, USER_PHONE, USER_EMAIL, USER_SEX, USER_CODE, USER_BOOK},
                USER_ACCOUNT + "=?", new String[]{account}, null, null, null);

        return DBTools.convertToUser(cursor, USER_NAME, USER_ACCOUNT, USER_PASSWORD, USER_PHONE, USER_EMAIL, USER_SEX, USER_CODE, USER_BOOK);
    }

    //todo:查询指定book
    public Book queryBook(String bookName) {
        Cursor cursor = db.query(BOOK_TABLE, new String[]{BOOK_NAME, BOOK_NUMBER, BOOK_AUTHOR, BOOK_PRICE, BOOK_STATUS},
                BOOK_NAME + "=?", new String[]{bookName}, null, null, null);

        return DBTools.convertToBook(cursor, BOOK_NAME, BOOK_NUMBER, BOOK_AUTHOR, BOOK_PRICE, BOOK_STATUS);
    }

    //todo:确认用户合法性
    public Integer checkUser(String account, String password, Integer level) {
        Cursor cursor = db.query(USER_TABLE, new String[]{USER_ACCOUNT, USER_PASSWORD, USER_LEVEL},
                USER_ACCOUNT + "=?", new String[]{account}, null, null, null);

        return DBTools.checkUserPwd(cursor, USER_PASSWORD, USER_LEVEL, password, level);
    }

    //todo： 查询所有用户
    public User[] queryAllUsers() {
        Cursor cursor = db.query(USER_TABLE,
                new String[]{USER_NAME, USER_ACCOUNT, USER_PASSWORD, USER_PHONE, USER_EMAIL, USER_SEX, USER_CODE, USER_BOOK, USER_LEVEL},
                null, null, null, null, null);

        return DBTools.convertToUsers(cursor, USER_NAME, USER_ACCOUNT, USER_PASSWORD, USER_PHONE, USER_EMAIL, USER_SEX, USER_CODE, USER_BOOK, USER_LEVEL);

    }

    //todo: 查询所有图书
    public Book[] queryAllBooks() {
        Cursor cursor = db.query(BOOK_TABLE, new String[]{BOOK_NAME, BOOK_NUMBER, BOOK_AUTHOR, BOOK_PRICE, BOOK_STATUS},
                null, null, null, null, null);
        return DBTools.convertToBooks(cursor, BOOK_NAME, BOOK_AUTHOR, BOOK_NUMBER, BOOK_PRICE, BOOK_STATUS);
    }

    //todo:删除图书
    public boolean deleteBook(String bookName) {
        //todo:检验此书是否存在
        @SuppressLint("Recycle")
        Cursor cursor = db.query(BOOK_TABLE, new String[]{BOOK_STATUS},
                BOOK_NAME + "=?",
                new String[]{bookName}, null, null, null);
        if (cursor == null) {
            return false;
        }
        db.delete(BOOK_TABLE, BOOK_NAME + "=?", new String[]{bookName});
        return true;
    }

    // todo: 删除用户
    public boolean deleteUser(String userName) {
        Cursor cursor = db.query(USER_TABLE, new String[]{USER_LEVEL}, USER_NAME + "=?", new String[]{userName},
                null, null, null);
        if (cursor == null) {
            return false;
        }
        db.delete(USER_TABLE, USER_NAME + "=?", new String[]{userName});
        return true;
    }

    /**************************************************************************************************************************/

    /**
     * Open the database
     * 打开数据库
     */
    public void open() throws SQLiteException {
        dbOpenHelper = new DBOpenHelper(context, DB_NAME, null, DB_VERSION);
        try {
            db = dbOpenHelper.getWritableDatabase();
        } catch (SQLiteException ex) {
            db = dbOpenHelper.getReadableDatabase();
        }
    }


    /**
     * Close the database
     * 关闭数据库
     */
    public void close() {
        if (db != null) {
            db.close();
            db = null;
        }
    }

    /**
     * 静态Helper类，用于建立、更新和打开数据库
     */
    private static class DBOpenHelper extends SQLiteOpenHelper {

        public DBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

//        private static final String DB_CREATE = "create table " + DB_TABLE + " (" + USER_ID +
//                " integer primary key autoincrement, " +
//                USER_NAME + " text not null, " + KEY_SEX + " integer," + KEY_TOTALCREDITS + " float);";

        //创建user表
        private static final String CREATE_USER_TABLE = "create table " + USER_TABLE + " ( "
                + USER_ID + " integer primary key autoincrement, "
                + USER_NAME + " text not null, " + USER_ACCOUNT + " text not null, "
                + USER_PASSWORD + " text not null, "
                + USER_PHONE + " text not null, " + USER_EMAIL + " text not null, "
                + USER_SEX + " text not null, "
                + USER_CODE + " int not null, " + USER_BOOK + " text, "
                + USER_LEVEL + " int not null) ";

        private static final String CREATE_BOOK_TABLE = "create table " + BOOK_TABLE + " ( "
                + BOOK_ID + " integer primary key autoincrement, "
                + BOOK_NAME + " text not null, " + BOOK_NUMBER + " int not null, "
                + BOOK_AUTHOR + " text not null, " + BOOK_PRICE + " real, "
                + BOOK_STATUS + " int ); ";

        @Override
        public void onCreate(SQLiteDatabase _db) {
//            _db.execSQL(DB_CREATE);

            _db.execSQL(CREATE_USER_TABLE);
            _db.execSQL(CREATE_BOOK_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion) {
            _db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
            _db.execSQL("DROP TABLE IF EXISTS " + BOOK_TABLE);
            onCreate(_db);
        }
    }
}


