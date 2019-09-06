package id.chessburger.wecare.module.schedule_akan_diikuti;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.chessburger.wecare.R;
import id.chessburger.wecare.base.BaseFragment;

public class ScheduleAkanDiikutiFragment extends BaseFragment implements IScheduleAkanDiikutiView {

    public static ScheduleAkanDiikutiFragment scheduleAkanDiikutiFragment;

    private ScheduleAkanDiikutiPresenter presenter;

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
        presenter = new ScheduleAkanDiikutiPresenter(this);
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
