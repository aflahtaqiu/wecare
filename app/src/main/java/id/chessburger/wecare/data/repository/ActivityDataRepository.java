package id.chessburger.wecare.data.repository;

import id.chessburger.wecare.data.remote.ActivityRemoteDataSource;
import id.chessburger.wecare.data.source.IActivityDataSource;

/**
 * Created by aflah on 12/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public class ActivityDataRepository implements IActivityDataSource {

    private ActivityRemoteDataSource remoteDataSource;
    private static ActivityDataRepository dataRepository;

    public static ActivityDataRepository getInstance() {
        if (dataRepository == null) {
            dataRepository = new ActivityDataRepository(ActivityRemoteDataSource.getInstance());
        }
        return dataRepository;
    }

    public ActivityDataRepository(ActivityRemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }
}
