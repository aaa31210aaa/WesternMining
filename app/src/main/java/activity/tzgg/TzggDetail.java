package activity.tzgg;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.dimine.project.westernmining.R;

import base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;

public class TzggDetail extends BaseActivity {
    @BindView(R.id.toolbar_title)
    TextView title_name;
    @BindView(R.id.notify_detail_title)
    TextView notify_detail_title;
    @BindView(R.id.notify_detail_date)
    TextView notify_detail_date;
    @BindView(R.id.notify_detail_content)
    TextView notify_detail_content;
    private String id = "";
    private String title = "";
    private String date = "";
    private String content = "";

    @Override
    public int getLayoutId() {
        return R.layout.activity_tzgg_detail;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {

    }

    @Override
    public void loadData() {
        title_name.setText(R.string.notify_title);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        title = intent.getStringExtra("title");
        date = intent.getStringExtra("date");
        content = intent.getStringExtra("content");
        notify_detail_title.setText(title);
        notify_detail_date.setText(date);
        notify_detail_content.setText(content);
    }

    @OnClick(R.id.toolbar_left)
    void Back() {
        finish();
    }
}
