package id.chessburger.wecare.module.campaign;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import id.chessburger.wecare.R;
import id.chessburger.wecare.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class CampaignFragment extends BaseFragment implements ICampaignView {

    private static CampaignFragment campaignFragment;

    private CampaignPresenter presenter;

    public CampaignFragment() {
        // Required empty public constructor
    }

    public static CampaignFragment getInstance() {
        if (campaignFragment == null) {
            campaignFragment = new CampaignFragment();
        }
        return campaignFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_campaign, container, false);
        presenter = new CampaignPresenter(this);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        customToolbar();
    }

    private void customToolbar() {
        getActivity().setTitle(getResources().getString(R.string.kegiatan_kampanyemu));
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.campaign_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
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
