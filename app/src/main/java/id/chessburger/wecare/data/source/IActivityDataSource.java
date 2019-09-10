package id.chessburger.wecare.data.source;

import java.util.List;

import javax.annotation.Nullable;

import id.chessburger.wecare.model.Activity;
import id.chessburger.wecare.model.ActivityCategory;
import id.chessburger.wecare.model.response.ResponsePostDonation;
import okhttp3.MultipartBody;

/**
 * Created by aflah on 12/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public interface IActivityDataSource {

    void followActivity (String token, int idActivity, FollowActivityCallback callback);

    void getAllActivitiesJoinQuery (String joinQuery, GetActivitiesCallback callback);
    void getAllActivitiesFilterQuery (String filter, String filter2, @Nullable String joinQuery, GetActivitiesCallback callback);

    void getActivityById (String token, int idActivity, String joinRelation, @Nullable String joinRelation2,
                          GetActivityByIdCallback callback);
    void getAllCategory (GetAllCategoryCallback callback);

    void createActivityCariRelawan(String bearerToken, String name, String start, String end, String registerDeadline,
                                   String description, String volunteerTasks, String volunteerEquipments,
                                   String volunteerRequirements, String briefs, int minVolunteers,
                                   int donationTarget, int categoryId, String city, String address,
                                   MultipartBody.Part photo, CreateActivityCallback callback);
    void createActivityCariLokasi (String bearerToken, String name, String start, String end,
                                   String registerDeadline, String description, String area,
                                   int categoryId, int maxParticipants, int volunteersTotal,
                                   String preparedByFacilitator, String activityPlan, String locationRequirement,
                                   String additionalInformation, MultipartBody.Part photo, CreateActivityCallback callback);

    void presenceUser (String token, List<Integer> userIds, PresenceCallack callack);
    void doneActivity (String token, int idActivity, String reportText, MultipartBody.Part photo, DoneActivityCallback callback);

    void bookmarkActivity (String token, int idActivity, BookmarkActivityCallback callback);
    void unBookmarkActivity (String token, int idActivity, UnBookmarkActivityCallback callback);

    void postDonation (String token, int amount, int activityId, MultipartBody.Part transferValidation, PostDonationCallback callback);

    void postLocation (String token, int idActivity, String city, String address, String startDateTime,
                       String endDateTime, String description, int capacity, MultipartBody.Part locationPhoto,
                       MultipartBody.Part licensePhoto, PostLocationCallback callback);

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

    interface CreateActivityCallback {
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

    interface PostDonationCallback {
        void onSuccess (ResponsePostDonation responsePostDonation);
        void onError (String errorMessage);
    }

    interface PostLocationCallback {
        void onSuccess (String successMessage);
        void onError (String errorMessage);
    }

    interface PresenceCallack {
        void onSuccess (Activity activity);
        void onError (String errorMessage);
    }

    interface DoneActivityCallback {
        void onSuccess (Activity activity);
        void onError (String errorMessage);
    }
}
