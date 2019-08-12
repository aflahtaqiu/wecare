package id.chessburger.wecare.module.home;

import id.chessburger.wecare.data.repository.ActivityDataRepository;
import id.chessburger.wecare.data.repository.UserDataRepository;
import id.chessburger.wecare.di.Injector;

/**
 * Created by aflah on 09/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public class HomePresenter {

    private IHomeView view;
    private UserDataRepository userDataRepository;
    private ActivityDataRepository activityDataRepository;

    public HomePresenter(IHomeView view) {
        this.view = view;
        userDataRepository = Injector.provideUserRepository();
        activityDataRepository = Injector.provideActivityRepository();
    }
}
