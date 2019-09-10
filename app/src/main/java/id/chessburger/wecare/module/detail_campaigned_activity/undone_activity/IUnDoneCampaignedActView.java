package id.chessburger.wecare.module.detail_campaigned_activity.undone_activity;

import id.chessburger.wecare.base.IBaseView;
import id.chessburger.wecare.model.Activity;

/**
 * Created by aflah on 12/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public interface IUnDoneCampaignedActView extends IBaseView {
    void setActivity (Activity activity);
    void setActivityPicture (String imageUrl);
}
