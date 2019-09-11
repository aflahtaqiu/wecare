package id.chessburger.wecare.module.article;

import id.chessburger.wecare.data.repository.ActivityDataRepository;
import id.chessburger.wecare.data.repository.ArticleDataRepository;
import id.chessburger.wecare.di.Injector;

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
}
