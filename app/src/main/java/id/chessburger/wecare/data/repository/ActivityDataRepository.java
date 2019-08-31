package id.chessburger.wecare.data.repository;

import java.util.List;

import id.chessburger.wecare.data.remote.ActivityRemoteDataSource;
import id.chessburger.wecare.data.source.IActivityDataSource;
import id.chessburger.wecare.model.Activity;

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

    @Override
    public void followActivity(String token, int idActivity, FollowActivityCallback callback) {
        remoteDataSource.followActivity(token,idActivity, new FollowActivityCallback() {
            @Override
            public void onSuccess(Activity activity) {
                callback.onSuccess(activity);
            }

            @Override
            public void onError(String errorMessage) {
                callback.onError(errorMessage);
            }
        });
    }

    @Override
    public void getAllActivitiesJoinQuery(String joinQuery, GetActivitiesCallback callback) {
        remoteDataSource.getAllActivitiesJoinQuery(joinQuery, new GetActivitiesCallback() {
            @Override
            public void onSuccess(List<Activity> activities) {
                callback.onSuccess(activities);
            }

            @Override
            public void onError(String errorMessage) {
                callback.onError(errorMessage);
            }
        });
    }

    @Override
    public void getActivityById(int idActivity,String joinRelation, GetActivityByIdCallback callback) {
        remoteDataSource.getActivityById(idActivity, joinRelation, new GetActivityByIdCallback() {
            @Override
            public void onSuccess(Activity activity) {
                callback.onSuccess(activity);
            }

            @Override
            public void onError(String errorMessage) {
                callback.onError(errorMessage);
            }
        });
    }

    @Override
    public void getAllCategory(GetAllCategoryCallback callback) {

    }
}
