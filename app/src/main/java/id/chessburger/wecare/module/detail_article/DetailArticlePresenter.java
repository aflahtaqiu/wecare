package id.chessburger.wecare.module.detail_article;

import id.chessburger.wecare.data.repository.ArticleDataRepository;
import id.chessburger.wecare.di.Injector;

/**
 * Created by aflah on 12/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public class DetailArticlePresenter {

    private IDetailArticleView view;
    private ArticleDataRepository articleDataRepository;

    public DetailArticlePresenter(IDetailArticleView view) {
        this.view = view;
        this.articleDataRepository = Injector.provideArticleRepository();
    }
}
