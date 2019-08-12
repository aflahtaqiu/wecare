package id.chessburger.wecare.data.remote;

import id.chessburger.wecare.base.BaseRemoteDataSource;
import id.chessburger.wecare.data.source.IUserDataSource;

/**
 * Created by aflah on 08/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public class UserRemoteDataSource extends BaseRemoteDataSource implements IUserDataSource {

    private static UserRemoteDataSource remoteDataSource;
    public static UserRemoteDataSource getInstance(){
        if (remoteDataSource == null) {
            remoteDataSource = new UserRemoteDataSource();
        }
        return remoteDataSource;
    }
}
