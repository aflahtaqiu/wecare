package id.chessburger.wecare.module.create_campaign_search_place;

import java.util.List;

import id.chessburger.wecare.data.repository.ActivityDataRepository;
import id.chessburger.wecare.data.source.IActivityDataSource;
import id.chessburger.wecare.di.Injector;
import id.chessburger.wecare.model.ActivityCategory;

/**
 * Created by aflah on 12/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public class CreateCampaignSearchPlacePresenter {

    private ICreateCampaignSearchPlaceView view;
    private ActivityDataRepository repository;

    public CreateCampaignSearchPlacePresenter(ICreateCampaignSearchPlaceView view) {
        this.view = view;
        this.repository = Injector.provideActivityRepository();
    }


}
