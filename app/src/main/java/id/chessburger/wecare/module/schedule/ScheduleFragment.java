package id.chessburger.wecare.module.schedule;


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
public class ScheduleFragment extends BaseFragment implements IScheduleView {

    private static ScheduleFragment scheduleFragment;

    private SchedulePresenter presenter;

    public ScheduleFragment() {
        // Required empty public constructor
    }

    public static ScheduleFragment getInstance() {
        if (scheduleFragment == null) {
            scheduleFragment = new ScheduleFragment();
        }
        return scheduleFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);
        presenter = new SchedulePresenter(this);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        customToolbar();
    }

    private void customToolbar() {
        getActivity().setTitle(getResources().getString(R.string.kegiatan_kamu_ikuti));
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.schedule_menu, menu);
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
