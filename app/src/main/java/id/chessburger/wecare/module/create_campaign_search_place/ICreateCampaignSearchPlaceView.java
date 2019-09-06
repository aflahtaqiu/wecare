package id.chessburger.wecare.module.create_campaign_search_place;

import java.util.List;

import id.chessburger.wecare.base.IBaseView;
import id.chessburger.wecare.model.ActivityCategory;

/**
 * Created by aflah on 12/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public interface ICreateCampaignSearchPlaceView extends IBaseView {
    void setActivityCategory (List<ActivityCategory> categoryList);
}
