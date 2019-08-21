package id.chessburger.wecare.module.profile;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import id.chessburger.wecare.R;
import id.chessburger.wecare.base.BaseFragment;
import id.chessburger.wecare.model.User;
import id.chessburger.wecare.model.enumerations.CommunicationKeys;
import id.chessburger.wecare.module.edit_profile.EditProfileActivity;
import id.chessburger.wecare.module.login.LoginActivity;
import id.chessburger.wecare.utils.CommunicationUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends BaseFragment implements IProfileView {

    @BindView(R.id.iv_logout_profile)
    ImageView ivLogout;

    @BindView(R.id.iv_edit_profile)
    ImageView ivEditProfile;

    @BindView(R.id.iv_user_photo)
    CircleImageView ivUserPhoto;

    @BindView(R.id.tv_name_profile)
    TextView tvUserName;

    @BindView(R.id.tv_email_profile)
    TextView tvUserEmail;

    @BindView(R.id.tv_notelp_profile)
    TextView tvUserPhoneNumber;

    private static ProfileFragment profileFragment;

    private ProfilePresenter presenter;
    private User user;

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
        ButterKnife.bind(this, view);

        presenter = new ProfilePresenter(this);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.getUserProfileData();
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
    public void moveIntoLogin() {
        CommunicationUtils.changeActivity(getActivity(), LoginActivity.class);
    }

    @Override
    public void moveIntoEditProfile() {
        Bundle bundle = new Bundle();
        bundle.putSerializable(CommunicationKeys.SELECTED_USER.getKey(), user);

        CommunicationUtils.changeActivity(getActivity(), EditProfileActivity.class,
                bundle, CommunicationKeys.BUNDLE_KEY.getKey());
    }

    @Override
    public void showUserProfileData(User user) {
        if (!TextUtils.isEmpty(user.getPhoto()))
            Picasso.get()
                    .load(user.getPhoneNumber())
                    .error(R.mipmap.img_no_picture)
                    .into(ivUserPhoto);

        tvUserName.setText(user.getName());
        tvUserEmail.setText(user.getEmail());
        tvUserPhoneNumber.setText(user.getPhoneNumber());
    }

    @OnClick(R.id.iv_logout_profile)
    public void onLogoutClicked () {
        presenter.doLogout();
    }

    @OnClick(R.id.iv_edit_profile)
    public void onEditIVClicked () {
        moveIntoEditProfile();
    }
}
