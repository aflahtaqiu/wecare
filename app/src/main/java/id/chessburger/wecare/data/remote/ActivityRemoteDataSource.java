package id.chessburger.wecare.data.remote;

import android.util.Log;

import java.io.IOException;
import java.util.List;

import id.chessburger.wecare.base.BaseRemoteDataSource;
import id.chessburger.wecare.data.source.IActivityDataSource;
import id.chessburger.wecare.model.Activity;
import id.chessburger.wecare.model.ActivityCategory;
import id.chessburger.wecare.model.enumerations.ResponseServerCode;
import id.chessburger.wecare.model.response.ResponseError;
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
        Call<List<Activity>> call = apiEndpoint.getAllActivitiesJoin(joinQuery);
        call.enqueue(new GetAllActivitiesCallback(callback));
    }

    @Override
    public void getActivityById(String token, int idActivity, String joinRelation, GetActivityByIdCallback callback) {
        Call<Activity> call = apiEndpoint.getActivityByIdJoin(token, idActivity, joinRelation);
        call.enqueue(new Callback<Activity>() {
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
                                          int typeId, String city, String address, MultipartBody.Part photo,
                                          CreateActivityCariRelawanCallback callback) {

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
        RequestBody typeIdBody = RequestBody.create(okhttp3.MultipartBody.FORM, String.valueOf(typeId));

        RequestBody cityBody = RequestBody.create(okhttp3.MultipartBody.FORM, city);
        RequestBody addressBody = RequestBody.create(okhttp3.MultipartBody.FORM, address);


        Call<Activity> call = apiEndpoint.createActivityCariRelawan(bearerToken, nameBody, startBody,
                endBody, registerDeadlineBody, descriptionBody, volunterTasksBody,
                volunteerEquipmentsBody, volunteerRequirementsBody, briefsBody, minVolunteersBody,
                donationTargetBody, categoryIdBody, typeIdBody, cityBody, addressBody, photo);
        call.enqueue(new Callback<Activity>() {
            @Override
            public void onResponse(Call<Activity> call, Response<Activity> response) {
                if (response.code() == ResponseServerCode.CREATED.getCode()) {
                    callback.onSuccess("Activity sudah dibuat");
                } else {
                    try {
                        Log.e("response gagal", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    callback.onError("Gagal membuat activity");
                }
            }

            @Override
            public void onFailure(Call<Activity> call, Throwable t) {
                callback.onError(t.getMessage());
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
}
