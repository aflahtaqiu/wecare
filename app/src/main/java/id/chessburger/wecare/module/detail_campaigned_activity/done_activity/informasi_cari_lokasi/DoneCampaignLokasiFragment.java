package id.chessburger.wecare.module.detail_campaigned_activity.done_activity.informasi_cari_lokasi;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.chessburger.wecare.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DoneCampaignLokasiFragment extends Fragment {


    private static DoneCampaignLokasiFragment fragment;
    public static DoneCampaignLokasiFragment getInstance() {
        if (fragment == null) {
            fragment = new DoneCampaignLokasiFragment();
        }
        return fragment;
    }

    public DoneCampaignLokasiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_done_campaign_lokasi, container, false);
        return view;
    }

}
