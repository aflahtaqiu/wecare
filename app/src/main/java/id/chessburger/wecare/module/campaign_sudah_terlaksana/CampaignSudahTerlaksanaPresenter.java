package id.chessburger.wecare.module.campaign_sudah_terlaksana;

import java.util.List;

import id.chessburger.wecare.data.repository.ActivityDataRepository;
import id.chessburger.wecare.data.source.IActivityDataSource;
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


public class CampaignSudahTerlaksanaPresenter {

    private ICampaignSudahTerlaksanaView view;
    private ActivityDataRepository repository;
    private User user;

    public CampaignSudahTerlaksanaPresenter(ICampaignSudahTerlaksanaView view) {
        this.view = view;
        this.repository = Injector.provideActivityRepository();
        user = (User) SharedPrefUtils.getObjectSharedPref(SharedPrefKeys.PROFIL.getKey(),
                null, User.class);
    }

    void getCampaignedActivities () {
        String filterCampaignerId = "campaignerId||eq||"+user.getId();
        String filterIsDone = "isDone||eq||true";
        String joinCampaigner = "campaigner";
        repository.getAllActivitiesFilterQuery(filterCampaignerId, filterIsDone, joinCampaigner,
                new IActivityDataSource.GetActivitiesCallback() {
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
