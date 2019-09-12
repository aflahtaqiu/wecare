package id.chessburger.wecare.module.wecare_poin;

import android.util.Log;

import id.chessburger.wecare.data.repository.UserDataRepository;
import id.chessburger.wecare.data.source.IUserDataSource;
import id.chessburger.wecare.di.Injector;
import id.chessburger.wecare.model.User;
import id.chessburger.wecare.model.enumerations.SharedPrefKeys;
import id.chessburger.wecare.utils.SharedPrefUtils;

/**
 * Created by aflah on 12/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public class WeCarePoinPresenter {

    private IWeCarePoinView view;
    private UserDataRepository repository;

    private String token;

    public WeCarePoinPresenter(IWeCarePoinView view) {
        this.view = view;
        this.repository = Injector.provideUserRepository();
        this.token = SharedPrefUtils.getStringSharedPref(SharedPrefKeys.TOKEN.getKey(), "");
    }

    void updateWeCarePoin (int amount) {

        repository.updateWeCarePoint(token, amount, new IUserDataSource.LogInCallback() {
            @Override
            public void onSuccess(User user) {
                view.updateWeCarePoin(user.getWecarePoint());
            }

            @Override
            public void onError(String errorMessage) {

            }
        });
    }
}
