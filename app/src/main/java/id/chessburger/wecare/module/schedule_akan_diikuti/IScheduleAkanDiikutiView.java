package id.chessburger.wecare.module.schedule_akan_diikuti;

import java.util.List;

import id.chessburger.wecare.base.IBaseView;
import id.chessburger.wecare.model.Activity;
import id.chessburger.wecare.model.Schedule;

/**
 * Created by aflah on 27/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public interface IScheduleAkanDiikutiView extends IBaseView {
    void showListActivities (List<Schedule> schedules);
}
