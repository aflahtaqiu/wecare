package id.chessburger.wecare.module.campaign_sudah_terlaksana;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.chessburger.wecare.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CampaignSudahTerlaksanaFragment extends Fragment {

    public static CampaignSudahTerlaksanaFragment campaignSudahTerlaksanaFragment;

    public static CampaignSudahTerlaksanaFragment getInstance() {
        if (campaignSudahTerlaksanaFragment == null) {
            campaignSudahTerlaksanaFragment = new CampaignSudahTerlaksanaFragment();
        }
        return campaignSudahTerlaksanaFragment;
    }

    public CampaignSudahTerlaksanaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_campaign_sudah_terlaksana, container, false);

        return view;
    }

}
