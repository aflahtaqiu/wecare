package id.chessburger.wecare.module.donation;

import android.content.Context;

import id.chessburger.wecare.R;
import id.chessburger.wecare.data.repository.ActivityDataRepository;
import id.chessburger.wecare.data.source.IActivityDataSource;
import id.chessburger.wecare.di.Injector;
import id.chessburger.wecare.model.Donation;
import id.chessburger.wecare.model.enumerations.SharedPrefKeys;
import id.chessburger.wecare.model.response.ResponsePostDonation;
import id.chessburger.wecare.utils.SharedPrefUtils;
import okhttp3.MultipartBody;

/**
 * Created by aflah on 06/09/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


class DonationPresenter {

    private IDonationView view;
    private ActivityDataRepository repository;
    private String token;

    private final String LOADING_STRING = "Loading...";
    private String successMessage;


    DonationPresenter(IDonationView view) {
        this.view = view;
        this.repository = Injector.provideActivityRepository();
        this.token = SharedPrefUtils.getStringSharedPref(SharedPrefKeys.TOKEN.getKey(), "");
        this.successMessage = ((Context) view).getResources().getString(R.string.isi_popup_sukses_donasi);
    }

    void postDonation (Donation donation, MultipartBody.Part transferValidationPhoto) {
        view.showLoading(LOADING_STRING);
        repository.postDonation(token, donation.getAmount(), donation.getActivityId(),
                transferValidationPhoto, new IActivityDataSource.PostDonationCallback() {
            @Override
            public void onSuccess(ResponsePostDonation responsePostDonation) {
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
}
