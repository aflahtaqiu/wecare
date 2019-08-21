package id.chessburger.wecare.data.remote.api;

import id.chessburger.wecare.base.BaseResponse;
import id.chessburger.wecare.model.response.ResponseLogin;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by aflah on 08/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public interface IApiEndpoint {

    @FormUrlEncoded
    @POST("auth/login")
    Call<BaseResponse<ResponseLogin>> login (
            @Field("phone") String phoneNumber,
            @Field("password") String password
    );
}
