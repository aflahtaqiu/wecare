package id.chessburger.wecare.module.campaign_belum_terlaksana;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.chessburger.wecare.R;
import id.chessburger.wecare.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class CampaignBelumTerlaksanaFragment extends BaseFragment implements ICampaignBelumTerlaksanaView {

    public static CampaignBelumTerlaksanaFragment campaignBelumTerlaksanaFragment;

    private CampaignBelumTerlaksanaPresenter presenter;

    public static CampaignBelumTerlaksanaFragment getInstance() {
        if (campaignBelumTerlaksanaFragment == null) {
            campaignBelumTerlaksanaFragment = new CampaignBelumTerlaksanaFragment();
        }
        return campaignBelumTerlaksanaFragment;
    }


    public CampaignBelumTerlaksanaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_campaign_belum_terlaksana, container, false);
        presenter = new CampaignBelumTerlaksanaPresenter(this);
        return view;
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
}
