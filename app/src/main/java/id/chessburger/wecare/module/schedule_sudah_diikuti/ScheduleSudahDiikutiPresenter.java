package id.chessburger.wecare.module.schedule_sudah_diikuti;

import java.util.List;

import id.chessburger.wecare.data.repository.UserDataRepository;
import id.chessburger.wecare.data.source.IUserDataSource;
import id.chessburger.wecare.di.Injector;
import id.chessburger.wecare.model.Schedule;
import id.chessburger.wecare.model.User;
import id.chessburger.wecare.model.enumerations.SharedPrefKeys;
import id.chessburger.wecare.utils.SharedPrefUtils;

/**
 * Created by aflah on 26/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public class ScheduleSudahDiikutiPresenter {

    private IScheduleSudahDiikutiView view;
    private UserDataRepository repository;
    private User user;

    public ScheduleSudahDiikutiPresenter(IScheduleSudahDiikutiView view) {
        this.view = view;
        this.repository = Injector.provideUserRepository();
        user = (User) SharedPrefUtils.getObjectSharedPref(SharedPrefKeys.PROFIL.getKey(),
                null, User.class);
    }

    void getScheduledActivities () {
        repository.getDoneFollowedActivities(user.getId(), new IUserDataSource.GetScheduleCallback() {
            @Override
            public void onSuccess(List<Schedule> schedules) {
                view.showListActivities(schedules);
            }

            @Override
            public void onError(String errorMessage) {
                view.showMessage(errorMessage);
            }
        });
    }
}
