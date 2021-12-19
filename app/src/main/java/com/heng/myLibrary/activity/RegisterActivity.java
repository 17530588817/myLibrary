package com.heng.myLibrary.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.heng.myLibrary.R;
import com.heng.myLibrary.database.DB.DBDefinitionManipulation;
import com.heng.myLibrary.database.entity.User;

/**
 * @author : HengZhang
 * @date : 2021/11/25 15:29
 * <p>
 * 用户注册类
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "RegisterActivity";
    EditText nameTv, sexTv, accountTv, passwordTv, phoneTv, emailTv;
    RadioButton userBtn, adminBtn;
    Button registerYesBtn;
    DBDefinitionManipulation db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
    }

    private void initView() {
        nameTv = findViewById(R.id.register_username);
        sexTv = findViewById(R.id.register_sex);
        accountTv = findViewById(R.id.register_account);
        passwordTv = findViewById(R.id.register_password);
        phoneTv = findViewById(R.id.register_phone);
        emailTv = findViewById(R.id.register_email);
        registerYesBtn = findViewById(R.id.register_yes);
        userBtn = findViewById(R.id.register_user_btn);
        adminBtn = findViewById(R.id.register_admin_btn);

        registerYesBtn.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register_yes:
                if (checkRegister()) {
                    Toast.makeText(this, "注册成功！", Toast.LENGTH_SHORT).show();
                    Log.w(TAG, "register 注册 用户 成功");
                    finish();
                } else {
                    Toast.makeText(this, "注册失败！", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }

    // todo:验证数据的合法性
    private boolean checkRegister() {

        if (TextUtils.isEmpty(nameTv.getText()) || TextUtils.isEmpty(sexTv.getText()) || TextUtils.isEmpty(accountTv.getText()) ||
                TextUtils.isEmpty(passwordTv.getText()) || TextUtils.isEmpty(phoneTv.getText()) || TextUtils.isEmpty(emailTv.getText())) {
            Toast.makeText(this, "数据不能为空！", Toast.LENGTH_SHORT).show();
            return false;
        }
        db = new DBDefinitionManipulation(this);
        db.open();
        User user = new User();
        user.setUserName(nameTv.getText().toString());
        user.setSex(sexTv.getText().toString());
        user.setAccount(accountTv.getText().toString());
        user.setPassword(passwordTv.getText().toString());
        user.setPhone(phoneTv.getText().toString());
        user.setEmail(emailTv.getText().toString());
        user.setUserCode(0);
        user.setBookName(null);
        user.setUserLevel(userIdentity());
        long flag = db.addUser(user);
        db.close();

        return flag != -1;
    }

    //todo: 用户身份按钮识别,0失败1管理员2普通用户
    private Integer userIdentity() {
        if (userBtn.isChecked()) {
            return 2;
        } else if (adminBtn.isChecked()) {
            return 1;
        }
        return 0;
    }
}