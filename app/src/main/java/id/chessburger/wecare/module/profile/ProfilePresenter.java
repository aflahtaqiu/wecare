package id.chessburger.wecare.module.profile;

import android.util.Log;

import java.util.List;

import id.chessburger.wecare.data.repository.UserDataRepository;
import id.chessburger.wecare.data.source.IUserDataSource;
import id.chessburger.wecare.di.Injector;
import id.chessburger.wecare.model.Activity;
import id.chessburger.wecare.model.User;
import id.chessburger.wecare.model.enumerations.SharedPrefKeys;
import id.chessburger.wecare.utils.SharedPrefUtils;

/**
 * Created by aflah on 09/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public class ProfilePresenter {

    private IProfileView view;
    private UserDataRepository userDataRepository;
    private User user;

    ProfilePresenter(IProfileView view) {
        this.view = view;
        this.userDataRepository = Injector.provideUserRepository();
        user = (User) SharedPrefUtils.getObjectSharedPref(SharedPrefKeys.PROFIL.getKey(),
                null, User.class);
    }

    void doLogout () {
        SharedPrefUtils.clearAll();
        view.moveIntoLogin();
    }

    void getUserProfileData () {
        if (user != null) {
            view.showUserProfileData(user);
        }
    }

    void getBookmarkedActivities () {
        String joinQueryBookmark = "bookmarks";
        view.showLoading("Loading...");
        userDataRepository.getBookmarkedActivities(user.getId(), joinQueryBookmark, new IUserDataSource.GetBookmarkedActivitiesCallback() {
            @Override
            public void onSuccess(List<Activity> activities) {
                view.showBookmarkedActivities(activities);
                view.hideLoading();
            }

            @Override
            public void onError(String errorMessage) {
                view.hideLoading();
                view.showMessage(errorMessage);
            }
        });
    }

    void getUserStatistic (int idUser) {
        String joinQueryFollowedActivities = "followedActivities";
        String joinQueryCampaignedActivities = "activities";
        String joinQueryDonations = "donations";

        userDataRepository.getUserStatistic(idUser, joinQueryFollowedActivities, joinQueryCampaignedActivities, joinQueryDonations, new IUserDataSource.GetUserStatisticCallback() {
            @Override
            public void onSuccess(User user) {
                int followedActivities = user.getFollowedActivities().size();
                int campaignedActivities = user.getCampaignedActivities().size();
                int donations = user.getDonations().size();

                view.showUserStatistics(followedActivities, campaignedActivities, donations);
            }

            @Override
            public void onError(String errorMessage) {
                view.showMessage(errorMessage);
            }
        });
    }
}
