package activity.zy_report;

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
 * 分中段分采场保有资源储量台帐
 */
public class Fzdbyzycltz extends BaseActivity {
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


//    @BindView(R.id.fzdbyzycltz_table)
//    SmartTable fzdbyzycltz_table;
//    private static Calendar calendarDate = Calendar.getInstance();
//    //月报选择器
//    private static TimePickerView monthOption;
//    private static SimpleDateFormat sdfMonth = new SimpleDateFormat("yyyy-MM");
//
//    private Column<String> zdtj_column;
//    private Column<String> kt_column;
//    private ArrayColumn<String> kslx_column;
//    private Column<String> bz_column;
//
//    private Column totalColumn_cllb;
//    private Column totalColumn111b;
//    private Column totalColumn122b;
//    private Column totalColumn333;
//    private Column totalColumn_hj_or;
//    private List<FzdbyzycltzBean> fzdbyzycltz;
//    private List<KslxBean> kslxList;

    @Override
    public int getLayoutId() {
        return R.layout.activity_fzdbyzycltz;
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
        toolbar_title.setText(R.string.fzdbyzycltz);
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
                //选择矿种
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
        initMonthPicker(this, toolbar_right, refreshLayout);
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
//        zdtj_column = new Column<>("中段(台阶)", "zdtj");
//        kt_column = new Column<>("矿体", "kt");
//        bz_column = new Column<String>("备注", "bz");
//        kslx_column = new ArrayColumn<>("矿石类型", "kslxList.kslxName");
//
//        ArrayColumn<String> kz_column = new ArrayColumn<String>("矿种", "kslxList.kzList.kzName");
//        ArrayColumn<String> kc_ksl = new ArrayColumn<String>("矿石量(t)", "kslxList.kzList.kcksl");
//        ArrayColumn<String> kc_pw = new ArrayColumn<String>("品味%", "kslxList.kzList.kcpw");
//        ArrayColumn<String> kc_jsl = new ArrayColumn<String>("金属量(t)", "kslxList.kzList.kcjsl");
//
//        ArrayColumn<String> kz_ksl = new ArrayColumn<String>("矿石量(t)", "kslxList.kzList.kzksl");
//        ArrayColumn<String> kz_pw = new ArrayColumn<String>("品味%", "kslxList.kzList.kzpw");
//        ArrayColumn<String> kz_jsl = new ArrayColumn<String>("金属量(t)", "kslxList.kzList.kzjsl");
//
//        ArrayColumn<String> td_ksl = new ArrayColumn<String>("矿石量(t)", "kslxList.kzList.tdksl");
//        ArrayColumn<String> td_pw = new ArrayColumn<String>("品味%", "kslxList.kzList.tdpw");
//        ArrayColumn<String> td_jsl = new ArrayColumn<String>("金属量(t)", "kslxList.kzList.tdjsl");
//
//        ArrayColumn<String> hj_ksl = new ArrayColumn<String>("矿石量(t)", "kslxList.kzList.hjksl");
//        ArrayColumn<String> hj_pw = new ArrayColumn<String>("品味%", "kslxList.kzList.hjpw");
//        ArrayColumn<String> hj_jsl = new ArrayColumn<String>("金属量(t)", "kslxList.kzList.hjjsl");
//
//
//        totalColumn_cllb = new Column("储量类别", kz_column);
//        totalColumn111b = new Column("111b（331）", kc_ksl, kc_pw, kc_jsl);
//        totalColumn122b = new Column("122b（332）", kz_ksl, kz_pw, kz_jsl);
//        totalColumn333 = new Column("333", td_ksl, td_pw, td_jsl);
//        totalColumn_hj_or = new Column("合计", hj_ksl, hj_pw, hj_jsl);
//
//        zdtj_column.setFixed(true);
//        totalColumn_cllb.setFixed(true);
//
//        fzdbyzycltz_table.setZoom(true, 2, 0.2f);
//        fzdbyzycltz_table.getConfig().setShowXSequence(false); //横向标示
//        fzdbyzycltz_table.getConfig().setShowYSequence(false); //纵向标示
//    }
//
//    public void setData() {
//        TableData<FzdbyzycltzBean> tableData = new TableData<FzdbyzycltzBean>("分采场保有资源储量台账", getData(),
//                zdtj_column, kt_column, kslx_column,
//                totalColumn_cllb, totalColumn111b, totalColumn122b, totalColumn333, totalColumn_hj_or, bz_column);
//        fzdbyzycltz_table.setTableData(tableData);
//    }
//
//    private List<FzdbyzycltzBean> getData() {
//        //        dialog = DialogUtil.createLoadingDialog(Cmkczycltz.this, R.string.loading_write);
//        Random random = new Random();
//        //获取数据
//        fzdbyzycltz = new ArrayList<>();
//        JSONObject jsonObject = JSON.parseObject(json);
//        JSONArray data = jsonObject.getJSONArray("data");
//
//        //有几条数据
//        for (int i = 0; i < data.size(); i++) {
//            String ktsj = data.getJSONObject(i).getString("ktsj"); //勘探时间
//            String kccd = data.getJSONObject(i).getString("kccd"); //勘查程度
//            String ktfw = data.getJSONObject(i).getString("ktfw"); //勘探范围
//
//
//            JSONArray kslx_array = data.getJSONObject(i).getJSONArray("kslx");
//            kslxList = new ArrayList<>();
//            for (int j = 0; j < kslx_array.size(); j++) {
//                String kslxName = kslx_array.getJSONObject(j).getString("kslxname"); //矿石类型
//                JSONArray kz_array = kslx_array.getJSONObject(j).getJSONArray("kz");
//
//                List<KzBean> kzList = new ArrayList<>();
//                for (int k = 0; k < kz_array.size(); k++) {
//                    String kzName = kz_array.getJSONObject(k).getString("kzname");//矿种
//                    String kcksl = kz_array.getJSONObject(k).getString("111bksl");//可采矿石量
//                    String kcpw = kz_array.getJSONObject(k).getString("111bpw");//可采品味
//                    String kcjsl = kz_array.getJSONObject(k).getString("111bjsl");//可采金属量
//                    String kzksl = kz_array.getJSONObject(k).getString("122bksl"); //控制矿石量
//                    String kzpw = kz_array.getJSONObject(k).getString("122bpw");//控制品味
//                    String kzjsl = kz_array.getJSONObject(k).getString("122bjsl");//控制金属量
//                    String tdksl = kz_array.getJSONObject(k).getString("333ksl");//推断矿石量
//                    String tdpw = kz_array.getJSONObject(k).getString("333pw");// 推断品位
//                    String tdjsl = kz_array.getJSONObject(k).getString("333jsl");// 推断金属量
//                    String hjksl = kz_array.getJSONObject(k).getString("hjksl");// 合计矿石量
//                    String hjpw = kz_array.getJSONObject(k).getString("hjpw");// 合计品位
//                    String hjjsl = kz_array.getJSONObject(k).getString("hjjsl");// 合计金属量
//
//                    KzBean bean = new KzBean(kzName, kcksl, kcpw, kcjsl, kzksl, kzpw, kzjsl, tdksl, tdpw, tdjsl, hjksl, hjpw, hjjsl);
//                    kzList.add(bean);
//                }
//                KslxBean kslxBean = new KslxBean(kslxName, kzList);
//                kslxList.add(kslxBean);
//            }
//            FzdbyzycltzBean bean = new FzdbyzycltzBean(ktsj, kccd, ktfw, "", kslxList);
//            fzdbyzycltz.add(bean);
//        }
//
//        //创建纵向合计  累计探明
//        FzdbyzycltzBean ljtm = new FzdbyzycltzBean("合计", "", "", "", kslxList);
//        fzdbyzycltz.add(ljtm);
//
//
//        return fzdbyzycltz;
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
