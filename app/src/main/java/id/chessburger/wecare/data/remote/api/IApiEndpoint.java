package id.chessburger.wecare.data.remote.api;

import com.google.gson.JsonObject;

import java.util.List;

import javax.annotation.Nullable;

import id.chessburger.wecare.model.Activity;
import id.chessburger.wecare.model.ActivityCategory;
import id.chessburger.wecare.model.Location;
import id.chessburger.wecare.model.Schedule;
import id.chessburger.wecare.model.User;
import id.chessburger.wecare.model.response.ResponsePostDonation;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by aflah on 08/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public interface IApiEndpoint {

    @GET("activity/{id}")
    Call<Activity> getActivityByIdJoin(
            @Header("Authorization") String token,
            @Path("id") int idActivity,
            @Query("join") String joinRelational,
            @Nullable
            @Query("join") String joinRelational2
    );

    @GET("activity/{id}")
    Call<Activity> getActivityByIdJoinFilter(
            @Header("Authorization") String token,
            @Path("id") int idActivity,
            @Query("join") String joinRelational,
            @Nullable
            @Query("filter") String filterRelational
    );

    @GET("activity")
    Call<List<Activity>> getActivitiesWithJoin(
            @Query("join") String joinRelational
    );

    @GET("activity")
    Call<List<Activity>> getActivitiesWithFilter (
            @Query("filter") String filter,
            @Nullable
            @Query("filter") String filter2,
            @Nullable
            @Query("join") String join
    );

    @GET("activity")
    Call<List<Activity>> searchActivities (
            @Query("keyword") String keyword,
            @Nullable
            @Query("join") String join
    );

    @GET("category")
    Call<List<ActivityCategory>> getAllCatergory ();

    @GET("user/{id}")
    Call<User> getUserByIdWithJoin (
            @Path("id") int idUser,
            @Query("join") String joinRelational,
            @Nullable
            @Query("join") String joinRelational2,
            @Nullable
            @Query("join") String joinRelational3
    );

    @GET("user-schedule/done/{id}")
    Call<List<Schedule>> getDoneFollowedActivity (
            @Path("id") int idUser
    );


    @GET("user-schedule/undone/{id}")
    Call<List<Schedule>> getUndoneFollowedActivity (
            @Path("id") int idUser
    );

    @GET("user-activities/done/{id}")
    Call<List<Activity>> getDoneCampaignedActivity (
            @Path("id") int idUser
    );


    @GET("user-activities/undone/{id}")
    Call<List<Activity>> getUndoneCampaignedActivity (
            @Path("id") int idUser
    );

    @PATCH("activity/bookmark/{id}")
    Call<User> bookmarkActivity (
            @Header("Authorization") String bearerToken,
            @Path("id") int idActivity
    );

    @PATCH("activity/unbookmark/{id}")
    Call<User> unBoorkmarkActivity(
            @Header("Authorization") String bearerToken,
            @Path("id") int idActivity
    );

    @PATCH("activity/follow/{id}")
    Call<Activity> followActivity (
            @Header("Authorization") String token,
            @Path("id") int idActivity
    );

    @PATCH("activity/presence/{id}")
    Call<Activity> presenceUserByActivity (
            @Header("Authorization") String bearerToken,
            @Path("id") int idActivity,
            @Body JsonObject jsonObject
    );

    @Multipart
    @PATCH("activity/done/{id}")
    Call<Activity> doneActivity (
            @Header("Authorization") String bearerToken,
            @Path("id") int idActivity,
            @Part("reportText") RequestBody reportText,
            @Part MultipartBody.Part photo
    );

    @Multipart
    @POST("activity/find-volunteers")
    Call<Activity> createActivityCariRelawan (
            @Header("Authorization") String bearerToken,
            @Part("name") RequestBody name,
            @Part("start") RequestBody start,
            @Part("end") RequestBody end,
            @Part("registerDeadline") RequestBody registerDeadline,
            @Part("description") RequestBody description,
            @Part("volunteerTasks") RequestBody volunteerTasks,
            @Part("volunteerEquipments") RequestBody volunteerEquipments,
            @Part("volunteerRequirements") RequestBody volunteerRequirements,
            @Part("briefs") RequestBody briefs,
            @Part("minVolunteers") RequestBody minVolunteers,
            @Part("donationTarget") RequestBody donationTarget,
            @Part("categoryId") RequestBody categoryId,
            @Part("city") RequestBody city,
            @Part("address") RequestBody address,
            @Part MultipartBody.Part photo
    );

    @Multipart
    @POST("activity/find-location")
    Call<Activity> createActivityCariLokasi (
            @Header("Authorization") String bearerToken,
            @Part("name") RequestBody name,
            @Part("start") RequestBody start,
            @Part("end") RequestBody end,
            @Part("registerDeadline") RequestBody registerDeadline,
            @Part("description") RequestBody description,
            @Part("area") RequestBody area,
            @Part("categoryId") RequestBody categoryId,
            @Part("maxParticipants") RequestBody maxParticipants,
            @Part("volunteersTotal") RequestBody volunteersTotal,
            @Part("preparedByFacilitator") RequestBody preparedByFacilitator,
            @Part("activityPlan") RequestBody activityPlan,
            @Part("locationRequirement") RequestBody locationRequirement,
            @Part("additionalInformation") RequestBody additionalInformation,
            @Part MultipartBody.Part photo
    );

    @FormUrlEncoded
    @POST("auth/login")
    Call<User> login (
            @Field("phone") String phoneNumber,
            @Field("password") String password
    );

    @Multipart
    @POST("donation")
    Call<ResponsePostDonation> postDonation (
            @Header("Authorization") String bearerToken,
            @Part("amount") RequestBody amount,
            @Part("activityId") RequestBody activityId,
            @Part MultipartBody.Part transferValidation
    );

    @Multipart
    @POST("location")
    Call<Location> postLocation (
            @Header("Authorization") String bearerToken,
            @Part("activityId") RequestBody activityId,
            @Part("city") RequestBody city,
            @Part("address") RequestBody address,
            @Part("start") RequestBody start,
            @Part("end") RequestBody end,
            @Part("description") RequestBody description,
            @Part("capacity") RequestBody capacity,
            @Part MultipartBody.Part locationPhoto,
            @Part MultipartBody.Part licensePhoto
    );
}
