package id.chessburger.wecare.module.article;

import id.chessburger.wecare.data.repository.ArticleDataRepository;
import id.chessburger.wecare.di.Injector;

/**
 * Created by aflah on 09/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public class ArticlePresenter {

    private IArticleView view;
    private ArticleDataRepository repository;

    public ArticlePresenter(IArticleView view) {
        this.view = view;
        this.repository = Injector.provideArticleRepository();
    }
}
