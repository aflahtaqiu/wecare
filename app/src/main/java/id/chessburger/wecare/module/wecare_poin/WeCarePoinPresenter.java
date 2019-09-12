package id.chessburger.wecare.module.wecare_poin;

import id.chessburger.wecare.data.repository.UserDataRepository;
import id.chessburger.wecare.di.Injector;

/**
 * Created by aflah on 12/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public class WeCarePoinPresenter {

    private IWeCarePoinView view;
    private UserDataRepository repository;

    public WeCarePoinPresenter(IWeCarePoinView view) {
        this.view = view;
        this.repository = Injector.provideUserRepository();
    }

    void updateWeCarePoin () {

    }
}
