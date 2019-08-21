package id.chessburger.wecare.data.remote;

import java.io.IOException;

import id.chessburger.wecare.base.BaseRemoteDataSource;
import id.chessburger.wecare.base.BaseResponse;
import id.chessburger.wecare.data.source.IUserDataSource;
import id.chessburger.wecare.model.enumerations.ResponseServerCode;
import id.chessburger.wecare.model.response.ResponseError;
import id.chessburger.wecare.model.response.ResponseLogin;
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
        Call<BaseResponse<ResponseLogin>> call = apiEndpoint.login(phoneNumber, password);
        call.enqueue(new Callback<BaseResponse<ResponseLogin>>() {
            @Override
            public void onResponse(Call<BaseResponse<ResponseLogin>> call, Response<BaseResponse<ResponseLogin>> response) {
                if (response.code() == ResponseServerCode.OK.getCode()) {
                    callback.onSuccess(response.body().getData());
                } else {
                    sendErrorCallback(response);
                }
            }

            private void sendErrorCallback(Response<BaseResponse<ResponseLogin>> response) {
                try {
                    ResponseError responseError = ConverterUtils.stringToResponseError(response.errorBody().string());
                    callback.onError(responseError.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<ResponseLogin>> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }
}
