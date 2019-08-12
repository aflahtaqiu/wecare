package id.chessburger.wecare.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

import lombok.Data;

/**
 * Created By aflah on 03/06/19.
 * Email : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */

@Data
public class BaseResponse<T> {

    @SerializedName("code")
    private int code;

    @SerializedName("status")
    private String status;

    @SerializedName("data")
    private T data;

    @SerializedName("paging")
    private List<ResponsePaging> paging;

    @SerializedName("error")
    private Map<String, List<String>> errors;
}
