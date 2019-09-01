package id.chessburger.wecare.module.create_campaign_search_volunteer;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import id.chessburger.wecare.R;
import id.chessburger.wecare.base.BaseActivity;
import id.chessburger.wecare.model.ActivityCategory;

public class CreateCampaignSearchVolunteerActivity extends BaseActivity implements ICreateCampaignSearchVolunteerView {

    @BindView(R.id.spinner_activity_category)
    AppCompatSpinner spinnerActivityCategory;

    @BindView(R.id.btn_upload_foto)
    Button btnUploadFoto;

    @BindView(R.id.rb_yes_donate)
    RadioButton rbYesDonasi;

    @BindView(R.id.rb_no_donate)
    RadioButton rbNoDonasi;

    private CreateCampaignSearchVolunteerPresenter presenter;

    private boolean isDonasi = false;

    private static final String IMAGE_TYPE = "image/*";

    private static final Integer FIRST_INDEX = 0;
    private static final Integer EMPTY_SIZE = 0;
    private static final Integer SECOND_INDEX = 1;
    private static final Integer GET_IMAGE_REQUEST_CODE = 4105;
    private static final Integer STORAGE_PERMISSION_REQUEST_CODE = 4106;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_campaign_search_volunteer);
        ButterKnife.bind(this);
        presenter = new CreateCampaignSearchVolunteerPresenter(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (isRequestGranted(requestCode, permissions, grantResults)) {
            openGallery();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (isPickImage(requestCode, resultCode, data)) {
            Uri imageUri = data.getData();
            MimeTypeMap mime = MimeTypeMap.getSingleton();

            String type = mime.getExtensionFromMimeType(getContentResolver().getType(imageUri));
            String imagePath = imageUri.getPath();

            String imageFileName = imagePath+"."+type;
            btnUploadFoto.setText(imageFileName);
        }
    }

    private Boolean isPickImage(int requestCode, int resultCode, Intent data) {
        return requestCode == GET_IMAGE_REQUEST_CODE && resultCode == RESULT_OK &&
                data.getData() != null;
    }

    private String getRealPath(Uri imageUri) {
        Cursor cursor = getContentResolver()
                .query(imageUri, null, null, null, null);
        String realPath = null;

        if (cursor != null) {
            cursor.moveToFirst();

            int imageIndex = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            realPath = cursor.getString(imageIndex);

            cursor.close();
        }

        return realPath;
    }

    private Boolean isRequestGranted(int requestCode, String[] permissions, int[] grantResults) {
        return requestCode == STORAGE_PERMISSION_REQUEST_CODE &&
                permissions.length > EMPTY_SIZE &&
                permissions[FIRST_INDEX].equals(Manifest.permission.READ_EXTERNAL_STORAGE) &&
                grantResults.length > EMPTY_SIZE &&
                grantResults[FIRST_INDEX] == PackageManager.PERMISSION_GRANTED;
    }

    private void setGallery() {
        if (isNeedRequestPermission()) {
            checkPermission();
        } else {
            openGallery();
        }
    }

    private boolean isNeedRequestPermission() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    private void checkPermission() throws SecurityException {
        if (isStoragePermissionsGranted()) {
            openGallery();
        } else {
            requestStoragePermissions();
        }
    }

    private void openGallery() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType(IMAGE_TYPE);

        startActivityForResult(photoPickerIntent, GET_IMAGE_REQUEST_CODE);
    }

    private void requestStoragePermissions() {
        String[] storagePermissions = new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE
        };

        ActivityCompat.requestPermissions(this, storagePermissions,
                STORAGE_PERMISSION_REQUEST_CODE);
    }

    private boolean isStoragePermissionsGranted() {
        return ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.getAllActivityCategoty();
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

    @Override
    public void setActivityCategory(List<ActivityCategory> activityCategoryList) {
        ArrayAdapter spinnerCategoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, activityCategoryList);
        spinnerCategoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerActivityCategory.setAdapter(spinnerCategoryAdapter);
        spinnerActivityCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
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

    @OnClick(R.id.btn_upload_foto)
    public void onBtnUploadFotoClicked () {
        setGallery();
    }
}
