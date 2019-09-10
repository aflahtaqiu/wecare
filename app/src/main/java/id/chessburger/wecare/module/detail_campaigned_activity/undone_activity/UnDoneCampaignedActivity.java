package id.chessburger.wecare.module.detail_campaigned_activity.undone_activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.chessburger.wecare.R;
import id.chessburger.wecare.base.BaseActivity;
import id.chessburger.wecare.model.Activity;
import id.chessburger.wecare.model.enumerations.CommunicationKeys;
import id.chessburger.wecare.module.detail_campaigned_activity.undone_activity.hasil_kampanye.UndoneCampaignedHasilKampanyeFragment;
import id.chessburger.wecare.module.detail_campaigned_activity.undone_activity.informasi_cari_lokasi.UndoneCampaignedLokasiFragment;
import id.chessburger.wecare.module.detail_campaigned_activity.undone_activity.informasi_cari_relawan.UndoneCampaignedRelawanFragment;
import id.chessburger.wecare.utils.CommunicationUtils;

public class UnDoneCampaignedActivity extends BaseActivity implements IUnDoneCampaignedActView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fab_edit_activity_data)
    FloatingActionButton fab;

    @BindView(R.id.btn_informasi)
    Button btnInformasi;

    @BindView(R.id.btn_hasil_kampanye)
    Button btnHasilKampanye;

    @BindView(R.id.framelayout_undone_campaigned)
    FrameLayout frameLayout;

    @BindView(R.id.iv_acitvity_picture)
    ImageView ivActivityPicture;

    private int idType;
    private int idActivity;

    private static final int ID_CARI_RELAWAN = 1;

    private Activity activity;
    private UnDoneCampaignedActPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_un_done_campaigned);

        ButterKnife.bind(this);

        getBundleIntentData();
        setSupportActionBar(toolbar);

        presenter = new UnDoneCampaignedActPresenter(this);
        presenter.getDetailActivity(idActivity);
    }

    private void getBundleIntentData() {
        Bundle bundle = getIntent().getBundleExtra(CommunicationKeys.BUNDLE_KEY.getKey());
        idActivity = bundle.getInt(CommunicationKeys.SELECTED_ACTIVITY.getKey());
        idType = bundle.getInt(CommunicationKeys.SELECTED_TYPE.getKey());
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private boolean fragmentTransaction(Fragment fragment) {
        CommunicationUtils.switchFragment(getSupportFragmentManager(),
                R.id.framelayout_undone_campaigned, fragment, "", null);
        return true;
    }

    private void customButton (Button clickedButton, Button otherButton) {
        setButtonBackground(clickedButton, otherButton);
        setButtonTextColor(clickedButton, otherButton);
    }

    private void setButtonBackground(Button clickedButton, Button otherButton) {
        clickedButton.setBackground(getResources().getDrawable(R.drawable.btn_box_white));
        otherButton.setBackground(getResources().getDrawable(R.drawable.btn_box_grey));
    }

    private void setButtonTextColor(Button clickedButton, Button otherButton) {
        clickedButton.setTextColor(getResources().getColor(R.color.colorGreyButtonStroke));
        otherButton.setTextColor(getResources().getColor(R.color.colorWhite));
    }

    @OnClick(R.id.btn_informasi)
    public void onBtnInformasiClicked () {
        customButton(btnInformasi, btnHasilKampanye);
        if (idType == ID_CARI_RELAWAN)
            fragmentTransaction(UndoneCampaignedRelawanFragment.getInstance(activity));
        else
            fragmentTransaction(UndoneCampaignedLokasiFragment.getInstance());
    }

    @OnClick(R.id.btn_hasil_kampanye)
    public void onBtnHasilKampanyeClicked () {
        customButton(btnHasilKampanye, btnInformasi);
        fragmentTransaction(UndoneCampaignedHasilKampanyeFragment.getInstance());
    }

    @OnClick(R.id.fab_edit_activity_data)
    public void onFabClicked () {
        // TODO: on fab clicked
    }

    @Override
    public void setActivity(Activity activity) {
        this.activity = activity;
        btnInformasi.performClick();
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
