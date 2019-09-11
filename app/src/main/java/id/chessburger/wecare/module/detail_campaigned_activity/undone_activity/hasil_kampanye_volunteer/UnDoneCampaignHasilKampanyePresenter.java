package id.chessburger.wecare.module.detail_campaigned_activity.undone_activity.hasil_kampanye_volunteer;

import android.util.Log;

import id.chessburger.wecare.data.repository.ActivityDataRepository;
import id.chessburger.wecare.data.source.IActivityDataSource;
import id.chessburger.wecare.di.Injector;
import id.chessburger.wecare.model.Activity;
import id.chessburger.wecare.model.enumerations.SharedPrefKeys;
import id.chessburger.wecare.utils.SharedPrefUtils;

/**
 * Created by aflah on 11/09/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public class UnDoneCampaignHasilKampanyePresenter {

    private IUnDoneCampaignHasilKampanyeView view;
    private ActivityDataRepository repository;
    private String token;


    public UnDoneCampaignHasilKampanyePresenter(IUnDoneCampaignHasilKampanyeView view) {
        this.view = view;
        this.repository = Injector.provideActivityRepository();
        this.token = SharedPrefUtils.getStringSharedPref(SharedPrefKeys.TOKEN.getKey(), "");
    }

    void getVolunteersName (int idActivity) {
        Log.e("id activity", idActivity+ "");
        String volunteersJoinRelation = "volunteers.user";
        repository.getActivityById(token, idActivity, volunteersJoinRelation, null, new IActivityDataSource.GetActivityByIdCallback() {
            @Override
            public void onSuccess(Activity activity) {
                Log.e("activitynya", activity.toString());
                view.setFollowerActivities(activity.getFollowedActivities());
            }

            @Override
            public void onError(String errorMessage) {

            }
        });
    }
}
