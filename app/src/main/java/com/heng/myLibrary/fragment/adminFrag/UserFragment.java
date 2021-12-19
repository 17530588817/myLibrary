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
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.heng.myLibrary.R;
import com.heng.myLibrary.database.DB.DBDefinitionManipulation;
import com.heng.myLibrary.database.entity.User;

/**
 * @author : HengZhang
 * @date : 2021/11/28 15:53
 *
 * 管理员界面的用户操作界面
 */

public class UserFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "UserFragment";
    EditText nameTv, sexTv, accountTv, passwordTv, phoneTv, emailTv;
    RadioButton userBtn, adminBtn;
    Button addYesBtn, deleteYesBtn;
    DBDefinitionManipulation db;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_2_user, container, false);

        initView(view);
        return view;
    }

    private void initView(View view) {
        nameTv = view.findViewById(R.id.add_username);
        sexTv = view.findViewById(R.id.add_sex);
        accountTv = view.findViewById(R.id.add_account);
        passwordTv = view.findViewById(R.id.add_password);
        phoneTv = view.findViewById(R.id.add_phone);
        emailTv = view.findViewById(R.id.add_email);
        addYesBtn = view.findViewById(R.id.add_user_yes);
        deleteYesBtn = view.findViewById(R.id.delete_user_yes);
        userBtn = view.findViewById(R.id.add_user_btn);
        adminBtn = view.findViewById(R.id.add_admin_btn);

        addYesBtn.setOnClickListener(this);
        deleteYesBtn.setOnClickListener(this);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_user_yes:
                if (adminAddUser()) {
                    Toast.makeText(getContext(), "注册用户成功！", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "register 注册 用户 成功");
                } else {
                    Toast.makeText(getContext(), "注册用户失败！", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.delete_user_yes:
                if (adminDeleteUser()) {
                    Toast.makeText(getContext(), "删除用户成功！", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "register 注册 用户 成功");
                } else {
                    Toast.makeText(getContext(), "删除用户失败！", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private boolean adminDeleteUser() {
        if (TextUtils.isEmpty(nameTv.getText())){
            return false;
        }
        db = new DBDefinitionManipulation(getContext());
        db.open();
        boolean deleteUserFlag = db.deleteUser(nameTv.getText().toString());
        db.close();
        return deleteUserFlag;
    }

    private boolean adminAddUser() {

        if (TextUtils.isEmpty(nameTv.getText()) || TextUtils.isEmpty(sexTv.getText()) || TextUtils.isEmpty(accountTv.getText()) ||
                TextUtils.isEmpty(passwordTv.getText()) || TextUtils.isEmpty(phoneTv.getText()) || TextUtils.isEmpty(emailTv.getText())) {
            Toast.makeText(getContext(), "数据不能为空！", Toast.LENGTH_SHORT).show();
            return false;
        }
        db = new DBDefinitionManipulation(getContext());
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

    //用户身份按钮识别,0失败1管理员2普通用户
    private Integer userIdentity() {
        if (userBtn.isChecked()) {
            return 2;
        } else if (adminBtn.isChecked()) {
            return 1;
        }
        return 0;
    }
}
