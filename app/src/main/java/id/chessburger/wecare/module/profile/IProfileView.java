package id.chessburger.wecare.module.profile;

import java.util.List;

import id.chessburger.wecare.base.IBaseView;
import id.chessburger.wecare.model.Activity;
import id.chessburger.wecare.model.User;

/**
 * Created by aflah on 09/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public interface IProfileView extends IBaseView {
    void moveIntoLogin();
    void moveIntoEditProfile();
    void showUserProfileData(User user);
    void showBookmarkedActivities (List<Activity> activities);
    void showUserStatistics(int followedActivities, int campaignedActivity, int donations);
    void setWeCarePoin(int weCarePoin);
}
