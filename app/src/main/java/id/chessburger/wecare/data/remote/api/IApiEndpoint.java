package id.chessburger.wecare.data.remote.api;

import java.util.List;

import id.chessburger.wecare.base.BaseResponse;
import id.chessburger.wecare.model.Activity;
import id.chessburger.wecare.model.response.ResponseLogin;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

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

    @PATCH("activity/follow/{id}")
    Call<Activity> followActivity (
            @Header("Authorization") String token,
            @Path("id") int idActivity
    );

    @GET("activity/{id}")
    Call<Activity> getActivityById(
            @Path("id") int idActivity
    );

    @GET("activity")
    Call<List<Activity>> getAllActivitiesJoin (
            @Query("join") String joinRelational
    );
}
