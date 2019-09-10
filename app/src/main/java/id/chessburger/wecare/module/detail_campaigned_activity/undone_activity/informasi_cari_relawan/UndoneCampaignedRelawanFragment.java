package id.chessburger.wecare.module.detail_campaigned_activity.undone_activity.informasi_cari_relawan;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.chessburger.wecare.R;
import id.chessburger.wecare.model.Activity;
import id.chessburger.wecare.model.enumerations.CommunicationKeys;
import id.chessburger.wecare.module.create_report.CreateReportActivity;
import id.chessburger.wecare.utils.CommunicationUtils;
import id.chessburger.wecare.utils.DateTimeUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class UndoneCampaignedRelawanFragment extends Fragment {

    @BindView(R.id.progress_relawan_act_search_volunter)
    ProgressBar progressBarRelawan;

    @BindView(R.id.progress_donasi_act_search_volunter)
    ProgressBar progressBarDonasi;

    @BindView(R.id.tv_jumlah_hari_kegiatan)
    TextView tvJumlahHariKegiatan;

    @BindView(R.id.tv_city_activity)
    TextView tvCityKegiatan;

    @BindView(R.id.tv_penyelenggara_activity_search_volunter)
    TextView tvPenyelenggara;

    @BindView(R.id.tv_nama_activity_search_volunter)
    TextView tvNamaActivity;

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

    @BindView(R.id.btn_cancel_activity_volunter)
    Button btnCancelActivity;

    @BindView(R.id.btn_report_activity)
    Button btnReport;

    private static final int ZERO_VALUE = 0;
    private static final int ONE_HUNDRED = 100;

    private static UndoneCampaignedRelawanFragment fragment;

    private Activity activity;

    public static UndoneCampaignedRelawanFragment getInstance(Activity activity) {
        if (fragment == null) {
            fragment = new UndoneCampaignedRelawanFragment(activity);
        }
        return fragment;
    }

    UndoneCampaignedRelawanFragment(Activity activity) {
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_undone_campaigned_relawan, container, false);
        ButterKnife.bind(this, view);

        String penyelenggara = "Oleh " + activity.getCampaigner().getName();

        tvNamaActivity.setText(activity.getNameActivity());
        tvPenyelenggara.setText(penyelenggara);
        tvDeskripsi.setText(activity.getDescription());
        tvTugasRelawan.setText(activity.getVolunteerTasks());
        tvPerluDisiapkan.setText(activity.getVolunteerEquipments());
        tvPersyaratan.setText(activity.getVolunteerEquipments());
        tvBriefing.setText(activity.getBriefs());

        tvTempat.setText(activity.getLocation().getAddress());

        setDateTime();
        setVolunteerAndDonationData();

        return view;
    }

    void setVolunteerAndDonationData() {
        tvDonasiMinimum.setText(String.valueOf(activity.getDonationsTotal()));
        tvDonasiTerkumpul.setText(String.valueOf(activity.getDonationTarget()));

        tvVolunterTerdaftar.setText(String.valueOf(activity.getVolunteersTotal()));
        tvMinimumVolunter.setText(String.valueOf(activity.getMinVolunteers()));

        setDonationProgress((double) activity.getDonationTarget(), (double) activity.getDonationsTotal());
        setVolunteerProgress((double) activity.getMinVolunteers(), (double) activity.getVolunteersTotal());
    }

    void setDonationProgress (double requiredDonation, double collectedDonation) {
        if (isZero(requiredDonation)) {
            progressBarDonasi.setProgress(ZERO_VALUE);
        } else {
            double progressDonation = (collectedDonation / requiredDonation) * ONE_HUNDRED;
            progressBarDonasi.setProgress((int) progressDonation);
        }
    }

    void setVolunteerProgress (double minVolunteer, double registeredVolunteer) {
        if (isZero(minVolunteer)) {
            progressBarRelawan.setProgress(ZERO_VALUE);
        } else {
            double progressVolunteer = (registeredVolunteer / minVolunteer) * ONE_HUNDRED;
            progressBarRelawan.setProgress((int) progressVolunteer);
        }
    }

    private boolean isZero (double value) {
        return value == ZERO_VALUE;
    }

    void setDateTime() {
        setDeadlineData(activity.getRegisterDeadlineDate());
        setStartDateTime(activity.getStartDate());
        setEndDateTime(activity.getEndDate());
    }

    void setStartDateTime (Date dateTime) {
        String startDate = DateTimeUtils.dateToString(dateTime, DateTimeUtils.FORMAT_EEEEDDMMMYYYY);
        String startTime = DateTimeUtils.dateToString(dateTime, DateTimeUtils.FORMAT_HHMM);

        tvTanggalMulai.setText(startDate);
        tvWaktuMulai.setText(startTime);
    }

    void setEndDateTime (Date dateTime) {
        String endDate = DateTimeUtils.dateToString(dateTime, DateTimeUtils.FORMAT_EEEEDDMMMYYYY);
        String endTime = DateTimeUtils.dateToString(dateTime, DateTimeUtils.FORMAT_HHMM);

        tvTanggalSelesai.setText(endDate);
        tvWaktuSelesai.setText(endTime);
    }

    void setDeadlineData (Date deadlineDate) {
        String deadlineString = DateTimeUtils.dateToString(deadlineDate, DateTimeUtils.FORMAT_DDMMMMYY);
        tvDeadlinePendaftaran.setText(deadlineString);
    }

    @OnClick(R.id.btn_report_activity)
    public void onReportBtnClicked () {
        Bundle bundle = new Bundle();
        bundle.putInt(CommunicationKeys.SELECTED_ACTIVITY.getKey(), activity.getId());
        CommunicationUtils.changeActivity(getActivity(), CreateReportActivity.class, bundle,
                CommunicationKeys.BUNDLE_KEY.getKey(), false);
    }
}
