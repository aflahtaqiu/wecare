package id.chessburger.wecare.module.edit_profile;

import id.chessburger.wecare.data.repository.UserDataRepository;
import id.chessburger.wecare.di.Injector;

/**
 * Created by aflah on 12/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public class EditProfilePresenter {

    private IEditProfileView view;
    private UserDataRepository userDataRepository;

    public EditProfilePresenter(IEditProfileView view) {
        this.view = view;
        this.userDataRepository = Injector.provideUserRepository();
    }
}
