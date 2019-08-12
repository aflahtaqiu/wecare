package id.chessburger.wecare.module.create_campaign_search_place;

import id.chessburger.wecare.data.repository.ActivityDataRepository;
import id.chessburger.wecare.di.Injector;

/**
 * Created by aflah on 12/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public class CreateCampaignSearchPlacePresenter {

    private ICreateCampaignSearchPlaceView view;
    private ActivityDataRepository activityDataRepository;

    public CreateCampaignSearchPlacePresenter(ICreateCampaignSearchPlaceView view) {
        this.view = view;
        this.activityDataRepository = Injector.provideActivityRepository();
    }
}
