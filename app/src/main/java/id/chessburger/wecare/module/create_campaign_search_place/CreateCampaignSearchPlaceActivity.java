package id.chessburger.wecare.module.create_campaign_search_place;

import android.os.Bundle;

import id.chessburger.wecare.R;
import id.chessburger.wecare.base.BaseActivity;

public class CreateCampaignSearchPlaceActivity extends BaseActivity implements ICreateCampaignSearchPlaceView {

    private CreateCampaignSearchPlacePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_campaign_search_place);
        presenter = new CreateCampaignSearchPlacePresenter(this);
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
