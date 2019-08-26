package id.chessburger.wecare.module.schedule_akan_diikuti;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import id.chessburger.wecare.R;

public class ScheduleAkanDiikutiFragment extends Fragment {

    public static ScheduleAkanDiikutiFragment scheduleAkanDiikutiFragment;

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
        return view;
    }
}
