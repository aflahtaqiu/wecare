package id.chessburger.wecare.module.detail_activity_search_place;

import java.util.Date;

import id.chessburger.wecare.data.repository.ActivityDataRepository;
import id.chessburger.wecare.data.source.IActivityDataSource;
import id.chessburger.wecare.di.Injector;
import id.chessburger.wecare.model.Activity;
import id.chessburger.wecare.model.enumerations.SharedPrefKeys;
import id.chessburger.wecare.utils.DateTimeUtils;
import id.chessburger.wecare.utils.SharedPrefUtils;

/**
 * Created by aflah on 12/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


class DetailActSearchPlacePresenter {

    private IDetailActSearchPlaceView view;
    private ActivityDataRepository activityDataRepository;
    private String token;

    private final String LOADING_STRING = "Loading...";

    DetailActSearchPlacePresenter(IDetailActSearchPlaceView view) {
        this.view = view;
        this.activityDataRepository = Injector.provideActivityRepository();
        this.token = SharedPrefUtils.getStringSharedPref(SharedPrefKeys.TOKEN.getKey(), "");
    }

    void getDetailActivity (int idActivity) {
        String campaignerJoinRelation = "campaigner";
        String categoryJoinRelation = "category";
        view.showLoading(LOADING_STRING);
        activityDataRepository.getActivityById(token, idActivity, campaignerJoinRelation, categoryJoinRelation,
                new IActivityDataSource.GetActivityByIdCallback() {
            @Override
            public void onSuccess(Activity activity) {
                view.setActivityPhoto(activity.getPhoto());
                view.setCampaignerData(activity.getCampaigner());
                view.setBookmarkView(activity.getIsBookmarked());

                setStartEndDate(activity.getStartDate(), activity.getEndDate());

                view.setActivityName(activity.getNameActivity());
                view.setActivityCategory(activity.getCategory().getName());
                view.setActivityDescription(activity.getDescription());
                view.setJangkauanDaerah(activity.getArea());
                view.setRencanaKegiatan(activity.getActivityPlan());
                view.serPerluDisiapkanFasilitator(activity.getPreparedByFacilitator());
                view.setPersyaratan(activity.getLocationRequirement());
                view.setAdditionalInformation(activity.getAdditionalInformation());
                view.hideLoading();
            }

            void setStartEndDate (Date startDate, Date endDate) {
                String startDateString = DateTimeUtils.dateToString(startDate, DateTimeUtils.FORMAT_DDMMMMYY);
                String endDateString = DateTimeUtils.dateToString(endDate, DateTimeUtils.FORMAT_DDMMMMYY);

                view.setKetersediaanWaktu(startDateString, endDateString);

                calculateDifferenceDay(startDate, endDate);
            }

            void calculateDifferenceDay (Date startDate, Date endDate) {
                view.setActivityDuration((int) DateTimeUtils.getDayDifference(startDate, endDate));
            }

            @Override
            public void onError(String errorMessage) {

            }
        });
    }

    void bookmarkActivity (int idActivity) {
        view.showLoading(LOADING_STRING);
        activityDataRepository.bookmarkActivity(token, idActivity, new IActivityDataSource.BookmarkActivityCallback() {
            @Override
            public void onSuccess(String successMessage) {
                view.hideLoading();
                view.setBookmarkView(true);
                view.showMessage(successMessage);
            }

            @Override
            public void onError(String errorMessage) {
                view.hideLoading();
                view.showMessage(errorMessage);
            }
        });
    }
    void unBookmarkActivity (int idActivity) {
        view.showLoading(LOADING_STRING);
        activityDataRepository.unBookmarkActivity(token, idActivity, new IActivityDataSource.UnBookmarkActivityCallback() {
            @Override
            public void onSuccess(String successMessage) {
                view.hideLoading();
                view.setBookmarkView(false);
                view.showMessage(successMessage);
            }

            @Override
            public void onError(String errorMessage) {
                view.hideLoading();
                view.showMessage(errorMessage);
            }
        });
    }
}
