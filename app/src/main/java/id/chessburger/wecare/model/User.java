package id.chessburger.wecare.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import lombok.Builder;
import lombok.Data;

/**
 * Created by aflah on 08/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */

@Data
@Builder
public class User implements Serializable {

    @SerializedName("email")
    private String email;

    @SerializedName("phone")
    private String phoneNumber;

    @SerializedName("name")
    private String name;

    @SerializedName("gender")
    private String gender;

    @SerializedName("age")
    private Integer age;

    @SerializedName("profession")
    private String profession;

    @SerializedName("domicile")
    private String domicile;

    @SerializedName("description")
    private String description;

    @SerializedName("expertises")
    private String expertises;

    @SerializedName("relevance_issues")
    private String relevanceIssues;

    @SerializedName("photo")
    private String photo;

    @SerializedName("accessToken")
    private String accessToken;

    @SerializedName("wecarePoint")
    private Integer wecarePoint;

    @SerializedName("id")
    private int id;

    @SerializedName("bookmarks")
    private List<Activity> bookmarkedActivities;

    @SerializedName("followedActivities")
    private List<FollowedActivity> followedActivities;

    @SerializedName("activities")
    private List<Activity> campaignedActivities;

    @SerializedName("donations")
    private List<Donation> donations;
}
