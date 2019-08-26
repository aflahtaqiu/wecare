package id.chessburger.wecare.module.schedule_sudah_diikuti;


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
public class ScheduleSudahDiikutiFragment extends BaseFragment implements IScheduleSudahDiikutiView {

    public static ScheduleSudahDiikutiFragment scheduleSudahDiikutiFragment;

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
