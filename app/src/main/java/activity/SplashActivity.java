package activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.dimine.project.westernmining.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import utils.StatusBarUtils;

public class SplashActivity extends AppCompatActivity {
    private long send_time = 2500;
    @BindView(R.id.skip)
    Button skip;
    private Unbinder bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
    }

    private void initView() {
        StatusBarUtils.transparencyBar(this);
        bind = ButterKnife.bind(this);
        skip.setVisibility(View.VISIBLE);
        countDownTimer.start();

    }


    @OnClick(R.id.skip)
    void Skip() {
        countDownTimer.cancel();
        startActivity(new Intent(SplashActivity.this, Login.class));
        finish();
    }


    //跳过欢迎页面
    private CountDownTimer countDownTimer = new CountDownTimer(4000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            skip.setText("跳过(" + millisUntilFinished / 1000 + "s)");
        }

        @Override
        public void onFinish() {
            skip.setText("跳过(" + 0 + "s)");
            startActivity(new Intent(SplashActivity.this, Login.class));
            finish();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
        countDownTimer.cancel();
    }
}
