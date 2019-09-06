package id.chessburger.wecare.module.create_campaign_search_volunteer;

import android.util.Log;

import java.util.List;

import id.chessburger.wecare.data.repository.ActivityDataRepository;
import id.chessburger.wecare.data.repository.RajaOngkirDataRepository;
import id.chessburger.wecare.data.source.IActivityDataSource;
import id.chessburger.wecare.data.source.IRajaOngkirDataSource;
import id.chessburger.wecare.di.Injector;
import id.chessburger.wecare.model.Activity;
import id.chessburger.wecare.model.ActivityCategory;
import id.chessburger.wecare.model.City;
import id.chessburger.wecare.model.enumerations.ApiKey;
import id.chessburger.wecare.model.enumerations.SharedPrefKeys;
import id.chessburger.wecare.utils.SharedPrefUtils;
import okhttp3.MultipartBody;

/**
 * Created by aflah on 12/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public class CreateCampaignSearchVolunteerPresenter {

    private ICreateCampaignSearchVolunteerView view;
    private ActivityDataRepository activityRepository;
    private RajaOngkirDataRepository rajaOngkirRepository;
    private String token;

    CreateCampaignSearchVolunteerPresenter(ICreateCampaignSearchVolunteerView view) {
        this.view = view;
        this.activityRepository = Injector.provideActivityRepository();
        this.rajaOngkirRepository = Injector.provideRajaOngkirRepository();
        this.token = SharedPrefUtils.getStringSharedPref(SharedPrefKeys.TOKEN.getKey(), "");
    }

    void getAllActivityCategoty () {

        activityRepository.getAllCategory(new IActivityDataSource.GetAllCategoryCallback() {
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

    void getAllIndonesiaCities () {

        rajaOngkirRepository.getCities(ApiKey.RAJA_ONGKIR.getApiKey(), new IRajaOngkirDataSource.GetCitiesCallback() {
            @Override
            public void onSuccess(List<City> cities) {
                view.setIndonesiaCities(cities);
            }

            @Override
            public void onError(String errorMessage) {
                Log.e("error get cities", errorMessage);
            }
        });
    }

    void createActivityCampaign (Activity activity, String startDate, String endDate, String deadlineDate, MultipartBody.Part photo) {
        activityRepository.createActivityCariRelawan(token, activity.getNameActivity(), startDate, endDate,
                deadlineDate, activity.getDescription(), activity.getVolunteerTasks(),
                activity.getVolunteerEquipments(), activity.getVolunteerRequirements(), activity.getBriefs(),
                activity.getMinVolunteers(), activity.getDonationTarget(), activity.getCategoryId(),
                activity.getTypeId(), activity.getCity(), activity.getAddress(), photo, new IActivityDataSource.CreateActivityCariRelawanCallback() {
                    @Override
                    public void onSuccess(String successMessage) {
                        Log.e("lele", successMessage);
                    }

                    @Override
                    public void onError(String errorMessage) {
                        Log.e("lelegagal", errorMessage);
                    }
                });
    }
}
