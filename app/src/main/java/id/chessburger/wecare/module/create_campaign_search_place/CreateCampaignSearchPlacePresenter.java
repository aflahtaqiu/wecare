package id.chessburger.wecare.module.create_campaign_search_place;

import java.util.List;

import id.chessburger.wecare.data.repository.ActivityDataRepository;
import id.chessburger.wecare.data.source.IActivityDataSource;
import id.chessburger.wecare.di.Injector;
import id.chessburger.wecare.model.Activity;
import id.chessburger.wecare.model.ActivityCategory;
import id.chessburger.wecare.model.enumerations.SharedPrefKeys;
import id.chessburger.wecare.utils.SharedPrefUtils;
import okhttp3.MultipartBody;

/**
 * Created by aflah on 12/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


class CreateCampaignSearchPlacePresenter {

    private ICreateCampaignSearchPlaceView view;
    private ActivityDataRepository repository;
    private String token;

    private static final String LOADING_STRING = "Loading...";

    CreateCampaignSearchPlacePresenter(ICreateCampaignSearchPlaceView view) {
        this.view = view;
        this.repository = Injector.provideActivityRepository();
        this.token = SharedPrefUtils.getStringSharedPref(SharedPrefKeys.TOKEN.getKey(), "");
    }

    void getAllActivityCategoty () {

        repository.getAllCategory(new IActivityDataSource.GetAllCategoryCallback() {
            @Override
            public void onSuccess(List<ActivityCategory> categoryList) {
                view.setActivityCategory(categoryList);
            }

            @Override
            public void onError(String errorMessage) {
                view.showMessage(errorMessage);
            }
        });
    }

    void createActivityCampaign (Activity activity, String startDate, String endDate, String deadlineDate, MultipartBody.Part photo) {

        view.showLoading(LOADING_STRING);
        repository.createActivityCariLokasi(token, activity.getNameActivity(), startDate, endDate,
                deadlineDate, activity.getDescription(), activity.getArea(), activity.getCategoryId(),
                activity.getMaxParticipants(), activity.getVolunteersTotal(), activity.getPreparedByFacilitator(),
                activity.getActivityPlan(), activity.getLocationRequirement(), activity.getAdditionalInformation(),
                photo, new IActivityDataSource.CreateActivityCallback() {
            @Override
            public void onSuccess(String successMessage) {
                view.hideLoading();
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
