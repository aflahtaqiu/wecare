package id.chessburger.wecare.data.source;

import java.util.List;

import id.chessburger.wecare.model.City;

/**
 * Created by aflah on 06/09/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public interface IRajaOngkirDataSource {

    void getCities(String apiKey, GetCitiesCallback callback);

    interface GetCitiesCallback{
        void onSuccess (List<City> cities);
        void onError (String errorMessage);
    }
}
