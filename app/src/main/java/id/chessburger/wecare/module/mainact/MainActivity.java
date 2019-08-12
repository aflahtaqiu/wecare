package id.chessburger.wecare.module.mainact;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.fragment.app.Fragment;

import id.chessburger.wecare.R;
import id.chessburger.wecare.base.BaseActivity;
import id.chessburger.wecare.module.article.ArticleFragment;
import id.chessburger.wecare.module.campaign.CampaignFragment;
import id.chessburger.wecare.module.home.HomeFragment;
import id.chessburger.wecare.module.profile.ProfileFragment;
import id.chessburger.wecare.module.schedule.ScheduleFragment;
import id.chessburger.wecare.utils.CommunicationUtils;

public class MainActivity extends BaseActivity implements IMainactView {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        fragmentTransaction(HomeFragment.getInstance());
                    case R.id.navigation_article:
                        fragmentTransaction(ArticleFragment.getInstance());
                    case R.id.navigation_jadwal:
                        fragmentTransaction(ScheduleFragment.getInstance());
                    case R.id.navigation_kampanye:
                        fragmentTransaction(CampaignFragment.getInstance());
                    case R.id.navigation_profile:
                        fragmentTransaction(ProfileFragment.getInstance());
                }
                return false;
            };

    private MainactPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainactPresenter(this);

        setBottomNavigation();
    }

    private void setBottomNavigation() {
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navView.setSelectedItemId(R.id.navigation_home);
    }

    private boolean fragmentTransaction(Fragment fragment) {
        CommunicationUtils.switchFragment(getSupportFragmentManager(),
                R.id.framelayout_container, fragment, "", null);
        return true;
    }

    @Override
    public void showLoading(String message) {
        super.onShowLoading(message);
    }

    @Override
    public void hideLoading() {
        super.onHideLoading();
    }

    @Override
    public void showMessage(String message) {

    }
}
