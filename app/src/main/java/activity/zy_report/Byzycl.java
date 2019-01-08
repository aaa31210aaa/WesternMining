package activity.zy_report;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bin.david.form.core.SmartTable;
import com.bin.david.form.data.column.ArrayColumn;
import com.bin.david.form.data.column.Column;
import com.bin.david.form.data.table.TableData;
import com.dimine.project.westernmining.R;

import java.util.ArrayList;
import java.util.List;

import base.BaseActivity;
import bean.ByzyclBean;
import bean.ByzyclKslBean;
import bean.ByzyclKslxBean;
import bean.ByzyclKzBean;
import bean.ByzyclZylbBean;
import bean.DateBean;
import butterknife.BindView;
import butterknife.OnClick;
import utils.DateUtils;

public class Byzycl extends BaseActivity {
    @BindView(R.id.toolbar_title)
    TextView toolbar_title;
    @BindView(R.id.byzycl_table)
    SmartTable byzycl_table;
    @BindView(R.id.toolbar_right)
    TextView toolbar_right;
    private static int yindex = 0;
    private static int qindex = 0;
    //初始化的年，季度位置
    private static int yearIndex;
    private static int quarterIndex;
    //季度选择器
    private static OptionsPickerView qOption;
    //年数据
    private static ArrayList<DateBean> dateItem = new ArrayList<>();
    //月数据
    private static ArrayList<ArrayList<String>> mdateItem = new ArrayList<>();
    //季度数据
    private static ArrayList<ArrayList<String>> qdateItem = new ArrayList<>();
    //初始年份
    private static int initYear = 2005;
    //取多少年
    private static int years = 50;

    private Column<String> kqmc_column;
    private Column<String> tbdw_column;
    private Column<String> jzrq_column;
    private ArrayColumn<String> kslx_column;
    private ArrayColumn<String> ksl_column;
    private ArrayColumn<String> zylb_column;
    private ArrayColumn<String> kz_column;
    private ArrayColumn<String> hj_column;
    private Column<String> totalColumn_ksl;
    private List<ByzyclBean> byzyclList;


    private String json = "{\"data\":[{\"kqmc\":\"巴彦淖尔西部铜业有限公司获各琦多金属矿\",\"tbdw\":\"巴彦淖尔西部铜业有限公司\",\"jzrq\":\"2017/12/31\",\"kslx\":[{\"kslxname\":\"硫化矿\",\"ksl\":[{\"kslname\":\"本季度保有量\",\"zylb\":[{\"zylbname\":\"矿石量\",\"kz\":[{\"kzname\":\"Cu(%)\",\"hj\":\"0\"},{\"kzname\":\"Ag(g/t)\",\"hj\":\"0\"},{\"kzname\":\"Au(%)\",\"hj\":\"0\"}]},{\"zylbname\":\"地质品味\",\"kz\":[{\"kzname\":\"Cu(%)\",\"hj\":\"0\"},{\"kzname\":\"Ag(g/t)\",\"hj\":\"0\"},{\"kzname\":\"Au(%)\",\"hj\":\"0\"}]},{\"zylbname\":\"金属量\",\"kz\":[{\"kzname\":\"Cu(%)\",\"hj\":\"0\"},{\"kzname\":\"Ag(g/t)\",\"hj\":\"0\"},{\"kzname\":\"Au(%)\",\"hj\":\"0\"}]}]},{\"kslname\":\"本季度开采量\",\"zylb\":[{\"zylbname\":\"地质品味\",\"kz\":[{\"kzname\":\"Cu(%)\",\"hj\":\"0\"},{\"kzname\":\"Ag(g/t)\",\"hj\":\"0\"},{\"kzname\":\"Au(%)\",\"hj\":\"0\"}]},{\"zylbname\":\"金属量\",\"kz\":[{\"kzname\":\"Cu(%)\",\"hj\":\"0\"},{\"kzname\":\"Ag(g/t)\",\"hj\":\"0\"},{\"kzname\":\"Au(%)\",\"hj\":\"0\"}]}]}]}]}]}";


    @Override
    public int getLayoutId() {
        return R.layout.activity_byzycl;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        initTable();
        setData();
        initDate();
    }

    @Override
    public void loadData() {
        toolbar_title.setText(R.string.byzycl);
    }

    @OnClick(R.id.toolbar_left)
    void Back() {
        finish();
    }


    private void initDate() {
        //初始化时间 选择器
        toolbar_right.setText(DateUtils.mYear() + "-" + "1" + "季度");
        getYearCardData(toolbar_right);
        //初始化季度数据
        getQCardData(toolbar_right);
    }


    /**
     * 初始化年数据
     */
    private static void getYearCardData(TextView textView) {
        for (int i = 0; i <= years; i++) {
            ++initYear;
            String str = String.valueOf(initYear);
            dateItem.add(new DateBean(i, str));
            String st = textView.getText().toString();

            if (str.equals(st.split("-")[0])) {
                yindex = i;
                yearIndex = i;
            }
        }
    }

    /**
     * 初始化季度数据
     */
    public static void getQCardData(TextView textView) {
        String st = textView.getText().toString();
        for (int i = 0; i <= years; i++) {
            ArrayList<String> qdateItemArr = new ArrayList<>();
            for (int j = 1; j <= 4; j++) {
                int index = j;
                qdateItemArr.add(j + "");

                if (qdateItemArr.get((j - 1)).equals(st.split("-")[1])) {
                    qindex = j;
                    quarterIndex = j;
                }
            }
            qdateItem.add(qdateItemArr);
        }
    }

    @OnClick(R.id.toolbar_right)
    void Date() {
        initQuarterPicker(toolbar_right);
    }

    /**
     * 初始化季度选择器
     */
    private void initQuarterPicker(final TextView textView) {

        if (textView.getTag() == null) {
            yindex = yearIndex;
            qindex = quarterIndex;
        }


        qOption = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String str1 = dateItem.get(options1).getPickerViewText();
                String str2 = qdateItem.get(options1).get(options2);
                yindex = options1;
                qindex = options2;
                String mindex = yindex + "," + qindex;
                textView.setText(str1 + "-" + str2 + "季度");
                textView.setTag(mindex);
            }
        }).setLayoutRes(R.layout.pickerview_custom_options, new CustomListener() {
            @Override
            public void customLayout(View v) {
                final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
//              final TextView tvAdd = (TextView) v.findViewById(R.id.tv_add);
                ImageView ivCancel = (ImageView) v.findViewById(R.id.iv_cancel);
                tvSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        qOption.returnData();
                        qOption.dismiss();
                        setData();
                    }
                });

                ivCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        qOption.dismiss();
                    }
                });
            }
        })
                .setLabels("年", "季度", "")
                .setSelectOptions(yindex, qindex)
                .build();
        qOption.setPicker(dateItem, qdateItem);
        qOption.show();
    }

    private void initTable() {
        kqmc_column = new Column<String>("矿区名称", "kqmc");
        tbdw_column = new Column<String>("填报单位", "tbdw");
        jzrq_column = new Column<String>("截止日期", "jzrq");
        kslx_column = new ArrayColumn<String>("矿石类型", "kslxList.kslxName");
        ksl_column = new ArrayColumn<String>("", "kslxList.kslList.kslName");
        zylb_column = new ArrayColumn<String>("", "kslxList.kslList.zylbList.zylbName");
        kz_column = new ArrayColumn<String>("矿种", "kslxList.kslList.zylbList.kzList.kzName");
        hj_column = new ArrayColumn<String>("合计", "kslxList.kslList.zylbList.kzList.hj");
        totalColumn_ksl = new Column<String>("", ksl_column,zylb_column, kz_column);

        kqmc_column.setFixed(true); //固定列
        kz_column.setFixed(true);
        byzycl_table.setZoom(true, 2, 0.2f);
        byzycl_table.getConfig().setShowXSequence(false); //横向标示
        byzycl_table.getConfig().setShowYSequence(false); //纵向标示
    }


    private void setData() {
        TableData<ByzyclBean> tableData = new TableData<ByzyclBean>("保有资源储量汇总", getData(),
                kqmc_column, tbdw_column, jzrq_column, kslx_column, totalColumn_ksl,hj_column);
        byzycl_table.setTableData(tableData);
        byzycl_table.refreshDrawableState();
        byzycl_table.invalidate();
    }

    private List<ByzyclBean> getData() {
        byzyclList = new ArrayList<>();
        JSONObject jsonObject = JSON.parseObject(json);
        JSONArray data = jsonObject.getJSONArray("data");

        for (int i = 0; i < data.size(); i++) {
            String kqmc = data.getJSONObject(i).getString("kqmc");
            String tbdw = data.getJSONObject(i).getString("tbdw");
            String jzrq = data.getJSONObject(i).getString("jzrq");
            JSONArray kslx_array = data.getJSONObject(i).getJSONArray("kslx");
            List<ByzyclKslxBean> kslxList = new ArrayList<>();

            for (int j = 0; j < kslx_array.size(); j++) {
                String kslxName = kslx_array.getJSONObject(j).getString("kslxname");
                JSONArray ksl_array = kslx_array.getJSONObject(j).getJSONArray("ksl");
                List<ByzyclKslBean> kslList = new ArrayList<>();
                for (int k = 0; k < ksl_array.size(); k++) {
                    String kslName = ksl_array.getJSONObject(k).getString("kslname");
                    JSONArray zylb_arr = ksl_array.getJSONObject(k).getJSONArray("zylb");
                    List<ByzyclZylbBean> zylbList = new ArrayList<>();
                    for (int l = 0; l < zylb_arr.size(); l++) {
                        String zylbName = zylb_arr.getJSONObject(l).getString("zylbname");
                        JSONArray kz_array = zylb_arr.getJSONObject(l).getJSONArray("kz");
                        List<ByzyclKzBean> kzList = new ArrayList<>();
                        for (int m = 0; m < kz_array.size(); m++) {
                            String kzName = kz_array.getJSONObject(m).getString("kzname");
                            String hj = kz_array.getJSONObject(m).getString("hj");
                            ByzyclKzBean bean = new ByzyclKzBean(kzName, hj);
                            kzList.add(bean);
                        }
                        ByzyclZylbBean bean = new ByzyclZylbBean(zylbName, kzList);
                        zylbList.add(bean);
                    }
                    ByzyclKslBean bean = new ByzyclKslBean(kslName, zylbList);
                    kslList.add(bean);
                }
                ByzyclKslxBean bean = new ByzyclKslxBean(kslxName, kslList);
                kslxList.add(bean);
            }
            ByzyclBean bean = new ByzyclBean(kqmc, tbdw, jzrq, kslxList);
            byzyclList.add(bean);
        }
        return byzyclList;
    }

}
