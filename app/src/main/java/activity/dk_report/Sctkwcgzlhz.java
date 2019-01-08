package activity.dk_report;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dimine.project.westernmining.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import adapter.SctkwcgzlhzAdapter;
import base.BaseActivity;
import bean.DzkcwcgzlhzBean;
import butterknife.BindView;
import butterknife.OnClick;
import utils.DialogUtil;
import utils.DividerItemDecoration;
import utils.ShowToast;

public class Sctkwcgzlhz extends BaseActivity {
    @BindView(R.id.toolbar_title)
    TextView toolbar_title;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private List<DzkcwcgzlhzBean> mDatas;
    private List<DzkcwcgzlhzBean> searchDatas;
    private SctkwcgzlhzAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_sctkwcgzlhz;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        mDatas = new ArrayList<>();
        initRv();
        initRefresh();
    }

    @Override
    public void loadData() {
        toolbar_title.setText(R.string.sctkwcgzlhz);
        mConnect();
    }

    @OnClick(R.id.toolbar_left)
    void Back() {
        finish();
    }

    private void initRv() {
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this));
        if (adapter == null) {
            adapter = new SctkwcgzlhzAdapter(R.layout.sctkwcgzlhz_item, mDatas);
            adapter.bindToRecyclerView(recyclerView);
            adapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
            adapter.isFirstOnly(true);
            recyclerView.setAdapter(adapter);
        }
        dialog = DialogUtil.createLoadingDialog(this, R.string.loading);
    }

    private void initRefresh() {

        //刷新
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                handler.sendEmptyMessageDelayed(1, ShowToast.refreshTime);
            }
        });

        //加载
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                handler.sendEmptyMessageDelayed(0, ShowToast.refreshTime);
            }

        });
    }


    private Handler handler = new Handler() {
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            switch (msg.what) {
                case 0:
                    refreshLayout.finishLoadMore();
                    break;
                case 1:
                    mConnect();
                    break;
                default:
                    break;
            }
        }
    };


    private void mConnect() {
        if (mDatas.size() > 0)
            mDatas.clear();
        for (int i = 0; i < 10; i++) {
            DzkcwcgzlhzBean bean = new DzkcwcgzlhzBean();
            if (i == 0) {
                bean.setQyname("集团合计");
            } else {
                bean.setQyname("企业名称" + i);
            }
            bean.setByjhgzl_zt("1800.00");
            bean.setByjhgzl_kt("-");
            bean.setByjhgzl_kt_m3("-");
            bean.setBywcgzl_zt("1822.50");
            bean.setBywcgzl_kt("216.69");
            bean.setBywcgzl_kt_m3("200.21");
            bean.setYdwcl_zt("97.05");
            bean.setYdwcl_kt("216.69");
            bean.setYdwcl_kt_m3("30.56");

            bean.setNdjhgzl_zt("5000.00");
            bean.setNdjhgzl_kt("396.00");
            bean.setNdjhgzl_kt_m3("421.13");

            bean.setNdljwcgzl_zt("7100.00");
            bean.setNdljwcgzl_kt("137.56");
            bean.setNdljwcgzl_kt_m3("1004.18");

            bean.setNdljwcl_zt("20.47");
            bean.setNdljwcl_kt("150.54");
            bean.setNdljwcl_kt_m3("43.04");
            mDatas.add(bean);
        }

        //如果无数据设置空布局
        if (mDatas.size() == 0) {
            adapter.setEmptyView(R.layout.nodata_layout, (ViewGroup) recyclerView.getParent());
        } else {
            adapter.setNewData(mDatas);
        }

        if (dialog.isShowing())
            dialog.dismiss();
        if (refreshLayout.getState() == RefreshState.Refreshing)
            refreshLayout.finishRefresh();
    }
}
