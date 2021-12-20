package com.heng.myLibrary.fragment.operationFrag;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.heng.myLibrary.R;
import com.heng.myLibrary.fragment.adminFrag.adminSelectAdapter.SelectBookAdapter;
import com.heng.myLibrary.database.DB.DBDefinitionManipulation;
import com.heng.myLibrary.database.bean.SelectBookItem;
import com.heng.myLibrary.database.entity.Book;
import com.heng.myLibrary.database.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : HengZhang
 * @date : 2021/11/23 18:38
 * <p>
 * 借还界面
 */
public class OperationFragment extends Fragment implements View.OnClickListener {

    User user;
    TextView nameEdit, codeEdit;
    ViewPager operationVp;
    ListView booksLv;
    Button lendBtn, backBtn, showAllBook;
    Fragment lendFrag, backFrag;
    operationVPAdapter operationVpAdapter;
    List<Fragment> VPList;
    DBDefinitionManipulation db;
    List<SelectBookItem> mBookDatas;
    SelectBookAdapter bookAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_1_operation, container, false);

        //从MainActivity获取数据
        Bundle bundle1 = getArguments();
        assert bundle1 != null;
        user = (User) bundle1.getSerializable("userInfo");

        initView(view);
        initFrag();
        setVPSelectListener();
        showBooks();

        return view;
    }

    private void setVPSelectListener() {
        operationVp.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                setButtonStyle(position);
            }
        });
    }

    private void initView(View view) {
        lendBtn = view.findViewById(R.id.lend_frag_btn);
        backBtn = view.findViewById(R.id.back_frag_btn);
        operationVp = view.findViewById(R.id.operation_vp);
        showAllBook = view.findViewById(R.id.operation_showallbook);
        booksLv = view.findViewById(R.id.operation_allbook);

        nameEdit = view.findViewById(R.id.operation_username);
        codeEdit = view.findViewById(R.id.operation_usercode);
        nameEdit.setText(user.getUserName());
        codeEdit.setText(String.valueOf(user.getUserCode()));

        lendBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);
        showAllBook.setOnClickListener(this);
    }

    private void initFrag() {
        VPList = new ArrayList<>();

        //添加对象
        lendFrag = new LendFragment();
        backFrag = new BackFragment();

        VPList.add(lendFrag);
        VPList.add(backFrag);
        assert this.getFragmentManager() != null;
        operationVpAdapter = new operationVPAdapter(this.getFragmentManager(), VPList);
        operationVp.setAdapter(operationVpAdapter);
    }

    @SuppressLint("NonConstantResourceId")
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lend_frag_btn:
                setButtonStyle(0);
                operationVp.setCurrentItem(0);
                break;
            case R.id.back_frag_btn:
                setButtonStyle(1);
                operationVp.setCurrentItem(1);
                break;
            case R.id.operation_showallbook:
                bookAdapter.clear();
                showBooks();
                break;
        }
    }

    /* 设置按钮样式的改变 */
    private void setButtonStyle(int kind) {
        if (kind == 1) {
            lendBtn.setBackgroundResource(R.drawable.operation_bg);
            backBtn.setBackgroundResource(R.drawable.opration_btn_bg);
        } else if (kind == 0) {
            backBtn.setBackgroundResource(R.drawable.operation_bg);
            lendBtn.setBackgroundResource(R.drawable.opration_btn_bg);
        }
    }

    private void showBooks() {
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
        mBookDatas = new ArrayList<>();
        if (books != null) {
            for (Book book : books) {
                SelectBookItem bookItem = new SelectBookItem();
                bookItem.setBookName(book.getBookName());
                bookItem.setBookAuthor(book.getAuthor());
                bookItem.setBookStatus(book.getStatus());
                bookItem.setBookNumber(book.getBookNumber());
                mBookDatas.add(bookItem);
            }
        }
        // 设置适配器
        bookAdapter = new SelectBookAdapter(getContext(), mBookDatas);
        booksLv.setAdapter(bookAdapter);
    }

}
