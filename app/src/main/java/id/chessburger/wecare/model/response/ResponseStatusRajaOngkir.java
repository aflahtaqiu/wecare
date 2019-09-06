package id.chessburger.wecare.model.response;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * Created by aflah on 06/09/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */

@Data
public class ResponseStatusRajaOngkir {

    @SerializedName("code")
    private Integer code;

    @SerializedName("description")
    private String description;
}
