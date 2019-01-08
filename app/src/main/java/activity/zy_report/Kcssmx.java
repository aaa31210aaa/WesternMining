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
import bean.DateBean;
import bean.KcssmxBean;
import bean.TableKz;
import butterknife.BindView;
import butterknife.OnClick;
import utils.DateUtils;

public class Kcssmx extends BaseActivity {
    @BindView(R.id.toolbar_title)
    TextView toolbar_title;
    @BindView(R.id.kcssmx_table)
    SmartTable kcssmx_table;
    private Column<String> ksmc_column;
    private ArrayColumn<String> kz_name;
    private ArrayColumn<String> kc_jsl;
    private ArrayColumn<String> ss_jsl;
    private ArrayColumn<String> kczj_jsl;
    private ArrayColumn<String> cs;
    private ArrayColumn<String> zjssl;
    public static String[] kz_arr = {"Ag", "Au"};
    private List<KcssmxBean> kcssmxList;
    private List<TableKz> kzList;
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


    private String json = "{\"data\":[{\"ksmc\":\"玉龙\",\"kz\":[{\"kzname\":\"Cu(t)\"," +
            "\"kcjsl\":\"0\",\"ssjsl\":\"0\",\"kczjjsl\":\"0\",\"cs\":\"0\",\"zjssl\":\"0\"}," +
            "{\"kzname\":\"Ag(t)\",\"kcjsl\":\"0\",\"ssjsl\":\"0\",\"kczjjsl\":\"0\",\"cs\":\"0\",\"zjssl\":\"0\"}," +
            "{\"kzname\":\"Au(t)\",\"kcjsl\":\"0\",\"ssjsl\":\"0\",\"kczjjsl\":\"0\",\"cs\":\"0\",\"zjssl\":\"0\"}]}," +
            "{\"ksmc\":\"获各琦\",\"kz\":[{\"kzname\":\"Cu(t)\",\"kcjsl\":\"0\",\"ssjsl\":\"0\",\"kczjjsl\":\"0\",\"cs\":\"0\",\"zjssl\":\"0\"}," +
            "{\"kzname\":\"Au(t)\",\"kcjsl\":\"0\",\"ssjsl\":\"0\",\"kczjjsl\":\"0\",\"cs\":\"0\",\"zjssl\":\"0\"}]}," +
            "{\"ksmc\":\"呷村\",\"kz\":[{\"kzname\":\"Cu(t)\",\"kcjsl\":\"0\",\"ssjsl\":\"0\",\"kczjjsl\":\"0\",\"cs\":\"0\",\"zjssl\":\"0\"}," +
            "{\"kzname\":\"Au(t)\",\"kcjsl\":\"0\",\"ssjsl\":\"0\",\"kczjjsl\":\"0\",\"cs\":\"0\",\"zjssl\":\"0\"}]}]}";


    @Override
    public int getLayoutId() {
        return R.layout.activity_kcssmx;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        initTable();
        setData();
        initDate();
    }

    @Override
    public void loadData() {
        toolbar_title.setText(R.string.kcssmx);
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
        ksmc_column = new Column<String>("矿山名称", "ksmc");
        kz_name = new ArrayColumn<String>("矿种", "tableKzList.kzName");
        kc_jsl = new ArrayColumn<String>("开采金属量", "tableKzList.kcjsl");
        ss_jsl = new ArrayColumn<String>("损失金属量", "tableKzList.ssjsl");
        kczj_jsl = new ArrayColumn<String>("勘查增减金属量", "tableKzList.kczjjsl");
        cs = new ArrayColumn<String>("重算", "tableKzList.cs");
        zjssl = new ArrayColumn<String>("直接损失率", "tableKzList.zjssl");

        ksmc_column.setFixed(true); //固定列
        kz_name.setFixed(true);

        kcssmx_table.setZoom(true, 2, 0.2f);
        kcssmx_table.getConfig().setShowXSequence(false); //横向标示
        kcssmx_table.getConfig().setShowYSequence(false); //纵向标示
    }

    private void setData() {
        TableData<KcssmxBean> tableData = new TableData<KcssmxBean>("开采损失明细", getData(),
                ksmc_column, kz_name, kc_jsl, ss_jsl, kczj_jsl, cs, zjssl);
        kcssmx_table.setTableData(tableData);
        kcssmx_table.refreshDrawableState();
        kcssmx_table.invalidate();
    }


    private List<KcssmxBean> getData() {
        kcssmxList = new ArrayList<>();
        JSONObject jsonObject = JSON.parseObject(json);
        JSONArray data = jsonObject.getJSONArray("data");

        for (int i = 0; i < data.size(); i++) {
            kzList = new ArrayList<>();
            String ksmc = data.getJSONObject(i).getString("ksmc");
            JSONArray kz_array = data.getJSONObject(i).getJSONArray("kz");

            for (int j = 0; j < kz_array.size(); j++) {
                String kzName = kz_array.getJSONObject(j).getString("kzname");
                String kcjsl = kz_array.getJSONObject(j).getString("kcjsl");
                String ssjsl = kz_array.getJSONObject(j).getString("ssjsl");
                String kczjjsl = kz_array.getJSONObject(j).getString("kczjjsl");
                String cs = kz_array.getJSONObject(j).getString("cs");
                String zjssl = kz_array.getJSONObject(j).getString("zjssl");
                TableKz bean = new TableKz(kzName,kcjsl,ssjsl,kczjjsl,cs,zjssl);
                kzList.add(bean);
            }
            KcssmxBean bean = new KcssmxBean(ksmc,kzList);
            kcssmxList.add(bean);
        }

        return kcssmxList;
    }

}
