package id.chessburger.wecare.model;

import com.google.gson.annotations.SerializedName;

import lombok.Builder;
import lombok.Data;

/**
 * Created by aflah on 08/09/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


@Data
@Builder
public class FollowedActivity {

    @SerializedName("id")
    private int id;

    @SerializedName("activityId")
    private int idActivity;

    @SerializedName("userId")
    private int idUser;

    @SerializedName("isPresent")
    private Boolean isPresent;

    @SerializedName("user")
    private User user;
}
