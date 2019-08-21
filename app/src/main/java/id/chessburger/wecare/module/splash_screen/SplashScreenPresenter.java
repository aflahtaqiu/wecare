package id.chessburger.wecare.module.splash_screen;

import android.text.TextUtils;

import id.chessburger.wecare.model.enumerations.SharedPrefKeys;
import id.chessburger.wecare.utils.SharedPrefUtils;

/**
 * Created by aflah on 20/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public class SplashScreenPresenter {

    private ISplashScreenView view;

    public SplashScreenPresenter(ISplashScreenView view) {
        this.view = view;
    }

    void checkCache () {

        boolean isNotLogin = TextUtils.isEmpty(SharedPrefUtils.getStringSharedPref(
                SharedPrefKeys.TOKEN.getKey(), ""));

        if (isNotLogin) {
            view.moveIntoLogin();
        } else {
            view.moveIntoMain();
        }
    }
}
