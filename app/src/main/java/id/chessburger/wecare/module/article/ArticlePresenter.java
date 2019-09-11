package id.chessburger.wecare.module.article;

import java.util.List;

import id.chessburger.wecare.data.repository.ActivityDataRepository;
import id.chessburger.wecare.data.repository.ArticleDataRepository;
import id.chessburger.wecare.data.source.IActivityDataSource;
import id.chessburger.wecare.di.Injector;
import id.chessburger.wecare.model.Activity;

/**
 * Created by aflah on 09/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


class ArticlePresenter {

    private IArticleView view;
    private ActivityDataRepository repository;

    ArticlePresenter(IArticleView view) {
        this.view = view;
        this.repository = Injector.provideActivityRepository();
    }

    void getDoneActivities () {
        String isDoneFilter = "isDone||eq||true";
        repository.getAllActivitiesFilterQuery(isDoneFilter, null, "campaigner",
                new IActivityDataSource.GetActivitiesCallback() {
            @Override
            public void onSuccess(List<Activity> activities) {
                view.showListActivities(activities);
            }

            @Override
            public void onError(String errorMessage) {

            }
        });
    }
}
