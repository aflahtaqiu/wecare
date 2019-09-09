package id.chessburger.wecare.module.schedule_sudah_diikuti;

import id.chessburger.wecare.data.repository.UserDataRepository;
import id.chessburger.wecare.di.Injector;

/**
 * Created by aflah on 26/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public class ScheduleSudahDiikutiPresenter {

    private IScheduleSudahDiikutiView view;
    private UserDataRepository repository;

    public ScheduleSudahDiikutiPresenter(IScheduleSudahDiikutiView view) {
        this.view = view;
        this.repository = Injector.provideUserRepository();
    }
}
