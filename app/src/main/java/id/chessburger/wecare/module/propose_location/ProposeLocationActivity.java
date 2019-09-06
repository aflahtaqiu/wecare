package id.chessburger.wecare.module.propose_location;

import android.os.Bundle;

import id.chessburger.wecare.R;
import id.chessburger.wecare.base.BaseActivity;

public class ProposeLocationActivity extends BaseActivity implements IProposeLocationView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propose_location);
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
