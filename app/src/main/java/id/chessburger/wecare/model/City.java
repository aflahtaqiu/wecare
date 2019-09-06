package id.chessburger.wecare.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by aflah on 06/09/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */

@Getter
@Setter
public class City {

    @SerializedName("city_id")
    private int idCity;

    @SerializedName("province_id")
    private int idProvince;

    @SerializedName("province")
    private String province;

    @SerializedName("type")
    private String type;

    @SerializedName("city_name")
    private String cityName;

    @NonNull
    @Override
    public String toString() {
        return type + " " + cityName + ", " + province;
    }
}
