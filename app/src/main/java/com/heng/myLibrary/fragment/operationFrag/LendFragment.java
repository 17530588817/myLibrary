package com.heng.myLibrary.fragment.operationFrag;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.heng.myLibrary.R;
import com.heng.myLibrary.database.DB.DBDefinitionManipulation;
import com.heng.myLibrary.util.MyLogging;

/**
 * @author : HengZhang
 * @date : 2021/11/23 19:22
 * <p>
 * 借书界面
 */

public class LendFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "LendFragment";
    DBDefinitionManipulation db;
    EditText lendUserName, lendBookName;
    Button lendBtn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyLogging.myLog(TAG, "onCreate()");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_2_lend, container, false);

        MyLogging.myLog(TAG, "onCreateView()");

        initView(view);

        return view;
    }

    private void initView(View view) {
        MyLogging.myLog(TAG, "initView()");

        lendUserName = view.findViewById(R.id.lend_user_account);
        lendBookName = view.findViewById(R.id.lend_bookname);
        lendBtn = view.findViewById(R.id.lend_btn);

        lendBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.lend_btn) {
            if (userLendBook(lendUserName.getText().toString().trim(), lendBookName.getText().toString().trim())) {
                Toast.makeText(getContext(), "借书成功", Toast.LENGTH_SHORT).show();
                MyLogging.myLog(TAG, "借书成功");
            } else {
                Toast.makeText(getContext(), "借书失败", Toast.LENGTH_SHORT).show();
                MyLogging.myLog(TAG, "借书失败");
            }
        }
    }

    private boolean userLendBook(String userName, String bookName) {
        MyLogging.myLog(TAG, "userLendBook()");

        db = new DBDefinitionManipulation(getContext());
        db.open();
        boolean lendBookFlag = db.lendBook(userName, bookName);
        db.close();
        return lendBookFlag;
    }
}
