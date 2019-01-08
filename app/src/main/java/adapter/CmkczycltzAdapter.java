package adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dimine.project.westernmining.R;

import java.util.List;

import bean.CmkczycltzBean;

public class CmkczycltzAdapter extends BaseQuickAdapter<CmkczycltzBean, BaseViewHolder> {
    public CmkczycltzAdapter(int layoutResId, @Nullable List<CmkczycltzBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CmkczycltzBean item) {
        helper.setText(R.id.cmkczycltz_ksl, item.getKsl());
        helper.setText(R.id.cmkczycltz_pw, item.getPw());
        helper.setText(R.id.cmkczycltz_jsl, item.getJsl());
    }
}
