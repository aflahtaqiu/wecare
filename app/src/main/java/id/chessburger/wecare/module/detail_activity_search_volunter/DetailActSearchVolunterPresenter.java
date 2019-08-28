package id.chessburger.wecare.module.detail_activity_search_volunter;

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


public class DetailActSearchVolunterPresenter {

    private IDetailActSearchVolunterView view;
    private ActivityDataRepository activityDataRepository;

    public DetailActSearchVolunterPresenter(IDetailActSearchVolunterView view) {
        this.view = view;
        this.activityDataRepository = Injector.provideActivityRepository();
    }

    public void getDetailActivity (int idActivity) {
        activityDataRepository.getActivityById(idActivity, new IActivityDataSource.GetActivityByIdCallback() {
            @Override
            public void onSuccess(Activity activity) {
                Log.e("lele", activity.toString());
            }

            @Override
            public void onError(String errorMessage) {

            }
        });
    }

    public void followActivity (int idActivity) {
        String token = SharedPrefUtils.getStringSharedPref(SharedPrefKeys.TOKEN.getKey(), "");
        activityDataRepository.followActivity(token, idActivity, new IActivityDataSource.FollowActivityCallback() {
            @Override
            public void onSuccess(Activity activity) {
                Log.e("folllow activity", "sukses");
            }

            @Override
            public void onError(String errorMessage) {
                Log.e("folllow activity", errorMessage);
            }
        });
    }
}
