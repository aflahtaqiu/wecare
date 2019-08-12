package id.chessburger.wecare.module.wecare_poin;

import android.os.Bundle;

import id.chessburger.wecare.R;
import id.chessburger.wecare.base.BaseActivity;

public class WeCarePoinActivity extends BaseActivity implements IWeCarePoinView {

    private WeCarePoinPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_we_care_poin);
        presenter = new WeCarePoinPresenter(this);
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
