package id.chessburger.wecare.data.remote;

import id.chessburger.wecare.base.BaseRemoteDataSource;
import id.chessburger.wecare.data.source.IUserDataSource;

/**
 * Created by aflah on 12/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public class ActivityRemoteDataSource extends BaseRemoteDataSource implements IUserDataSource {

    private static ActivityRemoteDataSource remoteDataSource;
    public static ActivityRemoteDataSource getInstance() {
        if (remoteDataSource == null) {
            remoteDataSource = new ActivityRemoteDataSource();
        }
        return remoteDataSource;
    }
}
