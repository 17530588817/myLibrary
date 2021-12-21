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

/**
 * @author : HengZhang
 * @date : 2021/11/23 19:22
 */

public class BackFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "BackFragment";
    EditText backUserName, backBookName;
    Button backBtn;
    DBDefinitionManipulation db;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_2_back, container, false);
        initView(view);

        return view;
    }

    private void initView(View view) {
        backUserName = view.findViewById(R.id.back_account);
        backBookName = view.findViewById(R.id.back_bookname);
        backBtn = view.findViewById(R.id.back_yes_btn);

        backBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.back_yes_btn) {
            if (userBackBook(backUserName.getText().toString().trim(), backBookName.getText().toString().trim())) {
                Toast.makeText(getContext(), "还书成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "还书失败", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean userBackBook(String userName, String bookName) {
        db = new DBDefinitionManipulation(getContext());
        db.open();
        boolean backBookFlag = db.backBook(userName, bookName);
        db.close();
        return backBookFlag;
    }
}
