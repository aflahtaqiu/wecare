package id.chessburger.wecare.data.source;

import java.util.List;

import id.chessburger.wecare.model.Activity;
import id.chessburger.wecare.model.ActivityCategory;

/**
 * Created by aflah on 12/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public interface IActivityDataSource {

    void followActivity (String token, int idActivity, FollowActivityCallback callback);
    void getAllActivitiesJoinQuery (String joinQuery, GetActivitiesCallback callback);
    void getActivityById (String token, int idActivity, String joinRelation, GetActivityByIdCallback callback);
    void getAllCategory (GetAllCategoryCallback callback);

    interface FollowActivityCallback {
        void onSuccess (Activity activity);
        void onError (String errorMessage);
    }

    interface GetActivitiesCallback {
        void onSuccess (List<Activity> activities);
        void onError (String errorMessage);

    }

    interface GetActivityByIdCallback {
        void onSuccess (Activity activity);
        void onError (String errorMessage);

    }

    interface GetAllCategoryCallback {
        void onSuccess (List<ActivityCategory> categoryList);
        void onError (String errorMessage);
    }
}
