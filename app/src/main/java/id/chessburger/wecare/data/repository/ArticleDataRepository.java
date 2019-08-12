package id.chessburger.wecare.data.repository;

import id.chessburger.wecare.data.remote.ArticleRemoteDataSource;
import id.chessburger.wecare.data.source.IArticleDataSource;

/**
 * Created by aflah on 12/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public class ArticleDataRepository implements IArticleDataSource {

    private ArticleRemoteDataSource remoteDataSource;
    private static ArticleDataRepository dataRepository;

    public static ArticleDataRepository getInstance () {
        if (dataRepository == null) {
            dataRepository = new ArticleDataRepository(ArticleRemoteDataSource.getInstance());
        }
        return dataRepository;
    }

    public ArticleDataRepository(ArticleRemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }
}
