package id.chessburger.wecare.model;

import com.google.gson.annotations.SerializedName;

import lombok.Builder;
import lombok.Data;

/**
 * Created by aflah on 08/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */

@Data
@Builder
public class User {

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

    @SerializedName("wecare_point")
    private Integer wecarePoint;

    @SerializedName("id")
    private String id;
}
