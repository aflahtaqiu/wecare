package id.chessburger.wecare.module.profile;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import id.chessburger.wecare.R;
import id.chessburger.wecare.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends BaseFragment implements IProfileView {

    private static ProfileFragment profileFragment;

    private ProfilePresenter presenter;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment getInstance() {
        if (profileFragment == null) {
            profileFragment = new ProfileFragment();
        }
        return profileFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        presenter = new ProfilePresenter(this);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        customToolbar();
    }

    private void customToolbar() {
        getActivity().setTitle(getResources().getString(R.string.profil_kamu));
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.profile_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
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
