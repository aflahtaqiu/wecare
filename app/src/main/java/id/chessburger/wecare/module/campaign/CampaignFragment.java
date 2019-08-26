package id.chessburger.wecare.module.campaign;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.chessburger.wecare.R;
import id.chessburger.wecare.base.BaseFragment;
import id.chessburger.wecare.module.campaign_belum_terlaksana.CampaignBelumTerlaksanaFragment;
import id.chessburger.wecare.module.campaign_sudah_terlaksana.CampaignSudahTerlaksanaFragment;
import id.chessburger.wecare.utils.CommunicationUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class CampaignFragment extends BaseFragment implements ICampaignView {

    @BindView(R.id.btn_belum_terlaksana_campaign)
    Button btnBelumTerlaksana;

    @BindView(R.id.btn_sudah_terlaksana_campaign)
    Button btnSudahTerlaksana;

    @BindView(R.id.iv_sort_campaign)
    ImageView ivSort;

    @BindView(R.id.iv_add_campaign)
    ImageView ivAddCampaign;

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
        ButterKnife.bind(this, view);
        presenter = new CampaignPresenter(this);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        btnBelumTerlaksana.performClick();
    }

    private boolean fragmentTransaction(Fragment fragment) {
        CommunicationUtils.switchFragment(getChildFragmentManager(),
                R.id.framelayout_campaign, fragment, "", null);
        return true;
    }

    private void customButton (Button clickedButton, Button otherButton) {
        setButtonBackground(clickedButton, otherButton);
        setButtonTextColor(clickedButton, otherButton);
    }

    private void setButtonBackground(Button clickedButton, Button otherButton) {
        clickedButton.setBackground(getResources().getDrawable(R.drawable.btn_round_red_solid));
        otherButton.setBackground(getResources().getDrawable(R.drawable.btn_round_white_border_grey));
    }

    private void setButtonTextColor(Button clickedButton, Button otherButton) {
        clickedButton.setTextColor(getResources().getColor(R.color.colorWhite));
        otherButton.setTextColor(getResources().getColor(R.color.colorGreyButtonStroke));
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

    @OnClick(R.id.btn_belum_terlaksana_campaign)
    public void btnBelumTerlaksanaClicked () {
        customButton(btnBelumTerlaksana, btnSudahTerlaksana);
        fragmentTransaction(CampaignBelumTerlaksanaFragment.getInstance());
    }

    @OnClick(R.id.btn_sudah_terlaksana_campaign)
    public void btnSudahTerlaksanaClicked () {
        customButton(btnSudahTerlaksana, btnBelumTerlaksana);
        fragmentTransaction(CampaignSudahTerlaksanaFragment.getInstance());
    }
}
