package id.chessburger.wecare.module.create_campaign_search_volunteer;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TimePicker;

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
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import id.chessburger.wecare.R;
import id.chessburger.wecare.base.BaseActivity;
import id.chessburger.wecare.model.Activity;
import id.chessburger.wecare.model.ActivityCategory;
import id.chessburger.wecare.model.City;
import id.chessburger.wecare.module.mainact.MainActivity;
import id.chessburger.wecare.utils.CommunicationUtils;
import id.chessburger.wecare.utils.DateTimeUtils;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class CreateCampaignSearchVolunteerActivity extends BaseActivity implements ICreateCampaignSearchVolunteerView {

    @BindView(R.id.spinner_activity_category)
    AppCompatSpinner spinnerActivityCategory;

    @BindView(R.id.spinner_kabupaten_kota)
    AppCompatSpinner spinnerKabupatenKota;

    @BindView(R.id.btn_upload_foto)
    Button btnUploadFoto;

    @BindView(R.id.btn_create_campaign)
    Button btnCreateCampaign;

    @BindView(R.id.et_tanggal_mulai)
    EditText etTanggalMulai;

    @BindView(R.id.et_tanggal_selesai)
    EditText etTanggalSelesai;

    @BindView(R.id.et_waktu_mulai)
    EditText etWaktuMulai;

    @BindView(R.id.et_waktu_selesai)
    EditText etWaktuSelesai;

    @BindView(R.id.et_nama_kegiatan)
    EditText etNamaKegiatan;

    @BindView(R.id.et_deskripsi_kegiatan)
    EditText etDeskripsi;

    @BindView(R.id.et_alamat_kegiatan)
    EditText etAlamat;

    @BindView(R.id.et_kuota_relawan)
    EditText etKuotaRelawan;

    @BindView(R.id.et_nominal_donasi)
    EditText etNominalDonasi;

    @BindView(R.id.et_tugas_relawan)
    EditText etTugasRelawan;

    @BindView(R.id.et_yang_perlu_dibawa_relawan)
    EditText etYangPerluDibawaRelawan;

    @BindView(R.id.et_persyaratan_relawan)
    EditText etPersyaratanRelawan;

    @BindView(R.id.et_briefing)
    EditText etBriefing;

    @BindView(R.id.et_deadline_pendaftaran)
    EditText etDeadlinePendaftaran;

    @BindView(R.id.linearlayout_nominal_donasi)
    LinearLayout linearLayoutNominalDonasi;

    @BindView(R.id.rb_yes_donate)
    RadioButton rbYesDonasi;

    @BindView(R.id.rb_no_donate)
    RadioButton rbNoDonasi;

    private CreateCampaignSearchVolunteerPresenter presenter;
    private final Calendar calendar = Calendar.getInstance();

    private ActivityCategory selectedCategory;
    private City selectedCity;

    private boolean isDonasi = false;
    private String photoUri;

    private static final String IMAGE_TYPE = "image/*";
    private static final String PHOTO_KEY = "photo";

    private static final Integer FIRST_INDEX = 0;
    private static final Integer EMPTY_SIZE = 0;
    private static final Integer ZERO_VALUE = 0;
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
    protected void onStart() {
        super.onStart();
        presenter.getAllActivityCategoty();
        presenter.getAllIndonesiaCities();
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

    private String getImageType (Uri imageUri) {
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(getContentResolver().getType(imageUri));
    }

    private void updateBtnUploadFotoText (Uri imageUri) {
        String type = getImageType(imageUri);
        String imagePath = imageUri.getPath();

        setBtnUploadFotoText(type, imagePath);
    }

    private void setBtnUploadFotoText(String type, String imagePath) {
        String imageFileName = imagePath + "." + type;
        btnUploadFoto.setText(imageFileName);
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

    private void logicRadioButtonClicked (boolean _isDonasi, RadioButton reverseRadioButton) {
        isDonasi = _isDonasi;
        etNominalDonasi.setEnabled(_isDonasi);
        reverseRadioButton.setChecked(false);
    }

    private int setDonationNominal () {
        if (isDonasi)
            return Integer.valueOf(etNominalDonasi.getText().toString());
        else
            return ZERO_VALUE;
    }

    private String parseDateTimeRequestFormat (EditText editTextTanggal, EditText editTextWaktu) {
        return editTextTanggal.getText().toString() + " " + editTextWaktu.getText().toString();
    }

    private Activity createActivity () {
        return Activity.builder()
                .nameActivity(etNamaKegiatan.getText().toString())
                .categoryId(selectedCategory.getId())
                .description(etDeskripsi.getText().toString())
                .address(etAlamat.getText().toString())
                .city(selectedCity.toString())
                .minVolunteers(Integer.valueOf(etKuotaRelawan.getText().toString()))
                .donationTarget(setDonationNominal())
                .volunteerTasks(etTugasRelawan.getText().toString())
                .volunteerEquipments(etYangPerluDibawaRelawan.getText().toString())
                .volunteerRequirements(etPersyaratanRelawan.getText().toString())
                .briefs(etBriefing.getText().toString())
                .build();
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
                .setTitle(R.string.buat_kampanye)
                .setMessage(message)
                .setPositiveButton(R.string.oke, new OnOkClickListener())
                .setCancelable(false)
                .create();

        confirmationDialog.setCanceledOnTouchOutside(true);
        confirmationDialog.show();
    }

    @Override
    public void setActivityCategory(List<ActivityCategory> activityCategoryList) {
        ArrayAdapter spinnerCategoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, activityCategoryList);
        spinnerCategoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerActivityCategory.setAdapter(spinnerCategoryAdapter);
        spinnerActivityCategory.setOnItemSelectedListener(new OnCategorySelectedListener());
    }

    @Override
    public void setIndonesiaCities(List<City> indonesiaCities) {
        ArrayAdapter spinnerCitiesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, indonesiaCities);
        spinnerCitiesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerKabupatenKota.setAdapter(spinnerCitiesAdapter);
        spinnerKabupatenKota.setOnItemSelectedListener(new OnCitiesSelectedListener());
    }

    @OnCheckedChanged({R.id.rb_yes_donate, R.id.rb_no_donate})
    public void onRadioButtonClicked(CompoundButton radioButton, boolean isSelected) {
        int rbId = radioButton.getId();

        if (isSelected) {
            switch (rbId) {
                case R.id.rb_yes_donate :
                    logicRadioButtonClicked(true, rbNoDonasi);
                    break;
                case R.id.rb_no_donate :
                    logicRadioButtonClicked(false, rbYesDonasi);
                    break;
            }
        }
    }

    @OnClick(R.id.btn_upload_foto)
    public void onBtnUploadFotoClicked () {
        setGallery();
    }

    @OnClick(R.id.btn_create_campaign)
    public void onBtnCreateCampaignClicked () {

        String startDateTime = parseDateTimeRequestFormat(etTanggalMulai, etWaktuMulai);
        String endDateTime = parseDateTimeRequestFormat(etTanggalSelesai, etWaktuSelesai);
        String deadlineDateTime = etDeadlinePendaftaran.getText().toString();

        Activity createdActivity = createActivity();
        MultipartBody.Part picture = getPhotoMultipart();

        presenter.createActivityCampaign(createdActivity, startDateTime, endDateTime, deadlineDateTime, picture);
    }

    @OnClick(R.id.et_tanggal_mulai)
    public void onEtTanggalMulaiClicked () {
        new DatePickerDialog(this, new OnStartDateSetListener(), calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    @OnClick(R.id.et_tanggal_selesai)
    public void onEtTanggalSelesaiClicked () {
        new DatePickerDialog(this, new OnEndDateSetListener(), calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    @OnClick(R.id.et_waktu_mulai)
    public void onEtWaktuMulaiClicked () {
        new TimePickerDialog(this, new OnStartTimeSetListener(),
                calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.HOUR), true)
                .show();
    }

    @OnClick(R.id.et_waktu_selesai)
    public void onEtWaktuSelesaiClicked () {
        new TimePickerDialog(this, new OnEndTimeSetListener(),
                calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.HOUR), true)
                .show();
    }

    @OnClick(R.id.et_deadline_pendaftaran)
    public void onEtDeadlinePendaftaranCLicked () {
        new DatePickerDialog(this, new OnDeadlineDateSetListener(), calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
                .show();
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

    private class OnStartTimeSetListener implements TimePickerDialog.OnTimeSetListener {
        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {
            if (i1<10)
                etWaktuMulai.setText(i + ":0" + i1);
            else etWaktuMulai.setText(i + ":" + i1);
        }
    }

    private class OnEndTimeSetListener implements TimePickerDialog.OnTimeSetListener {
        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {
            if (i1<10)
                etWaktuSelesai.setText(i + ":0" + i1);
            else etWaktuSelesai.setText(i + ":" + i1);
        }
    }

    private class OnDeadlineDateSetListener implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            calendar.set(Calendar.YEAR, i);
            calendar.set(Calendar.MONTH, i1);
            calendar.set(Calendar.DAY_OF_MONTH, i2);

            String deadlinePendaftaran = DateTimeUtils.dateToString(calendar.getTime(),DateTimeUtils.FORMAT_YYYYMMDD);
            etDeadlinePendaftaran.setText(deadlinePendaftaran);
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

    private class OnCitiesSelectedListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            selectedCity = (City) adapterView.getItemAtPosition(i);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    private class OnOkClickListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
            CommunicationUtils.changeActivity(CreateCampaignSearchVolunteerActivity.this,
                    MainActivity.class);
        }
    }
}
