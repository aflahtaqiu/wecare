package id.chessburger.wecare.data.repository;

import id.chessburger.wecare.data.remote.UserRemoteDataSource;
import id.chessburger.wecare.data.source.IUserDataSource;

/**
 * Created by aflah on 12/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public class UserDataRepository implements IUserDataSource {

    private UserRemoteDataSource remoteDataSource;
    private static UserDataRepository dataRepository;

    public static UserDataRepository getInstance() {
        if (dataRepository == null) {
            dataRepository = new UserDataRepository(UserRemoteDataSource.getInstance());
        }
        return dataRepository;
    }

    public UserDataRepository(UserRemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }
}
