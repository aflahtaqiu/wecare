package id.chessburger.wecare.module.detail_activity_search_place;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.chessburger.wecare.R;
import id.chessburger.wecare.base.BaseActivity;
import id.chessburger.wecare.model.enumerations.CommunicationKeys;

public class DetailActSearchPlaceActivity extends BaseActivity implements IDetailActSearchPlaceView {

    @BindView(R.id.fab_bookmark_activity_search_place)
    FloatingActionButton fabBookmark;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.app_bar)
    AppBarLayout appBarLayout; //Change the background to activity image

    @BindView(R.id.btn_ajukan_tempat)
    Button btnAjukanTempat;

    @BindView(R.id.tv_nama_activity_search_place)
    TextView tvNamaKegiatan;

    @BindView(R.id.tv_penyelenggara_activity_search_place)
    TextView tvPenyelenggara;

    @BindView(R.id.tv_jumlah_relawan)
    TextView tvJumlahRelawan;

    @BindView(R.id.tv_jumlah_peserta)
    TextView tvJumlahPeserta;

    @BindView(R.id.tv_durasi_kegiatan)
    TextView tvDurasiKegiatan;

    @BindView(R.id.tv_deskripsi_act_search_place)
    TextView tvDeskripsi;

    @BindView(R.id.tv_narahubung_no_telp_act_search_place)
    TextView tvNoTelpNarahubung;

    @BindView(R.id.tv_narahubung_email_act_search_place)
    TextView tvEmailNarahubung;

    @BindView(R.id.tv_jangkauan_daerah_act_search_place)
    TextView tvJangkauanDaerah;

    @BindView(R.id.tv_ketersediaan_waktu_act_search_place)
    TextView tvKetersediaanWaktu;

    @BindView(R.id.tv_tanggal_telah_terisi_act_search_place)
    TextView tvTanggalTelahTerisi;

    @BindView(R.id.tv_rencana_kegiatan_act_search_place)
    TextView tvRencanaKegiatan;

    @BindView(R.id.tv_perlu_disiapkan_fasilitator_act_search_place)
    TextView tvPerluDisiapkanFasilitator;

    @BindView(R.id.tv_persyaratan_act_search_place)
    TextView tvPersyaratan;

    @BindView(R.id.tv_informasi_tambahan_act_search_place)
    TextView tvInformasiTambahan;

    private DetailActSearchPlacePresenter presenter;

    private int idActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_act_search_place);
        ButterKnife.bind(this);

        setToolbar();
        getBundleIntentData();

        presenter = new DetailActSearchPlacePresenter(this);
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

    }

    @OnClick(R.id.btn_ajukan_tempat)
    public void onAjukanTempatBtnClicked () {

    }
}
