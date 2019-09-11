package id.chessburger.wecare.module.detail_campaigned_activity.undone_activity;

import android.util.Log;

import id.chessburger.wecare.data.repository.ActivityDataRepository;
import id.chessburger.wecare.data.source.IActivityDataSource;
import id.chessburger.wecare.di.Injector;
import id.chessburger.wecare.model.Activity;
import id.chessburger.wecare.model.enumerations.SharedPrefKeys;
import id.chessburger.wecare.utils.SharedPrefUtils;

/**
 * Created by aflah on 12/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public class UnDoneCampaignedActPresenter {

    private IUnDoneCampaignedActView view;
    private ActivityDataRepository repository;
    private String token;

    private final String LOADING_STRING = "Loading...";

    UnDoneCampaignedActPresenter(IUnDoneCampaignedActView view) {
        this.view = view;
        this.repository = Injector.provideActivityRepository();
        this.token = SharedPrefUtils.getStringSharedPref(SharedPrefKeys.TOKEN.getKey(), "");
    }

    void getDetailActivity (int idActivity) {
        String campaignerJoinRelation = "campaigner";
        view.showLoading(LOADING_STRING);
        repository.getActivityById(token, idActivity, campaignerJoinRelation, null,
                new GetActivityByIdCallback());
        view.hideLoading();
    }

    private class GetActivityByIdCallback implements IActivityDataSource.GetActivityByIdCallback {
        @Override
        public void onSuccess(Activity activity) {
            view.setActivity(activity);
            view.setActivityPicture(activity.getPhoto());
        }

        @Override
        public void onError(String errorMessage) {
            view.showMessage(errorMessage);
        }
    }

}
