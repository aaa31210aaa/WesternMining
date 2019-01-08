package activity.kq_report;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.dimine.project.westernmining.R;

import base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;

public class KqQuarterReportDetail extends BaseActivity {
    @BindView(R.id.toolbar_title)
    TextView toolbar_title;
    @BindView(R.id.toolbar_right)
    TextView toolbar_right;
    @BindView(R.id.kq_quarter_report_kyqmc)
    EditText kq_quarter_report_kyqmc;
    @BindView(R.id.kq_quarter_report_kyqr)
    EditText kq_quarter_report_kyqr;
    @BindView(R.id.kq_quarter_report_zh)
    EditText kq_quarter_report_zh;
    @BindView(R.id.kq_quarter_report_kz)
    EditText kq_quarter_report_kz;
    @BindView(R.id.kq_quarter_report_mj)
    EditText kq_quarter_report_mj;
    @BindView(R.id.kq_quarter_report_yxq)
    EditText kq_quarter_report_yxq;
    @BindView(R.id.kq_quarter_report_njqk)
    EditText kq_quarter_report_njqk;
    @BindView(R.id.kq_quarter_report_sbzt)
    EditText kq_quarter_report_sbzt;
    @BindView(R.id.kq_quarter_report_fgld)
    EditText kq_quarter_report_fgld;
    @BindView(R.id.kq_quarter_report_tbr)
    EditText kq_quarter_report_tbr;
    @BindView(R.id.kq_quarter_report_tbrq)
    EditText kq_quarter_report_tbrq;
    @BindView(R.id.kq_quarter_report_bz)
    EditText kq_quarter_report_bz;
    @BindView(R.id.kq_quarter_report_shr)
    EditText kq_quarter_report_shr;
    @BindView(R.id.kq_quarter_report_spzt)
    EditText kq_quarter_report_spzt;
    @BindView(R.id.kq_quarter_report_spsj)
    EditText kq_quarter_report_spsj;
    @BindView(R.id.kq_quarter_report_spyj)
    EditText kq_quarter_report_spyj;

    @Override
    public int getLayoutId() {
        return R.layout.activity_kq_quarter_report_detail;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {

    }

    @Override
    public void loadData() {
        toolbar_title.setText(R.string.kq_quarter_report_detail_title);
        toolbar_right.setText(R.string.submit);
    }

    @OnClick(R.id.toolbar_left)
    void Back() {
        finish();
    }
}
