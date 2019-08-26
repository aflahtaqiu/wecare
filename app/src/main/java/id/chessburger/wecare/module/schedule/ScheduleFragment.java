package id.chessburger.wecare.module.schedule;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.chessburger.wecare.R;
import id.chessburger.wecare.base.BaseFragment;
import id.chessburger.wecare.module.schedule_akan_diikuti.ScheduleAkanDiikutiFragment;
import id.chessburger.wecare.module.schedule_sudah_diikuti.ScheduleSudahDiikutiFragment;
import id.chessburger.wecare.utils.CommunicationUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScheduleFragment extends BaseFragment implements IScheduleView {

    @BindView(R.id.btn_akan_diikuti_schedule)
    Button btnAkanDiikuti;

    @BindView(R.id.btn_sudah_diikuti)
    Button btnSudahDiikuti;

    @BindView(R.id.iv_sort_schedule)
    ImageView ivSort;

    @BindView(R.id.iv_filter_schedule)
    ImageView ivFilter;

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
        ButterKnife.bind(this, view);
        presenter = new SchedulePresenter(this);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        btnAkanDiikuti.performClick();
    }

    private boolean fragmentTransaction(Fragment fragment) {
        CommunicationUtils.switchFragment(getChildFragmentManager(),
                R.id.framelayout_schedule, fragment, "", null);
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

    @OnClick (R.id.btn_akan_diikuti_schedule)
    public void btnAkanDiikutiClicked () {
        customButton(btnAkanDiikuti, btnSudahDiikuti);
        fragmentTransaction(ScheduleAkanDiikutiFragment.getInstance());
    }

    @OnClick (R.id.btn_sudah_diikuti)
    public void btnSudahDiikutiClicked () {
        customButton(btnSudahDiikuti, btnAkanDiikuti);
        fragmentTransaction(ScheduleSudahDiikutiFragment.getInstance());
    }
}
