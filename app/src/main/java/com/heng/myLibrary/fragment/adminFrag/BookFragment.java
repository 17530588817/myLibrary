package com.heng.myLibrary.fragment.adminFrag;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.heng.myLibrary.R;
import com.heng.myLibrary.database.DB.DBDefinitionManipulation;
import com.heng.myLibrary.database.entity.Book;
import com.heng.myLibrary.util.MyLogging;

/**
 * @author : HengZhang
 * @date : 2021/11/28 15:52
 * <p>
 * 管理员界面的图书操作界面
 */

public class BookFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "BookFragment";
    EditText bookName, bookNumber, bookAuthor, bookPrice;
    Button addBookYesBtn, delBookYesBtn;
    DBDefinitionManipulation db;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyLogging.myLog(TAG,"onCreate()");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_2_book, container, false);

        MyLogging.myLog(TAG,"onCreateView()");

        initView(view);
        return view;
    }

    private void initView(View view) {
        MyLogging.myLog(TAG,"initView()");

        bookName = view.findViewById(R.id.admin_bookname);
        bookNumber = view.findViewById(R.id.admin_booknumber);
        bookAuthor = view.findViewById(R.id.admin_bookauthor);
        bookPrice = view.findViewById(R.id.admin_bookprice);
        addBookYesBtn = view.findViewById(R.id.add_book_yes);
        delBookYesBtn = view.findViewById(R.id.delete_book_yes);

        addBookYesBtn.setOnClickListener(this);
        delBookYesBtn.setOnClickListener(this);
    }

    private void clearEdit() {
        bookName.setText("");
        bookNumber.setText("");
        bookAuthor.setText("");
        bookPrice.setText("");
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_book_yes:
                if (adminAddBook()) {
                    Toast.makeText(getContext(), "添加图书成功!", Toast.LENGTH_SHORT).show();
                    clearEdit();
                } else {
                    Toast.makeText(getContext(), "添加图书失败!", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.delete_book_yes:
                if (adminDeleteBook()) {
                    Toast.makeText(getContext(), "删除图书成功!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "删除图书失败!", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private boolean adminAddBook() {
        MyLogging.myLog(TAG,"adminAddBook()");
        if (TextUtils.isEmpty(bookName.getText()) || TextUtils.isEmpty(bookNumber.getText())
                || TextUtils.isEmpty(bookAuthor.getText()) || TextUtils.isEmpty(bookPrice.getText())) {
            Toast.makeText(getContext(), "数据不能为空！", Toast.LENGTH_SHORT).show();
            return false;
        }

        db = new DBDefinitionManipulation(getContext());
        db.open();
        Book book = new Book();
        book.setBookName(bookName.getText().toString());
        book.setBookNumber(Integer.valueOf(bookNumber.getText().toString()));
        book.setAuthor(bookAuthor.getText().toString());
        book.setPrice(Double.valueOf(bookPrice.getText().toString()));
        //状态：0未借出1已借出
        book.setStatus(0);
        long flag = db.addBook(book);
        db.close();
        return flag != -1;
    }

    private boolean adminDeleteBook() {
        MyLogging.myLog(TAG,"adminDeleteBook()");
        if (TextUtils.isEmpty(bookName.getText())) {
            return false;
        }
        db = new DBDefinitionManipulation(getContext());
        db.open();
        boolean deleteBookFlag = db.deleteBook(bookName.getText().toString());
        db.close();
        return deleteBookFlag;
    }

}
