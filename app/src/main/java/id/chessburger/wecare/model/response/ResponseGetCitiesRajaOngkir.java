package id.chessburger.wecare.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import id.chessburger.wecare.model.City;
import lombok.Data;

/**
 * Created by aflah on 06/09/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */

@Data
public class ResponseGetCitiesRajaOngkir {

    @SerializedName("status")
    private ResponseStatusRajaOngkir responseStatusRajaOngkir;

    @SerializedName("results")
    private List<City> cities;
}
