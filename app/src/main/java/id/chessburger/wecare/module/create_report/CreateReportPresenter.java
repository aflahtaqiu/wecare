package id.chessburger.wecare.module.create_report;

import id.chessburger.wecare.data.repository.ActivityDataRepository;
import id.chessburger.wecare.data.source.IActivityDataSource;
import id.chessburger.wecare.di.Injector;
import id.chessburger.wecare.model.Activity;
import id.chessburger.wecare.model.enumerations.SharedPrefKeys;
import id.chessburger.wecare.utils.SharedPrefUtils;
import okhttp3.MultipartBody;

/**
 * Created by aflah on 10/09/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


class CreateReportPresenter {

    private ICreateReport view;
    private ActivityDataRepository repository;
    private String token;

    private static final String LOADING_STRING = "Loading...";

    CreateReportPresenter(ICreateReport view) {
        this.view = view;
        this.repository = Injector.provideActivityRepository();
        this.token = SharedPrefUtils.getStringSharedPref(SharedPrefKeys.TOKEN.getKey(), "");
    }

    void getVolunteersName () {
        
    }

    void postReport (int idActivity, String reportText, MultipartBody.Part picture) {
        view.showLoading(LOADING_STRING);
        repository.doneActivity(token, idActivity, reportText, picture, new IActivityDataSource.DoneActivityCallback() {
            @Override
            public void onSuccess(Activity activity) {
                view.hideLoading();
                view.showMessage("Kegiatan sudah selesai dan laporan sudah terkirim");
            }

            @Override
            public void onError(String errorMessage) {
                view.showMessage(errorMessage);
            }
        });

    }

    void postAbsence () {

    }
}
