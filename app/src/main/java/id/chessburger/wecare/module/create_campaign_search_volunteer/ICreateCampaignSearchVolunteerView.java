package id.chessburger.wecare.module.create_campaign_search_volunteer;

import java.util.List;

import id.chessburger.wecare.base.IBaseView;
import id.chessburger.wecare.model.ActivityCategory;

/**
 * Created by aflah on 12/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public interface ICreateCampaignSearchVolunteerView extends IBaseView {

    void setActivityCategory (List<ActivityCategory> activityCategoryList);
}
