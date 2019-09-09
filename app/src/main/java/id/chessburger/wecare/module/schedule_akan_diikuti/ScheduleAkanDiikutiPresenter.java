package id.chessburger.wecare.module.schedule_akan_diikuti;

import id.chessburger.wecare.data.repository.UserDataRepository;
import id.chessburger.wecare.di.Injector;

/**
 * Created by aflah on 27/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public class ScheduleAkanDiikutiPresenter {

    private IScheduleAkanDiikutiView view;
    private UserDataRepository repository;

    public ScheduleAkanDiikutiPresenter(IScheduleAkanDiikutiView view) {
        this.view = view;
        this.repository = Injector.provideUserRepository();
    }
}
