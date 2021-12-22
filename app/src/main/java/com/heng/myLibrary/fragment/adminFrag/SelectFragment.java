package com.heng.myLibrary.fragment.adminFrag;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.heng.myLibrary.R;
import com.heng.myLibrary.adapter.SelectBookAdapter;
import com.heng.myLibrary.adapter.SelectUserAdapter;
import com.heng.myLibrary.database.DB.DBDefinitionManipulation;
import com.heng.myLibrary.database.bean.SelectBookItem;
import com.heng.myLibrary.database.bean.SelectUserItem;
import com.heng.myLibrary.database.entity.Book;
import com.heng.myLibrary.database.entity.User;
import com.heng.myLibrary.util.MyLogging;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : HengZhang
 * @date : 2021/11/28 16:28
 * <p>
 * 管理员界面的查询操作界面
 */

public class SelectFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "SelectFragment";

    ListView listView;
    List<SelectBookItem> mBookDatas;
    List<SelectUserItem> mUserDatas;
    Button bookBtn, userBtn;
    DBDefinitionManipulation db;
    SelectBookAdapter bookAdapter;
    SelectUserAdapter userAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBookDatas = new ArrayList<>();
        mUserDatas = new ArrayList<>();

        MyLogging.myLog(TAG, "onCreate()");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_2_select, container, false);

        MyLogging.myLog(TAG, "onCreateView()");

        initView(view);
        showUsers();
        showBooks();
        bookAdapter.clear();
        userAdapter.clear();

        return view;
    }

    private void initView(View view) {
        MyLogging.myLog(TAG, "initView()");

        bookBtn = view.findViewById(R.id.select_book_btn);
        userBtn = view.findViewById(R.id.select_user_btn);
        listView = view.findViewById(R.id.admin_select_lv);

        bookBtn.setOnClickListener(this);
        userBtn.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.select_book_btn:
                bookAdapter.clear();
                userAdapter.clear();
                showBooks();
                break;
            case R.id.select_user_btn:
                bookAdapter.clear();
                userAdapter.clear();
                showUsers();
                break;
        }
    }


    /**
     * todo: 显示所有图书
     * 1.数据库获取 图书 数据
     * 2.list[]填充数据
     * 3.适配器装载数据
     * 4.show
     */
    private void showBooks() {
        MyLogging.myLog(TAG, "showBooks()");
        addBooksToList(getBookInfo());
    }

    private Book[] getBookInfo() {
        db = new DBDefinitionManipulation(getContext());
        db.open();
        Book[] books = db.queryAllBooks();
        db.close();
        return books;
    }

    private void addBooksToList(Book[] books) {
        for (Book book : books) {
            SelectBookItem bookItem = new SelectBookItem();
            bookItem.setBookName(book.getBookName());
            bookItem.setBookAuthor(book.getAuthor());
            bookItem.setBookStatus(book.getStatus());
            bookItem.setBookNumber(book.getBookNumber());
            mBookDatas.add(bookItem);
        }

        // 设置适配器
        bookAdapter = new SelectBookAdapter(getContext(), mBookDatas);
        listView.setAdapter(bookAdapter);
    }

    /**
     * todo:显示所有用户
     * 1.数据库获取 users 数据
     * 2.list[]填充数据
     * 3.适配器装载数据
     * 4.show
     */
    private void showUsers() {
        MyLogging.myLog(TAG, "showUsers()");
        addUsersToList(getUsersInfo());
    }

    private User[] getUsersInfo() {
        db = new DBDefinitionManipulation(getContext());
        db.open();
        User[] users = db.queryAllUsers();
        db.close();
        return users;
    }

    private void addUsersToList(User[] users) {
        for (User user : users) {
            SelectUserItem userItem = new SelectUserItem();
            userItem.setUserName(user.getUserName());
            userItem.setUserSex(user.getSex());
            userItem.setUserPhone(user.getPhone());
            userItem.setUserEmail(user.getEmail());
            userItem.setUserLevel(user.getUserLevel());
            userItem.setUserCode(user.getUserCode());
            mUserDatas.add(userItem);
        }
        // 设置适配器
        userAdapter = new SelectUserAdapter(getContext(), mUserDatas);
        listView.setAdapter(userAdapter);
    }

}
