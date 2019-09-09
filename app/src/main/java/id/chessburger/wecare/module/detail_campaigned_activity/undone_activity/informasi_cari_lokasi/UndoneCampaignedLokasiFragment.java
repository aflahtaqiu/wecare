package id.chessburger.wecare.module.detail_campaigned_activity.undone_activity.informasi_cari_lokasi;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.chessburger.wecare.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UndoneCampaignedLokasiFragment extends Fragment {


    private static UndoneCampaignedLokasiFragment fragment;
    public static UndoneCampaignedLokasiFragment getInstance() {
        if (fragment == null) {
            fragment = new UndoneCampaignedLokasiFragment();
        }
        return fragment;
    }

    public UndoneCampaignedLokasiFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_undone_campaigned_lokasi, container, false);
        return view;
    }

}
