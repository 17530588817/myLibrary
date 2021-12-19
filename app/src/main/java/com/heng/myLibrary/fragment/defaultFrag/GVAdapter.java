package com.heng.myLibrary.fragment.defaultFrag;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.heng.myLibrary.R;
import com.heng.myLibrary.database.bean.DefaultGVItem;

import java.util.List;


/**
 * @author : HengZhang
 * @date : 2021/11/21 16:23
 */

public class GVAdapter extends BaseAdapter {
    Context context;
    List<DefaultGVItem> mDatas;

    int[] imgIds = {R.mipmap.ib_book01,R.mipmap.ib_book02,R.mipmap.ib_book03,R.mipmap.ib_book04,
            R.mipmap.ib_book05,R.mipmap.ib_book06,R.mipmap.ib_book07,R.mipmap.ib_book08,R.mipmap.ib_book09};
    List<ImageView> ivList;


    public GVAdapter(Context context, List<DefaultGVItem> mDatas) {
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
        ViewHolder holder = null;


        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_default_gv, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //获取指定位置的数据
        DefaultGVItem bean = mDatas.get(position);
        holder.itemTv.setText(bean.getItemName());

        //通过名称，获取存储在内存当中的图片
        holder.imageIv.setImageResource(imgIds[position]);

        return convertView;
    }

    class ViewHolder {
        ImageView imageIv;
        TextView itemTv;

        public ViewHolder(View view) {
            imageIv = view.findViewById(R.id.gv_item_image);
            itemTv = view.findViewById(R.id.gv_item_tv);
        }
    }
}
