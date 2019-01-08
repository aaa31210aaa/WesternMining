package com.dimine.project.westernmining;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import adapter.ViewPagerAdapter;
import base.BaseActivity;
import butterknife.BindView;
import fragment.Approval;
import fragment.HomeFragment;
import fragment.MineFragment;
import fragment.Presentation;
import utils.ShowToast;


public class MainActivity extends BaseActivity {
    @BindView(R.id.main_viewpager)
    ViewPager main_viewpager;
    @BindView(R.id.main_tablayout)
    TabLayout main_tablayout;
    private List<Fragment> fragments;
    private ViewPagerAdapter adapter;
    private String[] titles = {"首页", "业务审批", "报告查询", "我的"};
    private int[] images = {R.drawable.home_tab, R.drawable.approval_tab, R.drawable.presentation_tab, R.drawable.mine_tab};
    //是否退出的标识
    private boolean isExit = false;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        setTab();
    }


    @Override
    public void loadData() {

    }


    Handler handler = new Handler() {
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            switch (msg.what) {
                case 0:
                    isExit = false;
                    break;
            }
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 延迟发送退出
     */
    private void exit() {
        if (!isExit) {
            isExit = true;
            ShowToast.showShort(this, R.string.click_agin);
            // 利用handler延迟发送更改状态信息
            handler.sendEmptyMessageDelayed(0, 2000);
        } else {
            finish();
            System.exit(0);
        }
    }


    private void setTab() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new Approval());
        fragments.add(new Presentation());
//        fragments.add(new KqReportQuery());
        fragments.add(new MineFragment());


        adapter = new ViewPagerAdapter(this, getSupportFragmentManager(), fragments);
        main_viewpager.setOffscreenPageLimit(3);
        main_viewpager.setAdapter(adapter);
        main_tablayout.setupWithViewPager(main_viewpager);

        for (int i = 0; i < adapter.getCount(); i++) {
            TabLayout.Tab tab = main_tablayout.getTabAt(i);//获得每一个tab
            tab.setCustomView(R.layout.tab_item);//给每一个tab设置view
            if (i == 0) {
                // 设置第一个tab的TextView是被选择的样式
                tab.getCustomView().findViewById(R.id.tab_text).setSelected(true);//第一个tab被选中
                tab.getCustomView().findViewById(R.id.tab_image).setSelected(true);//第一个tab被选中
            }
            TextView textView = (TextView) tab.getCustomView().findViewById(R.id.tab_text);
            textView.setText(titles[i]);//设置tab上的文字
            ImageView imageView = tab.getCustomView().findViewById(R.id.tab_image);
            imageView.setImageResource(images[i]);
        }


        main_tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getCustomView().findViewById(R.id.tab_text).setSelected(true);
                tab.getCustomView().findViewById(R.id.tab_image).setSelected(true);
                main_viewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getCustomView().findViewById(R.id.tab_text).setSelected(false);
                tab.getCustomView().findViewById(R.id.tab_image).setSelected(false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

}
