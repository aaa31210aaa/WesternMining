package adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dimine.project.westernmining.R;

import java.util.List;

import bean.KqWeekReportBean;

public class KqWeekReportAdaper extends BaseQuickAdapter<KqWeekReportBean, BaseViewHolder> {

    public KqWeekReportAdaper(int layoutResId, @Nullable List<KqWeekReportBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, KqWeekReportBean item) {
        helper.setText(R.id.item1, item.getKyqmc());
        helper.setText(R.id.item2, item.getSbsx());
        helper.setText(R.id.item3, item.getTbr());
        helper.setText(R.id.item4, item.getTbrq());
    }
}
