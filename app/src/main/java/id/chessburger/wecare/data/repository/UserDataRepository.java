package id.chessburger.wecare.data.repository;

import java.util.List;

import id.chessburger.wecare.data.remote.UserRemoteDataSource;
import id.chessburger.wecare.data.source.IUserDataSource;
import id.chessburger.wecare.model.Activity;
import id.chessburger.wecare.model.Schedule;
import id.chessburger.wecare.model.User;

/**
 * Created by aflah on 12/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public class UserDataRepository implements IUserDataSource {

    private UserRemoteDataSource remoteDataSource;
    private static UserDataRepository dataRepository;

    public static UserDataRepository getInstance() {
        if (dataRepository == null) {
            dataRepository = new UserDataRepository(UserRemoteDataSource.getInstance());
        }
        return dataRepository;
    }

    UserDataRepository(UserRemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public void login(String phoneNumber, String password, LogInCallback callback) {
        remoteDataSource.login(phoneNumber, password, new LogInCallback() {

            @Override
            public void onSuccess(User user) {
                callback.onSuccess(user);
            }

            @Override
            public void onError(String errorMessage) {
                callback.onError(errorMessage);
            }
        });
    }

    @Override
    public void getBookmarkedActivities(int idUser, String joinQuery, GetBookmarkedActivitiesCallback callback) {
        remoteDataSource.getBookmarkedActivities(idUser, joinQuery, new GetBookmarkedActivitiesCallback() {
            @Override
            public void onSuccess(List<Activity> activities) {
                callback.onSuccess(activities);
            }

            @Override
            public void onError(String errorMessage) {
                callback.onError(errorMessage);
            }
        });
    }

    @Override
    public void getUserStatistic(int idUser, String joinQuery, String joinQuery2, String joinQuery3, GetUserStatisticCallback callback) {
        remoteDataSource.getUserStatistic(idUser, joinQuery, joinQuery2, joinQuery3, new GetUserStatisticCallback() {
            @Override
            public void onSuccess(User user) {
                callback.onSuccess(user);
            }

            @Override
            public void onError(String errorMessage) {
                callback.onError(errorMessage);
            }
        });
    }

    @Override
    public void getDoneFollowedActivities(int idUser, GetScheduleCallback callback) {
        remoteDataSource.getDoneFollowedActivities(idUser, new GetScheduleCallback() {
            @Override
            public void onSuccess(List<Schedule> schedules) {
                callback.onSuccess(schedules);
            }

            @Override
            public void onError(String errorMessage) {
                callback.onError(errorMessage);
            }
        });
    }

    @Override
    public void getUndoneFollowedActivities(int idUser, GetScheduleCallback callback) {
        remoteDataSource.getUndoneFollowedActivities(idUser, new GetScheduleCallback() {
            @Override
            public void onSuccess(List<Schedule> schedules) {
                callback.onSuccess(schedules);
            }

            @Override
            public void onError(String errorMessage) {
                callback.onError(errorMessage);
            }
        });
    }

    @Override
    public void getDoneCampaignedActivities(int idUser, GetActivitiesCallback callback) {
        remoteDataSource.getDoneCampaignedActivities(idUser, new GetActivitiesCallback() {
            @Override
            public void onSuccess(List<Activity> activities) {
                callback.onSuccess(activities);
            }

            @Override
            public void onError(String errorMessage) {
                callback.onError(errorMessage);
            }
        });
    }

    @Override
    public void getUnDoneCampaignedActivities(int idUser, GetActivitiesCallback callback) {
        remoteDataSource.getUnDoneCampaignedActivities(idUser, new GetActivitiesCallback() {
            @Override
            public void onSuccess(List<Activity> activities) {
                callback.onSuccess(activities);
            }

            @Override
            public void onError(String errorMessage) {
                callback.onError(errorMessage);
            }
        });
    }
}
