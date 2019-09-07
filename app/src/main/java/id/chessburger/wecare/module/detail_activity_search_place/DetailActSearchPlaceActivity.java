package id.chessburger.wecare.module.detail_activity_search_place;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
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
import id.chessburger.wecare.module.propose_location.ProposeLocationActivity;
import id.chessburger.wecare.utils.CommunicationUtils;

public class DetailActSearchPlaceActivity extends BaseActivity implements IDetailActSearchPlaceView {

    @BindView(R.id.fab_bookmark_activity_search_place)
    FloatingActionButton fabBookmark;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.iv_acitvity_picture)
    ImageView ivActivityPicture;

    @BindView(R.id.btn_ajukan_tempat)
    Button btnAjukanTempat;

    @BindView(R.id.tv_nama_activity_search_place)
    TextView tvNamaKegiatan;

    @BindView(R.id.tv_kategori_kegiatan)
    TextView tvKategoriKegiatan;

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
    private boolean isBookmarked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_act_search_place);
        ButterKnife.bind(this);

        setToolbar();
        getBundleIntentData();

        presenter = new DetailActSearchPlacePresenter(this);
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

    @Override
    public void setActivityPhoto(String imageUrl) {
        Picasso.get().load(imageUrl).fit().into(ivActivityPicture);
    }

    @Override
    public void setActivityCategory(String category) {
        tvKategoriKegiatan.setText(category);
    }

    @Override
    public void setActivityName(String activityName) {
        tvNamaKegiatan.setText(activityName);
    }

    @Override
    public void setCampaignerData(User campaigner) {
        String penyelenggara = getText(R.string.oleh)+ " " + campaigner.getName();

        tvPenyelenggara.setText(penyelenggara);
        tvNoTelpNarahubung.setText(campaigner.getPhoneNumber());
        tvEmailNarahubung.setText(campaigner.getEmail());
    }

    @Override
    public void setActivityDescription(String description) {
        tvDeskripsi.setText(description);
    }

    @Override
    public void setJangkauanDaerah(String area) {
        tvJangkauanDaerah.setText(area);
    }

    @Override
    public void setKetersediaanWaktu(String startDate, String endDate) {
        String ketersediaanWaktu = startDate + " s/d " + endDate;
        tvKetersediaanWaktu.setText(ketersediaanWaktu);
    }

    @Override
    public void setTanggalTelahTerisi() {

    }

    @Override
    public void setActivityDuration(int duration) {
        tvDurasiKegiatan.setText(duration + " " + getResources().getString(R.string.hari));
    }

    @Override
    public void setRencanaKegiatan(String rencanaKegiatan) {
        tvRencanaKegiatan.setText(rencanaKegiatan);
    }

    @Override
    public void serPerluDisiapkanFasilitator(String perluDisiapkanFasilitator) {
        tvPerluDisiapkanFasilitator.setText(perluDisiapkanFasilitator);
    }

    @Override
    public void setPersyaratan(String persyaratan) {
        tvPersyaratan.setText(persyaratan);
    }

    @Override
    public void setAdditionalInformation(String additionalInformation) {
        tvInformasiTambahan.setText(additionalInformation);
    }

    @Override
    public void setBookmarkView(boolean isBookmarked) {
        this.isBookmarked = isBookmarked;

        if (isBookmarked)
            fabBookmark.setImageResource(R.drawable.ic_already_bookmark);
        else
            fabBookmark.setImageResource(R.drawable.ic_bookmark_white);
    }

    @OnClick(R.id.fab_bookmark_activity_search_place)
    public void onBookmarkBtnClicked () {
        fabBookmark.setImageResource(R.drawable.ic_already_bookmark);

        if (isBookmarked)
            presenter.unBookmarkActivity(idActivity);
        else
            presenter.bookmarkActivity(idActivity);
    }

    @OnClick(R.id.btn_ajukan_tempat)
    public void onBtnAjukanTempatClicked() {
        Bundle bundle = new Bundle();
        bundle.putSerializable(CommunicationKeys.SELECTED_ACTIVITY.getKey(), idActivity);

        CommunicationUtils.changeActivity(this, ProposeLocationActivity.class,
                false, bundle, CommunicationKeys.BUNDLE_KEY.getKey());
    }

    private static class OnOkeClickListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
        }
    }
}
