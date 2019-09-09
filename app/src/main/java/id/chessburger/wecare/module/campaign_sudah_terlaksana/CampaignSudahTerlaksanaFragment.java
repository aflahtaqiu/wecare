package id.chessburger.wecare.module.campaign_sudah_terlaksana;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.chessburger.wecare.R;
import id.chessburger.wecare.base.BaseFragment;
import id.chessburger.wecare.model.Activity;

/**
 * A simple {@link Fragment} subclass.
 */
public class CampaignSudahTerlaksanaFragment extends BaseFragment implements ICampaignSudahTerlaksanaView {

    @BindView(R.id.recyclerview_rekomendasi_kegiatan_campaign)
    RecyclerView recyclerView;

    public static CampaignSudahTerlaksanaFragment campaignSudahTerlaksanaFragment;

    private CampaignSudahTerlaksanaPresenter presenter;
    private ListCampaignActivityAdapter adapter;

    private List<Activity> activityList = new ArrayList<>();

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
        ButterKnife.bind(this, view);

        setRecyclerView();

        presenter = new CampaignSudahTerlaksanaPresenter(this);
        presenter.getCampaignedActivities();

        return view;
    }

    private void setRecyclerView () {
        adapter = new ListCampaignActivityAdapter(this.activityList, getContext());

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
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

    @Override
    public void showListActivities(List<Activity> activities) {
        this.activityList.clear();
        this.activityList.addAll(activities);

        adapter.notifyDataSetChanged();
    }
}
