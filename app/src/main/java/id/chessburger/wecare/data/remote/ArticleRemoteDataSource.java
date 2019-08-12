package id.chessburger.wecare.data.remote;

import id.chessburger.wecare.base.BaseRemoteDataSource;
import id.chessburger.wecare.data.source.IActivityDataSource;

/**
 * Created by aflah on 12/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public class ArticleRemoteDataSource extends BaseRemoteDataSource implements IActivityDataSource {

    private static ArticleRemoteDataSource remoteDataSource;
    public static ArticleRemoteDataSource getInstance() {
        if (remoteDataSource == null) {
            remoteDataSource = new ArticleRemoteDataSource();
        }
        return remoteDataSource;
    }
}
