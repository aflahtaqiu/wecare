package id.chessburger.wecare.module.splash_screen;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.DatePicker;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.chessburger.wecare.R;
import id.chessburger.wecare.module.login.LoginActivity;
import id.chessburger.wecare.module.mainact.MainActivity;
import id.chessburger.wecare.utils.CommunicationUtils;

public class SplashScreenActivity extends AppCompatActivity implements ISplashScreenView {

    @BindView(R.id.layout_splash_screen)
    RelativeLayout relativeLayout;

    private static final Integer DELAY_MILLIS = 2750;

    private SplashScreenPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ButterKnife.bind(this);

        Animation animFadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        relativeLayout.startAnimation(animFadeIn);

        presenter = new SplashScreenPresenter(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        new Handler().postDelayed(new CheckCacheThread(), DELAY_MILLIS);
    }

    @Override
    public void moveIntoLogin() {
        CommunicationUtils.changeActivity(this, LoginActivity.class);
    }

    @Override
    public void moveIntoMain() {
        CommunicationUtils.changeActivity(this, MainActivity.class);
    }

    private class CheckCacheThread implements Runnable {
        @Override
        public void run() {
            presenter.checkCache();
        }
    }
}
