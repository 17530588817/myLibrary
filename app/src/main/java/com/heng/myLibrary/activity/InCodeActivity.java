package com.heng.myLibrary.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.heng.myLibrary.R;
import com.heng.myLibrary.util.MyLogging;

/**
 * @author : HengZhang
 * @date : 2021/12/21 22:19
 * 入馆码界面
 */
public class InCodeActivity extends AppCompatActivity {
    private static final String TAG = "InCodeActivity";

    ImageView backImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_code_activity);

        MyLogging.myLog(TAG,"onCreate()");

        backImg = findViewById(R.id.incode_back);
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}