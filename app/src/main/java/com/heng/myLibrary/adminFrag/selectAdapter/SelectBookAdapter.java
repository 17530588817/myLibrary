package com.heng.myLibrary.adminFrag.selectAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.heng.myLibrary.R;
import com.heng.myLibrary.database.bean.SelectBookItem;

import java.util.List;

/**
 * @author : HengZhang
 * @date : 2021/11/28 19:32
 * <p>
 * 查询图书适配器
 */

public class SelectBookAdapter extends BaseAdapter {

    Context context;
    List<SelectBookItem> mDatas;

    public SelectBookAdapter(Context context, List<SelectBookItem> mDatas) {
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
        SelectBookAdapter.ViewHolder holder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_select_book_lv, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (SelectBookAdapter.ViewHolder) convertView.getTag();
        }
        SelectBookItem bookItem = mDatas.get(position);
        holder.bookNameTv.setText(bookItem.getBookName());
        holder.bookAuthorTv.setText(bookItem.getBookAuthor());
        holder.bookNumberTv.setText(String.valueOf(bookItem.getBookNumber()));
        if (bookItem.getBookStatus() == 0) {
            holder.bookStatusTv.setText("未借出");
        } else {
            holder.bookStatusTv.setText("已借出");
        }

        return convertView;
    }

    static class ViewHolder {
        TextView bookNameTv, bookAuthorTv, bookStatusTv, bookNumberTv;

        public ViewHolder(View view) {
            bookNameTv = view.findViewById(R.id.select_bookname);
            bookAuthorTv = view.findViewById(R.id.select_bookauthor);
            bookStatusTv = view.findViewById(R.id.select_bookstatus);
            bookNumberTv = view.findViewById(R.id.select_booknumber);
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
