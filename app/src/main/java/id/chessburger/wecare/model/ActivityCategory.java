package id.chessburger.wecare.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by aflah on 12/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


@Setter
@Getter
@Builder
public class ActivityCategory {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
