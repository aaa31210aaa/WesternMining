package activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dimine.project.westernmining.R;

import base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;

public class ModifyPwd extends BaseActivity {
    @BindView(R.id.toolbar_title)
    TextView toolbar_title;
    @BindView(R.id.etv_oldpwd)
    EditText etv_oldpwd;
    @BindView(R.id.etv_newpwd)
    EditText etv_newpwd;
    @BindView(R.id.etv_newpwd_again)
    EditText etv_newpwd_again;
    @BindView(R.id.modify_submit)
    Button modify_submit;
    @BindView(R.id.displaypwd_btn)
    ImageButton displaypwd_btn;
    @BindView(R.id.display_hidden_tv)
    TextView display_hidden_tv;
    private String userOldPwd = "";
    private String message;
    private boolean isChecked = false;
    @Override
    public int getLayoutId() {
        return R.layout.activity_modify_pwd;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {

    }

    @Override
    public void loadData() {
        toolbar_title.setText("修改密码");
    }

    @OnClick(R.id.toolbar_left)
    void Back() {
        finish();
    }




}
