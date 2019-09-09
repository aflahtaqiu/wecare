package id.chessburger.wecare.module.detail_campaigned_activity.done_activity.hasil_kampanye;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.chessburger.wecare.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DoneCampaignHasilKampanyeFragment extends Fragment {

    private static DoneCampaignHasilKampanyeFragment fragment;
    public static DoneCampaignHasilKampanyeFragment getInstance() {
        if (fragment == null) {
            fragment = new DoneCampaignHasilKampanyeFragment();
        }
        return fragment;
    }

    public DoneCampaignHasilKampanyeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_done_campaign_hasil_kampanye, container, false);
        return view;
    }
}
