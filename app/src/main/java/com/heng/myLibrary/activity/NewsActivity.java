package com.heng.myLibrary.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.heng.myLibrary.R;
import com.heng.myLibrary.adapter.NewsAdapter;
import com.heng.myLibrary.database.bean.NewsInfo;
import com.heng.myLibrary.util.JsonParse;
import com.heng.myLibrary.util.MyLogging;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.image.SmartImageView;

import java.nio.charset.StandardCharsets;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class NewsActivity extends AppCompatActivity {
    private static final String TAG = "NewsActivity";

    private LinearLayout loading;
    private ListView lvNews;
    private List<NewsInfo> newsInfos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        MyLogging.myLog(TAG, "onCreate()");

        initView();
        fillData();
    }

    //初始化控件
    private void initView() {
        MyLogging.myLog(TAG, "initView()");

        loading = (LinearLayout) findViewById(R.id.loading);
        lvNews = (ListView) findViewById(R.id.lv_news);
    }

    //使用AsyncHttpClient访问网络
    private void fillData() {
        MyLogging.myLog(TAG, "fillData()");

        //创建AsyncHttpClient实例
        AsyncHttpClient client = new AsyncHttpClient();
        //使用GET方式请求
        client.get("http://10.200.10.10:8080/chapter12/test.json", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                //请求成功
                try {
                    String json = new String(bytes, StandardCharsets.UTF_8);
                    newsInfos = JsonParse.getNewsInfo(json);
                    if (newsInfos == null) {
                        Toast.makeText(NewsActivity.this, "解析失败", Toast.LENGTH_SHORT).show();
                    } else {
                        //更新界面
                        loading.setVisibility(View.INVISIBLE);
                        lvNews.setAdapter(new NewsAdapter(getBaseContext(), newsInfos));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
            }
        });
    }

}

