package id.chessburger.wecare.base;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

import id.chessburger.wecare.model.response.ResponsePaging;
import lombok.Data;

/**
 * Created By aflah on 03/06/19.
 * Email : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */

@Data
public class BaseResponse<T> {

    @SerializedName("statusCode")
    private int code;

    @SerializedName("error")
    private String status;

    @SerializedName("data")
    private T data;

    @SerializedName("paging")
    private List<ResponsePaging> paging;

    @SerializedName("error")
    private Map<String, List<String>> errors;
}
