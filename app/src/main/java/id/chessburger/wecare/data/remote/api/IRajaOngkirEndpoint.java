package id.chessburger.wecare.data.remote.api;

import id.chessburger.wecare.model.response.BaseResponseRajaOngkir;
import id.chessburger.wecare.model.response.ResponseGetCitiesRajaOngkir;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by aflah on 06/09/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public interface IRajaOngkirEndpoint {

    @GET("city")
    Call<BaseResponseRajaOngkir<ResponseGetCitiesRajaOngkir>> getIndonesiaCities (
            @Header("key") String apiKey
    );
}
