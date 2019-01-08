package activity.zy_report;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.bin.david.form.core.SmartTable;
import com.dimine.project.westernmining.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;
import utils.DateUtils;

import static utils.CustomDatePicker.getMonthCardData;
import static utils.CustomDatePicker.getMonthTime;
import static utils.CustomDatePicker.getYearCardData;

/**
 * 损失率贫化率报表
 */
public class Sslphlbb extends BaseActivity {
    @BindView(R.id.toolbar_title)
    TextView toolbar_title;
    @BindView(R.id.toolbar_right)
    TextView toolbar_right;
    @BindView(R.id.sslphlbb_table)
    SmartTable sslphlbb_table;

    private static Calendar calendarDate = Calendar.getInstance();
    //月报选择器
    private static TimePickerView monthOption;
    private static SimpleDateFormat sdfMonth = new SimpleDateFormat("yyyy-MM");


    @Override
    public int getLayoutId() {
        return R.layout.activity_sslphlbb;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        initDate();
//        initTable();
//        setData();
    }

    @Override
    public void loadData() {
        toolbar_title.setText(R.string.sslphlbb);
    }


    @OnClick(R.id.toolbar_left)
    void Back() {
        finish();
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
        initMonthPicker(toolbar_right);
    }



    /**
     * 年月dialog
     */
    //初始化月报选择器
    private void initMonthPicker(final TextView textView) {
        //控制时间范围(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
        //因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
        final Calendar selectedMonthDate;

        if (textView.getTag() == null) {
            selectedMonthDate = (Calendar) calendarDate.clone();
        } else {
            selectedMonthDate = (Calendar) textView.getTag();
        }
        Calendar startDate = Calendar.getInstance();
        startDate.set(2005, 0, 1);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2055, 11, 31);
        //时间选择器
        monthOption = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                // 这里回调过来的v,就是show()方法里面所添加的 View 参数，如果show的时候没有添加参数，v则为null
                /*btn_Time.setText(getTime(date));*/
                textView.setText(getMonthTime(date));
//                setData();
                try {
                    date = sdfMonth.parse(textView.getText().toString());
                    selectedMonthDate.setTime(date);
                    textView.setTag(selectedMonthDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        })
                //年月日时分秒 的显示与否，不设置则默认全部显示
                .setType(new boolean[]{true, true, false, false, false, false})
                .setLabel("年", "月", "", "", "", "")
                .isCenterLabel(false)
                .setDividerColor(Color.DKGRAY)
                .setContentSize(21)
                .setDate(selectedMonthDate)
                .setRangDate(startDate, endDate)
                .setBackgroundId(0x00FFFFFF) //设置外部遮罩颜色
                .setDecorView(null)
                .build();
        monthOption.show();
    }

}
