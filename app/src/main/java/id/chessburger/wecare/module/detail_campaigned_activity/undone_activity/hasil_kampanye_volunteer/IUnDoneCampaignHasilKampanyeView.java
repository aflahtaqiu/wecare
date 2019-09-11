package id.chessburger.wecare.module.detail_campaigned_activity.undone_activity.hasil_kampanye_volunteer;

import java.util.List;

import id.chessburger.wecare.base.IBaseView;
import id.chessburger.wecare.model.FollowedActivity;

/**
 * Created by aflah on 11/09/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public interface IUnDoneCampaignHasilKampanyeView extends IBaseView {
    void setFollowerActivities (List<FollowedActivity> followerActivities);
    void setAbsencedFollowedActivities (List<FollowedActivity> followedActivities);
}
