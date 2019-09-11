package id.chessburger.wecare.module.article;

import java.util.List;

import id.chessburger.wecare.base.IBaseView;
import id.chessburger.wecare.model.Activity;

/**
 * Created by aflah on 09/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public interface IArticleView extends IBaseView {
    void showListActivities (List<Activity> activities);
}
