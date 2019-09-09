package id.chessburger.wecare.module.schedule_sudah_diikuti;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
public class ScheduleSudahDiikutiFragment extends BaseFragment implements IScheduleSudahDiikutiView {

    @BindView(R.id.recyclerview_rekomendasi_kegiatan_schedule)
    RecyclerView recyclerView;

    public static ScheduleSudahDiikutiFragment scheduleSudahDiikutiFragment;

    private ScheduleSudahDiikutiPresenter presenter;
    private ListFollowedActivityAdapter adapter;

    private List<Activity> activityList = new ArrayList<>();

    public static ScheduleSudahDiikutiFragment getInstance() {
        if (scheduleSudahDiikutiFragment == null) {
            scheduleSudahDiikutiFragment = new ScheduleSudahDiikutiFragment();
        }
        return scheduleSudahDiikutiFragment;
    }

    public ScheduleSudahDiikutiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule_sudah_diikuti, container, false);
        ButterKnife.bind(this, view);

        setRecyclerView();

        presenter = new ScheduleSudahDiikutiPresenter(this);
        return view;
    }

    private void setRecyclerView () {
        adapter = new ListFollowedActivityAdapter(this.activityList, getContext());

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
