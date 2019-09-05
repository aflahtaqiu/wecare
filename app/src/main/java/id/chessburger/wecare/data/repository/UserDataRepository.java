package id.chessburger.wecare.data.repository;

import java.util.List;

import id.chessburger.wecare.data.remote.UserRemoteDataSource;
import id.chessburger.wecare.data.source.IUserDataSource;
import id.chessburger.wecare.model.Activity;
import id.chessburger.wecare.model.response.ResponseLogin;

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
            public void onSuccess(ResponseLogin responseLogin) {
                callback.onSuccess(responseLogin);
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
}
