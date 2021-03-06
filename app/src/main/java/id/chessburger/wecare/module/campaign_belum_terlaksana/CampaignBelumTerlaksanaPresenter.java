package id.chessburger.wecare.module.campaign_belum_terlaksana;

import java.util.List;

import id.chessburger.wecare.data.repository.UserDataRepository;
import id.chessburger.wecare.data.source.IUserDataSource;
import id.chessburger.wecare.di.Injector;
import id.chessburger.wecare.model.Activity;
import id.chessburger.wecare.model.User;
import id.chessburger.wecare.model.enumerations.SharedPrefKeys;
import id.chessburger.wecare.utils.SharedPrefUtils;

/**
 * Created by aflah on 27/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


class CampaignBelumTerlaksanaPresenter {

    private ICampaignBelumTerlaksanaView view;
    private UserDataRepository repository;
    private User user;

    CampaignBelumTerlaksanaPresenter(ICampaignBelumTerlaksanaView view) {
        this.view = view;
        this.repository = Injector.provideUserRepository();
        user = (User) SharedPrefUtils.getObjectSharedPref(SharedPrefKeys.PROFIL.getKey(),
                null, User.class);
    }

    void getCampaignedActivities () {
        repository.getUnDoneCampaignedActivities(user.getId(), new IUserDataSource.GetActivitiesCallback() {
            @Override
            public void onSuccess(List<Activity> activities) {
                view.showListActivities(activities);
            }

            @Override
            public void onError(String errorMessage) {
                view.showMessage(errorMessage);
            }
        });
    }
}
