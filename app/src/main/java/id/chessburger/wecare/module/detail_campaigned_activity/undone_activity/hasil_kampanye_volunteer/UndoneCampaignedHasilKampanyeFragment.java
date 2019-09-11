package id.chessburger.wecare.module.detail_campaigned_activity.undone_activity.hasil_kampanye_volunteer;


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
import id.chessburger.wecare.model.FollowedActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class UndoneCampaignedHasilKampanyeFragment extends BaseFragment implements IUnDoneCampaignHasilKampanyeView {

    @BindView(R.id.recyclerview_relawan_mendaftar)
    RecyclerView recyclerViewRelawanMendaftar;

    @BindView(R.id.recyclerview_relawan_mendaftar_bottom)
    RecyclerView recyclerViewRelawanMendaftarBottom;

    private int idActivity;

    private List<FollowedActivity> followedActivities = new ArrayList<>();

    private UnDoneCampaignHasilKampanyePresenter presenter;
    private ListVolunteerAdapter adapter;

    private static UndoneCampaignedHasilKampanyeFragment fragment;
    public static UndoneCampaignedHasilKampanyeFragment getInstance(int idActivity) {
        if (fragment == null) {
            fragment = new UndoneCampaignedHasilKampanyeFragment(idActivity);
        }
        return fragment;
    }

    UndoneCampaignedHasilKampanyeFragment(int idActivity) {
        this.idActivity = idActivity;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_undone_campaigned_hasil_kampanye, container, false);

        ButterKnife.bind(this, view);
        setRecyclerView();

        presenter = new UnDoneCampaignHasilKampanyePresenter(this);

        presenter.getVolunteersName(idActivity);

        return view;
    }


    private void setRecyclerView () {
        adapter = new ListVolunteerAdapter(this.followedActivities, getContext(), this.presenter);

        recyclerViewRelawanMendaftar.setAdapter(adapter);
        recyclerViewRelawanMendaftar.setLayoutManager(new LinearLayoutManager(getContext()));
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
    public void setFollowerActivities(List<FollowedActivity> followerActivities) {
        this.followedActivities.clear();
        this.followedActivities.addAll(followerActivities);

        adapter.notifyDataSetChanged();
    }
}
