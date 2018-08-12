package com.bwei.lqs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bwei.adapter.MyFragmentPager;
import com.bwei.fragment.Fragment_Shouye;
import com.bwei.fragment.Fragment_WoDe;
import com.bwei.fragment.Fragment_XiaoXi;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager view_page;
    private RadioButton btn1;
    private RadioButton btn2;
    private RadioButton btn3;
    private RadioGroup radio_group;
    private DrawerLayout drawerlayout;
    private List<Fragment> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        //创建集合
        list = new ArrayList<>();
        //添加
        list.add(new Fragment_Shouye());
        list.add(new Fragment_XiaoXi());
        list.add(new Fragment_WoDe());
        //创建适配器
        MyFragmentPager myFragmentPager = new MyFragmentPager(getSupportFragmentManager(), list);
        view_page.setAdapter(myFragmentPager);
        view_page.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                radio_group.check(radio_group.getChildAt(position% list.size()).getId());


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.btn1:
                        view_page.setCurrentItem(0);

                        break;
                    case R.id.btn2:
                        view_page.setCurrentItem(1);

                        break;
                    case R.id.btn3:
                        view_page.setCurrentItem(2);

                        break;


                }
            }
        });
    }

    private void initView() {
        view_page = (ViewPager) findViewById(R.id.view_page);
        btn1 = (RadioButton) findViewById(R.id.btn1);
        btn2 = (RadioButton) findViewById(R.id.btn2);
        btn3 = (RadioButton) findViewById(R.id.btn3);
        radio_group = (RadioGroup) findViewById(R.id.radio_group);
        drawerlayout = (DrawerLayout) findViewById(R.id.drawerlayout);
    }
}
