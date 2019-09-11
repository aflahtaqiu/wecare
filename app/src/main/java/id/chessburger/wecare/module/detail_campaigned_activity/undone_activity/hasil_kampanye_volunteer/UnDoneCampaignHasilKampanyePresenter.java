package id.chessburger.wecare.module.detail_campaigned_activity.undone_activity.hasil_kampanye_volunteer;

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
        repository.getActivityById(token, idActivity, volunteersJoinRelation, filterIsPresentFalse,
                new IActivityDataSource.GetActivityByIdCallback() {
            @Override
            public void onSuccess(Activity activity) {
                view.setFollowerActivities(activity.getFollowedActivities());
            }

            @Override
            public void onError(String errorMessage) {

            }
        });
    }

    void absenceVolunteer (int idUser) {
        repository.presenceUser(token, idUser, new IActivityDataSource.PresenceCallack() {
            @Override
            public void onSuccess(Activity activity) {
                view.showMessage("User ini sudah diabsensi");
            }

            @Override
            public void onError(String errorMessage) {

            }
        });
    }
}
