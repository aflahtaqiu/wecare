package id.chessburger.wecare.module.home;

import android.text.TextUtils;
import android.util.Log;

import java.util.List;

import id.chessburger.wecare.data.repository.ActivityDataRepository;
import id.chessburger.wecare.data.repository.UserDataRepository;
import id.chessburger.wecare.data.source.IActivityDataSource;
import id.chessburger.wecare.di.Injector;
import id.chessburger.wecare.model.Activity;

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

    public void getAllActivities () {
        activityDataRepository.getAllActivitiesJoinQuery("type", new IActivityDataSource.GetActivitiesCallback() {
            @Override
            public void onSuccess(List<Activity> activities) {
                view.showListActivities(activities);
            }

            @Override
            public void onError(String errorMessage) {
                Log.e("error home presenter" , errorMessage);
                if (TextUtils.equals("timeout", errorMessage)) {
                    getAllActivities();
                }
            }
        });
    }
}
