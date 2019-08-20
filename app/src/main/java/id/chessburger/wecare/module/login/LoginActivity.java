package id.chessburger.wecare.module.login;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.chessburger.wecare.R;
import id.chessburger.wecare.base.BaseActivity;
import id.chessburger.wecare.module.mainact.MainActivity;
import id.chessburger.wecare.utils.CommunicationUtils;

public class LoginActivity extends BaseActivity implements ILoginView {

    @BindView(R.id.et_phone_number_login)
    EditText etPhoneNumber;

    @BindView(R.id.et_password_login)
    EditText etPassword;

    @BindView(R.id.btn_login)
    Button btnLogin;

    @BindView(R.id.tv_daftar_disini)
    TextView tvDaftarDisini;

    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        presenter = new LoginPresenter(this);
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

    @OnClick(R.id.btn_login)
    public void onBtnLoginClicked () {
        presenter.doLogin();
    }

    @OnClick(R.id.tv_daftar_disini)
    public void onDaftarDisiniClicked () {

    }

    @Override
    public void moveIntoMain() {
        CommunicationUtils.changeActivity(this, MainActivity.class);
    }
}
