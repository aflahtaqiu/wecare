package id.chessburger.wecare.model;

import com.google.gson.annotations.SerializedName;

import lombok.Builder;
import lombok.Data;

/**
 * Created by aflah on 12/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


@Data
@Builder
public class ActivityType {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;
}
