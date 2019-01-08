package activity.kq_report;


import android.app.Dialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dimine.project.westernmining.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import adapter.KqQuarterReportAdapter;
import base.BaseFragment;
import bean.KqQuarterReportBean;
import butterknife.BindView;
import butterknife.OnClick;
import utils.DateUtils;
import utils.DialogUtil;
import utils.ShowToast;

import static utils.CustomDatePicker.getQCardData;
import static utils.CustomDatePicker.getYearCardData;
import static utils.CustomDatePicker.initQuarterPicker;

/**
 * 矿权季度报表
 */
public class KqQuarterReportFragment extends BaseFragment {
    @BindView(R.id.search_edittext)
    EditText search_edittext;
    @BindView(R.id.search_clear)
    ImageView search_clear;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.quarter_date_btn)
    Button quarter_date_btn;
    private List<KqQuarterReportBean> mDatas;
    private List<KqQuarterReportBean> searchDatas;
    private KqQuarterReportAdapter adapter;
    private Dialog dialog;


    public KqQuarterReportFragment() {
        // Required empty public constructor
    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_kq_quarter_report;
    }

    @Override
    protected void loadData() {
        initRv();
        initRefresh();
        MonitorEditext();
        mConnect();
        initDate();
    }

    private void initRv() {
        mDatas = new ArrayList<>();
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);
//        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity()));
        if (adapter == null) {
            adapter = new KqQuarterReportAdapter(R.layout.rv_item4, mDatas);
            adapter.bindToRecyclerView(recyclerView);
            adapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
            adapter.isFirstOnly(true);
            recyclerView.setAdapter(adapter);
        }
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


    /**
     * 监听搜索框
     */
    private void MonitorEditext() {
        search_edittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.e(TAG, count + "----");
                if (mDatas != null) {
                    if (search_edittext.length() > 0) {
                        refreshLayout.setEnableRefresh(false);
                        search_clear.setVisibility(View.VISIBLE);
                        search(search_edittext.getText().toString().trim());
                    } else {
                        refreshLayout.setEnableRefresh(true);
                        search_clear.setVisibility(View.GONE);
                        if (adapter != null) {
                            adapter.setNewData(mDatas);
                        }
                    }
                } else {
                    if (search_edittext.length() > 0) {
                        search_clear.setVisibility(View.VISIBLE);
                    } else {
                        search_clear.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /**
     * 清除搜索框内容
     */
    @OnClick(R.id.search_clear)
    public void ClearSearch() {
        search_edittext.setText("");
        search_clear.setVisibility(View.GONE);
    }


    //搜索框
    private void search(String str) {
        if (mDatas != null) {
            searchDatas = new ArrayList<KqQuarterReportBean>();
            for (KqQuarterReportBean entity : mDatas) {
                try {
                    if (entity.getKyqmc().contains(str) || entity.getYxq().contains(str) || entity.getTbr().contains(str) || entity.getTbrq().contains(str)) {
                        searchDatas.add(entity);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                adapter.setNewData(searchDatas);
            }
        }
    }

    /**
     * 获取数据
     */
    private void mConnect() {
        dialog = DialogUtil.createLoadingDialog(getActivity(), R.string.loading);
        if (mDatas.size() > 0)
            mDatas.clear();
        for (int i = 0; i < 10; i++) {
            KqQuarterReportBean bean = new KqQuarterReportBean();
            bean.setKyqmc("矿业权名称：" + "XXXX" + i);
            bean.setYxq("2018-10-22" + "至" + "2018-10-23");
            bean.setTbr("填报人：" + "XXXX" + i);
            bean.setTbrq("填报日期：" + "XXXX" + i);
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

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                KqQuarterReportBean bean = (KqQuarterReportBean) adapter.getData().get(position);
                Intent intent = new Intent(getActivity(), KqQuarterReportDetail.class);
                startActivity(intent);
            }
        });
    }


    private void initDate() {
        //初始化时间 选择器
        quarter_date_btn.setText(DateUtils.mYear() + "-" + "1" + "季度");
        getYearCardData(quarter_date_btn);
        //初始化季度数据
        getQCardData(quarter_date_btn);
    }

    @OnClick(R.id.quarter_date_btn)
    void Date() {
        initQuarterPicker(getActivity(), quarter_date_btn, refreshLayout);
    }

}
