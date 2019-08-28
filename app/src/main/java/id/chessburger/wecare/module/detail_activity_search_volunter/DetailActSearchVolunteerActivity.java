package id.chessburger.wecare.module.detail_activity_search_volunter;

import android.app.Activity;
import android.os.Bundle;

import id.chessburger.wecare.R;
import id.chessburger.wecare.base.BaseActivity;
import id.chessburger.wecare.model.enumerations.CommunicationKeys;

public class DetailActSearchVolunteerActivity extends BaseActivity implements IDetailActSearchVolunterView {

    private DetailActSearchVolunterPresenter presenter;

    private Activity activity;
    private int idActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_act_search_volunter);
        presenter = new DetailActSearchVolunterPresenter(this);
        getBundleIntentData();
    }

    private void getBundleIntentData() {
        Bundle bundle = getIntent().getBundleExtra(CommunicationKeys.BUNDLE_KEY.getKey());
        idActivity = bundle.getInt(CommunicationKeys.SELECTED_ACTIVITY.getKey());
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.getDetailActivity(idActivity);
//        presenter.followActivity(idActivity);
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
