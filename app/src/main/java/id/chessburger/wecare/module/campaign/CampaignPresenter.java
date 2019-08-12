package id.chessburger.wecare.module.campaign;

import id.chessburger.wecare.data.repository.ActivityDataRepository;
import id.chessburger.wecare.data.repository.UserDataRepository;
import id.chessburger.wecare.di.Injector;

/**
 * Created by aflah on 09/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public class CampaignPresenter {

    private ICampaignView view;
    private ActivityDataRepository activityDataRepository;
    private UserDataRepository userDataRepository;

    public CampaignPresenter(ICampaignView view) {
        this.view = view;
        this.activityDataRepository = Injector.provideActivityRepository();
        this.userDataRepository = Injector.provideUserRepository();
    }
}
