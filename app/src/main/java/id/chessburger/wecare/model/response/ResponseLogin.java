package id.chessburger.wecare.model.response;

import com.google.gson.annotations.SerializedName;

import id.chessburger.wecare.model.User;
import lombok.Builder;
import lombok.Data;

/**
 * Created by aflah on 20/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */

@Data
public class ResponseLogin {

    @SerializedName("user")
    private User profile;

    @SerializedName("accessToken")
    private String accessToken;
}
