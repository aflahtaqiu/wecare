package id.chessburger.wecare.module.detail_campaigned_activity.undone_activity.hasil_kampanye;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.chessburger.wecare.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UndoneCampaignedHasilKampanyeFragment extends Fragment {


    private static UndoneCampaignedHasilKampanyeFragment fragment;
    public static UndoneCampaignedHasilKampanyeFragment getInstance() {
        if (fragment == null) {
            fragment = new UndoneCampaignedHasilKampanyeFragment();
        }
        return fragment;
    }

    public UndoneCampaignedHasilKampanyeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_undone_campaigned_hasil_kampanye, container, false);
        return view;
    }

}
