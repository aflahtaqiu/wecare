package id.chessburger.wecare.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

/**
 * Created by aflah on 12/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


@Data
@Builder
public class Location {

    @SerializedName("id")
    private int id;

    @SerializedName("userId")
    private int userId;

    @SerializedName("city")
    private String city;

    @SerializedName("address")
    private String address;

    @SerializedName("latitude")
    private float latitude;

    @SerializedName("longitude")
    private float longitude;

    @SerializedName("start")
    private Date startDate;

    @SerializedName("end")
    private Date endDate;

    @SerializedName("description")
    private String description;

    @SerializedName("licensePhoto")
    private String licensePhoto;

    @SerializedName("locationPhoto")
    private String locationPhoto;

    @SerializedName("isApproved")
    private Boolean isApproved;
}
