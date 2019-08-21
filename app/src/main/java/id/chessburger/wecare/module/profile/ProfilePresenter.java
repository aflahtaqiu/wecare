package id.chessburger.wecare.module.profile;

import id.chessburger.wecare.data.repository.UserDataRepository;
import id.chessburger.wecare.di.Injector;
import id.chessburger.wecare.model.User;
import id.chessburger.wecare.model.enumerations.SharedPrefKeys;
import id.chessburger.wecare.utils.SharedPrefUtils;

/**
 * Created by aflah on 09/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public class ProfilePresenter {

    private IProfileView view;
    private UserDataRepository userDataRepository;

    public ProfilePresenter(IProfileView view) {
        this.view = view;
        this.userDataRepository = Injector.provideUserRepository();
    }

    void doLogout () {
        SharedPrefUtils.clearAll();
        view.moveIntoLogin();
    }

    void getUserProfileData () {
        User user = (User) SharedPrefUtils.getObjectSharedPref(SharedPrefKeys.PROFIL.getKey(),
                null, User.class);

        if (user != null) {
            view.showUserProfileData(user);
        }
    }
}
