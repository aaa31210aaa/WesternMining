package activity.kq_report;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.dimine.project.westernmining.R;

import base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;

public class KqWeekReportDetail extends BaseActivity {
    @BindView(R.id.toolbar_title)
    TextView toolbar_title;
    @BindView(R.id.toolbar_right)
    TextView toolbar_left;
    @BindView(R.id.kq_week_report_kyqmc)
    EditText kq_week_report_kyqmc;
    @BindView(R.id.kq_week_report_sbsx)
    EditText kq_week_report_sbsx;
    @BindView(R.id.kq_week_report_bzjzqk)
    EditText kq_week_report_bzjzqk;
    @BindView(R.id.kq_week_report_czwt)
    EditText kq_week_report_czwt;
    @BindView(R.id.kq_week_report_jjfajcs)
    EditText kq_week_report_jjfajcs;
    @BindView(R.id.kq_week_report_xbsx)
    EditText kq_week_report_xbsx;
    @BindView(R.id.kq_week_report_sbzt)
    EditText kq_week_report_sbzt;
    @BindView(R.id.kq_week_report_fgld)
    EditText kq_week_report_fgld;
    @BindView(R.id.kq_week_report_tbr)
    EditText kq_week_report_tbr;
    @BindView(R.id.kq_week_report_tbrq)
    EditText kq_week_report_tbrq;
    @BindView(R.id.kq_week_report_bz)
    EditText kq_week_report_bz;
    @BindView(R.id.kq_week_report_shr)
    EditText kq_week_report_shr;
    @BindView(R.id.kq_week_report_spzt)
    EditText kq_week_report_spzt;
    @BindView(R.id.kq_week_report_spsj)
    EditText kq_week_report_spsj;
    @BindView(R.id.kq_week_report_spyj)
    EditText kq_week_report_spyj;


    @Override
    public int getLayoutId() {
        return R.layout.activity_kq_week_report_detail;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
    }

    @Override
    public void loadData() {
        toolbar_title.setText(R.string.kq_week_report_detail_title);
        toolbar_left.setText(R.string.submit);
    }

    @OnClick(R.id.toolbar_left)
    void Back() {
        finish();
    }
}
