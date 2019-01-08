package activity.zy_report;

import android.app.Dialog;
import android.os.Bundle;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.dimine.project.westernmining.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import adapter.CmkczycltzAdapter;
import base.BaseActivity;
import bean.CmkczycltzBean;
import butterknife.BindView;
import butterknife.OnClick;
import utils.DateUtils;
import utils.DialogUtil;
import utils.DividerItemDecoration;
import utils.ShowToast;

import static activity.zy_report.Cmkczycltz.kz_arr;
import static utils.CustomDatePicker.getMonthCardData;
import static utils.CustomDatePicker.getYearCardData;
import static utils.CustomDatePicker.initMonthPicker;

/**
 * 分中段（台阶）勘探增减资源储量台账
 */
public class Fzdktzjzycltz extends BaseActivity {
    @BindView(R.id.toolbar_title)
    TextView toolbar_title;
    @BindView(R.id.toolbar_right)
    TextView toolbar_right;
    @BindView(R.id.search_edittext)
    EditText search_edittext;
    @BindView(R.id.search_clear)
    ImageView search_clear;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private List<CmkczycltzBean> mDatas;
    private List<CmkczycltzBean> searchDatas;
    private CmkczycltzAdapter adapter;
    @BindView(R.id.cmkczycltz_kz)
    Spinner cmkczycltz_kz;
    private Dialog dialog;

//    @BindView(R.id.fzdktzjzycltz_table)
//    SmartTable fzdktzjzycltz_table;
//    private static Calendar calendarDate = Calendar.getInstance();
//    //月报选择器
//    private static TimePickerView monthOption;
//    private static SimpleDateFormat sdfMonth = new SimpleDateFormat("yyyy-MM");
//
//    public static String[] cllb_arr = {"111b", "333", "122b"};
//
//    private List<FzdcszjzycltzBean> fzdcszjzycltz;
//    private Column<String> zdtj_column;
//    private ArrayColumn<String> kslx_column;
//
//    private ArrayColumn<String> cllb_column;
//    private ArrayColumn<String> kz_column;
//    private ArrayColumn<String> kz_ksl;
//    private ArrayColumn<String> kz_pw;
//    private ArrayColumn<String> kz_jsl;
//    private Column<String> totalColumnKtbgcmzycl; //勘探报告查明资源储量
//    private Column<String> totalColumnSctksjl;   //生产探矿升级量
//    private Column<String> totalColumnEctksjl;  //二次探矿升级量
//    private Column<String> totalColumnKtsjzjl; //勘探升级增减量


    @Override
    public int getLayoutId() {
        return R.layout.activity_fzdktzjzycltz;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        initSpinner();
        initDate();
//        initTable();
//        setData();
        initRv();
        initRefresh();
        MonitorEditext();
        mConnect();
    }

    @Override
    public void loadData() {
        toolbar_title.setText(R.string.fzdktzjzycltz);
    }

    @OnClick(R.id.toolbar_left)
    void Back() {
        finish();
    }

    /**
     * 获取矿种
     */
    private void initSpinner() {
        // 数据源手动添加
        ArrayAdapter<String> spinnerAadapter = new ArrayAdapter<String>(this,
                R.layout.custom_spiner_text_item, getDataSource());
        spinnerAadapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);
        cmkczycltz_kz.setAdapter(spinnerAadapter);

        cmkczycltz_kz.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public List<String> getDataSource() {
        List<String> spinnerList = new ArrayList<String>();
        for (int i = 0; i < kz_arr.length; i++) {
            spinnerList.add(kz_arr[i]);
        }
        return spinnerList;
    }

    /**
     * 初始化时间
     */
    private void initDate() {
        toolbar_right.setText(DateUtils.getStringDate());
        getYearCardData(toolbar_right);
        getMonthCardData(toolbar_right);
    }


    @OnClick(R.id.toolbar_right)
    void Date() {
        initMonthPicker(this,toolbar_right,refreshLayout);
    }


    private void initRv() {
        mDatas = new ArrayList<>();
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this));
        if (adapter == null) {
            adapter = new CmkczycltzAdapter(R.layout.cmkczycltz_item, mDatas);
            adapter.bindToRecyclerView(recyclerView);
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
            searchDatas = new ArrayList<CmkczycltzBean>();
            for (CmkczycltzBean entity : mDatas) {
                try {
                    if (entity.getKsl().contains(str) || entity.getPw().contains(str) || entity.getJsl().contains(str)) {
                        searchDatas.add(entity);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                adapter.setNewData(searchDatas);
            }
        }
    }

    private void mConnect() {
        if (mDatas.size() > 0)
            mDatas.clear();

        for (int i = 0; i < 10; i++) {
            CmkczycltzBean bean = new CmkczycltzBean();
            bean.setKsl("矿石量" + i);
            bean.setPw("品味" + i);
            bean.setJsl("金属量" + i);
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



//    public void initTable() {
//        zdtj_column = new Column<String>("中段(台阶)", "zdtj");
//
//        kslx_column = new ArrayColumn<>("矿石类型", "fzdKslxList.FzdKslxName");
//        cllb_column = new ArrayColumn<String>("储量类别", "fzdKslxList.cllbList.cllb_name");
//        kz_column = new ArrayColumn<String>("矿种", "fzdKslxList.cllbList.kzList.kzName");
//        kz_ksl = new ArrayColumn<String>("矿石量(t)", "fzdKslxList.cllbList.kzList.ksl");
//        kz_pw = new ArrayColumn<String>("品味%", "fzdKslxList.cllbList.kzList.pw");
//        kz_jsl = new ArrayColumn<String>("金属量(t)", "fzdKslxList.cllbList.kzList.jsl");
//
//        //合并
//        totalColumnKtbgcmzycl = new Column<String>("勘探报告查明资源储量", kz_ksl, kz_pw, kz_jsl);
//        totalColumnSctksjl = new Column<String>("生产探矿升级量", kz_ksl, kz_pw, kz_jsl);
//        totalColumnEctksjl = new Column<String>("二次探矿升级量", kz_ksl, kz_pw, kz_jsl);
//        totalColumnKtsjzjl = new Column<String>("勘探升级增减量", kz_ksl, kz_pw, kz_jsl);
//
//
//        fzdktzjzycltz_table.setZoom(true, 2, 0.2f);
//        fzdktzjzycltz_table.getConfig().setShowXSequence(false); //横向标示
//        fzdktzjzycltz_table.getConfig().setShowYSequence(false); //纵向标示
//    }
//
//
//    public void setData() {
//        TableData<FzdcszjzycltzBean> tableData = new TableData<FzdcszjzycltzBean>("分中段勘探增减资源储量台帐", getData(),
//                zdtj_column, kslx_column, cllb_column, kz_column, totalColumnKtbgcmzycl, totalColumnSctksjl, totalColumnEctksjl, totalColumnKtsjzjl);
//
//        fzdktzjzycltz_table.setTableData(tableData);
//        fzdktzjzycltz_table.refreshDrawableState();
//        fzdktzjzycltz_table.invalidate();
//
//    }
//
//    private List<FzdcszjzycltzBean> getData() {
//        Random random = new Random();
//        //获取数据
//        fzdcszjzycltz = new ArrayList<>();
////        List<FzdKslxBean> fzdKslxList = new ArrayList<>();
////        List<CllbBean> cllbList = new ArrayList<>();
////        List<KzBean> kzList = new ArrayList<>();
////        //赋值矿种数据
////        for (int i = 0; i < kz_arr.length; i++) {
////            KzBean kz = new KzBean(kz_arr[i], String.valueOf(random.nextInt(70) * 80), String.valueOf(random.nextInt(70) * 80), String.valueOf(random.nextInt(70) * 80));
////            kzList.add(kz);
////            //赋值储量类别
////        }
////
////        //获取储量类别
////        for (int j = 0; j < 3; j++) {
////            CllbBean bean = new CllbBean(cllb_arr[j], kzList);
////            cllbList.add(bean);
////        }
////
////        //获取矿石类型
////        for (int o = 0; o < kslx_arr.length; o++) {
////            FzdKslxBean bean = new FzdKslxBean(kslx_arr[o], cllbList);
////            fzdKslxList.add(bean);
////        }
////
////        //有几条数据
////        for (int k = 0; k < 2; k++) {
////            FzdcszjzycltzBean fzd = new FzdcszjzycltzBean("中段" + k, "矿石类型" + k, "备注", fzdKslxList);
////            fzdcszjzycltz.add(fzd);
////        }
////
////        //创建纵向合计
////        FzdcszjzycltzBean hj = new FzdcszjzycltzBean("合计", "", "", fzdKslxList);
////        fzdcszjzycltz.add(hj);
//
//
//        return fzdcszjzycltz;
//    }
//
//
//    /**
//     * 年月dialog
//     */
//    //初始化月报选择器
//    private void initMonthPicker(final TextView textView) {
//        //控制时间范围(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
//        //因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
//        final Calendar selectedMonthDate;
//
//        if (textView.getTag() == null) {
//            selectedMonthDate = (Calendar) calendarDate.clone();
//        } else {
//            selectedMonthDate = (Calendar) textView.getTag();
//        }
//        Calendar startDate = Calendar.getInstance();
//        startDate.set(2005, 0, 1);
//        Calendar endDate = Calendar.getInstance();
//        endDate.set(2055, 11, 31);
//        //时间选择器
//        monthOption = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
//            @Override
//            public void onTimeSelect(Date date, View v) {//选中事件回调
//                // 这里回调过来的v,就是show()方法里面所添加的 View 参数，如果show的时候没有添加参数，v则为null
//                /*btn_Time.setText(getTime(date));*/
//                textView.setText(getMonthTime(date));
//                setData();
//                try {
//                    date = sdfMonth.parse(textView.getText().toString());
//                    selectedMonthDate.setTime(date);
//                    textView.setTag(selectedMonthDate);
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//            }
//        })
//                //年月日时分秒 的显示与否，不设置则默认全部显示
//                .setType(new boolean[]{true, true, false, false, false, false})
//                .setLabel("年", "月", "", "", "", "")
//                .isCenterLabel(false)
//                .setDividerColor(Color.DKGRAY)
//                .setContentSize(21)
//                .setDate(selectedMonthDate)
//                .setRangDate(startDate, endDate)
//                .setBackgroundId(0x00FFFFFF) //设置外部遮罩颜色
//                .setDecorView(null)
//                .build();
//        monthOption.show();
//    }
}
