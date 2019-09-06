package id.chessburger.wecare.base;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * Created By aflah on 03/06/19.
 * Email : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */

@Data
public class BaseResponse<T> {

    @SerializedName("data")
    private T data;

}
