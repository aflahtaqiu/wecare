package id.chessburger.wecare.module.detail_activity_search_place;

import id.chessburger.wecare.base.IBaseView;
import id.chessburger.wecare.model.User;

/**
 * Created by aflah on 12/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public interface IDetailActSearchPlaceView extends IBaseView {
    void setActivityPhoto (String imageUrl);

    void setActivityCategory(String category);
    void setActivityName(String activityName);
    void setCampaignerData (User campaigner);
    void setActivityDescription(String description);
    void setJangkauanDaerah (String area);
    void setKetersediaanWaktu (String startDate , String endDate);
    void setTanggalTelahTerisi ();
    void setActivityDuration(int duration);
    void setRencanaKegiatan (String rencanaKegiatan);
    void serPerluDisiapkanFasilitator (String perluDisiapkanFasilitator);
    void setPersyaratan (String persyaratan);
    void setAdditionalInformation (String additionalInformation);

    void setBookmarkView (boolean isBookmarked);
}
