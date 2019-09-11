package id.chessburger.wecare.module.home;

import java.util.List;

import id.chessburger.wecare.base.IBaseView;
import id.chessburger.wecare.model.Activity;
import id.chessburger.wecare.model.ActivityCategory;

/**
 * Created by aflah on 09/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public interface IHomeView extends IBaseView {
    void showListActivities (List<Activity> activities);
    void showCategories (List<ActivityCategory> activityCategories);
}
