package com.heng.myLibrary.meFrag;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.heng.myLibrary.R;
import com.heng.myLibrary.activity.LoginActivity;
import com.heng.myLibrary.activity.MainActivity;
import com.heng.myLibrary.activity.NewsActivity;
import com.heng.myLibrary.database.bean.MeItemBean;
import com.heng.myLibrary.database.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 我的页面
 */
public class MeFragment extends Fragment implements View.OnClickListener {

    CircleImageView iconIv;
    TextView nameTv, codeTv;
    User user;
    ListView listView;
    List<MeItemBean> mDatas;
    Button exitBtn,newsBtn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDatas = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_1_me, container, false);

        //todo: 获取数据
        Bundle bundle = getArguments();
        assert bundle != null;
        user = (User) bundle.getSerializable("userInfo");

        initView(view);
        addDataToList();
        loadUserImg();
        return view;
    }

    private void initView(View view) {
        iconIv = view.findViewById(R.id.meFrag_iv);
        nameTv = view.findViewById(R.id.user_name);
        codeTv = view.findViewById(R.id.user_code);
        listView = view.findViewById(R.id.me_lv);
        exitBtn = view.findViewById(R.id.exit_login);
        newsBtn = view.findViewById(R.id.news);

        exitBtn.setOnClickListener(this);
        newsBtn.setOnClickListener(this);

        //todo:设置监听器，选择相册图片当头像
        iconIv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //todo:动态申请权限
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission
                        .WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    //todo: 执行启动相册的方法
                    openAlbum();
                }
            }
        });
        nameTv.setText(user.getUserName());
        codeTv.setText(String.valueOf(user.getUserCode()));
    }

    //todo: 启动相册的方法
    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, 2);
    }

    //todo:获取权限的结果
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                openAlbum();
            else Toast.makeText(getContext(), "你拒绝了", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 2) {
            //判断安卓版本
            if (resultCode == Activity.RESULT_OK && data != null) {
                if (Build.VERSION.SDK_INT >= 19)
                    handImage(data);
                else handImageLow(data);
            }
        }
    }

    //todo: 安卓版本大于4.4的处理方法
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void handImage(Intent data) {
        String path = null;
        Uri uri = data.getData();
        //根据不同的uri进行不同的解析
        if (DocumentsContract.isDocumentUri(getContext(), uri)) {
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                path = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                path = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            path = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            path = uri.getPath();
        }
        //todo: 展示图片
        displayImage(path);
    }

    //todo: 安卓小于4.4的处理方法
    private void handImageLow(Intent data) {
        Uri uri = data.getData();
        String path = getImagePath(uri, null);
        displayImage(path);
    }

    //todo: content类型的uri获取图片路径的方法
    @SuppressLint("Range")
    private String getImagePath(Uri uri, String selection) {
        String path = null;
        Cursor cursor = getContext().getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    //todo: 根据路径展示图片的方法
    private void displayImage(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            iconIv.setImageBitmap(bitmap);
        } else {
            Toast.makeText(getContext(), "fail to set image", Toast.LENGTH_SHORT).show();
        }
    }

    // todo: list添加数据
    private void addDataToList() {
        MeItemBean userCode = new MeItemBean("我的积分", "", "");
        MeItemBean inLibTime = new MeItemBean("在馆时间", "", "");
        MeItemBean collection = new MeItemBean("我的收藏", "", "");
        MeItemBean bookReview = new MeItemBean("我的书评", "", "");
        MeItemBean bookLend = new MeItemBean("我借的书", "", "");

        mDatas.add(userCode);
        mDatas.add(inLibTime);
        mDatas.add(collection);
        mDatas.add(bookReview);
        mDatas.add(bookLend);

        //todo:  设置适配器
        MeItemAdapter adapter = new MeItemAdapter(getContext(), mDatas);
        listView.setAdapter(adapter);
    }

    //todo: 设置默认头像
    private void loadUserImg() {
        if (user != null) {
            switch (user.getSex()) {
                case "男":
                    iconIv.setImageResource(R.mipmap.man);
                    break;
                case "女":
                    iconIv.setImageResource(R.mipmap.woman);
                    break;
            }
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.exit_login) {
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
            Objects.requireNonNull(getActivity()).finish();
        }else if(view.getId() == R.id.news){
            Intent intent = new Intent(getContext(), NewsActivity.class);
            startActivity(intent);
        }
    }
}