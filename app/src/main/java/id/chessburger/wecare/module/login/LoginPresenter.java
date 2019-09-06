package id.chessburger.wecare.module.login;

import android.text.TextUtils;
import android.util.Log;

import id.chessburger.wecare.data.repository.UserDataRepository;
import id.chessburger.wecare.data.source.IUserDataSource;
import id.chessburger.wecare.di.Injector;
import id.chessburger.wecare.model.enumerations.SharedPrefKeys;
import id.chessburger.wecare.model.response.ResponseLogin;
import id.chessburger.wecare.utils.ConverterUtils;
import id.chessburger.wecare.utils.SharedPrefUtils;

/**
 * Created by aflah on 20/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


class LoginPresenter {

    private static final String BEARER_TOKEN_PREFIX = "Bearer ";
    private static final String LOGIN_LOADING_MESSAGE = "Login process ...";
    private static final String INVALID_TOKEN_MESSAGE = "Login failed - invalid user token...";

    private ILoginView view;
    private UserDataRepository userDataRepository;

    LoginPresenter(ILoginView view) {
        this.view = view;
        this.userDataRepository = Injector.provideUserRepository();
    }

    void doLogin (String phoneNumber, String password) {
        view.showLoading(LOGIN_LOADING_MESSAGE);

        userDataRepository.login(phoneNumber, password, new IUserDataSource.LogInCallback() {
            @Override
            public void onSuccess(ResponseLogin responseLogin) {
                String token = BEARER_TOKEN_PREFIX + responseLogin.getAccessToken();

                if (!TextUtils.isEmpty(token)) {
                    SharedPrefUtils.setStringSharedPref(SharedPrefKeys.TOKEN.getKey(), token);
                } else {
                    view.showMessage(INVALID_TOKEN_MESSAGE);
                }

                String userStringJson = ConverterUtils.object2StringJSON(responseLogin.getProfile());
                SharedPrefUtils.setStringSharedPref(SharedPrefKeys.PROFIL.getKey(), userStringJson);

                view.hideLoading();
                view.moveIntoMain();
            }

            @Override
            public void onError(String errorMessage) {
                view.hideLoading();
                view.showMessage(errorMessage);
            }
        });
    }
}
