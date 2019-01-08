package adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dimine.project.westernmining.R;

import java.util.List;

import bean.DzkcwcgzlhzBean;

public class SctkwcgzlhzAdapter extends BaseQuickAdapter<DzkcwcgzlhzBean, BaseViewHolder> {
    public SctkwcgzlhzAdapter(int layoutResId, @Nullable List<DzkcwcgzlhzBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DzkcwcgzlhzBean item) {
        helper.setText(R.id.sctkwcgzlhz_qyname,item.getQyname());
        helper.setText(R.id.byjhgzl_zt_tv, item.getByjhgzl_zt());
        helper.setText(R.id.byjhgzl_kt_m, item.getByjhgzl_kt());
        helper.setText(R.id.byjhgzl_kt_m3, item.getByjhgzl_kt_m3());
        helper.setText(R.id.bywcgzl_zt_tv, item.getBywcgzl_zt());
        helper.setText(R.id.bywcgzl_kt_m, item.getBywcgzl_kt());
        helper.setText(R.id.bywcgzl_kt_m3, item.getBywcgzl_kt_m3());
        helper.setText(R.id.ydwcl_zt_tv, item.getYdwcl_zt());
        helper.setText(R.id.ydwcl_kt_m, item.getYdwcl_kt());
        helper.setText(R.id.ydwcl_kt_m3, item.getYdwcl_kt_m3());
        helper.setText(R.id.ndjhgzl_zt_tv, item.getNdjhgzl_zt());
        helper.setText(R.id.ndjhgzl_kt_m, item.getNdjhgzl_kt());
        helper.setText(R.id.ndjhgzl_kt_m3, item.getNdjhgzl_kt_m3());
        helper.setText(R.id.ndljwcgzl_zt_tv, item.getNdljwcgzl_zt());
        helper.setText(R.id.ndljwcgzl_kt_m, item.getNdljwcgzl_kt());
        helper.setText(R.id.ndljwcgzl_kt_m3, item.getNdljwcgzl_kt_m3());
        helper.setText(R.id.ndljwcl_zt_tv, item.getNdljwcl_zt());
        helper.setText(R.id.ndljwcl_kt_m, item.getNdljwcl_kt());
        helper.setText(R.id.ndljwcl_kt_m3, item.getNdljwcl_kt_m3());

    }
}
