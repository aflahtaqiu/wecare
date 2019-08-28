package id.chessburger.wecare.module.detail_activity_search_volunter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.chessburger.wecare.R;
import id.chessburger.wecare.base.BaseActivity;
import id.chessburger.wecare.model.enumerations.CommunicationKeys;

public class DetailActSearchVolunterActivity extends BaseActivity implements IDetailActSearchVolunterView {

    @BindView(R.id.fab_bookmark_activity_search_volunter)
    FloatingActionButton fabBookmark;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private DetailActSearchVolunterPresenter presenter;

    private Activity activity;
    private int idActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_act_seacrh_volunter);
        ButterKnife.bind(this);
        setToolbar();
        getBundleIntentData();

        fabBookmark.setOnClickListener(new FabBookmarkOnClickListener());

        presenter = new DetailActSearchVolunterPresenter(this);
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void getBundleIntentData() {
        Bundle bundle = getIntent().getBundleExtra(CommunicationKeys.BUNDLE_KEY.getKey());
        idActivity = bundle.getInt(CommunicationKeys.SELECTED_ACTIVITY.getKey());
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.getDetailActivity(idActivity);
//        presenter.followActivity(idActivity);
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

    private static class FabBookmarkOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }
}
