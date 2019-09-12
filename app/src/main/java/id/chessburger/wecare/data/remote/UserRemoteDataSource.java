package id.chessburger.wecare.data.remote;


import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.List;

import id.chessburger.wecare.base.BaseRemoteDataSource;
import id.chessburger.wecare.data.source.IUserDataSource;
import id.chessburger.wecare.model.Activity;
import id.chessburger.wecare.model.Schedule;
import id.chessburger.wecare.model.User;
import id.chessburger.wecare.model.enumerations.ResponseServerCode;
import id.chessburger.wecare.model.response.ResponseError;
import id.chessburger.wecare.utils.ConverterUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by aflah on 08/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public class UserRemoteDataSource extends BaseRemoteDataSource implements IUserDataSource {

    private static UserRemoteDataSource remoteDataSource;
    public static UserRemoteDataSource getInstance(){
        if (remoteDataSource == null) {
            remoteDataSource = new UserRemoteDataSource();
        }
        return remoteDataSource;
    }

    @Override
    public void login(String phoneNumber, String password, LogInCallback callback) {
        Call<User> call = apiEndpoint.login(phoneNumber, password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.code() == ResponseServerCode.OK.getCode()) {
                    callback.onSuccess(response.body());
                } else {
                    sendErrorCallback(response);
                }
            }

            private void sendErrorCallback(Response<User> response) {
                try {
                    if (response.errorBody() != null) {
                        ResponseError responseError = ConverterUtils.stringToResponseError(response.errorBody().string());
                        callback.onError(responseError.getMessage());
                    } else {
                        callback.onError("Login Failed");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

    @Override
    public void getBookmarkedActivities(int idUser, String joinQuery, GetBookmarkedActivitiesCallback callback) {
        Call<User> call = apiEndpoint.getUserByIdWithJoin(idUser, joinQuery, null, null);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.code() == ResponseServerCode.OK.getCode()) {
                    callback.onSuccess(response.body().getBookmarkedActivities());
                } else {
                    try {
                        if (response.errorBody() != null) {
                            ResponseError responseError = ConverterUtils.stringToResponseError(response.errorBody().string());
                            callback.onError(responseError.getMessage());
                        } else {
                            callback.onError("Gagal mendapatkan bookmarked activities");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

    @Override
    public void getUserStatistic(int idUser, String joinQuery, String joinQuery2, String joinQuery3, GetUserStatisticCallback callback) {
        Call<User> call = apiEndpoint.getUserByIdWithJoin(idUser, joinQuery, joinQuery2, joinQuery3);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.code() == ResponseServerCode.OK.getCode()) {
                    callback.onSuccess(response.body());
                } else {
                    try {
                        if (response.errorBody() != null) {
                            ResponseError responseError = ConverterUtils.stringToResponseError(response.errorBody().string());
                            callback.onError(responseError.getMessage());
                        } else {
                            callback.onError("Gagal mendapatkan bookmarked activities");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

    @Override
    public void getDoneFollowedActivities(int idUser, GetScheduleCallback callback) {
        Call<List<Schedule>> call = apiEndpoint.getDoneFollowedActivity(idUser);
        call.enqueue(new Callback<List<Schedule>>() {
            @Override
            public void onResponse(Call<List<Schedule>> call, Response<List<Schedule>> response) {
                if (response.code() == ResponseServerCode.OK.getCode()) {
                    callback.onSuccess(response.body());
                } else {
                    try {
                        if (response.errorBody() != null) {
                            ResponseError responseError = ConverterUtils.stringToResponseError(response.errorBody().string());
                            callback.onError(responseError.getMessage());
                        } else {
                            callback.onError("Gagal mendapatkan activities");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Schedule>> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

    @Override
    public void getUndoneFollowedActivities(int idUser, GetScheduleCallback callback) {
        Call<List<Schedule>> call = apiEndpoint.getUndoneFollowedActivity(idUser);
        call.enqueue(new Callback<List<Schedule>>() {
            @Override
            public void onResponse(Call<List<Schedule>> call, Response<List<Schedule>> response) {
                if (response.code() == ResponseServerCode.OK.getCode()) {
                    callback.onSuccess(response.body());
                } else {
                    try {
                        if (response.errorBody() != null) {
                            ResponseError responseError = ConverterUtils.stringToResponseError(response.errorBody().string());
                            callback.onError(responseError.getMessage());
                        } else {
                            callback.onError("Gagal mendapatkan activities");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Schedule>> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

    @Override
    public void getDoneCampaignedActivities(int idUser, GetActivitiesCallback callback) {
        Call<List<Activity>> call = apiEndpoint.getDoneCampaignedActivity(idUser);
        call.enqueue(new Callback<List<Activity>>() {
            @Override
            public void onResponse(Call<List<Activity>> call, Response<List<Activity>> response) {
                if (response.code() == ResponseServerCode.OK.getCode()) {
                    callback.onSuccess(response.body());
                } else {
                    try {
                        if (response.errorBody() != null) {
                            ResponseError responseError = ConverterUtils.stringToResponseError(response.errorBody().string());
                            callback.onError(responseError.getMessage());
                        } else {
                            callback.onError("Gagal mendapatkan activities");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Activity>> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

    @Override
    public void getUnDoneCampaignedActivities(int idUser, GetActivitiesCallback callback) {
        Call<List<Activity>> call = apiEndpoint.getUndoneCampaignedActivity(idUser);
        call.enqueue(new Callback<List<Activity>>() {
            @Override
            public void onResponse(Call<List<Activity>> call, Response<List<Activity>> response) {
                if (response.code() == ResponseServerCode.OK.getCode()) {
                    callback.onSuccess(response.body());
                } else {
                    try {
                        if (response.errorBody() != null) {
                            ResponseError responseError = ConverterUtils.stringToResponseError(response.errorBody().string());
                            callback.onError(responseError.getMessage());
                        } else {
                            callback.onError("Gagal mendapatkan activities");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Activity>> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

    @Override
    public void updateWeCarePoint(String token, int amount, LogInCallback callback) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("amount", amount);

        Call<User> call = apiEndpoint.updateWeCarePoint(token, jsonObject);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.code() == ResponseServerCode.CREATED.getCode()) {
                    callback.onSuccess(response.body());
                } else {
                    try {
                        if (response.errorBody() != null) {
                            ResponseError responseError = ConverterUtils.stringToResponseError(response.errorBody().string());
                            callback.onError(responseError.getMessage());
                        } else {
                            callback.onError("Coba lagi :(");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }
}
