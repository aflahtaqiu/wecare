package id.chessburger.wecare.module.create_campaign_search_volunteer;

import android.util.Log;

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


public class CreateCampaignSearchVolunteerPresenter {

    private ICreateCampaignSearchVolunteerView view;
    private ActivityDataRepository repository;

    public CreateCampaignSearchVolunteerPresenter(ICreateCampaignSearchVolunteerView view) {
        this.view = view;
        this.repository = Injector.provideActivityRepository();
    }

    public void getAllActivityCategoty () {

        repository.getAllCategory(new IActivityDataSource.GetAllCategoryCallback() {
            @Override
            public void onSuccess(List<ActivityCategory> categoryList) {
                view.setActivityCategory(categoryList);
            }

            @Override
            public void onError(String errorMessage) {
                view.showMessage(errorMessage);
            }
        });
    }
}
