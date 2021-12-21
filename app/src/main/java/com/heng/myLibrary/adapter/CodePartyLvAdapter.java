package com.heng.myLibrary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.heng.myLibrary.R;
import com.heng.myLibrary.database.bean.CodePartyItemBean;

import java.util.List;

/**
 * @author : HengZhang
 * @date : 2021/12/21 21:28
 * <p>
 * 积分活动lv的适配器
 */

public class CodePartyLvAdapter extends BaseAdapter {
    Context context;
    List<CodePartyItemBean> mDatas;

    public CodePartyLvAdapter(Context context, List<CodePartyItemBean> mDatas) {
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

        CodePartyLvAdapter.ViewHolder holder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_code_party_lv, null);
            holder = new CodePartyLvAdapter.ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (CodePartyLvAdapter.ViewHolder) convertView.getTag();
        }
        CodePartyItemBean codePartyItemBean = mDatas.get(position);
        holder.codePartyItemTitleLv.setText(codePartyItemBean.getCodePartyItemBeanTitle());
        holder.codePartyItemContentLv.setText(codePartyItemBean.getCodePartyItemBeanContent());
        holder.codePartyItemCategoryLv.setText(codePartyItemBean.getCodePartyItemBeanCategory());

        return convertView;
    }

    static class ViewHolder {
        TextView codePartyItemCategoryLv,codePartyItemTitleLv, codePartyItemContentLv;

        public ViewHolder(View view) {
            codePartyItemTitleLv = view.findViewById(R.id.code_party_item_title);
            codePartyItemCategoryLv = view.findViewById(R.id.code_party_item_category);
            codePartyItemContentLv = view.findViewById(R.id.code_party_item_content);

        }
    }
}
