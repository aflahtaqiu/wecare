package id.chessburger.wecare.module.detail_campaigned_activity;

import id.chessburger.wecare.data.repository.UserDataRepository;
import id.chessburger.wecare.di.Injector;

/**
 * Created by aflah on 12/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public class DetailCampaignedActPresenter {

    private IDetailCampaignedActView view;
    private UserDataRepository userDataRepository;

    public DetailCampaignedActPresenter(IDetailCampaignedActView view) {
        this.view = view;
        this.userDataRepository = Injector.provideUserRepository();
    }
}
