package id.chessburger.wecare.module.detail_activity_search_volunter;

import android.os.Bundle;

import id.chessburger.wecare.R;
import id.chessburger.wecare.base.BaseActivity;

public class DetailActSearchVolunteerActivity extends BaseActivity implements IDetailActSearchVolunterView {

    private DetailActSearchVolunterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_act_search_volunter);
        presenter = new DetailActSearchVolunterPresenter(this);
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
