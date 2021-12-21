package com.heng.myLibrary.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.heng.myLibrary.R;

/**
 * @author : HengZhang
 * @date : 2021/12/21 22:19
 * 入馆码界面
 */
public class InCodeActivity extends AppCompatActivity {

    ImageView backImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_code_activity);

        backImg = findViewById(R.id.incode_back);
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}