package id.chessburger.wecare.module.detail_campaigned_activity.undone_activity;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.chessburger.wecare.R;
import id.chessburger.wecare.model.enumerations.CommunicationKeys;
import id.chessburger.wecare.module.detail_campaigned_activity.undone_activity.hasil_kampanye.UndoneCampaignedHasilKampanyeFragment;
import id.chessburger.wecare.module.detail_campaigned_activity.undone_activity.informasi_cari_lokasi.UndoneCampaignedLokasiFragment;
import id.chessburger.wecare.module.detail_campaigned_activity.undone_activity.informasi_cari_relawan.UndoneCampaignedRelawanFragment;
import id.chessburger.wecare.utils.CommunicationUtils;

public class UnDoneCampaignedActivity extends AppCompatActivity {

    @BindView(R.id.btn_informasi)
    Button btnInformasi;

    @BindView(R.id.btn_hasil_kampanye)
    Button btnHasilKampanye;

    @BindView(R.id.framelayout_undone_campaigned)
    FrameLayout frameLayout;

    int idType;

    private static final int ID_CARI_RELAWAN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_un_done_campaigned);

        ButterKnife.bind(this);

        getBundleIntentData();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    private void getBundleIntentData() {
        Bundle bundle = getIntent().getBundleExtra(CommunicationKeys.BUNDLE_KEY.getKey());
        idType = bundle.getInt(CommunicationKeys.SELECTED_TYPE.getKey());
    }

    @Override
    protected void onStart() {
        super.onStart();
        btnInformasi.performClick();
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
            fragmentTransaction(UndoneCampaignedRelawanFragment.getInstance());
        else
            fragmentTransaction(UndoneCampaignedLokasiFragment.getInstance());
    }

    @OnClick(R.id.btn_hasil_kampanye)
    public void onBtnHasilKampanyeClicked () {
        customButton(btnHasilKampanye, btnInformasi);
        fragmentTransaction(UndoneCampaignedHasilKampanyeFragment.getInstance());
    }
}