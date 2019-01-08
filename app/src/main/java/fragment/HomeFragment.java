package fragment;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.ToxicBakery.viewpager.transforms.ABaseTransformer;
import com.ToxicBakery.viewpager.transforms.AccordionTransformer;
import com.ToxicBakery.viewpager.transforms.BackgroundToForegroundTransformer;
import com.ToxicBakery.viewpager.transforms.CubeInTransformer;
import com.ToxicBakery.viewpager.transforms.CubeOutTransformer;
import com.ToxicBakery.viewpager.transforms.DefaultTransformer;
import com.ToxicBakery.viewpager.transforms.DepthPageTransformer;
import com.ToxicBakery.viewpager.transforms.FlipHorizontalTransformer;
import com.ToxicBakery.viewpager.transforms.FlipVerticalTransformer;
import com.ToxicBakery.viewpager.transforms.ForegroundToBackgroundTransformer;
import com.ToxicBakery.viewpager.transforms.RotateDownTransformer;
import com.ToxicBakery.viewpager.transforms.RotateUpTransformer;
import com.ToxicBakery.viewpager.transforms.StackTransformer;
import com.ToxicBakery.viewpager.transforms.ZoomInTransformer;
import com.ToxicBakery.viewpager.transforms.ZoomOutTranformer;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.dimine.project.westernmining.R;
import com.github.mikephil.charting.charts.LineChart;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;

import java.util.ArrayList;
import java.util.List;

import activity.dk_report.DkReportQuery;
import activity.dsdb_dk.Dk;
import activity.dsdb_kq.Kq;
import activity.dsdb_zy.Zy;
import activity.kq_report.KqReportQuery;
import activity.tzgg.Tzgg;
import activity.yjxx.Yjxx;
import activity.zy_report.ZyReportQuery;
import base.BaseFragment;
import butterknife.BindView;
import butterknife.OnClick;
import utils.BuilderManager;
import utils.LineChartManager;
import utils.LocalImageHolderView;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {
    private View view;
    @BindView(R.id.toolbar_title)
    TextView toolbar_title;
    @BindView(R.id.report_query)
    BoomMenuButton report_query;
    private int str[] = {R.string.kq, R.string.dk, R.string.zy};
    private int sub_str[] = {R.string.kq_sub, R.string.kq_sub, R.string.kq_sub};
    private int getImage[] = {R.drawable.kq_img, R.drawable.dk_img, R.drawable.zy_img};


    @BindView(R.id.homeBanner)
    ConvenientBanner homeBanner;
    @BindView(R.id.home_spinner)
    Spinner home_spinner;
    @BindView(R.id.home_linechart)
    LineChart home_linechart;
    private LineChartManager lineChartManager; //折线图管理类

    private ArrayList<String> xValues; //X轴数据
    private List<Float> yValue; //Y轴数据
    private String xValues_6[] = {"一月", "二月", "三月", "四月", "五月", "六月"};  //X轴上半年
    private String xValues_12[] = {"七月", "八月", "九月", "十月", "十一月", "十二月"};//X轴下半年
    private float total[];

    //翻页效果集
    private ArrayList<String> transformerList;
    private ABaseTransformer transforemer;
    //banner加载的图片集
    private ArrayList<Integer> localImages;
    private Class cls;
    private String transforemerName;
    //    @BindView(R.id.boom)
//    BoomMenuButton boom;
    private static int index = 0;
    private static int imageResourceIndex = 0;
    private boolean isFirst = true;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_home;
    }

    @Override
    protected void loadData() {
        toolbar_title.setText("首页");

        setHomeBanner();
        initSpinner();
        initLineChart();
        initReportQuery();
//        initBoomMenu();
    }

    private int[] press_color = {R.color.orange_normal, R.color.login_btn_enable, R.color.red, R.color.colorPrimary, R.color.colorPrimaryDark, R.color.gray_deep};

    /**
     * 设置轮播
     */
    private void setHomeBanner() {
        localImages = new ArrayList<Integer>();
        transformerList = new ArrayList<String>();

        for (int position = 1; position < 5; position++) {
            localImages.add(getResId("banner" + position, R.drawable.class));
        }

        //自定义Holder
        homeBanner.setPages(
                new CBViewHolderCreator<LocalImageHolderView>() {
                    @Override
                    public LocalImageHolderView createHolder() {
                        return new LocalImageHolderView();
                    }
                }, localImages)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused})
                //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
        // 设置翻页的效果，不需要翻页效果可以不设

//                .setPageTransformer(Transformer.CubeIn);
//        convenientBanner.setManualPageable(false);//设置不能手动影响

        //各种翻页效果
        transformerList.add(DefaultTransformer.class.getSimpleName());
        transformerList.add(AccordionTransformer.class.getSimpleName());
        transformerList.add(BackgroundToForegroundTransformer.class.getSimpleName());
        transformerList.add(CubeInTransformer.class.getSimpleName());
        transformerList.add(CubeOutTransformer.class.getSimpleName());
        transformerList.add(DepthPageTransformer.class.getSimpleName());
        transformerList.add(FlipHorizontalTransformer.class.getSimpleName());
        transformerList.add(FlipVerticalTransformer.class.getSimpleName());
        transformerList.add(ForegroundToBackgroundTransformer.class.getSimpleName());
        transformerList.add(RotateDownTransformer.class.getSimpleName());
        transformerList.add(RotateUpTransformer.class.getSimpleName());
        transformerList.add(StackTransformer.class.getSimpleName());
        transformerList.add(ZoomInTransformer.class.getSimpleName());
        transformerList.add(ZoomOutTranformer.class.getSimpleName());

        transforemerName = transformerList.get(13);
        try {
            cls = Class.forName("com.ToxicBakery.viewpager.transforms." + transforemerName);
            transforemer = (ABaseTransformer) cls.newInstance();
            homeBanner.getViewPager().setPageTransformer(true, transforemer);

            if (transforemerName.equals("StackTransformer")) {
                homeBanner.setScrollDuration(5000);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    // 开始自动翻页
    @Override
    public void onResume() {
        super.onResume();
        //开始自动翻页
        homeBanner.startTurning(5000);
    }

    // 停止自动翻页
    @Override
    public void onStop() {
        super.onStop();
        //停止翻页
        homeBanner.stopTurning();
    }

    /**
     * 矿权
     */
    @OnClick(R.id.home_kq_line)
    void Kq() {
        Intent intent = new Intent(getActivity(), Kq.class);
        startActivity(intent);
    }

    /**
     * 地勘
     */
    @OnClick(R.id.home_dk_lin)
    void Dk() {
        Intent intent = new Intent(getActivity(), Dk.class);
        startActivity(intent);
    }

    /**
     * 资源
     */
    @OnClick(R.id.home_zy_lin)
    void Zy() {
        Intent intent = new Intent(getActivity(), Zy.class);
        startActivity(intent);
    }

    /**
     * 通知公告
     */
    @OnClick(R.id.home_tzgg_lin)
    void Tzgg() {
        Intent intent = new Intent(getActivity(), Tzgg.class);
        startActivity(intent);
    }


    /**
     * 预警信息
     */
    @OnClick(R.id.home_yjxx_lin)
    void Yjxx() {
        Intent intent = new Intent(getActivity(), Yjxx.class);
        startActivity(intent);
    }

    private void initSpinner() {
        // 数据源手动添加
        ArrayAdapter<String> spinnerAadapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, getDataSource());
        spinnerAadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        home_spinner.setAdapter(spinnerAadapter);

        home_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (!isFirst) {
                    xValues.clear();
                    yValue.clear();
                    //改变上下半年条件  获取数据
                    if (adapterView.getItemAtPosition(position).toString().equals("上半年")) {
                        for (int i = 0; i < xValues_6.length; i++) {
                            xValues.add(xValues_6[i]);
                        }
                    } else {
                        for (int i = 0; i < xValues_6.length; i++) {
                            xValues.add(xValues_12[i]);
                        }
                    }


                    for (int j = 0; j < xValues_6.length; j++) {
                        yValue.add((float) (Math.random() * 80));
                    }

                    lineChartManager.showLineChart(xValues, yValue, getResources().getColor(R.color.colorAccent));
                    home_linechart.getData().notifyDataChanged();
                    home_linechart.notifyDataSetChanged();
                    home_linechart.animateXY(1500, 1500);
                }
                isFirst = false;
                return;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public List<String> getDataSource() {
        List<String> spinnerList = new ArrayList<String>();
        spinnerList.add("上半年");
        spinnerList.add("下半年");
        return spinnerList;
    }


    /**
     * 两率变化图
     */
    private void initLineChart() {
        lineChartManager = new LineChartManager(home_linechart);

        //设置x轴的数据
        xValues = new ArrayList<>();
        for (int i = 0; i < xValues_6.length; i++) {
            xValues.add(xValues_6[i]);
        }

        //设置y轴的数据()
//      List<List<Float>> yValues = new ArrayList<>();
        yValue = new ArrayList<>();
        for (int j = 0; j < xValues_6.length; j++) {
            yValue.add((float) (Math.random() * 80));  //赋值
        }

        //颜色集合
//        List<Integer> colors = new ArrayList<>();
//        colors.add(Color.GREEN);

        //线的名字集合
//        List<String> names = new ArrayList<>();
//        names.add("两率变化");

        //创建单条折线的图表
        lineChartManager.showLineChart(xValues, yValue, getResources().getColor(R.color.colorAccent));
        lineChartManager.setYAxis(100, 0, 5);
        lineChartManager.setDescription("");
        // lineChartManager1.setXAxis(10,0,xValues.size());
//        lineChartManager1.setHightLimitLine(70,"报警",Color.RED);
    }


    /**
     * 获得消息数量
     */
//    private void getMessage() {
//        OkGo.<String>get(PortIpAddress.GetTomatter())
//                .tag(this)
//                .params(TOKEN_KEY, token)
//                .params(USERID_KEY, PortIpAddress.getUserId(getActivity()))
//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(Response<String> response) {
//                        try {
//                            Log.e(TAG, response.body().toString());
//                            JSONObject jsonObject = new JSONObject(response.body().toString());
//                            String err = jsonObject.getString(MESSAGE);
//                            if (jsonObject.getString(CODE).equals(SUCCESS_CODE)) {
//                                message.add(jsonObject.getString("Matter"));
//                                message.add(jsonObject.getString("alarm"));
//                                message.add(jsonObject.getString("warning"));
//                                message.add(jsonObject.getString("message"));
//                                Log.e(TAG, message.size() + "");
//
//                                for (int i = 0; i < mDatas.size(); i++) {
//                                    HomeRvBean bean = mDatas.get(i);
//                                    if (i<mDatas.size()-1){
//                                        bean.setMsgNum(message.get(i));
//                                    }
//                                    adapter.notifyDataSetChanged();
//                                }
//                            } else {
//                                ShowToast.showShort(getActivity(), err);
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                    @Override
//                    public void onError(Response<String> response) {
//                        super.onError(response);
//                        ShowToast.showShort(getActivity(), R.string.connect_err);
//                    }
//                });
//    }


    /**
     * 报表菜单
     */
    private void initReportQuery() {
        report_query.setButtonEnum(ButtonEnum.Ham);
        for (int i = 0; i < report_query.getPiecePlaceEnum().pieceNumber(); i++)
            report_query.addBuilder(BuilderManager.getHamButtonBuilderWithDifferentPieceColor(getImage[i], str[i], sub_str[i])
                    .listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index) {
                            switch (index) {
                                case 0:
                                    //矿权报表
                                    startActivity(new Intent(getActivity(), KqReportQuery.class));
                                    break;
                                case 1:
                                    //地勘报表
                                    startActivity(new Intent(getActivity(), DkReportQuery.class));
                                    break;
                                case 2:
                                    //资源报表
                                    startActivity(new Intent(getActivity(), ZyReportQuery.class));
                                    break;
                            }
                        }
                    }));
    }


    /**
     * 弹出菜单
     */
//    private void initBoomMenu() {
//        for (int i = 0; i < boom.getPiecePlaceEnum().pieceNumber(); i++) {
//            TextInsideCircleButton.Builder builder = new TextInsideCircleButton.Builder()
//                    .listener(new OnBMClickListener() {
//                        @Override
//                        public void onBoomButtonClick(int index) {
//                            switch (index) {
//                                case 0:
//                                    break;
//                                case 1:
//                                    break;
//                                case 2:
//                                    break;
//                                case 3:
//                                    break;
//                            }
//                        }
//                    })
//                    .normalImageRes(getImageResource())
//                    .normalColorRes(press_color[i])
//                    .normalText(getext());
//            boom.addBuilder(builder);
//        }
//    }
    static String getext() {
        if (index >= text.length) index = 0;
        return text[index++];
    }

    private static String[] text = new String[]{"测试数据1", "测试数据2", "测试数据3", "测试数据4", "测试数据5", "测试数据6"};


    static int getImageResource() {
        if (imageResourceIndex >= imageResources.length) imageResourceIndex = 0;
        return imageResources[imageResourceIndex++];
    }

    private static int[] imageResources = new int[]{
            R.drawable.bat,
            R.drawable.bear,
            R.drawable.bee,
            R.drawable.butterfly,
            R.drawable.cat,
            R.drawable.deer,
    };

}
