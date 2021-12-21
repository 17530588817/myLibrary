package com.heng.myLibrary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.heng.myLibrary.R;
import com.heng.myLibrary.database.bean.SelectUserItem;

import java.util.List;

/**
 * @author : HengZhang
 * @date : 2021/11/28 20:03
 * <p>
 * 查询用户适配器
 */

public class SelectUserAdapter extends BaseAdapter {

    Context context;
    List<SelectUserItem> mDatas;

    public SelectUserAdapter(Context context, List<SelectUserItem> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        SelectUserAdapter.ViewHolder holder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_select_user_lv, null);
            holder = new SelectUserAdapter.ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (SelectUserAdapter.ViewHolder) convertView.getTag();
        }

        SelectUserItem userItem = mDatas.get(position);
        holder.userNameTv.setText(userItem.getUserName());
        holder.userSexTv.setText(userItem.getUserSex());
        holder.userPhoneTv.setText(userItem.getUserPhone());
        holder.userEmailTv.setText(userItem.getUserEmail());
        holder.userCode.setText(userItem.getUserCode().toString());
        if (userItem.getUserLevel() == 1) {
            holder.userLevel.setText("管理员");
        } else {
            holder.userLevel.setText("普通用户");
        }
//        this.clear();
        return convertView;
    }

    static class ViewHolder {
        TextView userNameTv, userSexTv, userPhoneTv, userEmailTv, userLevel,userCode;

        public ViewHolder(View view) {
            userNameTv = view.findViewById(R.id.select_user_name);
            userSexTv = view.findViewById(R.id.select_user_sex);
            userPhoneTv = view.findViewById(R.id.select_user_phone);
            userEmailTv = view.findViewById(R.id.select_user_email);
            userLevel = view.findViewById(R.id.select_user_level);
            userCode = view.findViewById(R.id.select_user_code);
        }
    }

    //todo: 清空listview中的数据
    public void clear() {
        if (mDatas != null) {
            mDatas.clear();
        }
        notifyDataSetChanged();
    }
}
