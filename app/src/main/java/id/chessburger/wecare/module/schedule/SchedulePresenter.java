package id.chessburger.wecare.module.schedule;

import id.chessburger.wecare.data.repository.UserDataRepository;
import id.chessburger.wecare.di.Injector;

/**
 * Created by aflah on 09/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public class SchedulePresenter {

    private IScheduleView view;
    private UserDataRepository userDataRepository;

    public SchedulePresenter(IScheduleView view) {
        this.view = view;
        this.userDataRepository = Injector.provideUserRepository();
    }
}
