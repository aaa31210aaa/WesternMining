package activity.dk_report;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.widget.TextView;

import com.dimine.project.westernmining.R;

import base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;

public class DkReportQuery extends BaseActivity {
    @BindView(R.id.toolbar_title)
    TextView toolbar_title;
    @BindView(R.id.dzkcwcgzlhz_cv)
    CardView dzkcybbhz_cv;
    @BindView(R.id.sctkwcgzlhz_cv)
    CardView dzkcfxtb_cv;

    @Override
    public int getLayoutId() {
        return R.layout.activity_dk_report_query;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {

    }

    @Override
    public void loadData() {
        toolbar_title.setText(R.string.dk_report_title);
    }

    @OnClick(R.id.toolbar_left)
    void Back() {
        finish();
    }

    /**
     * 地质勘查完成工作量汇总
     */
    @OnClick(R.id.dzkcwcgzlhz_cv)
    void Dzkcwcgzlhz() {
        startActivity(new Intent(this, Dzkcwcgzlhz.class));
    }

    /**
     * 生产探矿完成工作量汇总
     */
    @OnClick(R.id.sctkwcgzlhz_cv)
    void Sctkwcgzlhz() {
        startActivity(new Intent(this,Sctkwcgzlhz.class));
    }


}
