package id.chessburger.wecare.data.remote;

import id.chessburger.wecare.base.BaseRemoteDataSource;
import id.chessburger.wecare.data.source.IRajaOngkirDataSource;
import id.chessburger.wecare.model.enumerations.ResponseServerCode;
import id.chessburger.wecare.model.response.BaseResponseRajaOngkir;
import id.chessburger.wecare.model.response.ResponseGetCitiesRajaOngkir;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by aflah on 06/09/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public class RajaOngkirRemoteDataSource extends BaseRemoteDataSource implements IRajaOngkirDataSource {

    private static RajaOngkirRemoteDataSource remoteDataSource;
    public static RajaOngkirRemoteDataSource getInstance() {
        if (remoteDataSource == null) {
            remoteDataSource = new RajaOngkirRemoteDataSource();
        }
        return remoteDataSource;
    }

    @Override
    public void getCities(String apiKey, GetCitiesCallback callback) {
        Call<BaseResponseRajaOngkir<ResponseGetCitiesRajaOngkir>> call = rajaOngkirEndpoint.getIndonesiaCities(apiKey);
        call.enqueue(new Callback<BaseResponseRajaOngkir<ResponseGetCitiesRajaOngkir>>() {
            @Override
            public void onResponse(Call<BaseResponseRajaOngkir<ResponseGetCitiesRajaOngkir>> call, Response<BaseResponseRajaOngkir<ResponseGetCitiesRajaOngkir>> response) {
                if (response.body().getRajaOngkir().getResponseStatusRajaOngkir().getCode() == ResponseServerCode.OK.getCode()) {
                    callback.onSuccess(response.body().getRajaOngkir().getCities());
                } else {
                    callback.onError("Gagal mendapatkan nama kabupaten kota");
                }
            }

            @Override
            public void onFailure(Call<BaseResponseRajaOngkir<ResponseGetCitiesRajaOngkir>> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }
}
