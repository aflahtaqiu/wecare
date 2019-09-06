package id.chessburger.wecare.module.detail_activity_search_place;

import android.util.Log;

import id.chessburger.wecare.data.repository.ActivityDataRepository;
import id.chessburger.wecare.data.source.IActivityDataSource;
import id.chessburger.wecare.di.Injector;
import id.chessburger.wecare.model.Activity;
import id.chessburger.wecare.model.enumerations.SharedPrefKeys;
import id.chessburger.wecare.module.detail_activity_search_volunter.DetailActSearchVolunterPresenter;
import id.chessburger.wecare.utils.SharedPrefUtils;

/**
 * Created by aflah on 12/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public class DetailActSearchPlacePresenter {

    private IDetailActSearchPlaceView view;
    private ActivityDataRepository activityDataRepository;
    private String token;

    private final String LOADING_STRING = "Loading...";

    DetailActSearchPlacePresenter(IDetailActSearchPlaceView view) {
        this.view = view;
        this.activityDataRepository = Injector.provideActivityRepository();
        this.token = SharedPrefUtils.getStringSharedPref(SharedPrefKeys.TOKEN.getKey(), "");
    }

    void getDetailActivity (int idActivity) {
        String campaignerJoinRelation = "campaigner";
        String categoryJoinRelation = "category";
        view.showLoading(LOADING_STRING);
        activityDataRepository.getActivityById(token, idActivity, campaignerJoinRelation, categoryJoinRelation,
                new IActivityDataSource.GetActivityByIdCallback() {
            @Override
            public void onSuccess(Activity activity) {
                Log.e("activitynya", activity.toString());
            }

            @Override
            public void onError(String errorMessage) {

            }
        });
        view.hideLoading();
    }
}
