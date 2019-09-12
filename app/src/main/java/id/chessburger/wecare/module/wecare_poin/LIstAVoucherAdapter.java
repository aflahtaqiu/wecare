package id.chessburger.wecare.module.wecare_poin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.chessburger.wecare.R;

/**
 * Created by aflah on 12/09/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public class LIstAVoucherAdapter extends RecyclerView.Adapter<LIstAVoucherAdapter.ListVoucherViewHolder> {
    private final Context context;
    int[] imagesId;
    String[] namaVoucers;
    String[] asalaVoucers;
    int[] pointVoucer;

    public LIstAVoucherAdapter(Context context, int[] imagesId, String[] namaVoucers, String[] asalaVoucers, int[] pointVoucer) {
        this.context = context;
        this.imagesId = imagesId;
        this.namaVoucers = namaVoucers;
        this.asalaVoucers = asalaVoucers;
        this.pointVoucer = pointVoucer;
    }

    @Override
    public ListVoucherViewHolder onCreateViewHolder(ViewGroup parent,
                                                    int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_voucher, parent, false);
        return new ListVoucherViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ListVoucherViewHolder holder, int position) {
       holder.ivPhotoVoucer.setImageDrawable(context.getResources().getDrawable(imagesId[position]));
       holder.tvNamaVoucer.setText(namaVoucers[position]);
       holder.tvPoinVoucer.setText(pointVoucer[position] + " Poin");
       holder.tvAsalVoucer.setText(asalaVoucers[position]);
    }

    @Override
    public int getItemCount() {
        if (imagesId == null) {
            return 0;
        }
        return imagesId.length;
    }

    public class ListVoucherViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.iv_photo_voucher)
        ImageView ivPhotoVoucer;

        @BindView(R.id.tv_nama_voucer)
        TextView tvNamaVoucer;

        @BindView(R.id.tv_poin_voucer)
        TextView tvPoinVoucer;

        @BindView(R.id.tv_asal_voucer)
        TextView tvAsalVoucer;


        public ListVoucherViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}   