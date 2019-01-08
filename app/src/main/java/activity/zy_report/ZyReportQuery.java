package activity.zy_report;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.dimine.project.westernmining.R;

import base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;

public class ZyReportQuery extends BaseActivity {
    @BindView(R.id.toolbar_title)
    TextView toolbar_title;

    @Override
    public int getLayoutId() {
        return R.layout.activity_zy_report_query;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {

    }

    @Override
    public void loadData() {
        toolbar_title.setText(R.string.zy_report_title);
    }

    @OnClick(R.id.toolbar_left)
    void Back() {
        finish();
    }

    /**
     * 三级矿量
     */
    @OnClick(R.id.sjkl_cv)
    void Sjkl() {
        startActivity(new Intent(this, Sjkl.class));
    }

    /**
     * 保有资源储量
     */
    @OnClick(R.id.byzycl_cv)
    void Byzycl() {
        startActivity(new Intent(this, Byzycl.class));
    }

    /**
     * 储量变动
     */
    @OnClick(R.id.clbd_cv)
    void Clbd() {
        startActivity(new Intent(this, Clbd.class));
    }

    /**
     * 损失贫化
     */
    @OnClick(R.id.ssph_cv)
    void Ssph() {
        startActivity(new Intent(this, Ssph.class));
    }


    /**
     * 开采损失明细
     */
    @OnClick(R.id.kcssmx_cv)
    void Kcssmx(){
        startActivity(new Intent(this,Kcssmx.class));
    }



//    /**
//     * 查明矿产资源储量台账
//     */
//    @OnClick(R.id.cmkczycltz_cv)
//    void Cmkczycltz() {
//        startActivity(new Intent(this,Cmkczycltz.class));
//    }
//
//    /**
//     * 分中段(台阶)分采场保有资源储量台帐
//     */
//    @OnClick(R.id.fzdbyzycltz_cv)
//    void Fzdbyzycltz() {
//        startActivity(new Intent(this,Fzdbyzycltz.class));
//    }
//
//    /**
//     * 分中段(台阶)重算增减资源储量台帐
//     */
//    @OnClick(R.id.fzdcszjzycltz_cv)
//    void Fzdcszjzycltz() {
//        startActivity(new Intent(this,Fzdcszjzycltz.class));
//    }
//
//
//    /**
//     * 分中段(台阶)分采场开采损失资源储量台帐
//     */
//    @OnClick(R.id.fzdfcckcsszycltz_cv)
//    void Fzdfcckcsszycltz() {
//        startActivity(new Intent(this,Fzdfcckcsszycltz.class));
//    }
//
//    /**
//     * 分中段(台阶)勘探增减资源储量台帐
//     */
//    @OnClick(R.id.fzdktzjzycltz_cv)
//    void Fzdktzjzycltz() {
//        startActivity(new Intent(this,Fzdktzjzycltz.class));
//    }
//
//    /**
//     * 损失率贫化率报表
//     */
//    @OnClick(R.id.sslphlbb_cv)
//    void Sslphlbb() {
//        startActivity(new Intent(this,Sslphlbb.class));
//    }
//
//    /**
//     * 资源储量变动汇总
//     */
//    @OnClick(R.id.zyclbdhz_cv)
//    void Zyclbdhz() {
//
//    }


}
