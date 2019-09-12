package id.chessburger.wecare.data.source;

import java.util.List;

import id.chessburger.wecare.model.Activity;
import id.chessburger.wecare.model.Schedule;
import id.chessburger.wecare.model.User;

/**
 * Created by aflah on 12/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public interface IUserDataSource {

    void login (String phoneNumber, String password, LogInCallback callback);
    void getBookmarkedActivities (int idUser, String joinQuery, GetBookmarkedActivitiesCallback callback);
    void getUserStatistic (int idUser, String joinQuery, String joinQuery2, String joinQuery3, GetUserStatisticCallback callback);

    void getDoneFollowedActivities(int idUser, GetScheduleCallback callback);
    void getUndoneFollowedActivities(int idUser, GetScheduleCallback callback);

    void getDoneCampaignedActivities(int idUser, GetActivitiesCallback callback);
    void getUnDoneCampaignedActivities(int idUser, GetActivitiesCallback callback);

    void updateWeCarePoint (String token, int amount, LogInCallback callback);

    interface LogInCallback {
        void onSuccess (User user);
        void onError (String errorMessage);
    }

    interface GetBookmarkedActivitiesCallback {
        void onSuccess (List<Activity> activities);
        void onError (String errorMessage);
    }

    interface GetUserStatisticCallback  {
        void onSuccess (User user);
        void onError(String errorMessage);
    }

    interface GetActivitiesCallback {
        void onSuccess (List<Activity> activities);
        void onError (String errorMessage);
    }

    interface GetScheduleCallback {
        void onSuccess (List<Schedule> schedules);
        void onError (String errorMessage);
    }
}
