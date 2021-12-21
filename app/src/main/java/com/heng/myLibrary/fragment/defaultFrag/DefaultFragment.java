package com.heng.myLibrary.fragment.defaultFrag;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.heng.myLibrary.R;
import com.heng.myLibrary.activity.NewsActivity;
import com.heng.myLibrary.adapter.DefaultGVAdapter;
import com.heng.myLibrary.adapter.DefaultVPAdapter;
import com.heng.myLibrary.database.bean.DefaultGVItem;

import java.util.ArrayList;
import java.util.List;

/**
 * default主界面
 */
public class DefaultFragment extends Fragment implements View.OnClickListener{

    List<DefaultGVItem> mDatas;
    private DefaultGVAdapter adapter;

    ViewPager defaultVp;
    GridView defaultGv;
    LinearLayout pointLayout;

    //todo: 声明图片数组
    int[] imgIds = {R.mipmap.ib_default01, R.mipmap.ib_default02};

    //todo: 声明ViewPager的数据源
    List<ImageView> ivList;

    //todo: 声明管理指示器小圆点的集合
    List<ImageView> pointList;
    private DefaultVPAdapter vPAdapterDefault;

    //todo: 完成定时装置，实现自动滑动的效果
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @SuppressLint("HandlerLeak")
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.what == 1) {
                // todo: 获取当前ViewPager显示的页面
                int currentItem = defaultVp.getCurrentItem();

                // todo: 判断是否为最后一张，如果是最后一张回到第一张，否则显示最后一张
                if (currentItem == ivList.size() - 1) {
                    defaultVp.setCurrentItem(0);
                } else {
                    currentItem++;
                    defaultVp.setCurrentItem(currentItem);
                }
                 //todo: 形成循环发送--接受消息的效果，在接受消息的同时，也要进行消息发送
                handler.sendEmptyMessageDelayed(1, 5000);
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_1_default, container, false);

        initView(view);

        //todo: 初始化图片
        initPager();

        initGV();

        //todo: 设置小圆点的监听
        setVPListener();

//        //todo: 设置gridview的监听
//        setGVListener();

        //todo: 延迟5秒钟发送一条消息，通知可以切换viewpager的图片了
        handler.sendEmptyMessageDelayed(1, 5000);

        return view;
    }

    private void initGV() {
        DefaultGVItem startRead = new DefaultGVItem("开始阅读", null, "", "");
        DefaultGVItem allBooks = new DefaultGVItem("馆藏图书", null, "", "");
        DefaultGVItem beforeInto = new DefaultGVItem("入馆预约", null, "", "");
        DefaultGVItem activities = new DefaultGVItem("积分活动", null, "", "");
        DefaultGVItem bookAdvice = new DefaultGVItem("每日推荐", null, "", "");
        DefaultGVItem nowInto = new DefaultGVItem("我要入馆", null, "", "");
        DefaultGVItem a7 = new DefaultGVItem("待续", null, "", "");
        DefaultGVItem a8 = new DefaultGVItem("待续", null, "", "");
        DefaultGVItem a9 = new DefaultGVItem("待续", null, "", "");


        mDatas = new ArrayList<>();

        mDatas.add(startRead);
        mDatas.add(allBooks);
        mDatas.add(beforeInto);
        mDatas.add(nowInto);
        mDatas.add(bookAdvice);
        mDatas.add(activities);
        mDatas.add(a7);
        mDatas.add(a8);
        mDatas.add(a9);
        //todo: 创建适配器对象
        adapter = new DefaultGVAdapter(getContext(), mDatas);
        //todo: 设置适配器
        defaultGv.setAdapter(adapter);
    }


    private void setVPListener() {
        defaultVp.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < pointList.size(); i++) {
                    pointList.get(i).setImageResource(R.mipmap.point_normal);
                }
                pointList.get(position).setImageResource(R.mipmap.point_focus);
            }
        });
    }

    private void initPager() {
        ivList = new ArrayList<>();
        pointList = new ArrayList<>();

        for (int i = 0; i < imgIds.length; i++) {
            ImageView iv = new ImageView(getContext());
            iv.setImageResource(imgIds[i]);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);

            // todo: 设置图片view的宽高
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            iv.setLayoutParams(lp);

            // todo: 将图片view加载到集合当中
            ivList.add(iv);

            //todo:  创建图片对应的指示器小圆点
            ImageView piv = new ImageView(getContext());
            piv.setImageResource(R.mipmap.point_normal);
            LinearLayout.LayoutParams plp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);

            plp.setMargins(20, 0, 0, 0);
            piv.setLayoutParams(plp);

            //todo:  将小圆点添加到布局当中
            pointLayout.addView(piv);

            //todo: 为了便于操作，将小圆点添加到统一管理的集合中
            pointList.add(piv);
        }

        //todo: 默认第一个小圆点是获取焦点的状态
        pointList.get(0).setImageResource(R.mipmap.point_focus);
        vPAdapterDefault = new DefaultVPAdapter(getContext(), ivList);
        defaultVp.setAdapter(vPAdapterDefault);
    }

    /**
     * 初始化布局
     *
     * @param view
     */
    private void initView(View view) {
        defaultVp = view.findViewById(R.id.default_img_vp);
        defaultGv = view.findViewById(R.id.default_gv);
        pointLayout = view.findViewById(R.id.default_point);

        defaultVp.setOnClickListener(this);
    }

    // todo: 点击图片跳转到新闻界面
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.default_img_vp:
                Intent intent = new Intent(getContext(), NewsActivity.class);
                startActivity(intent);
                break;
        }
    }
}