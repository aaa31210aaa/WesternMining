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
 * 分中段重算增减资源储量台帐
 */
public class Fzdcszjzycltz extends BaseActivity {
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


//    @BindView(R.id.fzdcszjzycltz_table)
//    SmartTable fzdcszjzycltz_table;
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
//    private ArrayColumn<String> cllb_column;
//    private ArrayColumn<String> kz_column;
//    private ArrayColumn<String> kz_ksl;
//    private ArrayColumn<String> kz_pw;
//    private ArrayColumn<String> kz_jsl;
//    private Column<String> totalColumnCszjl;
//    private Column<String> bz_column;
//
//
//    public static String fzdJson = "{\"data\":[{\"zdtj\":\"2702\",\"bz\":\"备注信息\",\"kslx\":[{\"kslxname\":\"硫化矿型\",\"cllb\":[{\"cllbname\":\"111b\",\"kz\":[{\"kzname\":\"Ag\",\"ksl\":\"25053.30\",\"pw\":\"2.40\",\"jsl\":\"249\"},{\"kzname\":\"Au\",\"ksl\":\"33333.30\",\"pw\":\"5.40\",\"jsl\":\"112\"},{\"kzname\":\"Pb\",\"ksl\":\"12311.30\",\"pw\":\"1.40\",\"jsl\":\"222\"},{\"kzname\":\"Zn\",\"ksl\":\"41241.30\",\"pw\":\"0.40\",\"jsl\":\"111\"},{\"kzname\":\"S\",\"ksl\":\"41213.30\",\"pw\":\"5.40\",\"jsl\":\"333\"}]}," +
//            "{\"cllbname\":\"333\",\"kz\":[{\"kzname\":\"Ag\",\"ksl\":\"31231.30\",\"pw\":\"2.10\",\"jsl\":\"333\"},{\"kzname\":\"Au\",\"ksl\":\"12331.30\",\"pw\":\"6.40\",\"jsl\":\"321\"},{\"kzname\":\"Pb\",\"ksl\":\"25053.30\",\"pw\":\"2.40\",\"jsl\":\"249\"},{\"kzname\":\"Zn\",\"ksl\":\"25053.30\",\"pw\":\"2.40\",\"jsl\":\"249\"},{\"kzname\":\"S\",\"ksl\":\"25053.30\",\"pw\":\"2.40\",\"jsl\":\"249\"}]}," +
//            "{\"cllbname\":\"122b\",\"kz\":[{\"kzname\":\"Ag\",\"ksl\":\"24114.30\",\"pw\":\"1.40\",\"jsl\":\"441\"},{\"kzname\":\"Au\",\"ksl\":\"33331.30\",\"pw\":\"1.40\",\"jsl\":\"442\"},{\"kzname\":\"Pb\",\"ksl\":\"25053.30\",\"pw\":\"2.40\",\"jsl\":\"249\"},{\"kzname\":\"Zn\",\"ksl\":\"25053.30\",\"pw\":\"2.40\",\"jsl\":\"249\"},{\"kzname\":\"S\",\"ksl\":\"25053.30\",\"pw\":\"2.40\",\"jsl\":\"249\"}]}" +
//            "]}]}],\"hjdata\":[{\"zdtj\":\"2702\",\"bz\":\"备注信息\",\"kslx\":[{\"kslxname\":\"硫化矿型\",\"cllb\":[{\"cllbname\":\"111b\",\"kz\":[{\"kzname\":\"Ag\",\"ksl\":\"25053.30\",\"pw\":\"2.40\",\"jsl\":\"249\"},{\"kzname\":\"Au\",\"ksl\":\"25053.30\",\"pw\":\"2.40\",\"jsl\":\"249\"},{\"kzname\":\"Pb\",\"ksl\":\"25053.30\",\"pw\":\"2.40\",\"jsl\":\"249\"},{\"kzname\":\"Zn\",\"ksl\":\"25053.30\",\"pw\":\"2.40\",\"jsl\":\"249\"},{\"kzname\":\"S\",\"ksl\":\"25053.30\",\"pw\":\"2.40\",\"jsl\":\"249\"}]}," +
//            "{\"cllbname\":\"333\",\"kz\":[{\"kzname\":\"Ag\",\"ksl\":\"41211.30\",\"pw\":\"5.40\",\"jsl\":\"123\"},{\"kzname\":\"Au\",\"ksl\":\"55512.30\",\"pw\":\"5.40\",\"jsl\":\"111\"},{\"kzname\":\"Pb\",\"ksl\":\"25053.30\",\"pw\":\"2.40\",\"jsl\":\"249\"},{\"kzname\":\"Zn\",\"ksl\":\"25053.30\",\"pw\":\"2.40\",\"jsl\":\"249\"},{\"kzname\":\"S\",\"ksl\":\"25053.30\",\"pw\":\"2.40\",\"jsl\":\"249\"}]}," +
//            "{\"cllbname\":\"122b\",\"kz\":[{\"kzname\":\"Ag\",\"ksl\":\"33333.30\",\"pw\":\"7.40\",\"jsl\":\"551\"},{\"kzname\":\"Au\",\"ksl\":\"57552.30\",\"pw\":\"6.40\",\"jsl\":\"775\"},{\"kzname\":\"Pb\",\"ksl\":\"25053.30\",\"pw\":\"2.40\",\"jsl\":\"249\"},{\"kzname\":\"Zn\",\"ksl\":\"25053.30\",\"pw\":\"2.40\",\"jsl\":\"249\"},{\"kzname\":\"S\",\"ksl\":\"25053.30\",\"pw\":\"2.40\",\"jsl\":\"249\"}]}" +
//            "]}]}]}";
//    private List<FzdKslxBean> kslxList;


    @Override
    public int getLayoutId() {
        return R.layout.activity_fzdcszjzycltz;
    }

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
        toolbar_title.setText(R.string.fzdcszjzycltz);
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
//        bz_column = new Column<String>("备注", "bz");
//
//        kslx_column = new ArrayColumn<>("矿石类型", "fzdKslxList.FzdKslxName");
//        cllb_column = new ArrayColumn<String>("储量类别", "fzdKslxList.cllbList.cllb_name");
//        kz_column = new ArrayColumn<String>("矿种", "fzdKslxList.cllbList.kzList.kzName");
//        kz_ksl = new ArrayColumn<String>("矿石量(t)", "fzdKslxList.cllbList.kzList.kcksl");
//        kz_pw = new ArrayColumn<String>("品味%", "fzdKslxList.cllbList.kzList.kcpw");
//        kz_jsl = new ArrayColumn<String>("金属量(t)", "fzdKslxList.cllbList.kzList.kcjsl");
//        totalColumnCszjl = new Column<String>("重算增减量", kz_ksl, kz_pw, kz_jsl);
//
//        fzdcszjzycltz_table.setZoom(true, 2, 0.2f);
//        fzdcszjzycltz_table.getConfig().setShowXSequence(false); //横向标示
//        fzdcszjzycltz_table.getConfig().setShowYSequence(false); //纵向标示
//    }
//
//
//    public void setData() {
//        TableData<FzdcszjzycltzBean> tableData = new TableData<FzdcszjzycltzBean>("分中段（台阶）重算增减资源储量台账", getData(),
//                zdtj_column, kslx_column, cllb_column, kz_column, totalColumnCszjl, bz_column);
//
//        fzdcszjzycltz_table.setTableData(tableData);
//        fzdcszjzycltz_table.refreshDrawableState();
//        fzdcszjzycltz_table.invalidate();
//
//    }
//
//    private List<FzdcszjzycltzBean> getData() {
//        Random random = new Random();
//        //获取数据
//        fzdcszjzycltz = new ArrayList<>();
//
//        JSONObject jsonObject = JSON.parseObject(fzdJson);
//        JSONArray data = jsonObject.getJSONArray("data");
//        JSONArray hjdata = jsonObject.getJSONArray("hjdata");
//
//        //有几条数据
//        for (int i = 0; i < data.size(); i++) {
//            String zdtj = data.getJSONObject(i).getString("zdtj"); //中段台阶
//            String bz = data.getJSONObject(i).getString("bz"); //备注
//
//            JSONArray kslx_array = data.getJSONObject(i).getJSONArray("kslx");
//            kslxList = new ArrayList<>();
//            for (int j = 0; j < kslx_array.size(); j++) {
//                String kslxName = kslx_array.getJSONObject(j).getString("kslxname"); //矿石类型
//                JSONArray cllb_array = kslx_array.getJSONObject(j).getJSONArray("cllb");
//                List<CllbBean> cllbList = new ArrayList<>();
//                for (int k = 0; k < cllb_array.size(); k++) {
//                    String cllbName = cllb_array.getJSONObject(k).getString("cllbname");
//                    JSONArray kz_array = cllb_array.getJSONObject(k).getJSONArray("kz");
//                    List<KzBean> kzList = new ArrayList<>();
//                    for (int l = 0; l < kz_array.size(); l++) {
//                        String kzname = kz_array.getJSONObject(l).getString("kzname");
//                        String ksl = kz_array.getJSONObject(l).getString("ksl");
//                        String pw = kz_array.getJSONObject(l).getString("pw");
//                        String jsl = kz_array.getJSONObject(l).getString("jsl");
//
//                        KzBean bean = new KzBean(kzname, ksl, pw, jsl);
//                        kzList.add(bean);
//                    }
//                    CllbBean bean = new CllbBean(cllbName, kzList);
//                    cllbList.add(bean);
//                }
//                FzdKslxBean bean = new FzdKslxBean(kslxName, cllbList);
//                kslxList.add(bean);
//            }
//            FzdcszjzycltzBean bean = new FzdcszjzycltzBean(zdtj, bz, kslxList);
//            fzdcszjzycltz.add(bean);
//            //合计
//            FzdcszjzycltzBean hj_bean = new FzdcszjzycltzBean(zdtj, bz, kslxList);
//            fzdcszjzycltz.add(hj_bean);
//        }
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
