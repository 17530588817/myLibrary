package com.heng.myLibrary.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.heng.myLibrary.R;
import com.heng.myLibrary.adapter.HelperLvAdapter;
import com.heng.myLibrary.database.bean.HelperItemBean;
import com.heng.myLibrary.util.MyLogging;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : HengZhang
 * @date : 2021/12/20 19:20
 * <p>
 * 帮助界面
 */
public class HelperActivity extends AppCompatActivity {
    private static final String TAG = "HelperActivity";

    ListView helperLv;
    ImageView helperBack;
    List<HelperItemBean> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helper);

        MyLogging.myLog(TAG, "onCreate()");

        initView();

        showContent();
    }

    private void initView() {
        MyLogging.myLog(TAG, "initView");

        helperLv = findViewById(R.id.helper_lv);
        helperBack = findViewById(R.id.helper_back);
        helperBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void showContent() {
        MyLogging.myLog(TAG, "showContent()");

        mDatas = new ArrayList<>();
        HelperItemBean helperItemBean1 = new HelperItemBean(1, "登陆/注册",
                "   普通用户登陆，显示<用户名或密码错误>，说明您未注册或账号密码输入有误或权限不够。");
        HelperItemBean helperItemBean2 = new HelperItemBean(2, "管理员",
                "   新增图书或用户失败，说明输入数据格式不适或者某些必填信息留空。");
        HelperItemBean helperItemBean3 = new HelperItemBean(3, "借还书",
                "   借书失败，说明用户名错误或者图书不存在或者该图书已被借出。");
        HelperItemBean helperItemBean4 = new HelperItemBean(4, "关于BGM",
                "   普通用户登陆成功后，后台自动播放BGM,可手动点击右上角的音量图标控制播放与暂停。");
        HelperItemBean helperItemBean5 = new HelperItemBean(5, "待续",
                "   ...");

        mDatas.add(helperItemBean1);
        mDatas.add(helperItemBean2);
        mDatas.add(helperItemBean3);
        mDatas.add(helperItemBean4);
        mDatas.add(helperItemBean5);
        HelperLvAdapter helperLvAdapter = new HelperLvAdapter(getBaseContext(), mDatas);
        helperLv.setAdapter(helperLvAdapter);
    }
}