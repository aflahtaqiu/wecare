package id.chessburger.wecare.data.source;

import java.util.List;

import id.chessburger.wecare.model.Activity;
import id.chessburger.wecare.model.ActivityCategory;
import okhttp3.MultipartBody;

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
    void createActivityCariRelawan(String bearerToken, String name, String start, String end, String registerDeadline,
                                   String description, String volunteerTasks, String volunteerEquipments,
                                   String volunteerRequirements, String briefs, int minVolunteers,
                                   int donationTarget, int categoryId, int typeId, String city, String address,
                                   MultipartBody.Part photo, CreateActivityCariRelawanCallback callback);
    void bookmarkActivity (String token, int idActivity, BookmarkActivityCallback callback);
    void unBookmarkActivity (String token, int idActivity, UnBookmarkActivityCallback callback);


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

    interface CreateActivityCariRelawanCallback {
        void onSuccess (String successMessage);
        void onError (String errorMessage);
    }

    interface BookmarkActivityCallback {
        void onSuccess (String successMessage);
        void onError (String errorMessage);
    }

    interface UnBookmarkActivityCallback {
        void onSuccess (String successMessage);
        void onError (String errorMessage);
    }
}
