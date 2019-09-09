package id.chessburger.wecare.module.detail_campaigned_activity.undone_activity;

import id.chessburger.wecare.data.repository.UserDataRepository;
import id.chessburger.wecare.di.Injector;

/**
 * Created by aflah on 12/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public class UnDoneCampaignedActPresenter {

    private IUnDoneCampaignedActView view;
    private UserDataRepository userDataRepository;

    public UnDoneCampaignedActPresenter(IUnDoneCampaignedActView view) {
        this.view = view;
        this.userDataRepository = Injector.provideUserRepository();
    }
}
