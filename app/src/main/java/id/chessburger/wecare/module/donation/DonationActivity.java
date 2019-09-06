package id.chessburger.wecare.module.donation;

import android.os.Bundle;

import id.chessburger.wecare.R;
import id.chessburger.wecare.base.BaseActivity;

public class DonationActivity extends BaseActivity implements IDonationView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation);
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
