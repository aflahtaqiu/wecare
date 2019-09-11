package id.chessburger.wecare.module.detail_campaigned_activity.undone_activity.hasil_kampanye_volunteer;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import id.chessburger.wecare.data.repository.ActivityDataRepository;
import id.chessburger.wecare.data.source.IActivityDataSource;
import id.chessburger.wecare.di.Injector;
import id.chessburger.wecare.model.Activity;
import id.chessburger.wecare.model.FollowedActivity;
import id.chessburger.wecare.model.enumerations.SharedPrefKeys;
import id.chessburger.wecare.utils.SharedPrefUtils;

/**
 * Created by aflah on 11/09/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


class UnDoneCampaignHasilKampanyePresenter {

    private IUnDoneCampaignHasilKampanyeView view;
    private ActivityDataRepository repository;
    private String token;


    UnDoneCampaignHasilKampanyePresenter(IUnDoneCampaignHasilKampanyeView view) {
        this.view = view;
        this.repository = Injector.provideActivityRepository();
        this.token = SharedPrefUtils.getStringSharedPref(SharedPrefKeys.TOKEN.getKey(), "");
    }

    void getVolunteersName (int idActivity) {
        String volunteersJoinRelation = "volunteers.user";
        String filterIsPresentFalse = "volunteers.isPresent||eq||false";
        repository.getActivityByIdJoinFilter(token, idActivity, volunteersJoinRelation, filterIsPresentFalse,
                new IActivityDataSource.GetActivityByIdCallback() {
            @Override
            public void onSuccess(Activity activity) {
                view.setFollowerActivities(activity.getFollowedActivities());
            }

            @Override
            public void onError(String errorMessage) {
                if (TextUtils.equals("not_found", errorMessage)) {
                    List<FollowedActivity> followedActivities = new ArrayList<>();
                    view.setAbsencedFollowedActivities(followedActivities);
                } else {
                    view.showMessage(errorMessage);
                }
            }
        });
    }

    void getAbsencedVolunteersName (int idActivity) {
        String volunteersJoinRelation = "volunteers.user";
        String filterIsPresentFalse = "volunteers.isPresent||eq||true";
        repository.getActivityByIdJoinFilter(token, idActivity, volunteersJoinRelation, filterIsPresentFalse,
                new IActivityDataSource.GetActivityByIdCallback() {
                    @Override
                    public void onSuccess(Activity activity) {
                        view.setAbsencedFollowedActivities(activity.getFollowedActivities());
                    }

                    @Override
                    public void onError(String errorMessage) {
                        if (TextUtils.equals("not_found", errorMessage)) {
                            List<FollowedActivity> followedActivities = new ArrayList<>();
                            view.setAbsencedFollowedActivities(followedActivities);
                        } else {
                            view.showMessage(errorMessage);
                        }
                    }
                });
    }

    void absenceVolunteer (int idUser, int idActivity) {
        repository.presenceUser(token, idActivity, idUser,  new IActivityDataSource.PresenceCallack() {
            @Override
            public void onSuccess(Activity activity) {
                view.showMessage("Absensi volunteer ini diubah");
            }

            @Override
            public void onError(String errorMessage) {

            }
        });
    }
}
