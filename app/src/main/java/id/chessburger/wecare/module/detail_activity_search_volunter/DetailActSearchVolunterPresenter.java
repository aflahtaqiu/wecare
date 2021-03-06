package id.chessburger.wecare.module.detail_activity_search_volunter;

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


class DetailActSearchVolunterPresenter {

    private IDetailActSearchVolunterView view;
    private ActivityDataRepository activityDataRepository;
    private String token;

    private static final int ZERO_VALUE = 0;
    private static final int ONE_HUNDRED = 100;

    private final String LOADING_STRING = "Loading...";
    private final String BERHASIL_BUAT_ACTIVITY = "Kegiatan Anda telah dipublikasikan untuk dikampanyekan";

    DetailActSearchVolunterPresenter(IDetailActSearchVolunterView view) {
        this.view = view;
        this.activityDataRepository = Injector.provideActivityRepository();
        this.token = SharedPrefUtils.getStringSharedPref(SharedPrefKeys.TOKEN.getKey(), "");
    }

    void getDetailActivity (int idActivity) {
        String campaignerJoinRelation = "campaigner";
        view.showLoading(LOADING_STRING);
        activityDataRepository.getActivityById(token, idActivity, campaignerJoinRelation, null,
                new GetActivityByIdCallback());
        view.hideLoading();
    }

    void followActivity (int idActivity) {
        view.showLoading(LOADING_STRING);
        activityDataRepository.followActivity(token, idActivity, new IActivityDataSource.FollowActivityCallback() {
            @Override
            public void onSuccess(Activity activity) {
                view.showMessage(BERHASIL_BUAT_ACTIVITY);
                view.hideLoading();
            }

            @Override
            public void onError(String errorMessage) {
                view.hideLoading();
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

    private class GetActivityByIdCallback implements IActivityDataSource.GetActivityByIdCallback {
        @Override
        public void onSuccess(Activity activity) {
            view.setActivityImage(activity.getPhoto());
            view.setActivityName(activity.getNameActivity());
            view.setCampaignerData(activity.getCampaigner());
            view.setActivityDescription(activity.getDescription());
            view.setCity(activity.getLocation().getCity());
            view.setPersiapanActivityData(activity);
            view.setBookmarkView(activity.getIsBookmarked());

            setVolunteerAndDonationData(activity);
            setDateTime(activity.getRegisterDeadlineDate(), activity.getStartDate(), activity.getEndDate());
        }

        void setVolunteerAndDonationData(Activity activity) {
            view.setDonationData(activity.getDonationTarget(), activity.getDonationsTotal());
            view.setVolunteerData(activity.getMinVolunteers(), activity.getVolunteersTotal());
            setDonationProgress((double) activity.getDonationTarget(), (double) activity.getDonationsTotal());
            setVolunteerProgress((double) activity.getMinVolunteers(), (double) activity.getVolunteersTotal());
        }

        void setDonationProgress (double requiredDonation, double collectedDonation) {
            if (isZero(requiredDonation)) {
                view.setDonationProgress(ZERO_VALUE);
            } else {
                double progressDonation = (collectedDonation / requiredDonation) * ONE_HUNDRED;
                view.setDonationProgress((int) progressDonation);
            }
        }

        void setVolunteerProgress (double minVolunteer, double registeredVolunteer) {
            if (isZero(minVolunteer)) {
                view.setVolunteerProgress(ZERO_VALUE);
            } else {
                double progressVolunteer = (registeredVolunteer / minVolunteer) * ONE_HUNDRED;
                view.setVolunteerProgress((int) progressVolunteer);
            }
        }

        private boolean isZero (double value) {
            return value == ZERO_VALUE;
        }

        void setDateTime(Date deadlineDate, Date startDate, Date endDate) {
            setDeadlineData(deadlineDate);
            setStartDateTime(startDate);
            setEndDateTime(endDate);
        }

        void setStartDateTime (Date dateTime) {
            String startDate = DateTimeUtils.dateToString(dateTime, DateTimeUtils.FORMAT_EEEEDDMMMYYYY);
            String startTime = DateTimeUtils.dateToString(dateTime, DateTimeUtils.FORMAT_HHMM);
            view.setStartDateTime(startDate, startTime);
        }

        void setEndDateTime (Date dateTime) {
            String endDate = DateTimeUtils.dateToString(dateTime, DateTimeUtils.FORMAT_EEEEDDMMMYYYY);
            String endTime = DateTimeUtils.dateToString(dateTime, DateTimeUtils.FORMAT_HHMM);
            view.setEndDateTime(endDate, endTime);
        }

        void setDeadlineData (Date deadlineDate) {
            String deadlineString = DateTimeUtils.dateToString(deadlineDate, DateTimeUtils.FORMAT_DDMMMMYY);
            view.setDeadlinePendaftaran(deadlineString);
        }

        @Override
        public void onError(String errorMessage) {
            view.showMessage(errorMessage);
        }
    }
}
