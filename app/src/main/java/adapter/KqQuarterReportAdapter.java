package adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dimine.project.westernmining.R;

import java.util.List;

import bean.KqQuarterReportBean;

public class KqQuarterReportAdapter extends BaseQuickAdapter<KqQuarterReportBean, BaseViewHolder> {
    public KqQuarterReportAdapter(int layoutResId, @Nullable List<KqQuarterReportBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, KqQuarterReportBean item) {
        helper.setText(R.id.item1, item.getKyqmc());
        helper.setText(R.id.item2, item.getYxq());
        helper.setText(R.id.item3, item.getTbr());
        helper.setText(R.id.item4, item.getTbrq());
    }
}
