package id.chessburger.wecare.module.create_report;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.chessburger.wecare.R;
import id.chessburger.wecare.base.BaseActivity;
import id.chessburger.wecare.model.enumerations.CommunicationKeys;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class CreateReportActivity extends BaseActivity implements ICreateReport {

    @BindView(R.id.btn_upload_foto)
    Button btnUploadFoto;

    @BindView(R.id.btn_create_report)
    Button btnCreateReport;

    @BindView(R.id.et_laporan_kegiatan)
    EditText etLaporanKegiatan;

    private int idActivity;

    private String photoUri;

    private static final String IMAGE_TYPE = "image/*";
    private static final String PHOTO_KEY = "photo";

    private static final Integer FIRST_INDEX = 0;
    private static final Integer EMPTY_SIZE = 0;
    private static final Integer GET_IMAGE_REQUEST_CODE = 4105;
    private static final Integer STORAGE_PERMISSION_REQUEST_CODE = 4106;

    private CreateReportPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_report);

        ButterKnife.bind(this);

        getBundleIntentData();

        presenter = new CreateReportPresenter(this);
    }


    private void getBundleIntentData() {
        Bundle bundle = getIntent().getBundleExtra(CommunicationKeys.BUNDLE_KEY.getKey());
        idActivity = bundle.getInt(CommunicationKeys.SELECTED_ACTIVITY.getKey());
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
            photoUri = getRealPath(imageUri);
            updateBtnUploadFotoText(imageUri);
        }
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

    private void updateBtnUploadFotoText(Uri imageUri) {
        String type = getImageType(imageUri);
        String imagePath = imageUri.getPath();

        setBtnUploadFotoText(type, imagePath);
    }

    private String getImageType(Uri imageUri) {
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(getContentResolver().getType(imageUri));
    }

    private void setBtnUploadFotoText(String type, String imagePath) {
        String imageFileName = imagePath + "." + type;
        btnUploadFoto.setText(imageFileName);
    }

    private MultipartBody.Part getPhotoMultipart() {
        if (photoUri != null) {
            File file = new File(photoUri);
            RequestBody requestFile = RequestBody.create(MediaType.parse(IMAGE_TYPE), file);

            return MultipartBody.Part.createFormData(PHOTO_KEY, file.getName(),
                    requestFile);
        } else {
            return null;
        }
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
        AlertDialog confirmationDialog = new AlertDialog.Builder(this)
                .setTitle(R.string.laporan_kegiatan)
                .setMessage(message)
                .setPositiveButton(R.string.oke, new OnOkClickListener())
                .setCancelable(false)
                .create();

        confirmationDialog.setCanceledOnTouchOutside(true);
        confirmationDialog.show();
    }

    @OnClick(R.id.btn_upload_foto)
    public void onBtnUploadCliked() {
        setGallery();
    }

    @OnClick(R.id.btn_create_report)
    public void onBtnCreateReport() {

        String reportText = etLaporanKegiatan.getText().toString();
        MultipartBody.Part picture = getPhotoMultipart();

        presenter.postReport(idActivity, reportText, picture);
    }

    private class OnOkClickListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
        }
    }
}
