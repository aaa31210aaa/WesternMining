package activity.kq_report;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.dimine.project.westernmining.R;

import java.util.ArrayList;
import java.util.List;

import adapter.ViewPagerAdapter;
import base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 矿权报表
 */
public class KqReportQuery extends BaseActivity {
    @BindView(R.id.toolbar_title)
    TextView toolbar_title;
    @BindView(R.id.report_query_tab)
    TabLayout report_query_tab;
    @BindView(R.id.report_query_viewpager)
    ViewPager report_query_viewpager;
    private String[] titles = {"周报表","季度报表"};
    private List<Fragment> fragments;
    private ViewPagerAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_report_query;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        setTab();
    }

    @Override
    public void loadData() {
        toolbar_title.setText(R.string.kq_report_title);

    }

    @OnClick(R.id.toolbar_left)
    void Back() {
        finish();
    }


    private void setTab() {
        fragments = new ArrayList<>();
        fragments.add(new KqWeekReportFragment());
        fragments.add(new KqQuarterReportFragment());

        adapter = new ViewPagerAdapter(this, getSupportFragmentManager(), fragments);
        report_query_viewpager.setOffscreenPageLimit(1);
        report_query_viewpager.setAdapter(adapter);
        report_query_tab.setupWithViewPager(report_query_viewpager);

        for (int i = 0; i < adapter.getCount(); i++) {
            TabLayout.Tab tab = report_query_tab.getTabAt(i);//获得每一个tab
            tab.setCustomView(R.layout.report_tab);//给每一个tab设置view
            if (i == 0) {
                // 设置第一个tab的TextView是被选择的样式
                tab.getCustomView().findViewById(R.id.tab_text).setSelected(true);//第一个tab被选中
            }
            TextView textView = (TextView) tab.getCustomView().findViewById(R.id.tab_text);
            textView.setText(titles[i]);//设置tab上的文字
        }


        report_query_tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getCustomView().findViewById(R.id.tab_text).setSelected(true);
                report_query_viewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getCustomView().findViewById(R.id.tab_text).setSelected(false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
