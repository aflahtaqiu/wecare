package id.chessburger.wecare.module.login;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

    private static final String LOGIN_FAILED_TITLE = "Login Failed";
    private static final String OK = "OK";

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
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle(LOGIN_FAILED_TITLE)
                .setMessage(message)
                .setPositiveButton(OK, new AlertDialogOkClickListener())
                .setCancelable(true)
                .create();

        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.show();
    }

    @OnClick(R.id.btn_login)
    public void onBtnLoginClicked () {
        String phoneNumber = etPhoneNumber.getText().toString();
        String password = etPassword.getText().toString();

        presenter.doLogin(phoneNumber,password);
    }

    @OnClick(R.id.tv_daftar_disini)
    public void onDaftarDisiniClicked () {
        // TODO: change activity into register activity 
    }

    @Override
    public void moveIntoMain() {
        CommunicationUtils.changeActivity(this, MainActivity.class);
    }

    private static class AlertDialogOkClickListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
        }
    }
}
