package id.chessburger.wecare.model;

import com.google.gson.annotations.SerializedName;

import lombok.Builder;
import lombok.Data;

/**
 * Created by aflah on 10/09/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


@Data
@Builder
public class Schedule {

    @SerializedName("id")
    private int id;

    @SerializedName("activityId")
    private int activityId;

    @SerializedName("userId")
    private int userId;

    @SerializedName("isPresent")
    private Boolean isPresent;

    @SerializedName("activity")
    private Activity activity;


}
