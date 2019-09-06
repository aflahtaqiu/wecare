package id.chessburger.wecare.module.create_campaign_search_place;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.chessburger.wecare.R;
import id.chessburger.wecare.base.BaseActivity;
import id.chessburger.wecare.model.Activity;
import id.chessburger.wecare.model.ActivityCategory;
import id.chessburger.wecare.module.mainact.MainActivity;
import id.chessburger.wecare.utils.CommunicationUtils;
import id.chessburger.wecare.utils.DateTimeUtils;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class CreateCampaignSearchPlaceActivity extends BaseActivity implements ICreateCampaignSearchPlaceView {

    @BindView(R.id.btn_upload_foto)
    Button btnUploadPhoto;

    @BindView(R.id.et_nama_kegiatan)
    EditText etNama;

    @BindView(R.id.et_deskripsi_kegiatan)
    EditText etDeskripsi;

    @BindView(R.id.et_jangkauan_daerah_kegiatan)
    EditText etJangkauanDaerah;

    @BindView(R.id.et_tanggal_mulai)
    EditText etTanggalMulai;

    @BindView(R.id.et_tanggal_selesai)
    EditText etTanggalSelesai;

    @BindView(R.id.et_deadline_pengajuan)
    EditText etDeadlinePengajuan;

    @BindView(R.id.et_jumlah_relawan)
    EditText etJumlahRelawan;

    @BindView(R.id.et_jumlah_peserta)
    EditText etJumlahPeserta;

    @BindView(R.id.et_rencana_kegiatan)
    EditText etRencanaKegiatan;

    @BindView(R.id.et_perlu_disiapkan_fasilitator)
    EditText etPerluDisiapkanFasilitator;

    @BindView(R.id.et_persyaratan_tempat)
    EditText etPersyaratanTempat;

    @BindView(R.id.et_informasi_tambahan)
    EditText etInformasiTambahan;

    @BindView(R.id.spinner_activity_category)
    AppCompatSpinner spinnerCategory;

    @BindView(R.id.btn_create_campaign)
    Button btnAjukanKegiatan;

    private CreateCampaignSearchPlacePresenter presenter;
    private ActivityCategory selectedCategory;

    private final Calendar calendar = Calendar.getInstance();

    private String photoUri;

    private static final String IMAGE_TYPE = "image/*";
    private static final String PHOTO_KEY = "photo";

    private static final Integer FIRST_INDEX = 0;
    private static final Integer EMPTY_SIZE = 0;
    private static final Integer GET_IMAGE_REQUEST_CODE = 4105;
    private static final Integer STORAGE_PERMISSION_REQUEST_CODE = 4106;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_campaign_search_place);
        ButterKnife.bind(this);

        presenter = new CreateCampaignSearchPlacePresenter(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.getAllActivityCategoty();
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

    private void updateBtnUploadFotoText (Uri imageUri) {
        String type = getImageType(imageUri);
        String imagePath = imageUri.getPath();

        setBtnUploadFotoText(type, imagePath);
    }

    private String getImageType (Uri imageUri) {
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(getContentResolver().getType(imageUri));
    }

    private void setBtnUploadFotoText(String type, String imagePath) {
        String imageFileName = imagePath + "." + type;
        btnUploadPhoto.setText(imageFileName);
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

    private Activity createActivity () {
        return Activity.builder()
                .nameActivity(etNama.getText().toString())
                .categoryId(selectedCategory.getId())
                .description(etDeskripsi.getText().toString())
                .area(etJangkauanDaerah.getText().toString())
                .maxParticipants(Integer.valueOf(etJumlahPeserta.getText().toString()))
                .volunteersTotal(Integer.valueOf(etJumlahRelawan.getText().toString()))
                .activityPlan(etRencanaKegiatan.getText().toString())
                .preparedByFacilitator(etPerluDisiapkanFasilitator.getText().toString())
                .locationRequirement(etPersyaratanTempat.getText().toString())
                .additionalInformation(etInformasiTambahan.getText().toString())
                .build();
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
                .setTitle(R.string.buat_kampanye)
                .setMessage(message)
                .setPositiveButton(R.string.oke, new OnOkClickListener())
                .setCancelable(false)
                .create();

        confirmationDialog.setCanceledOnTouchOutside(true);
        confirmationDialog.show();
    }

    @OnClick(R.id.et_tanggal_mulai)
    public void onEtStartDateClicked () {
        new DatePickerDialog(this, new OnStartDateSetListener(),
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    @OnClick(R.id.et_tanggal_selesai)
    public void onEtEndDateClicked () {
        new DatePickerDialog(this, new OnEndDateSetListener(),
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    @OnClick(R.id.et_deadline_pengajuan)
    public void onEtDeadlineClicked () {
        new DatePickerDialog(this, new OnDeadlineDateSetListener(),
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    @OnClick(R.id.btn_upload_foto)
    public void onUploadPhotoClicked () {
        setGallery();
    }

    @OnClick(R.id.btn_create_campaign)
    public void onBtnAjukanKegiatanClicked () {
        String startDate = etTanggalMulai.getText().toString();
        String endDate = etTanggalSelesai.getText().toString();
        String deadlineDateTime = etDeadlinePengajuan.getText().toString();

        Activity createdActivity = createActivity();
        MultipartBody.Part picture = getPhotoMultipart();

        presenter.createActivityCampaign(createdActivity, startDate, endDate, deadlineDateTime, picture);
    }

    @Override
    public void setActivityCategory(List<ActivityCategory> categoryList) {
        ArrayAdapter spinnerCategoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categoryList);
        spinnerCategoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(spinnerCategoryAdapter);
        spinnerCategory.setOnItemSelectedListener(new OnCategorySelectedListener());
    }

    private class OnStartDateSetListener implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            calendar.set(Calendar.YEAR, i);
            calendar.set(Calendar.MONTH, i1);
            calendar.set(Calendar.DAY_OF_MONTH, i2);

            String tanggalMulai = DateTimeUtils.dateToString(calendar.getTime(),DateTimeUtils.FORMAT_YYYYMMDD);
            etTanggalMulai.setText(tanggalMulai);
        }
    }

    private class OnEndDateSetListener implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            calendar.set(Calendar.YEAR, i);
            calendar.set(Calendar.MONTH, i1);
            calendar.set(Calendar.DAY_OF_MONTH, i2);

            String tanggalSelesai = DateTimeUtils.dateToString(calendar.getTime(),DateTimeUtils.FORMAT_YYYYMMDD);
            etTanggalSelesai.setText(tanggalSelesai);
        }
    }

    private class OnDeadlineDateSetListener implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            calendar.set(Calendar.YEAR, i);
            calendar.set(Calendar.MONTH, i1);
            calendar.set(Calendar.DAY_OF_MONTH, i2);

            String deadlinePengajuan = DateTimeUtils.dateToString(calendar.getTime(),DateTimeUtils.FORMAT_YYYYMMDD);
            etDeadlinePengajuan.setText(deadlinePengajuan);
        }
    }

    private class OnCategorySelectedListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            selectedCategory = (ActivityCategory) adapterView.getItemAtPosition(i);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    private class OnOkClickListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
            CommunicationUtils.changeActivity(CreateCampaignSearchPlaceActivity.this,
                    MainActivity.class);
        }
    }
}
