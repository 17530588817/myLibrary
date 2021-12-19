package com.heng.myLibrary.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.heng.myLibrary.R;
import com.heng.myLibrary.fragment.adminFrag.AdminAdapter;
import com.heng.myLibrary.fragment.adminFrag.BookFragment;
import com.heng.myLibrary.fragment.adminFrag.SelectFragment;
import com.heng.myLibrary.fragment.adminFrag.UserFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 管理员界面
 */
public class AdminActivity extends AppCompatActivity implements View.OnClickListener {

    Button bookBtn, userBtn, selectBtn;
    ViewPager adminVp;
    Fragment bookFrag, userFrag, selectFrag;
    List<Fragment> adminVPList;
    AdminAdapter adminAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        initView();
        initFrag();
    }

    //todo:初始化fragment
    private void initFrag() {
        adminVPList = new ArrayList<>();

        bookFrag = new BookFragment();
        userFrag = new UserFragment();
        selectFrag = new SelectFragment();

        adminVPList.add(bookFrag);
        adminVPList.add(userFrag);
        adminVPList.add(selectFrag);
        adminAdapter = new AdminAdapter(getSupportFragmentManager(), adminVPList);
        adminVp.setAdapter(adminAdapter);
    }

    //todo:初始化UI
    private void initView() {

        bookBtn = findViewById(R.id.book_operation_btn);
        userBtn = findViewById(R.id.user_operation_btn);
        selectBtn = findViewById(R.id.select_btn);
        adminVp = findViewById(R.id.admin_vp);

        bookBtn.setOnClickListener(this);
        userBtn.setOnClickListener(this);
        selectBtn.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.book_operation_btn:
                setButtonStyle(0);
                adminVp.setCurrentItem(0);
                break;
            case R.id.user_operation_btn:
                setButtonStyle(1);
                adminVp.setCurrentItem(1);
                break;

            case R.id.select_btn:
                setButtonStyle(2);
                adminVp.setCurrentItem(2);
                break;
        }
    }


    /*todo: 设置按钮样式的改变 */
    private void setButtonStyle(int kind) {
        switch (kind) {
            case 0:
                bookBtn.setBackgroundResource(R.drawable.opration_btn_bg);
                userBtn.setBackgroundResource(R.drawable.operation_bg);
                selectBtn.setBackgroundResource(R.drawable.operation_bg);
                break;
            case 1:
                userBtn.setBackgroundResource(R.drawable.opration_btn_bg);
                bookBtn.setBackgroundResource(R.drawable.operation_bg);
                selectBtn.setBackgroundResource(R.drawable.operation_bg);
                break;

            case 2:
                selectBtn.setBackgroundResource(R.drawable.opration_btn_bg);
                bookBtn.setBackgroundResource(R.drawable.operation_bg);
                userBtn.setBackgroundResource(R.drawable.operation_bg);
                break;
        }
    }
}