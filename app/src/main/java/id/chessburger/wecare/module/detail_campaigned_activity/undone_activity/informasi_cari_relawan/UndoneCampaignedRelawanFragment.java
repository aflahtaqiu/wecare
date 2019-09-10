package id.chessburger.wecare.module.detail_campaigned_activity.undone_activity.informasi_cari_relawan;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.chessburger.wecare.R;
import id.chessburger.wecare.model.Activity;
import id.chessburger.wecare.model.User;
import id.chessburger.wecare.model.enumerations.CommunicationKeys;
import id.chessburger.wecare.module.create_report.CreateReportActivity;
import id.chessburger.wecare.utils.CommunicationUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class UndoneCampaignedRelawanFragment extends Fragment {

    @BindView(R.id.tv_nama_activity_search_volunter)
    TextView tvNamaActivity;

    @BindView(R.id.btn_cancel_activity_volunter)
    Button btnCancelActivity;

    @BindView(R.id.btn_report_activity)
    Button btnReport;

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

        tvNamaActivity.setText(activity.getNameActivity());

        return view;
    }

    @OnClick(R.id.btn_report_activity)
    public void onReportBtnClicked () {
        Bundle bundle = new Bundle();
        bundle.putInt(CommunicationKeys.SELECTED_ACTIVITY.getKey(), activity.getId());
        CommunicationUtils.changeActivity(getActivity(), CreateReportActivity.class, bundle,
                CommunicationKeys.BUNDLE_KEY.getKey(), false);
    }
}
