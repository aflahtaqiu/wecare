package id.chessburger.wecare.model.response;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * Created by aflah on 21/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


@Data
public class ResponseError {

    @SerializedName("statusCode")
    private int statusCode;

    @SerializedName("error")
    private String error;

    @SerializedName("message")
    private String message;
}
