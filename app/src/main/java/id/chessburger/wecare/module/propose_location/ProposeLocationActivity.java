package id.chessburger.wecare.module.propose_location;

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
import android.widget.DatePicker;
import android.widget.EditText;
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
import butterknife.OnClick;
import id.chessburger.wecare.R;
import id.chessburger.wecare.base.BaseActivity;
import id.chessburger.wecare.model.City;
import id.chessburger.wecare.model.Location;
import id.chessburger.wecare.model.enumerations.CommunicationKeys;
import id.chessburger.wecare.utils.DateTimeUtils;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ProposeLocationActivity extends BaseActivity implements IProposeLocationView {

    @BindView(R.id.spinner_kabupaten_kota)
    AppCompatSpinner spinnerKabupatenKota;

    @BindView(R.id.et_alamat_ajukan_tempat)
    EditText etAlamat;

    @BindView(R.id.et_tanggal_mulai)
    EditText etTanggalMulai;

    @BindView(R.id.et_tanggal_selesai)
    EditText etTanggalSelesai;

    @BindView(R.id.et_waktu_selesai)
    EditText etWaktuSelesai;

    @BindView(R.id.et_waktu_mulai)
    EditText etWaktuMulai;

    @BindView(R.id.et_jumlah_peserta)
    EditText etJumlahPeserta;

    @BindView(R.id.et_deskripsi_kondisi_tempat)
    EditText etDeskripsiKondisiTempat;

    @BindView(R.id.btn_perizinan_tempat)
    Button btnPerizinanTempat;

    @BindView(R.id.btn_kondisi_tempat)
    Button btnKondisiTempat;

    @BindView(R.id.btn_ajukan_tempat)
    Button btnAjukanTempat;

    private ProposeLocationPresenter presenter;

    private final Calendar calendar = Calendar.getInstance();
    private City selectedCity;

    private int idActivity;
    private String perizinanTempatUri;
    private String kondisiTempatUri;

    private static final String IMAGE_TYPE = "image/*";
    private static final String PERIZINAN_TEMPAT_KEY = "licensePhoto";
    private static final String KONDISI_TEMPAT_KEY = "locationPhoto";

    private static final Integer FIRST_INDEX = 0;
    private static final Integer EMPTY_SIZE = 0;
    private static final Integer PERIZINAN_TEMPAT_REQUEST_CODE = 4104;
    private static final Integer KONDISI_TEMPAT_REQUEST_CODE = 4105;
    private static final Integer STORAGE_PERMISSION_REQUEST_CODE = 4106;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propose_location);
        ButterKnife.bind(this);

        getBundleIntentData();

        presenter = new ProposeLocationPresenter(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.getAllIndonesiaCities();
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
            openGallery(requestCode);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (isPickImage(resultCode, data)) {
            Uri imageUri = data.getData();
            if (requestCode == PERIZINAN_TEMPAT_REQUEST_CODE) {
                perizinanTempatUri = getRealPath(imageUri);
                updateBtnUploadFotoText(imageUri, PERIZINAN_TEMPAT_REQUEST_CODE);
            } else {
                kondisiTempatUri = getRealPath(imageUri);
                updateBtnUploadFotoText(imageUri, KONDISI_TEMPAT_REQUEST_CODE);
            }
        }
    }

    private String getImageType (Uri imageUri) {
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(getContentResolver().getType(imageUri));
    }

    private void updateBtnUploadFotoText (Uri imageUri, int requestCode) {
        String type = getImageType(imageUri);
        String imagePath = imageUri.getPath();

        setBtnUploadFotoText(type, imagePath, requestCode);
    }

    private void setBtnUploadFotoText(String type, String imagePath, int requestCode) {
        String imageFileName = imagePath + "." + type;
        if (requestCode == PERIZINAN_TEMPAT_REQUEST_CODE)
            btnPerizinanTempat.setText(imageFileName);
        else
            btnKondisiTempat.setText(imageFileName);
    }

    private Boolean isPickImage(int resultCode, Intent data) {
        return resultCode == RESULT_OK && data.getData() != null;
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

    private void setGallery(int requestCode) {
        if (isNeedRequestPermission()) {
            checkPermission(requestCode);
        } else {
            openGallery(requestCode);
        }
    }

    private boolean isNeedRequestPermission() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    private void checkPermission(int requestCode) throws SecurityException {
        if (isStoragePermissionsGranted()) {
            openGallery(requestCode);
        } else {
            requestStoragePermissions();
        }
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

    private void openGallery(int requestCode) {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType(IMAGE_TYPE);

        startActivityForResult(photoPickerIntent, requestCode);
    }

    private MultipartBody.Part getPhotoMultipart(String photoUri, String key) {
        if (photoUri != null) {
            File file = new File(photoUri);
            RequestBody requestFile = RequestBody.create(MediaType.parse(IMAGE_TYPE), file);

            return MultipartBody.Part.createFormData(key, file.getName(),
                    requestFile);
        } else {
            return null;
        }
    }

    private Location createLocation () {
        return Location.builder()
                .address(etAlamat.getText().toString())
                .city(selectedCity.toString())
                .capacity(Integer.valueOf(etJumlahPeserta.getText().toString()))
                .description(etDeskripsiKondisiTempat.getText().toString())
                .build();
    }

    private String parseDateTimeRequestFormat (EditText editTextTanggal, EditText editTextWaktu) {
        return editTextTanggal.getText().toString() + " " + editTextWaktu.getText().toString();
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
                .setTitle(R.string.warning)
                .setMessage(message)
                .setPositiveButton(R.string.oke, new OnOkClickListener())
                .setCancelable(false)
                .create();

        confirmationDialog.setCanceledOnTouchOutside(true);
        confirmationDialog.show();
    }

    @OnClick(R.id.btn_perizinan_tempat)
    public void onBtnPerizinanTempatClicked () {
        setGallery(PERIZINAN_TEMPAT_REQUEST_CODE);
    }

    @OnClick(R.id.btn_kondisi_tempat)
    public void onBtnKondisiTempatClicked () {
        setGallery(KONDISI_TEMPAT_REQUEST_CODE);
    }

    @OnClick(R.id.btn_ajukan_tempat)
    public void onBtnAjukanTempatClicked () {
        String startDateTime = parseDateTimeRequestFormat(etTanggalMulai, etWaktuMulai);
        String endDateTime = parseDateTimeRequestFormat(etTanggalSelesai, etWaktuSelesai);

        Location location = createLocation();

        MultipartBody.Part perizinanTempat = getPhotoMultipart(perizinanTempatUri, PERIZINAN_TEMPAT_KEY);
        MultipartBody.Part kondisiTempat = getPhotoMultipart(kondisiTempatUri, KONDISI_TEMPAT_KEY);

        presenter.proposeLocationToServer(idActivity, location, startDateTime, endDateTime,
                perizinanTempat, kondisiTempat);
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

    @Override
    public void setIndonesiaCities(List<City> cities) {
        ArrayAdapter spinnerCitiesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, cities);
        spinnerCitiesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerKabupatenKota.setAdapter(spinnerCitiesAdapter);
        spinnerKabupatenKota.setOnItemSelectedListener(new OnCitiesSelectedListener());
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

    private class OnStartDateSetListener implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            calendar.set(Calendar.YEAR, i);
            calendar.set(Calendar.MONTH, i1);
            calendar.set(Calendar.DAY_OF_MONTH, i2);

            String tanggalMulai = DateTimeUtils.dateToString(calendar.getTime(), DateTimeUtils.FORMAT_YYYYMMDD);
            etTanggalMulai.setText(tanggalMulai);
        }
    }

    private class OnEndDateSetListener implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            calendar.set(Calendar.YEAR, i);
            calendar.set(Calendar.MONTH, i1);
            calendar.set(Calendar.DAY_OF_MONTH, i2);

            String tanggalSelesai = DateTimeUtils.dateToString(calendar.getTime(), DateTimeUtils.FORMAT_YYYYMMDD);
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

    private class OnOkClickListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
        }
    }
}
