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

import static utils.CustomDatePicker.getMonthCardData;
import static utils.CustomDatePicker.getYearCardData;
import static utils.CustomDatePicker.initMonthPicker;

/**
 * 查明矿产资源储量台账
 */
public class Cmkczycltz extends BaseActivity {
    public static String json = "{\"data\":[{\"ktsj\":\"2018-12-20\"," +
            "\"kccd\":\"程度1\"," +
            "\"ktfw\":\"10\"," +
            "\"kslx\":[{\"kslxname\":\"氧化矿\",\"kz\":[" +
            "{\"kzname\":\"Ag\",\"111bksl\":\"360\",\"111bpw\":\"2.32\",\"111bjsl\":\"232\",\"122bksl\":\"321\",\"122bpw\":\"6.56\",\"122bjsl\":\"451\",\"333ksl\":\"546\",\"333pw\":\"1.23\",\"333jsl\":\"411\",\"hjksl\":\"123\",\"hjpw\":\"1.33\",\"hjjsl\":\"555\"}," +
            "{\"kzname\":\"Au\",\"111bksl\":\"555\",\"111bpw\":\"2.12\",\"111bjsl\":\"232\",\"122bksl\":\"321\",\"122bpw\":\"6.56\",\"122bjsl\":\"451\",\"333ksl\":\"546\",\"333pw\":\"1.23\",\"333jsl\":\"411\",\"hjksl\":\"123\",\"hjpw\":\"1.33\",\"hjjsl\":\"555\"}," +
            "{\"kzname\":\"Pb\",\"111bksl\":\"777\",\"111bpw\":\"2.44\",\"111bjsl\":\"232\",\"122bksl\":\"321\",\"122bpw\":\"6.56\",\"122bjsl\":\"451\",\"333ksl\":\"546\",\"333pw\":\"1.23\",\"333jsl\":\"411\",\"hjksl\":\"123\",\"hjpw\":\"1.33\",\"hjjsl\":\"555\"}," +
            "{\"kzname\":\"Zn\",\"111bksl\":\"999\",\"111bpw\":\"2.32\",\"111bjsl\":\"232\",\"122bksl\":\"321\",\"122bpw\":\"6.56\",\"122bjsl\":\"451\",\"333ksl\":\"546\",\"333pw\":\"1.23\",\"333jsl\":\"411\",\"hjksl\":\"123\",\"hjpw\":\"1.33\",\"hjjsl\":\"555\"}," +
            "{\"kzname\":\"S\",\"111bksl\":\"544\",\"111bpw\":\"2.32\",\"111bjsl\":\"232\",\"122bksl\":\"321\",\"122bpw\":\"6.56\",\"122bjsl\":\"451\",\"333ksl\":\"546\",\"333pw\":\"1.23\",\"333jsl\":\"411\",\"hjksl\":\"123\",\"hjpw\":\"1.33\",\"hjjsl\":\"555\"}]}," +
            "{\"kslxname\":\"氧化矿\",\"kz\":[" +
            "{\"kzname\":\"Ag\",\"111bksl\":\"654\",\"111bpw\":\"2.32\",\"111bjsl\":\"232\",\"122bksl\":\"321\",\"122bpw\":\"6.56\",\"122bjsl\":\"451\",\"333ksl\":\"546\",\"333pw\":\"1.23\",\"333jsl\":\"411\",\"hjksl\":\"123\",\"hjpw\":\"1.33\",\"hjjsl\":\"555\"}," +
            "{\"kzname\":\"Au\",\"111bksl\":\"1123\",\"111bpw\":\"2.32\",\"111bjsl\":\"232\",\"122bksl\":\"321\",\"122bpw\":\"6.56\",\"122bjsl\":\"451\",\"333ksl\":\"546\",\"333pw\":\"1.23\",\"333jsl\":\"411\",\"hjksl\":\"123\",\"hjpw\":\"1.33\",\"hjjsl\":\"555\"}," +
            "{\"kzname\":\"Pb\",\"111bksl\":\"312\",\"111bpw\":\"2.32\",\"111bjsl\":\"232\",\"122bksl\":\"321\",\"122bpw\":\"6.56\",\"122bjsl\":\"451\",\"333ksl\":\"546\",\"333pw\":\"1.23\",\"333jsl\":\"411\",\"hjksl\":\"123\",\"hjpw\":\"1.33\",\"hjjsl\":\"555\"}," +
            "{\"kzname\":\"Zn\",\"111bksl\":\"456\",\"111bpw\":\"2.32\",\"111bjsl\":\"232\",\"122bksl\":\"321\",\"122bpw\":\"6.56\",\"122bjsl\":\"451\",\"333ksl\":\"546\",\"333pw\":\"1.23\",\"333jsl\":\"411\",\"hjksl\":\"123\",\"hjpw\":\"1.33\",\"hjjsl\":\"555\"}," +
            "{\"kzname\":\"S\",\"111bksl\":\"678\",\"111bpw\":\"2.32\",\"111bjsl\":\"232\",\"122bksl\":\"321\",\"122bpw\":\"6.56\",\"122bjsl\":\"451\",\"333ksl\":\"546\",\"333pw\":\"1.23\",\"333jsl\":\"411\",\"hjksl\":\"123\",\"hjpw\":\"1.33\",\"hjjsl\":\"555\"}]}" +
            "]}]}";

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
    public static String kz_arr[] = {"Ag", "Au", "Pb", "Zn", "S"};
    @BindView(R.id.cmkczycltz_kz)
    Spinner cmkczycltz_kz;
    private Dialog dialog;
//        @BindView(R.id.cmkczycltz_table)
//    SmartTable cmkczycltz_table;
//    public static String[] kz_arr = {"Ag", "Au", "Pb", "Zn", "S"};
//    public static String[] kslx_arr = {"氧化矿", "硫化矿"};
//
//    private List<CmkczycltzBean> cmkczycltz;
//
//
//    private Column<String> ktsj_column;
//    private Column<String> kccd_column;
//    private Column<String> ktfw_column;
//    private ArrayColumn<String> kslx_column;
//
//    private Column totalColumn_cllb;
//    private Column totalColumn111b;
//    private Column totalColumn122b;
//    private Column totalColumn333;
//    private Column totalColumn_hj_or;
//    private List<KslxBean> kslxList;
//
//    private static Calendar calendarDate = Calendar.getInstance();
//    //月报选择器
//    private static TimePickerView monthOption;
//    private static SimpleDateFormat sdfMonth = new SimpleDateFormat("yyyy-MM");

    @Override
    public int getLayoutId() {
        return R.layout.activity_cmkczycltz;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        initSpinner();
        initDate();
//      initTable();
//      setData();
        initRv();
        initRefresh();
        MonitorEditext();
        mConnect();
    }

    @Override
    public void loadData() {
        toolbar_title.setText(R.string.cmkczycltz);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    //    public void initTable() {
//        ktsj_column = new Column<>("勘探时间", "ktsj");
//        kccd_column = new Column<>("勘查程度", "kccd");
//        ktfw_column = new Column<>("勘探范围", "ktfw");
//        kslx_column = new ArrayColumn<>("矿石类型", "kslxList.kslxName");
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
////        kz_ksl.setAutoCount(true);  //开启统计
//
//        //合并单元
//        totalColumn_cllb = new Column("储量类别", kz_column);
//        totalColumn111b = new Column("111b（331）", kc_ksl, kc_pw, kc_jsl);
//        totalColumn122b = new Column("122b（332）", kz_ksl, kz_pw, kz_jsl);
//        totalColumn333 = new Column("333", td_ksl, td_pw, td_jsl);
//        totalColumn_hj_or = new Column("合计", hj_ksl, hj_pw, hj_jsl);
//        ktsj_column.setFixed(true); //固定列
//        totalColumn_cllb.setFixed(true);
//
//        //      tableData.setShowCount(true);
//        cmkczycltz_table.setZoom(true, 2, 0.2f);
//        cmkczycltz_table.getConfig().setShowXSequence(false); //横向标示
//        cmkczycltz_table.getConfig().setShowYSequence(false); //纵向标示
//
//    }
//
//
//    private List<CmkczycltzBean> getData() {
//        Random random = new Random();
//        //获取数据
//        cmkczycltz = new ArrayList<>();
////        dialog = DialogUtil.createLoadingDialog(Cmkczycltz.this, R.string.loading_write);
//
//
//        JSONObject jsonObject = JSON.parseObject(json);
//        JSONArray data = jsonObject.getJSONArray("data");
//
//        //有几条数据
//        for (int i = 0; i < data.size(); i++) {
//            String ktsj = data.getJSONObject(i).getString("ktsj"); //勘探时间
//            String kccd = data.getJSONObject(i).getString("kccd"); //勘查程度
//            String ktfw = data.getJSONObject(i).getString("ktfw"); //勘探范围
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
//            CmkczycltzBean bean = new CmkczycltzBean(ktsj, kccd, ktfw, kslxList);
//            cmkczycltz.add(bean);
//
//            //创建每条数据的合计数据
//            CmkczycltzBean cmk = new CmkczycltzBean("合计", "", "", kslxList);
//            cmkczycltz.add(cmk);
//        }
//
//        //创建纵向合计  累计探明
//        CmkczycltzBean ljtm = new CmkczycltzBean("累计探明", "", "", kslxList);
//        cmkczycltz.add(ljtm);
//
////        //多少条数据
//////        for (int i = 0; i < 1; i++) {
////        List<KslxBean> kslxList = new ArrayList<>();
////        List<KzBean> kzList = new ArrayList<>();
////
////        //赋值矿种数据
////        for (int j = 0; j < kz_arr.length; j++) {
////            KzBean kz = new KzBean(kz_arr[j],
////                    String.valueOf(random.nextInt(70) * 80),
////                    String.valueOf(random.nextInt(70) * 80),
////                    String.valueOf(random.nextInt(70) * 80));
////            kzList.add(kz);
////        }
////
////        //获取矿石类型
////        for (int o = 0; o < kslx_arr.length; o++) {
////            KslxBean bean = new KslxBean(kslx_arr[o], kzList);
////            kslxList.add(bean);
////        }
////
////        //有几条数据
////        for (int k = 0; k < 2; k++) {
////            CmkczycltzBean bean = new CmkczycltzBean("勘探时间" + k, "勘查程度" + k, "勘探范围" + k, "合计" + k, kslxList);
////            cmkczycltz.add(bean);
////            //创建每条数据的合计数据
////            CmkczycltzBean cmk = new CmkczycltzBean("合计", "", "", "", kslxList);
////            cmkczycltz.add(cmk);
////        }
////
////
////        //创建纵向合计  累计探明
////        CmkczycltzBean ljtm = new CmkczycltzBean("累计探明", "", "", "", kslxList);
////        cmkczycltz.add(ljtm);
//////        }
//        return cmkczycltz;
//    }


//    public void setData() {
//        TableData<CmkczycltzBean> tableData = new TableData<CmkczycltzBean>("查明矿产资源储量台账", getData(),
//                ktsj_column, kccd_column, ktfw_column, kslx_column,
//                totalColumn_cllb, totalColumn111b, totalColumn122b, totalColumn333, totalColumn_hj_or);
//        cmkczycltz_table.setTableData(tableData);
//        cmkczycltz_table.refreshDrawableState();
//        cmkczycltz_table.invalidate();
//    }


}
