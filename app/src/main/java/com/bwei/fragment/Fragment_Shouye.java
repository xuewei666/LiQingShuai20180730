package com.bwei.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.bwei.app.Netutis;
import com.bwei.bean.News;
import com.bwei.bean.NewsKu;
import com.bwei.db.ShowDao;
import com.bwei.httputis.HttpUrlConnection;
import com.bwei.lqs.R;
import com.bwei.zidingyi.MyListView;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class Fragment_Shouye extends Fragment {
    private static HttpUrlConnection httpUrlConnection = HttpUrlConnection.getInstance();
    private  String uuu = "http://www.xieast.com/api/news/news.php?page=";
    private int page = 1;
    private MyListView mylist_view;
    private PullToRefreshScrollView pull_to_refresh01;
    private ShowDao showDao;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_shouye, container, false);
        initView(inflate);
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showDao = new ShowDao(getActivity());
        NewsKu query = showDao.query(uuu + page);
        boolean b = Netutis.HavaNetWork(getActivity());
        if (b=true){
            getListDataFromJson();


        }else{
            getData01(query.json);

        }


        pull_to_refresh01.setMode(PullToRefreshBase.Mode.BOTH);
        pull_to_refresh01.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                page = 1;
                getListDataFromJson();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                page ++;
                getListDataFromJson();
            }
        });
    }

    private void getData02(String json){

        showDao.insert(new NewsKu(uuu+page,json));
        getData01(json);

    }
    private void getData01(String json){
        Gson gson = new Gson();
        News news = gson.fromJson(json, News.class);
        List<News.DataBean> list_yk = new ArrayList<>();
        List<News.DataBean> data = news.getData();
        list_yk.addAll(data);
        //创建适配器
        MyAdapter myAdapter = new MyAdapter(list_yk, getActivity());
        mylist_view.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
        //关闭头布局 和脚布局
        pull_to_refresh01.onRefreshComplete();

    }
    private void getListDataFromJson() {

        httpUrlConnection.getData(page);

        httpUrlConnection.setHttpListenter(new HttpUrlConnection.HttpListenter() {
            @Override
            public void getDataJson(String json) {
                getData02(json);

            }
        });

    }

    private void initView(View inflate) {
        mylist_view = (MyListView) inflate.findViewById(R.id.mylist_view);
        pull_to_refresh01 = (PullToRefreshScrollView) inflate.findViewById(R.id.pull_to_refresh01);

    }
}
