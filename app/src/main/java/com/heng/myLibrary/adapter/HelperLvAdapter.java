package com.heng.myLibrary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.heng.myLibrary.R;
import com.heng.myLibrary.database.bean.HelperItemBean;

import java.util.List;

/**
 * @author : HengZhang
 * @date : 2021/12/20 20:01
 *
 * 帮助页面的listiew适配器
 */

public class HelperLvAdapter  extends BaseAdapter {

    Context context;
    List<HelperItemBean> mDatas;

    public HelperLvAdapter(Context context, List<HelperItemBean> mDatas) {
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

        HelperLvAdapter.ViewHolder holder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_helper_lv, null);
            holder = new HelperLvAdapter.ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (HelperLvAdapter.ViewHolder) convertView.getTag();
        }
        HelperItemBean helperItemBean = mDatas.get(position);
        holder.helperItemId.setText(helperItemBean.getHelperItemId().toString());
        holder.helperItemTitle.setText(helperItemBean.getHelperItemTitle());
        holder.helperItemContent.setText(helperItemBean.getHelperItemContent());

        return convertView;
    }

    static class ViewHolder {
        TextView helperItemId,helperItemTitle,helperItemContent;

        public ViewHolder(View view) {
            helperItemId = view.findViewById(R.id.helper_item_id);
            helperItemTitle = view.findViewById(R.id.helper_item_title);
            helperItemContent = view.findViewById(R.id.helper_item_content);
        }
    }
}
