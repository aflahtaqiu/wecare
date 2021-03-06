package id.chessburger.wecare.data.remote;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import id.chessburger.wecare.base.BaseRemoteDataSource;
import id.chessburger.wecare.data.source.IActivityDataSource;
import id.chessburger.wecare.model.Activity;
import id.chessburger.wecare.model.ActivityCategory;
import id.chessburger.wecare.model.Location;
import id.chessburger.wecare.model.User;
import id.chessburger.wecare.model.enumerations.ResponseServerCode;
import id.chessburger.wecare.model.response.ResponseError;
import id.chessburger.wecare.model.response.ResponsePostDonation;
import id.chessburger.wecare.utils.ConverterUtils;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by aflah on 12/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public class ActivityRemoteDataSource extends BaseRemoteDataSource implements IActivityDataSource {

    private static ActivityRemoteDataSource remoteDataSource;
    private Gson gson = new GsonBuilder().create();

    public static ActivityRemoteDataSource getInstance() {
        if (remoteDataSource == null) {
            remoteDataSource = new ActivityRemoteDataSource();
        }
        return remoteDataSource;
    }

    @Override
    public void followActivity(String token, int idActivity, FollowActivityCallback callback) {
        Call<Activity> call = apiEndpoint.followActivity(token, idActivity);
        call.enqueue(new OnFollowActivityCallback(callback));
    }

    @Override
    public void getAllActivitiesJoinQuery(String joinQuery, GetActivitiesCallback callback) {
        Call<List<Activity>> call = apiEndpoint.getActivitiesWithJoin(joinQuery);
        call.enqueue(new GetAllActivitiesCallback(callback));
    }
    @Override
    public void getAllActivitiesFilterQuery(String filter, String filter2, @Nullable String joinQuery,
                                            GetActivitiesCallback callback) {
        Call<List<Activity>> call = apiEndpoint.getActivitiesWithFilter(filter, filter2, joinQuery);
        call.enqueue(new GetAllActivitiesCallback(callback));
    }

    @Override
    public void getAllActivitiesSearch(String keyword, GetActivitiesCallback callback) {
        Call<List<Activity>> call = apiEndpoint.searchActivities(keyword);
        call.enqueue(new Callback<List<Activity>>() {
            @Override
            public void onResponse(Call<List<Activity>> call, Response<List<Activity>> response) {
                if (response.code() == ResponseServerCode.OK.getCode()) {
                    callback.onSuccess(response.body());
                } else {
                    List<Activity> activities = new ArrayList<>();
                    callback.onSuccess(activities);
                }
            }

            @Override
            public void onFailure(Call<List<Activity>> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

    @Override
    public void getActivityById(String token, int idActivity, String joinRelation, @Nullable String joinRelation2,
                                GetActivityByIdCallback callback) {
        Call<Activity> call = apiEndpoint.getActivityByIdJoin(token, idActivity, joinRelation, joinRelation2);
        call.enqueue(new DetailActivityByIdCallback(callback));
    }

    @Override
    public void getActivityByIdJoinFilter(String token, int idActivity, String joinQuery, String filterQuery, GetActivityByIdCallback callback) {
        Call<Activity> call = apiEndpoint.getActivityByIdJoinFilter(token, idActivity, joinQuery, filterQuery);
        call.enqueue(new Callback<Activity>() {
            @Override
            public void onResponse(Call<Activity> call, Response<Activity> response) {
                if (response.code() == ResponseServerCode.OK.getCode()) {
                    callback.onSuccess(response.body());
                } else if (response.code() == ResponseServerCode.NOT_FOUND.getCode()) {
                  callback.onError("not_found");
                } else {
                    sendErrorCallback(response);
                }
            }

            private void sendErrorCallback(Response<Activity> response) {
                try {
                    if (response.errorBody() != null) {
                        ResponseError responseError = ConverterUtils.stringToResponseError(response.errorBody().string());
                        if (responseError.getMessage() == null)
                            callback.onError(responseError.getError());
                        else
                            callback.onError(responseError.getMessage());
                    } else {
                        callback.onError("Get Activities Failed");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Activity> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

    @Override
    public void getAllCategory(GetAllCategoryCallback callback) {
        Call<List<ActivityCategory>> call = apiEndpoint.getAllCatergory();
        call.enqueue(new Callback<List<ActivityCategory>>() {
            @Override
            public void onResponse(Call<List<ActivityCategory>> call, Response<List<ActivityCategory>> response) {
                if (response.code() == ResponseServerCode.OK.getCode()) {
                    callback.onSuccess(response.body());
                } else {
                    sendErrorCallback(response);
                }
            }

            private void sendErrorCallback(Response<List<ActivityCategory>> response) {
                try {
                    if (response.errorBody() != null) {
                        ResponseError responseError = ConverterUtils.stringToResponseError(response.errorBody().string());
                        if (responseError.getMessage() == null)
                            callback.onError(responseError.getError());
                        else
                            callback.onError(responseError.getMessage());
                    } else {
                        callback.onError("Get Activity Category Failed");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<ActivityCategory>> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

    @Override
    public void createActivityCariRelawan(String bearerToken, String name, String start, String end,
                                          String registerDeadline, String description, String volunteerTasks,
                                          String volunteerEquipments, String volunteerRequirements,
                                          String briefs, int minVolunteers, int donationTarget, int categoryId,
                                          String city, String address, MultipartBody.Part photo,
                                          CreateActivityCallback callback) {

        RequestBody nameBody = RequestBody.create(okhttp3.MultipartBody.FORM, name);
        RequestBody startBody = RequestBody.create(okhttp3.MultipartBody.FORM, start);
        RequestBody endBody = RequestBody.create(okhttp3.MultipartBody.FORM, end);
        RequestBody registerDeadlineBody = RequestBody.create(okhttp3.MultipartBody.FORM, registerDeadline);
        RequestBody descriptionBody = RequestBody.create(okhttp3.MultipartBody.FORM, description);
        RequestBody volunterTasksBody = RequestBody.create(okhttp3.MultipartBody.FORM, volunteerTasks);
        RequestBody volunteerEquipmentsBody = RequestBody.create(okhttp3.MultipartBody.FORM, volunteerEquipments);
        RequestBody volunteerRequirementsBody = RequestBody.create(okhttp3.MultipartBody.FORM, volunteerRequirements);
        RequestBody briefsBody = RequestBody.create(okhttp3.MultipartBody.FORM, briefs);

        RequestBody minVolunteersBody = RequestBody.create(okhttp3.MultipartBody.FORM, String.valueOf(minVolunteers));
        RequestBody donationTargetBody = RequestBody.create(okhttp3.MultipartBody.FORM, String.valueOf(donationTarget));
        RequestBody categoryIdBody = RequestBody.create(okhttp3.MultipartBody.FORM, String.valueOf(categoryId));

        RequestBody cityBody = RequestBody.create(okhttp3.MultipartBody.FORM, city);
        RequestBody addressBody = RequestBody.create(okhttp3.MultipartBody.FORM, address);


        Call<Activity> call = apiEndpoint.createActivityCariRelawan(bearerToken, nameBody, startBody,
                endBody, registerDeadlineBody, descriptionBody, volunterTasksBody,
                volunteerEquipmentsBody, volunteerRequirementsBody, briefsBody, minVolunteersBody,
                donationTargetBody, categoryIdBody, cityBody, addressBody, photo);

        call.enqueue(new Callback<Activity>() {
            @Override
            public void onResponse(Call<Activity> call, Response<Activity> response) {
                if (response.code() == ResponseServerCode.CREATED.getCode()) {
                    callback.onSuccess("Sukses membuat kegiatan");
                } else {
                    try {
                        if (response.errorBody() != null) {
                            ResponseError responseError = ConverterUtils.stringToResponseError(response.errorBody().string());
                            if (responseError.getMessage() == null)
                                callback.onError(responseError.getError());
                            else
                                callback.onError(responseError.getMessage());
                        } else {
                            callback.onError("Gagal membuat kegiatan");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Activity> call, Throwable t) {
                callback.onError(t.getMessage());
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

        RequestBody nameBody = RequestBody.create(okhttp3.MultipartBody.FORM, name);
        RequestBody startBody = RequestBody.create(okhttp3.MultipartBody.FORM, start);
        RequestBody endBody = RequestBody.create(okhttp3.MultipartBody.FORM, end);
        RequestBody registerDeadlineBody = RequestBody.create(okhttp3.MultipartBody.FORM, registerDeadline);
        RequestBody descriptionBody = RequestBody.create(okhttp3.MultipartBody.FORM, description);
        RequestBody areaBody = RequestBody.create(okhttp3.MultipartBody.FORM, area);

        RequestBody categoryIdBody = RequestBody.create(okhttp3.MultipartBody.FORM, String.valueOf(categoryId));
        RequestBody maxParticipantsBody = RequestBody.create(okhttp3.MultipartBody.FORM, String.valueOf(maxParticipants));
        RequestBody volunteersTotalBody = RequestBody.create(okhttp3.MultipartBody.FORM, String.valueOf(volunteersTotal));

        RequestBody preparedFacilitatorBody = RequestBody.create(okhttp3.MultipartBody.FORM, preparedByFacilitator);
        RequestBody activityPlanBody = RequestBody.create(okhttp3.MultipartBody.FORM, activityPlan);
        RequestBody locationRequirementBody = RequestBody.create(okhttp3.MultipartBody.FORM, locationRequirement);
        RequestBody additionalInfoBody = RequestBody.create(okhttp3.MultipartBody.FORM, additionalInformation);

        Call<Activity> call = apiEndpoint.createActivityCariLokasi(bearerToken, nameBody, startBody,
                endBody, registerDeadlineBody, descriptionBody, areaBody, categoryIdBody,
                maxParticipantsBody, volunteersTotalBody, preparedFacilitatorBody, activityPlanBody,
                locationRequirementBody, additionalInfoBody, photo);

        call.enqueue(new Callback<Activity>() {
            @Override
            public void onResponse(Call<Activity> call, Response<Activity> response) {
                if (response.code() == ResponseServerCode.CREATED.getCode()) {
                    callback.onSuccess("Sukses membuat kegiatan");
                } else {
                    try {
                        if (response.errorBody() != null) {
                            ResponseError responseError = ConverterUtils.stringToResponseError(response.errorBody().string());
                            if (responseError.getMessage() == null)
                                callback.onError(responseError.getError());
                            else
                                callback.onError(responseError.getMessage());
                        } else {
                            callback.onError("Gagal membuat kegiatan");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Activity> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

    @Override
    public void presenceUser(String token, int idActivity, int idUser, PresenceCallack callback) {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("userId", idUser);

        Call<Activity> call = apiEndpoint.presenceUserByActivity(token, idActivity, jsonObject);
        call.enqueue(new Callback<Activity>() {
            @Override
            public void onResponse(Call<Activity> call, Response<Activity> response) {
                if (response.code() == ResponseServerCode.OK.getCode()) {
                    callback.onSuccess(response.body());
                } else {
                    try {
                        if (response.errorBody() != null) {
                            ResponseError responseError = ConverterUtils.stringToResponseError(response.errorBody().string());
                            if (responseError.getMessage() == null)
                                callback.onError(responseError.getError());
                            else
                                callback.onError(responseError.getMessage());
                        } else {
                            callback.onError("Gagal upload absensi");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Activity> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

    @Override
    public void doneActivity(String token, int idActivity, String reportText, MultipartBody.Part photo,
                             DoneActivityCallback callback) {

        RequestBody reportTextBody = RequestBody.create(okhttp3.MultipartBody.FORM, reportText);

        Call<Activity> call = apiEndpoint.doneActivity(token, idActivity, reportTextBody, photo);
        call.enqueue(new Callback<Activity>() {
            @Override
            public void onResponse(Call<Activity> call, Response<Activity> response) {
                if (response.code() == ResponseServerCode.OK.getCode()) {
                    callback.onSuccess(response.body());
                } else {
                    try {
                        if (response.errorBody() != null) {
                            ResponseError responseError = ConverterUtils.stringToResponseError(response.errorBody().string());
                            if (responseError.getMessage() == null)
                                callback.onError(responseError.getError());
                            else
                                callback.onError(responseError.getMessage());
                        } else {
                            callback.onError("Gagal menyelesaikan kegiatan");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Activity> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }


    @Override
    public void bookmarkActivity(String token, int idActivity, BookmarkActivityCallback callback) {
        Call<User> call = apiEndpoint.bookmarkActivity(token, idActivity);
        call.enqueue(new BookmarkCallback(callback));
    }

    @Override
    public void unBookmarkActivity(String token, int idActivity, UnBookmarkActivityCallback callback) {
        Call<User> call = apiEndpoint.unBoorkmarkActivity(token, idActivity);
        call.enqueue(new UnBookmarkCallback(callback));
    }

    @Override
    public void postDonation(String token, int amount, int activityId, MultipartBody.Part transferValidation,
                             PostDonationCallback callback) {

        RequestBody amountBody = RequestBody.create(okhttp3.MultipartBody.FORM, String.valueOf(amount));
        RequestBody activityIdBody = RequestBody.create(okhttp3.MultipartBody.FORM, String.valueOf(activityId));

        Call<ResponsePostDonation> call = apiEndpoint.postDonation(token, amountBody, activityIdBody, transferValidation);
        call.enqueue(new Callback<ResponsePostDonation>() {
            @Override
            public void onResponse(Call<ResponsePostDonation> call, Response<ResponsePostDonation> response) {
                if (response.code() == ResponseServerCode.CREATED.getCode()) {
                    callback.onSuccess(response.body());
                } else {
                    try {
                        if (response.errorBody() != null) {
                            ResponseError responseError = ConverterUtils.stringToResponseError(response.errorBody().string());
                            if (responseError.getMessage() == null)
                                callback.onError(responseError.getError());
                            else
                                callback.onError(responseError.getMessage());
                        } else {
                            callback.onError("Failed donate to this activity");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponsePostDonation> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

    @Override
    public void postLocation(String token, int idActivity, String city, String address, String startDateTime,
                             String endDateTime, String description, int capacity, MultipartBody.Part locationPhoto,
                             MultipartBody.Part licensePhoto, PostLocationCallback callback) {

        RequestBody idActivityBody = RequestBody.create(okhttp3.MultipartBody.FORM, String.valueOf(idActivity));
        RequestBody capacityBody = RequestBody.create(okhttp3.MultipartBody.FORM, String.valueOf(capacity));

        RequestBody cityBody = RequestBody.create(okhttp3.MultipartBody.FORM, city);
        RequestBody addressBody = RequestBody.create(okhttp3.MultipartBody.FORM, address);
        RequestBody startDateTimeBody = RequestBody.create(okhttp3.MultipartBody.FORM, startDateTime);
        RequestBody endDateTimeBody = RequestBody.create(okhttp3.MultipartBody.FORM, endDateTime);
        RequestBody descriptionBody = RequestBody.create(okhttp3.MultipartBody.FORM, description);


        Call<Location> call = apiEndpoint.postLocation(token, idActivityBody, cityBody, addressBody,
                startDateTimeBody, endDateTimeBody, descriptionBody, capacityBody, locationPhoto,licensePhoto);
        call.enqueue(new Callback<Location>() {
            @Override
            public void onResponse(Call<Location> call, Response<Location> response) {
                if (response.code() == ResponseServerCode.CREATED.getCode()) {
                    callback.onSuccess("Anda berhasil mengajukan tempat untuk kegiatan ini.");
                } else {
                    try {
                        if (response.errorBody() != null) {
                            ResponseError responseError = ConverterUtils.stringToResponseError(response.errorBody().string());
                            if (responseError.getMessage() == null)
                                callback.onError(responseError.getError());
                            else
                                callback.onError(responseError.getMessage());
                        } else {
                            callback.onError("Failed mengajukan tempat");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Location> call, Throwable t) {
                callback.onError(t.toString());
                t.printStackTrace();
            }
        });
    }

    private static class GetAllActivitiesCallback implements Callback<List<Activity>> {
        private final GetActivitiesCallback callback;

        public GetAllActivitiesCallback(GetActivitiesCallback callback) {
            this.callback = callback;
        }

        @Override
        public void onResponse(Call<List<Activity>> call, Response<List<Activity>> response) {
            if (response.code() == ResponseServerCode.OK.getCode()) {
                callback.onSuccess(response.body());
            } else {
                sendErrorCallback(response);
            }
        }

        private void sendErrorCallback(Response<List<Activity>> response) {
            try {
                if (response.errorBody() != null) {
                    ResponseError responseError = ConverterUtils.stringToResponseError(response.errorBody().string());
                    if (responseError.getMessage() == null)
                        callback.onError(responseError.getError());
                    else
                        callback.onError(responseError.getMessage());
                } else {
                    callback.onError("Get Activities Failed");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(Call<List<Activity>> call, Throwable t) {
            callback.onError(t.getMessage());
        }
    }

    private static class OnFollowActivityCallback implements Callback<Activity> {
        private final FollowActivityCallback callback;

        public OnFollowActivityCallback(FollowActivityCallback callback) {
            this.callback = callback;
        }

        @Override
        public void onResponse(Call<Activity> call, Response<Activity> response) {

            if (response.code() == ResponseServerCode.OK.getCode()) {
                callback.onSuccess(response.body());
            } else {
                sendErrorCallback(response);
            }
        }

        private void sendErrorCallback(Response<Activity> response) {
            try {
                if (response.errorBody() != null) {
                    ResponseError responseError = ConverterUtils.stringToResponseError(response.errorBody().string());
                    if (responseError.getMessage() == null)
                        callback.onError(responseError.getError());
                    else
                        callback.onError(responseError.getMessage());
                } else {
                    callback.onError("Failed");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(Call<Activity> call, Throwable t) {
            callback.onError(t.getMessage());
        }
    }

    private static class DetailActivityByIdCallback implements Callback<Activity> {
        private final GetActivityByIdCallback callback;

        public DetailActivityByIdCallback(GetActivityByIdCallback callback) {
            this.callback = callback;
        }

        @Override
        public void onResponse(Call<Activity> call, Response<Activity> response) {
            if (response.code() == ResponseServerCode.OK.getCode()) {
                callback.onSuccess(response.body());
            } else {
                sendErrorCallback(response);
            }
        }

        private void sendErrorCallback(Response<Activity> response) {
            try {
                if (response.errorBody() != null) {
                    ResponseError responseError = ConverterUtils.stringToResponseError(response.errorBody().string());
                    if (responseError.getMessage() == null)
                        callback.onError(responseError.getError());
                    else
                        callback.onError(responseError.getMessage());
                } else {
                    callback.onError("Get Activity Failed");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(Call<Activity> call, Throwable t) {
            callback.onError(t.getMessage());
        }
    }

    private static class BookmarkCallback implements Callback<User> {
        private final BookmarkActivityCallback callback;

        BookmarkCallback(BookmarkActivityCallback callback) {
            this.callback = callback;
        }

        @Override
        public void onResponse(Call<User> call, Response<User> response) {
            if (response.code() == ResponseServerCode.OK.getCode()) {
                String successMessage = "Kegiatan ini berhasil disimpan";
                callback.onSuccess(successMessage);
            } else {
                sendErrorCallback(response);
            }
        }

        void sendErrorCallback(Response<User> response) {
            try {
                if (response.errorBody() != null) {
                    ResponseError responseError = ConverterUtils.stringToResponseError(response.errorBody().string());
                    if (responseError.getMessage() == null)
                        callback.onError(responseError.getError());
                    else
                        callback.onError(responseError.getMessage());
                } else {
                    callback.onError("Failed add to bookmark");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(Call<User> call, Throwable t) {
            callback.onError(t.getMessage());
        }
    }

    private static class UnBookmarkCallback implements Callback<User> {
        private final UnBookmarkActivityCallback callback;

        UnBookmarkCallback(UnBookmarkActivityCallback callback) {
            this.callback = callback;
        }

        @Override
        public void onResponse(Call<User> call, Response<User> response) {
            if (response.code() == ResponseServerCode.OK.getCode()) {
                callback.onSuccess("Activity ini berhasil dihapus.");
            } else {
                sendErrorCallback(response);
            }
        }

        void sendErrorCallback(Response<User> response) {
            try {
                if (response.errorBody() != null) {
                    ResponseError responseError = ConverterUtils.stringToResponseError(response.errorBody().string());
                    if (responseError.getMessage() == null)
                        callback.onError(responseError.getError());
                    else
                        callback.onError(responseError.getMessage());
                } else {
                    callback.onError("Failed delete from bookmark");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(Call<User> call, Throwable t) {
            callback.onError(t.getMessage());
        }
    }
}
