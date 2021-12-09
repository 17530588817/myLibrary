package com.heng.myLibrary.meFrag;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.heng.myLibrary.R;
import com.heng.myLibrary.database.bean.MeItemBean;

import java.util.List;

/**
 * @author : HengZhang
 * @date : 2021/11/21 14:43
 *
 * me页面listview的适配器
 */

public class MeItemAdapter extends BaseAdapter {
    Context context;
    List<MeItemBean> mDatas;


    public MeItemAdapter(Context context, List<MeItemBean> mDatas) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_me_listview, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        MeItemBean itemBean = mDatas.get(position);
        holder.titleTv.setText(itemBean.getItemName());
        holder.imageView.setImageResource(R.drawable.ic_me_item);
        return convertView;
    }

    static class ViewHolder {
        TextView titleTv;
        ImageView imageView;

        public ViewHolder(View view) {
            titleTv = view.findViewById(R.id.item_name);
            imageView = view.findViewById(R.id.item_img);
        }
    }
}
