package id.chessburger.wecare.module.detail_campaigned_activity.undone_activity.hasil_kampanye_volunteer;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
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
    private List<FollowedActivity> absencedFollowedActivities = new ArrayList<>();

    private UnDoneCampaignHasilKampanyePresenter presenter;

    private ListVolunteerAdapter volunteerAdapter;
    private ListAbsencedVolunteerAdapter absencedVolunteerAdapter;

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

        presenter = new UnDoneCampaignHasilKampanyePresenter(this);
        presenter.getVolunteersName(idActivity);
        presenter.getAbsencedVolunteersName(idActivity);

        setRecyclerView();

        return view;
    }


    private void setRecyclerView () {
        volunteerAdapter = new ListVolunteerAdapter(this.followedActivities, getContext(), this.presenter);
        absencedVolunteerAdapter = new ListAbsencedVolunteerAdapter(this.absencedFollowedActivities, getContext(), this.presenter);


        recyclerViewRelawanMendaftar.setAdapter(volunteerAdapter);
        recyclerViewRelawanMendaftarBottom.setAdapter(absencedVolunteerAdapter);

        recyclerViewRelawanMendaftar.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewRelawanMendaftarBottom.setLayoutManager(new LinearLayoutManager(getContext()));
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
        AlertDialog messageDialog = new AlertDialog.Builder(getContext())
                .setTitle(R.string.wecare_indonesia)
                .setMessage(message)
                .setPositiveButton(R.string.oke, new OnOkeClickListener())
                .setCancelable(false)
                .create();

        messageDialog.setCanceledOnTouchOutside(true);
        messageDialog.show();
    }

    @Override
    public void setFollowerActivities(List<FollowedActivity> followerActivities) {
        this.followedActivities.clear();
        this.followedActivities.addAll(followerActivities);

        volunteerAdapter.notifyDataSetChanged();
    }

    @Override
    public void setAbsencedFollowedActivities(List<FollowedActivity> followedActivities) {
        this.absencedFollowedActivities.clear();
        this.absencedFollowedActivities.addAll(followedActivities);

        absencedVolunteerAdapter.notifyDataSetChanged();
    }

    private class OnOkeClickListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();

            presenter.getVolunteersName(idActivity);
            presenter.getAbsencedVolunteersName(idActivity);
        }
    }
}
