package id.chessburger.wecare.module.detail_activity_search_volunter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.chessburger.wecare.R;
import id.chessburger.wecare.base.BaseActivity;
import id.chessburger.wecare.model.User;
import id.chessburger.wecare.model.enumerations.CommunicationKeys;
import id.chessburger.wecare.module.donation.DonationActivity;
import id.chessburger.wecare.utils.CommunicationUtils;

public class DetailActSearchVolunterActivity extends BaseActivity implements IDetailActSearchVolunterView {

    @BindView(R.id.fab_bookmark_activity_search_volunter)
    FloatingActionButton fabBookmark;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.iv_acitvity_picture)
    ImageView ivActivityPicture;

    @BindView(R.id.progress_relawan_act_search_volunter)
    ProgressBar progressBarRelawan;

    @BindView(R.id.progress_donasi_act_search_volunter)
    ProgressBar progressBarDonasi;

    @BindView(R.id.tv_nama_activity_search_volunter)
    TextView tvNamaKegiatan;

    @BindView(R.id.tv_city_activity)
    TextView tvCity;

    @BindView(R.id.tv_penyelenggara_activity_search_volunter)
    TextView tvPenyelenggara;

    @BindView(R.id.tv_sisa_volunter_tersedia_act_search_volunter)
    TextView tvSisaVolunter;

    @BindView(R.id.tv_relawan_terdaftar_act_search_volunter)
    TextView tvVolunterTerdaftar;

    @BindView(R.id.tv_minimal_relawan_act_search_volunter)
    TextView tvMinimumVolunter;

    @BindView(R.id.tv_donasi_terkumpul_act_search_volunter)
    TextView tvDonasiTerkumpul;

    @BindView(R.id.tv_donasi_minimum_act_search_volunter)
    TextView tvDonasiMinimum;

    @BindView(R.id.tv_deadline_pendaftaran_act_search_volunter)
    TextView tvDeadlinePendaftaran;

    @BindView(R.id.tv_deskripsi_act_search_volunter)
    TextView tvDeskripsi;

    @BindView(R.id.tv_narahubung_no_telp_act_search_volunter)
    TextView tvNoTelpCampaigner;

    @BindView(R.id.tv_narahubung_email_act_search_volunter)
    TextView tvEmailCampaigner;

    @BindView(R.id.tv_tempat_act_search_volunter)
    TextView tvTempat;

    @BindView(R.id.tv_tanggal_mulai_act_search_volunter)
    TextView tvTanggalMulai;

    @BindView(R.id.tv_waktu_mulai_act_search_volunter)
    TextView tvWaktuMulai;

    @BindView(R.id.tv_tanggal_selesai_act_search_volunter)
    TextView tvTanggalSelesai;

    @BindView(R.id.tv_waktu_selesai_act_search_volunter)
    TextView tvWaktuSelesai;

    @BindView(R.id.tv_tugas_relawan_act_search_volunter)
    TextView tvTugasRelawan;

    @BindView(R.id.tv_perlu_disiapkan_act_search_volunter)
    TextView tvPerluDisiapkan;

    @BindView(R.id.tv_persyaratan_act_search_volunter)
    TextView tvPersyaratan;

    @BindView(R.id.tv_briefing_act_search_volunter)
    TextView tvBriefing;

    @BindView(R.id.btn_donasi_activity_volunter)
    Button btnDonasi;

    @BindView(R.id.btn_follow_activity_volunter)
    Button btnFollow;

    private DetailActSearchVolunterPresenter presenter;

    private int idActivity;
    private boolean isBookmarked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_act_seacrh_volunter);
        ButterKnife.bind(this);
        setToolbar();
        getBundleIntentData();

        presenter = new DetailActSearchVolunterPresenter(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.getDetailActivity(idActivity);
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void getBundleIntentData() {
        Bundle bundle = getIntent().getBundleExtra(CommunicationKeys.BUNDLE_KEY.getKey());
        idActivity = bundle.getInt(CommunicationKeys.SELECTED_ACTIVITY.getKey());
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
        AlertDialog messageDialog = new AlertDialog.Builder(this)
                .setTitle(R.string.warning)
                .setMessage(message)
                .setPositiveButton(R.string.oke, new OnOkeClickListener())
                .setCancelable(false)
                .create();

        messageDialog.setCanceledOnTouchOutside(true);
        messageDialog.show();
    }

    @OnClick(R.id.fab_bookmark_activity_search_volunter)
    public void onBookmarkBtnClicked () {
        fabBookmark.setImageResource(R.drawable.ic_already_bookmark);

        if (isBookmarked)
            presenter.unBookmarkActivity(idActivity);
        else
            presenter.bookmarkActivity(idActivity);
    }

    @OnClick(R.id.btn_donasi_activity_volunter)
    public void onDonasiBtnClicked () {
        Bundle bundle = new Bundle();
        bundle.putInt(CommunicationKeys.SELECTED_ACTIVITY.getKey(), idActivity);
        CommunicationUtils.changeActivity(this, DonationActivity.class, bundle,
                CommunicationKeys.BUNDLE_KEY.getKey(), false);
    }

    @OnClick(R.id.btn_follow_activity_volunter)
    public void onFollowBtnClicked () {
        showConfirmationDialog();
    }

    @Override
    public void setActivityName(String activityName) {
        tvNamaKegiatan.setText(activityName);
    }

    @Override
    public void setCampaignerData(User campaigner) {
        String penyelenggara = getText(R.string.oleh)+ " " + campaigner.getName();

        tvPenyelenggara.setText(penyelenggara);
        tvNoTelpCampaigner.setText(campaigner.getPhoneNumber());
        tvEmailCampaigner.setText(campaigner.getEmail());
    }

    @Override
    public void setActivityDescription(String description) {
        tvDeskripsi.setText(description);
    }

    @Override
    public void setPersiapanActivityData(id.chessburger.wecare.model.Activity activityData) {
        tvTugasRelawan.setText(activityData.getVolunteerTasks());
        tvPerluDisiapkan.setText(activityData.getVolunteerEquipments());
        tvPersyaratan.setText(activityData.getVolunteerRequirements());
        tvBriefing.setText(activityData.getBriefs());
    }

    @Override
    public void setDonationData(int requiredDonation, int collectedDonation) {
        String rupiahString = "Rp. ";
        tvDonasiMinimum.setText(rupiahString + requiredDonation);
        tvDonasiTerkumpul.setText(rupiahString + collectedDonation);
    }

    @Override
    public void setDonationProgress(int donationProgress) {
        progressBarDonasi.setProgress(donationProgress);
    }

    @Override
    public void setVolunteerData(int minimumVolunteer, int registeredVolunteer) {
        tvMinimumVolunter.setText(String.valueOf(minimumVolunteer));
        tvVolunterTerdaftar.setText(String.valueOf(registeredVolunteer));
        setSisaVolunteer(minimumVolunteer, registeredVolunteer);
    }

    @Override
    public void setSisaVolunteer(int minimumVolunteer, int registeredVolunteer) {
        int sisaRequiredVolunteer = Math.abs(minimumVolunteer - registeredVolunteer);
        String sisaVolunteerString = getResources().getText(R.string.sisa) + " " + sisaRequiredVolunteer;
        tvSisaVolunter.setText(sisaVolunteerString);
    }

    @Override
    public void setVolunteerProgress(int volunteerProgress) {
        progressBarRelawan.setProgress(volunteerProgress);
    }

    @Override
    public void setDeadlinePendaftaran(String deadline) {
        tvDeadlinePendaftaran.setText(deadline);
    }

    @Override
    public void setStartDateTime(String startDate, String startTime) {
        tvTanggalMulai.setText(startDate);
        tvWaktuMulai.setText(startTime);
    }

    @Override
    public void setEndDateTime(String endDate, String endTime) {
        tvTanggalSelesai.setText(endDate);
        tvWaktuSelesai.setText(endTime);
    }

    @Override
    public void setCity(String city) {
        tvCity.setText(city);
    }

    @Override
    public void setActivityImage(String imageUrl) {
        Picasso.get().load(imageUrl).fit().into(ivActivityPicture);
    }

    @Override
    public void setBookmarkView(boolean isBookmarked) {
        this.isBookmarked = isBookmarked;

        if (isBookmarked)
            fabBookmark.setImageResource(R.drawable.ic_already_bookmark);
        else
            fabBookmark.setImageResource(R.drawable.ic_bookmark_white);
    }

    @Override
    public void showConfirmationDialog() {
        AlertDialog confirmationDialog = new AlertDialog.Builder(this)
                .setTitle(R.string.ikuti_kegiatan)
                .setMessage(R.string.anda_yakin_mengikuti_kegiatan_ini)
                .setPositiveButton(R.string.ya, new OnYaClickListener())
                .setNegativeButton(R.string.tidak, new OnTidakClickListener())
                .setCancelable(false)
                .create();

        confirmationDialog.setCanceledOnTouchOutside(true);
        confirmationDialog.show();
    }

    private static class OnTidakClickListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
        }
    }

    private static class OnOkeClickListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
        }
    }

    private class OnYaClickListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            presenter.followActivity(idActivity);
            dialogInterface.dismiss();
        }
    }
}
