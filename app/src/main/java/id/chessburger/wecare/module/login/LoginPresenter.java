package id.chessburger.wecare.module.login;

import id.chessburger.wecare.data.repository.UserDataRepository;
import id.chessburger.wecare.di.Injector;

/**
 * Created by aflah on 20/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


class LoginPresenter {

    private static final String BEARER_TOKEN_PREFIX = "Bearer ";
    private static final String INVALID_TOKEN_MESSAGE = "Login failed - invalid user token...";

    private ILoginView view;
    private UserDataRepository userDataRepository;

    LoginPresenter(ILoginView view) {
        this.view = view;
        this.userDataRepository = Injector.provideUserRepository();
    }

    void doLogin () {
        view.showLoading("Loading Process...");
        // TODO: do login logic and save token to SP
        view.hideLoading();
        view.moveIntoMain();
    }

}
