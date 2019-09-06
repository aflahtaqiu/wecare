package id.chessburger.wecare.data.repository;

import java.util.List;

import id.chessburger.wecare.data.remote.RajaOngkirRemoteDataSource;
import id.chessburger.wecare.data.source.IRajaOngkirDataSource;
import id.chessburger.wecare.model.City;

/**
 * Created by aflah on 06/09/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public class RajaOngkirDataRepository implements IRajaOngkirDataSource {

    private RajaOngkirRemoteDataSource remoteDataSource;
    private static RajaOngkirDataRepository repository;

    public static RajaOngkirDataRepository getInstance() {
        if (repository == null) {
            repository = new RajaOngkirDataRepository(RajaOngkirRemoteDataSource.getInstance());
        }
        return repository;
    }

    RajaOngkirDataRepository(RajaOngkirRemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public void getCities(String apiKey, GetCitiesCallback callback) {
        remoteDataSource.getCities(apiKey, new GetCitiesCallback() {
            @Override
            public void onSuccess(List<City> cities) {
                callback.onSuccess(cities);
            }

            @Override
            public void onError(String errorMessage) {
                callback.onError(errorMessage);
            }
        });
    }
}
