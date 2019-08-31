package id.chessburger.wecare.module.create_campaign_search_volunteer;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import androidx.appcompat.widget.AppCompatSpinner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import id.chessburger.wecare.R;
import id.chessburger.wecare.base.BaseActivity;
import id.chessburger.wecare.model.ActivityCategory;

public class CreateCampaignSearchVolunteerActivity extends BaseActivity implements ICreateCampaignSearchVolunteerView {

    @BindView(R.id.spinner_activity_category)
    AppCompatSpinner spinnerActivityCategory;

    @BindView(R.id.rb_yes_donate)
    RadioButton rbYesDonasi;

    @BindView(R.id.rb_no_donate)
    RadioButton rbNoDonasi;

    private CreateCampaignSearchVolunteerPresenter presenter;

    private List<ActivityCategory> categoryList = new ArrayList<>();
    private boolean isDonasi = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_campaign_search_volunteer);
        ButterKnife.bind(this);
        presenter = new CreateCampaignSearchVolunteerPresenter(this);

        categoryList.add(ActivityCategory.builder().id(1).name("kategori satu").build());
        categoryList.add(ActivityCategory.builder().id(2).name("kategori dua").build());
        categoryList.add(ActivityCategory.builder().id(3).name("kategori tiga").build());

        ArrayAdapter spinnerCategoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categoryList);
        spinnerCategoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerActivityCategory.setAdapter(spinnerCategoryAdapter);
        spinnerActivityCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("selected category", categoryList.get(i).getName());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @Override
    public void showLoading(String message) {
        super.onShowLoading(message);
    }

    @Override
    public void hideLoading() {
        super.onHideLoading();
    }

    @Override
    public void showMessage(String message) {

    }

    @OnCheckedChanged({R.id.rb_yes_donate, R.id.rb_no_donate})
    public void onRadioButtonClicked(CompoundButton radioButton, boolean isSelected) {
        int rbId = radioButton.getId();
        switch (rbId) {
            case R.id.rb_yes_donate :
                isDonasi = true;
                rbNoDonasi.setChecked(!isSelected);
                Log.e("lele", radioButton.getText().toString());
                break;
            case R.id.rb_no_donate :
                isDonasi = false;
                Log.e("lele", radioButton.getText().toString());
                rbYesDonasi.setChecked(!isSelected);
                break;
        }

    }
}
