package id.chessburger.wecare.data.repository;

import java.util.List;

import javax.annotation.Nullable;

import id.chessburger.wecare.data.remote.ActivityRemoteDataSource;
import id.chessburger.wecare.data.source.IActivityDataSource;
import id.chessburger.wecare.model.Activity;
import id.chessburger.wecare.model.ActivityCategory;
import id.chessburger.wecare.model.response.ResponsePostDonation;
import okhttp3.MultipartBody;

/**
 * Created by aflah on 12/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public class ActivityDataRepository implements IActivityDataSource {

    private ActivityRemoteDataSource remoteDataSource;
    private static ActivityDataRepository dataRepository;

    public static ActivityDataRepository getInstance() {
        if (dataRepository == null) {
            dataRepository = new ActivityDataRepository(ActivityRemoteDataSource.getInstance());
        }
        return dataRepository;
    }

    ActivityDataRepository(ActivityRemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public void followActivity(String token, int idActivity, FollowActivityCallback callback) {
        remoteDataSource.followActivity(token,idActivity, new FollowActivityCallback() {
            @Override
            public void onSuccess(Activity activity) {
                callback.onSuccess(activity);
            }

            @Override
            public void onError(String errorMessage) {
                callback.onError(errorMessage);
            }
        });
    }

    @Override
    public void getAllActivitiesJoinQuery(String joinQuery, GetActivitiesCallback callback) {
        remoteDataSource.getAllActivitiesJoinQuery(joinQuery, new GetActivitiesCallback() {
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
    public void getAllActivitiesFilterQuery(String filter, @Nullable String filter2, @Nullable String joinQuery,
                                            GetActivitiesCallback callback) {
        remoteDataSource.getAllActivitiesFilterQuery(filter, filter2, joinQuery, new GetActivitiesCallback() {
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
    public void getAllActivitiesSearch(String keyword, GetActivitiesCallback callback) {
        remoteDataSource.getAllActivitiesSearch(keyword, new GetActivitiesCallback() {
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
    public void getActivityById(String token, int idActivity, String joinRelation, @Nullable String joinRelation2, GetActivityByIdCallback callback) {
        remoteDataSource.getActivityById(token, idActivity, joinRelation, joinRelation2, new GetActivityByIdCallback() {
            @Override
            public void onSuccess(Activity activity) {
                callback.onSuccess(activity);
            }

            @Override
            public void onError(String errorMessage) {
                callback.onError(errorMessage);
            }
        });
    }

    @Override
    public void getActivityByIdJoinFilter(String token, int idActivity, String joinQuery, String filterQuery, GetActivityByIdCallback callback) {
        remoteDataSource.getActivityByIdJoinFilter(token, idActivity, joinQuery, filterQuery, new GetActivityByIdCallback() {
            @Override
            public void onSuccess(Activity activity) {
                callback.onSuccess(activity);
            }

            @Override
            public void onError(String errorMessage) {
                callback.onError(errorMessage);
            }
        });
    }

    @Override
    public void getAllCategory(GetAllCategoryCallback callback) {
        remoteDataSource.getAllCategory(new GetAllCategoryCallback() {
            @Override
            public void onSuccess(List<ActivityCategory> categoryList) {
                callback.onSuccess(categoryList);
            }

            @Override
            public void onError(String errorMessage) {
                callback.onError(errorMessage);
            }
        });
    }

    @Override
    public void createActivityCariRelawan(String bearerToken, String name, String start, String end,
                                          String registerDeadline, String description, String volunteerTasks,
                                          String volunteerEquipments, String volunteerRequirements, String briefs,
                                          int minVolunteers, int donationTarget, int categoryId, String city,
                                          String address, MultipartBody.Part photo, CreateActivityCallback callback) {
        remoteDataSource.createActivityCariRelawan(bearerToken, name, start, end, registerDeadline,
                description, volunteerTasks, volunteerEquipments, volunteerRequirements, briefs,
                minVolunteers, donationTarget, categoryId, city, address, photo,
                new CreateActivityCallback() {
            @Override
            public void onSuccess(String successMessage) {
                callback.onSuccess(successMessage);
            }

            @Override
            public void onError(String errorMessage) {
                callback.onError(errorMessage);
            }
        });
    }

    @Override
    public void createActivityCariLokasi(String bearerToken, String name, String start, String end,
                                         String registerDeadline, String description, String area,
                                         int categoryId, int maxParticipants, int volunteersTotal,
                                         String preparedByFacilitator, String activityPlan,
                                         String locationRequirement, String additionalInformation,
                                         MultipartBody.Part photo, CreateActivityCallback callback) {

        remoteDataSource.createActivityCariLokasi(bearerToken, name, start, end, registerDeadline,
                description, area, categoryId, maxParticipants, volunteersTotal, preparedByFacilitator,
                activityPlan, locationRequirement, additionalInformation, photo, new CreateActivityCallback() {
            @Override
            public void onSuccess(String successMessage) {
                callback.onSuccess(successMessage);
            }

            @Override
            public void onError(String errorMessage) {
                callback.onError(errorMessage);
            }
        });
    }

    @Override
    public void presenceUser(String token, int idActivity, int idUser, PresenceCallack callack) {
        remoteDataSource.presenceUser(token, idActivity, idUser, new PresenceCallack() {
            @Override
            public void onSuccess(Activity activity) {
                callack.onSuccess(activity);
            }

            @Override
            public void onError(String errorMessage) {
                callack.onError(errorMessage);
            }
        });
    }

    @Override
    public void doneActivity(String token, int idActivity, String reportText, MultipartBody.Part photo, DoneActivityCallback callback) {
        remoteDataSource.doneActivity(token, idActivity, reportText, photo, new DoneActivityCallback() {
            @Override
            public void onSuccess(Activity activity) {
                callback.onSuccess(activity);
            }

            @Override
            public void onError(String errorMessage) {
                callback.onError(errorMessage);
            }
        });
    }

    @Override
    public void bookmarkActivity(String token, int idActivity, BookmarkActivityCallback callback) {
        remoteDataSource.bookmarkActivity(token, idActivity, new BookmarkActivityCallback() {
            @Override
            public void onSuccess(String successMessage) {
                callback.onSuccess(successMessage);
            }

            @Override
            public void onError(String errorMessage) {
                callback.onError(errorMessage);
            }
        });
    }

    @Override
    public void unBookmarkActivity(String token, int idActivity, UnBookmarkActivityCallback callback) {
        remoteDataSource.unBookmarkActivity(token, idActivity, new UnBookmarkActivityCallback() {
            @Override
            public void onSuccess(String successMessage) {
                callback.onSuccess(successMessage);
            }

            @Override
            public void onError(String errorMessage) {
                callback.onError(errorMessage);
            }
        });
    }

    @Override
    public void postDonation(String token, int amount, int activityId, MultipartBody.Part transferValidation,
                             PostDonationCallback callback) {


        remoteDataSource.postDonation(token, amount, activityId, transferValidation, new PostDonationCallback() {

            @Override
            public void onSuccess(ResponsePostDonation responsePostDonation) {
                callback.onSuccess(responsePostDonation);
            }

            @Override
            public void onError(String errorMessage) {
                callback.onError(errorMessage);
            }
        });
    }

    @Override
    public void postLocation(String token, int idActivity, String city, String address, String startDateTime,
                             String endDateTime, String description, int capacity, MultipartBody.Part locationPhoto,
                             MultipartBody.Part licensePhoto, PostLocationCallback callback) {

        remoteDataSource.postLocation(token, idActivity, city, address, startDateTime, endDateTime, description,
                capacity, locationPhoto, licensePhoto, new PostLocationCallback() {
            @Override
            public void onSuccess(String successMessage) {
                callback.onSuccess(successMessage);
            }

            @Override
            public void onError(String errorMessage) {
                callback.onError(errorMessage);
            }
        });
    }
}
