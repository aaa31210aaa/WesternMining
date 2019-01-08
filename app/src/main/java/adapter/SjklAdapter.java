package adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dimine.project.westernmining.R;

import java.util.List;

import bean.SjklBean;

public class SjklAdapter extends BaseQuickAdapter<SjklBean, BaseViewHolder> {
    public SjklAdapter(int layoutResId, @Nullable List<SjklBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SjklBean item) {
        helper.setText(R.id.sjkl_kz,item.getKz());
        helper.setText(R.id.sjkl_ks,item.getKs());
        helper.setText(R.id.sjkl_ktkl,item.getKtkl());
        helper.setText(R.id.ktkl_bynx,item.getKtklbynx());
        helper.setText(R.id.sjkl_czkl,item.getCzkl());
        helper.setText(R.id.czkl_bynx,item.getCzklbynx());
        helper.setText(R.id.sjkl_bckl,item.getBckl());
        helper.setText(R.id.bckl_bynx,item.getBcklbynx());
        helper.setText(R.id.sjkl_clkl,item.getClkl());
        helper.setText(R.id.clkl_bynx,item.getClklbynx());
        helper.setText(R.id.sjkl_bz,item.getBz());
    }
}
