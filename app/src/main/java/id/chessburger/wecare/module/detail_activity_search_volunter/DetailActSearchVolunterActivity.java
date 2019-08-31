package id.chessburger.wecare.module.detail_activity_search_volunter;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.chessburger.wecare.R;
import id.chessburger.wecare.base.BaseActivity;
import id.chessburger.wecare.model.User;
import id.chessburger.wecare.model.enumerations.CommunicationKeys;

public class DetailActSearchVolunterActivity extends BaseActivity implements IDetailActSearchVolunterView {

    @BindView(R.id.fab_bookmark_activity_search_volunter)
    FloatingActionButton fabBookmark;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.app_bar)
    AppBarLayout appBarLayout; //Change the background to activity image

    @BindView(R.id.progress_relawan_act_search_volunter)
    ProgressBar progressBarRelawan;

    @BindView(R.id.progress_donasi_act_search_volunter)
    ProgressBar progressBarDonasi;

    @BindView(R.id.tv_nama_activity_search_volunter)
    TextView tvNamaKegiatan;

    @BindView(R.id.tv_penyelenggara_activity_search_volunter)
    TextView tvPenyelenggara;

    @BindView(R.id.tv_sisa_volunter_tersedia_act_search_volunter)
    TextView tvSisaVolunter;//min volunter - actual volunter

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

    private Activity activity;
    private int idActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_act_seacrh_volunter);
        ButterKnife.bind(this);
        setToolbar();
        getBundleIntentData();

        presenter = new DetailActSearchVolunterPresenter(this);
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
    protected void onStart() {
        super.onStart();
        presenter.getDetailActivity(idActivity);
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

    @OnClick(R.id.fab_bookmark_activity_search_volunter)
    public void onBookmarkBtnClicked () {
        Log.e("LELE", "bookmarked");
    }

    @OnClick(R.id.btn_donasi_activity_volunter)
    public void onDonasiBtnClicked () {

    }

    @OnClick(R.id.btn_follow_activity_volunter)
    public void onFollowBtnClicked () {
//        presenter.followActivity(idActivity);
    }

    @Override
    public void setCampaignerData(User campaigner) {
        String penyelenggara = getText(R.string.oleh)+ " " + campaigner.getName();

        tvPenyelenggara.setText(penyelenggara);
        tvNoTelpCampaigner.setText(campaigner.getPhoneNumber());
        tvEmailCampaigner.setText(campaigner.getEmail());
    }
}
