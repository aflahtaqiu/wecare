package id.chessburger.wecare.module.wecare_poin;

import android.os.Bundle;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.chessburger.wecare.R;
import id.chessburger.wecare.base.BaseActivity;

public class WeCarePoinActivity extends BaseActivity implements IWeCarePoinView {

    @BindView(R.id.rv_availableVoucher)
    RecyclerView recyclerView;

    @BindView(R.id.btn_jumlah_poin)
    Button btnJumlahPooin;

    private WeCarePoinPresenter presenter;

    int[] imagesId = {R.mipmap.vocher1, R.mipmap.vocher2, R.mipmap.vocher3};
    String[] namaVoucer = {"Catering Paket Komplit Discount 70%", "Asuransi jiwa Cashback Premi Sampai Rp. 500.000", "Discount Tiket Pesawat Semua Maskapai"};
    int[] points = {100, 200, 150};
    String[] alamats = {"Di Delixo", "Ciputra Life", "Indonesia Flight"};

    LIstAVoucherAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_we_care_poin);

        ButterKnife.bind(this);


        presenter = new WeCarePoinPresenter(this);
        adapter = new LIstAVoucherAdapter(this, imagesId, namaVoucer, alamats, points, presenter);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));

        adapter.notifyDataSetChanged();

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
    public void updateWeCarePoin(int wecarepoin) {
        btnJumlahPooin.setText("Poin Kamu : " + wecarepoin + " Poin");
    }
}
