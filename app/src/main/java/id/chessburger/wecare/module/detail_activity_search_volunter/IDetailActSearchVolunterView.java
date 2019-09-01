package id.chessburger.wecare.module.detail_activity_search_volunter;

import id.chessburger.wecare.base.IBaseView;
import id.chessburger.wecare.model.Activity;
import id.chessburger.wecare.model.User;

/**
 * Created by aflah on 12/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public interface IDetailActSearchVolunterView extends IBaseView {
    void setActivityName(String activityName);
    void setCampaignerData (User campaigner);
    void setActivityDescription(String description);
    void setPersiapanActivityData (Activity activityData);
    void setDonationData(int requiredDonation, int collectedDonation);
    void setDonationProgress(int requiredDonation, int collectedDonation);
    void setVolunteerData(int minimumVolunteer, int registeredVolunteer);
    void setSisaVolunteer(int minimumVolunteer, int registeredVolunteer);
    void setVolunteerProgress(int minimumVolunteer, int registeredVolunteer);
    void setDeadlinePendaftaran (String deadline);
    void setStartDateTime (String startDate, String startTime);
    void setEndDateTime (String endDate, String endTime);
    void setArea (String area);

    void showConfirmationDialog();
}
