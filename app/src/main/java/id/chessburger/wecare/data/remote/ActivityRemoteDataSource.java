package id.chessburger.wecare.data.remote;

import java.io.IOException;
import java.util.List;

import id.chessburger.wecare.base.BaseRemoteDataSource;
import id.chessburger.wecare.data.source.IActivityDataSource;
import id.chessburger.wecare.model.Activity;
import id.chessburger.wecare.model.enumerations.ResponseServerCode;
import id.chessburger.wecare.model.response.ResponseError;
import id.chessburger.wecare.utils.ConverterUtils;
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
    public void getActivityById(int idActivity, GetActivityByIdCallback callback) {
        Call<Activity> call = apiEndpoint.getActivityById(idActivity);
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
                    callback.onError(responseError.getError());
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
