package com.bwei.fragment;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwei.bean.News;
import com.bwei.lqs.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

class MyAdapter extends BaseAdapter {
   private List<News.DataBean> list;
   private Context context;
   private final int ONE_ITEM = 0;
   private final int TWO_ITEM = 1;


    public MyAdapter(List<News.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        String thumbnail_pic_s = list.get(position).getThumbnail_pic_s();
        String thumbnail_pic_s02 = list.get(position).getThumbnail_pic_s02();
        String thumbnail_pic_s03 = list.get(position).getThumbnail_pic_s03();
        if (thumbnail_pic_s!=null&&thumbnail_pic_s02==null&&thumbnail_pic_s03==null){
            return ONE_ITEM;


        }
        else if(thumbnail_pic_s!=null&&thumbnail_pic_s02!=null&&thumbnail_pic_s03!=null){

            return TWO_ITEM;
        }else {

            return ONE_ITEM;
        }


    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int i = getItemViewType(position);
        if (i==ONE_ITEM){
            viewHolder01 holder01 = null;
            if (convertView==null){
                holder01 = new viewHolder01();
                convertView = View.inflate(context, R.layout.layout_item01, null);
                holder01.title01 = convertView.findViewById(R.id.title01);
                holder01.image_view = convertView.findViewById(R.id.image_view);

                convertView.setTag(holder01);

            }else{
                holder01 = (viewHolder01) convertView.getTag();
            }
            holder01.title01.setText(list.get(position).getTitle());
            ImageLoader.getInstance().displayImage(list.get(position).getThumbnail_pic_s(),holder01.image_view );


        }else{

            viewHolder02 holder02 = null;
            if (convertView==null){
                holder02 = new viewHolder02();
                convertView = View.inflate(context, R.layout.layout_item02, null);
                holder02.title02 = convertView.findViewById(R.id.title02);
                holder02.imageview01 = convertView.findViewById(R.id.imageview01);
                holder02.imageview02 = convertView.findViewById(R.id.imageview02);
                holder02.imageview03 = convertView.findViewById(R.id.imageview03);

                convertView.setTag(holder02);

            }else{

                holder02 = (viewHolder02) convertView.getTag();
            }
            holder02.title02.setText(list.get(position).getTitle());
            ImageLoader.getInstance().displayImage(list.get(position).getThumbnail_pic_s(),holder02.imageview01 );
            ImageLoader.getInstance().displayImage(list.get(position).getThumbnail_pic_s(),holder02.imageview02 );
            ImageLoader.getInstance().displayImage(list.get(position).getThumbnail_pic_s(),holder02.imageview03 );


        }


        return convertView;
    }

    public class viewHolder01{
        TextView title01;
        ImageView image_view;


    }
    public class viewHolder02{
        TextView title02;
        ImageView imageview01;
        ImageView imageview02;
        ImageView imageview03;



    }
}
