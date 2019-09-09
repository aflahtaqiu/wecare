package id.chessburger.wecare.module.campaign_sudah_terlaksana;

import java.util.List;

import id.chessburger.wecare.base.IBaseView;
import id.chessburger.wecare.model.Activity;

/**
 * Created by aflah on 27/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public interface ICampaignSudahTerlaksanaView extends IBaseView {
    void showListActivities (List<Activity> activities);
}
