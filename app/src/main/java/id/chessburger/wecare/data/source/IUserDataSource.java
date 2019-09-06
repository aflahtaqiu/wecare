package id.chessburger.wecare.data.source;

import java.util.List;

import id.chessburger.wecare.model.Activity;
import id.chessburger.wecare.model.response.ResponseLogin;

/**
 * Created by aflah on 12/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public interface IUserDataSource {

    void login (String phoneNumber, String password, LogInCallback callback);
    void getBookmarkedActivities (int idUser, String joinQuery, GetBookmarkedActivitiesCallback callback);

    interface LogInCallback {
        void onSuccess (ResponseLogin responseLogin);
        void onError (String errorMessage);
    }


    interface GetBookmarkedActivitiesCallback {
        void onSuccess (List<Activity> activities);
        void onError (String errorMessage);
    }
}
