package com.heng.myLibrary.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.heng.myLibrary.R;

import com.heng.myLibrary.database.bean.NewsInfo;
import com.loopj.android.image.SmartImageView;

import java.util.List;

/**
 * @author : HengZhang
 * @date : 2021/12/21 19:19
 *
 * ListView适配器
 */

public class NewsAdapter extends BaseAdapter {

    private List<NewsInfo> newsInfos;
    Context context;

    public NewsAdapter(Context context, List<NewsInfo> newsInfos) {
        this.context = context;
        this.newsInfos = newsInfos;
    }

    //listview的item数
    @Override
    public int getCount() {
        return newsInfos.size();
    }

    //得到listview条目视图
    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        NewsAdapter.ViewHolder holder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_news, null);
            holder = new NewsAdapter.ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (NewsAdapter.ViewHolder) convertView.getTag();
        }
        NewsInfo newsInfo = newsInfos.get(position);
        //SmartImageView加载指定路径图片
        holder.siv.setImageUrl(newsInfo.getIcon(), R.drawable.ic_launcher, R.drawable.ic_launcher);
        //设置新闻标题
        holder.tv_title.setText(newsInfo.getTitle());
        //设置新闻描述
        holder.tv_description.setText(newsInfo.getContent());
        //1.一般新闻 2.专题 3.live
        int type = newsInfo.getType();
        switch (type) {
            //不同新闻类型设置不同的颜色和不同的内容
            case 1:
                holder.tv_type.setText("评论:" + newsInfo.getComment());
                break;
            case 2:
                holder.tv_type.setTextColor(Color.RED);
                holder.tv_type.setText("专题");
                break;
            case 3:
                holder.tv_type.setTextColor(Color.BLUE);
                holder.tv_type.setText("LIVE");
                break;
        }
        return convertView;
    }

    //条目对象
    @Override
    public Object getItem(int position) {
        return newsInfos.get(position);
    }

    //条目id
    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder {
        private SmartImageView siv;
        private TextView tv_title, tv_description, tv_type;

        public ViewHolder(View view) {

            siv = (SmartImageView) view.findViewById(R.id.siv_icon);
            tv_title = (TextView) view.findViewById(R.id.tv_title);
            tv_description = (TextView) view.findViewById(R.id.tv_description);
            tv_type = (TextView) view.findViewById(R.id.tv_type);

        }
    }
}
