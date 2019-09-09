package id.chessburger.wecare.module.schedule_akan_diikuti;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.chessburger.wecare.R;
import id.chessburger.wecare.base.BaseFragment;
import id.chessburger.wecare.model.Activity;
import id.chessburger.wecare.model.Schedule;

public class ScheduleAkanDiikutiFragment extends BaseFragment implements IScheduleAkanDiikutiView {

    @BindView(R.id.recyclerview_kegiatan_lain_schedule)
    RecyclerView recyclerView;

    public static ScheduleAkanDiikutiFragment scheduleAkanDiikutiFragment;

    private ScheduleAkanDiikutiPresenter presenter;
    private ListFollowedActivityAdapter adapter;

    private List<Schedule> schedules = new ArrayList<>();

    public ScheduleAkanDiikutiFragment() {
        // Required empty public constructor
    }

    public static ScheduleAkanDiikutiFragment getInstance() {
        if (scheduleAkanDiikutiFragment == null) {
            scheduleAkanDiikutiFragment = new ScheduleAkanDiikutiFragment();
        }
        return scheduleAkanDiikutiFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule_akan_diikuti, container, false);
        ButterKnife.bind(this, view);

        setRecyclerView();

        presenter = new ScheduleAkanDiikutiPresenter(this);
        presenter.getScheduledActivities();

        return view;
    }

    private void setRecyclerView () {
        adapter = new ListFollowedActivityAdapter(this.schedules, getContext());

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
    public void showListActivities(List<Schedule> schedules) {
        this.schedules.clear();
        this.schedules.addAll(schedules);

        adapter.notifyDataSetChanged();
    }
}
