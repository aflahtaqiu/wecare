package id.chessburger.wecare.module.detail_activity_search_place;

import id.chessburger.wecare.data.repository.ActivityDataRepository;
import id.chessburger.wecare.di.Injector;

/**
 * Created by aflah on 12/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public class DetailActSearchPlacePresenter {

    private IDetailActSearchPlaceView view;
    private ActivityDataRepository activityDataRepository;

    DetailActSearchPlacePresenter(IDetailActSearchPlaceView view) {
        this.view = view;
        this.activityDataRepository = Injector.provideActivityRepository();
    }
}
