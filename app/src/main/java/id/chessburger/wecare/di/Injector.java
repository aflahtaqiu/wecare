package id.chessburger.wecare.di;

import id.chessburger.wecare.data.repository.ActivityDataRepository;
import id.chessburger.wecare.data.repository.ArticleDataRepository;
import id.chessburger.wecare.data.repository.RajaOngkirDataRepository;
import id.chessburger.wecare.data.repository.UserDataRepository;

/**
 * Created by aflah on 07/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public class Injector {

    public static UserDataRepository provideUserRepository () {
        return UserDataRepository.getInstance();
    }

    public static ActivityDataRepository provideActivityRepository () {
        return ActivityDataRepository.getInstance();
    }

    public static ArticleDataRepository provideArticleRepository () {
        return ArticleDataRepository.getInstance();
    }

    public static RajaOngkirDataRepository provideRajaOngkirRepository () {
        return RajaOngkirDataRepository.getInstance();
    }
}
