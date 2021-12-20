package com.heng.myLibrary.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.heng.myLibrary.R;
import com.heng.myLibrary.broadcastReceiver.BatteryReceiver;
import com.heng.myLibrary.database.DB.DBDefinitionManipulation;
import com.heng.myLibrary.database.entity.User;
import com.heng.myLibrary.fragment.defaultFrag.DefaultFragment;
import com.heng.myLibrary.fragment.meFrag.MeFragment;
import com.heng.myLibrary.fragment.operationFrag.OperationFragment;
import com.heng.myLibrary.service.BGMService;

/**
 * @author : HengZhang
 * @date : 2021/11/20 15:20
 * <p>
 * 主界面
 */
public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private static final String TAG = "MainActivity";
    RadioGroup mainRg;
    DBDefinitionManipulation db;
    Intent bgmServiceIntent, broadcastReceiverIntent;
    ImageView bgmIv;
    boolean bgmFlag = true;
    BatteryReceiver batteryReceiver;


    //todo: 声明两个按钮对应的Fragment对象
    Fragment defaultFrag, meFrag, operationFrag;
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // todo:开启BGM
        bgmServiceIntent = new Intent(this, BGMService.class);
        bgmServiceIntent.putExtra("action", "play");
        Log.e(TAG, "onCreate: BGMService start " + BGMService.class);
        startService(bgmServiceIntent);

        // todo: 广播(显示bgm开启)
        broadcastReceiverIntent = new Intent();

        // 定义广播的事件类型
        broadcastReceiverIntent.setAction("Broadcast_Action_BGM");
        broadcastReceiverIntent.setComponent(new ComponentName(getPackageName(),
                getPackageName() + "MyBroadcastReceiver"));
        Log.e(TAG, "onCreate: broadcastReceiverIntent start ");
        // 发送广播
        sendBroadcast(broadcastReceiverIntent);

        //todo:广播,获取当前手机电量
        IntentFilter intentFilter = new IntentFilter(
                Intent.ACTION_BATTERY_CHANGED);
        batteryReceiver = new BatteryReceiver();
        // 注册receiver
        registerReceiver(batteryReceiver, intentFilter);

        mainRg = findViewById(R.id.main_rg);
        bgmIv = findViewById(R.id.mainActivity_image_bgm);
        bgmIv.setImageResource(R.drawable.mainactivity_bgm_yes);
        bgmIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bgmFlag) {
                    bgmIv.setImageResource(R.drawable.mainactivity_bgm_no);
                    bgmServiceIntent.putExtra("action", "pause");
                    startService(bgmServiceIntent);
                    bgmFlag = false;
                } else {
                    bgmIv.setImageResource(R.drawable.mainactivity_bgm_yes);
                    bgmServiceIntent.putExtra("action", "play");
                    startService(bgmServiceIntent);
                    bgmFlag = true;
                }
            }
        });

        db = new DBDefinitionManipulation(this);

        //todo: 获取数据
        User user = (User) getIntent().getSerializableExtra("user");
        Log.e(TAG, "onCreate: " + user);
        //todo: 加载数据到内存里
        Bundle bundle = new Bundle();
        bundle.putSerializable("userInfo", user);

        //todo: 设置监听点击了哪个单选按钮
        mainRg.setOnCheckedChangeListener(this);

        //todo: 加载到fragment中
        defaultFrag = new DefaultFragment();
        defaultFrag.setArguments(bundle);
        meFrag = new MeFragment();
        meFrag.setArguments(bundle);
        operationFrag = new OperationFragment();
        operationFrag.setArguments(bundle);

        //todo: 将3个Fragment进行动态加载，一起加载到布局当中。replace       add/hide/show
        addFragmentPage();
    }

    /**
     * @des 将主页当中的碎片一起加载进入布局，有用的显示，暂时无用的隐藏
     */
    private void addFragmentPage() {
        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.add(R.id.main_layout_center, defaultFrag);
        transaction.add(R.id.main_layout_center, meFrag);
        transaction.add(R.id.main_layout_center, operationFrag);

        transaction.hide(operationFrag);
        transaction.hide(meFrag);
        transaction.commit();
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentTransaction transaction = manager.beginTransaction();
        switch (checkedId) {
            case R.id.default_rb:
                transaction.hide(meFrag);
                transaction.hide(operationFrag);
                transaction.show(defaultFrag);
                Log.e(TAG, "onCheckedChanged: default");
                break;

            case R.id.me_rb:
                transaction.hide(defaultFrag);
                transaction.hide(operationFrag);
                transaction.show(meFrag);
                Log.e(TAG, "onCheckedChanged: me");
                break;

            case R.id.operation_rb:
                transaction.hide(defaultFrag);
                transaction.hide(meFrag);
                transaction.show(operationFrag);
                Log.e(TAG, "onCheckedChanged: operation");
                break;
        }
        transaction.commit();
    }

    @Override
    protected void onDestroy() {
        Log.e(TAG, "onCreate: BGMService stop " + BGMService.class);
        stopService(bgmServiceIntent);
        unregisterReceiver(batteryReceiver);
        super.onDestroy();
    }
}