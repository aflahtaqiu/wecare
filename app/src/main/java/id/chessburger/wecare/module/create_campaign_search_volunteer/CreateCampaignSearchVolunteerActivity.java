package id.chessburger.wecare.module.create_campaign_search_volunteer;

import android.os.Bundle;

import id.chessburger.wecare.R;
import id.chessburger.wecare.base.BaseActivity;

public class CreateCampaignSearchVolunteerActivity extends BaseActivity implements ICreateCampaignSearchVolunteerView {

    private CreateCampaignSearchVolunteerPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_campaign_search_volunteer);
        presenter = new CreateCampaignSearchVolunteerPresenter(this);
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
