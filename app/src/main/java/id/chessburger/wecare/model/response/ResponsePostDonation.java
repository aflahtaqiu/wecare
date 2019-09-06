package id.chessburger.wecare.model.response;

import com.google.gson.annotations.SerializedName;

import lombok.Builder;
import lombok.Data;

/**
 * Created by aflah on 06/09/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */

@Data
@Builder
public class ResponsePostDonation {

    @SerializedName("id")
    private int id;

    @SerializedName("amount")
    private int amount;

    @SerializedName("activityId")
    private int activityId;

    @SerializedName("transferValidation")
    private String transferValidation;

    @SerializedName("userId")
    private int userId;

    @SerializedName("isVerified")
    private Boolean isVerified;
}
