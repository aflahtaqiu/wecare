package id.chessburger.wecare.module.home;

import android.text.TextUtils;

import java.util.List;

import id.chessburger.wecare.data.repository.ActivityDataRepository;
import id.chessburger.wecare.data.repository.UserDataRepository;
import id.chessburger.wecare.data.source.IActivityDataSource;
import id.chessburger.wecare.di.Injector;
import id.chessburger.wecare.model.Activity;
import id.chessburger.wecare.model.ActivityCategory;

/**
 * Created by aflah on 09/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


class HomePresenter {

    private IHomeView view;
    private UserDataRepository userDataRepository;
    private ActivityDataRepository activityDataRepository;

    HomePresenter(IHomeView view) {
        this.view = view;
        userDataRepository = Injector.provideUserRepository();
        activityDataRepository = Injector.provideActivityRepository();
    }

    void getAllActivities () {
        activityDataRepository.getAllActivitiesJoinQuery("type", new IActivityDataSource.GetActivitiesCallback() {
            @Override
            public void onSuccess(List<Activity> activities) {
                view.showListActivities(activities);
            }

            @Override
            public void onError(String errorMessage) {
                if (TextUtils.equals("timeout", errorMessage)) {
                    getAllActivities();
                }
            }
        });
    }

    void searchActivitiesByName (String nameQuery) {
//        activityDataRepository.getAllActivitiesSearch(nameQuery, "type",
//                new IActivityDataSource.GetActivitiesCallback() {
//            @Override
//            public void onSuccess(List<Activity> activities) {
//                view.showListActivities(activities);
//            }
//
//            @Override
//            public void onError(String errorMessage) {
//
//            }
//        });
    }

    void getCategories () {
        activityDataRepository.getAllCategory(new IActivityDataSource.GetAllCategoryCallback() {
            @Override
            public void onSuccess(List<ActivityCategory> categoryList) {
                view.showCategories(categoryList);
            }

            @Override
            public void onError(String errorMessage) {

            }
        });
    }

    void getActivitiesByCategory (int categoryId) {
        String filterCategory = "categoryId||eq||" + categoryId;
        if (categoryId != 0) {
            activityDataRepository.getAllActivitiesFilterQuery(filterCategory, null, "type",
                    new IActivityDataSource.GetActivitiesCallback() {
                        @Override
                        public void onSuccess(List<Activity> activities) {
                            view.showListActivities(activities);
                        }

                        @Override
                        public void onError(String errorMessage) {

                        }
                    });
        } else {
            getAllActivities();
        }
    }
}
