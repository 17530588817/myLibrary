package com.heng.myLibrary.operationFrag;

import android.os.Bundle;
import android.util.Log;
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

public class LendFragment extends Fragment implements View.OnClickListener{
    private static final String TAG = "LendFragment";
    DBDefinitionManipulation db;
    EditText lendUserName,lendBookName;
    Button lendBtn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_2_lend, container, false);
        initView(view);

        return view;
    }

    private void initView(View view) {
        lendUserName = view.findViewById(R.id.lend_user_account);
        lendBookName = view.findViewById(R.id.lend_bookname);
        lendBtn = view.findViewById(R.id.lend_btn);

        lendBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId()== R.id.lend_btn){
            if (userLendBook(lendUserName.getText().toString().trim(),lendBookName.getText().toString().trim())) {
                Toast.makeText(getContext(),"借书成功",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getContext(),"借书失败",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean userLendBook(String userName, String bookName) {
        db = new DBDefinitionManipulation(getContext());
        db.open();
        boolean lendBookFlag = db.lendBook(userName, bookName);
        db.close();
        Log.e(TAG, "userLendBook->> username:" + userName +"  bookname: " + bookName);
        Log.e(TAG, "userLendBook->> lendBookFlag:" + lendBookFlag);
        return lendBookFlag;
    }
}
