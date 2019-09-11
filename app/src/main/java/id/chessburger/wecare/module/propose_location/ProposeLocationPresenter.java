package id.chessburger.wecare.module.propose_location;

import android.util.Log;

import java.util.List;

import id.chessburger.wecare.data.repository.ActivityDataRepository;
import id.chessburger.wecare.data.repository.RajaOngkirDataRepository;
import id.chessburger.wecare.data.source.IActivityDataSource;
import id.chessburger.wecare.data.source.IRajaOngkirDataSource;
import id.chessburger.wecare.di.Injector;
import id.chessburger.wecare.model.City;
import id.chessburger.wecare.model.Location;
import id.chessburger.wecare.model.enumerations.ApiKey;
import id.chessburger.wecare.model.enumerations.SharedPrefKeys;
import id.chessburger.wecare.utils.SharedPrefUtils;
import okhttp3.MultipartBody;

/**
 * Created by aflah on 06/09/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


class ProposeLocationPresenter {

    private IProposeLocationView view;
    private ActivityDataRepository activityRepository;
    private RajaOngkirDataRepository rajaOngkirRepository;
    private String token;

    private static final String LOADING_STRING = "Loading...";

    ProposeLocationPresenter(IProposeLocationView view) {
        this.view = view;
        this.activityRepository = Injector.provideActivityRepository();
        this.rajaOngkirRepository = Injector.provideRajaOngkirRepository();
        this.token = SharedPrefUtils.getStringSharedPref(SharedPrefKeys.TOKEN.getKey(), "");
    }

    void proposeLocationToServer (int idActivity, Location location,  String startDateTime,
                                  String endDateTime, MultipartBody.Part perizinanTempat,
                                  MultipartBody.Part kondisiTempat) {

        view.showLoading(LOADING_STRING);
        activityRepository.postLocation(token, idActivity, location.getCity(), location.getAddress(),
                startDateTime, endDateTime, location.getDescription(), location.getCapacity(),
                kondisiTempat, perizinanTempat, new IActivityDataSource.PostLocationCallback() {
            @Override
            public void onSuccess(String successMessage) {
                view.hideLoading();
                view.showMessage(successMessage);
            }

            @Override
            public void onError(String errorMessage) {
                view.hideLoading();
                view.showMessage(errorMessage);
            }
        });

    }

    void getAllIndonesiaCities () {

        rajaOngkirRepository.getCities(ApiKey.RAJA_ONGKIR.getApiKey(), new IRajaOngkirDataSource.GetCitiesCallback() {
            @Override
            public void onSuccess(List<City> cities) {
                view.setIndonesiaCities(cities);
            }

            @Override
            public void onError(String errorMessage) {
                view.showMessage(errorMessage);
            }
        });
    }
}
