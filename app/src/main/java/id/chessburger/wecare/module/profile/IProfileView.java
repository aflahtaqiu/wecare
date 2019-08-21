package id.chessburger.wecare.module.profile;

import id.chessburger.wecare.base.IBaseView;
import id.chessburger.wecare.model.User;

/**
 * Created by aflah on 09/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public interface IProfileView extends IBaseView {
    void moveIntoLogin();
    void moveIntoEditProfile();
    void showUserProfileData(User user);
}
