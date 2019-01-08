package adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dimine.project.westernmining.R;

import java.util.List;

import bean.DzkcwcgzlhzBean;

public class DzkcwcgzlhzAdapter extends BaseQuickAdapter<DzkcwcgzlhzBean, BaseViewHolder> {
    public DzkcwcgzlhzAdapter(int layoutResId, @Nullable List<DzkcwcgzlhzBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DzkcwcgzlhzBean item) {
        helper.setText(R.id.dzkcwcgzlhz_qyname, item.getQyname());
        helper.setText(R.id.byjhgzl_zt_tv, item.getByjhgzl_zt());
        helper.setText(R.id.byjhgzl_kt_tv, item.getByjhgzl_kt());
        helper.setText(R.id.bywcgzl_zt_tv, item.getBywcgzl_zt());
        helper.setText(R.id.bywcgzl_kt_tv, item.getBywcgzl_kt());
        helper.setText(R.id.ydwcl_zt_tv, item.getYdwcl_zt());
        helper.setText(R.id.ydwcl_kt_tv, item.getYdwcl_kt());
        helper.setText(R.id.ndjhgzl_zt_tv, item.getNdjhgzl_zt());
        helper.setText(R.id.ndjhgzl_kt_tv, item.getNdjhgzl_kt());
        helper.setText(R.id.ndwcgzl_zt_tv, item.getNdwcgzl_zt());
        helper.setText(R.id.ndwcgzl_kt_tv, item.getNdwcgzl_kt());
        helper.setText(R.id.ndwcl_zt_tv, item.getNdwcl_zt());
        helper.setText(R.id.ndwcl_kt_tv, item.getNdwcl_kt());
        helper.setText(R.id.xyjh_zt_tv, item.getXyjh_zt());
        helper.setText(R.id.xyjh_kt_tv, item.getXyjh_kt());
    }
}
