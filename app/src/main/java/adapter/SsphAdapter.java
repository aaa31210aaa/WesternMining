package adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import bean.SsphBean;

public class SsphAdapter extends BaseQuickAdapter<SsphBean, BaseViewHolder> {
    public SsphAdapter(int layoutResId, @Nullable List<SsphBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SsphBean item) {

    }
}
