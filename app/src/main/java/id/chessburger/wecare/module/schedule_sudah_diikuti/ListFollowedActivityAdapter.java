package id.chessburger.wecare.module.schedule_sudah_diikuti;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.chessburger.wecare.R;
import id.chessburger.wecare.model.Activity;
import id.chessburger.wecare.model.Schedule;

/**
 * Created by aflah on 08/09/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public class ListFollowedActivityAdapter extends RecyclerView.Adapter<ListFollowedActivityAdapter.FollowedActivityViewHolder> {

    private final Context context;
    private List<Schedule> items;

    ListFollowedActivityAdapter(List<Schedule> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public FollowedActivityViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_campaign, parent, false);
        return new FollowedActivityViewHolder(v);
    }

    @Override
    public void onBindViewHolder(FollowedActivityViewHolder holder, int position) {
        Schedule item = items.get(position);

        String penyelenggara = context.getResources().getString(R.string.oleh) + " " + item.getActivity().getCampaigner().getName();

        Picasso.get().load(item.getActivity().getPhoto()).fit().into(holder.ivCampaign);

        holder.tvNameActivity.setText(item.getActivity().getNameActivity());
        holder.tvCampaigner.setText(penyelenggara);
    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }

    public class FollowedActivityViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cardview_campaign)
        CardView cardView;

        @BindView(R.id.tv_name_campaign)
        TextView tvNameActivity;

        @BindView(R.id.tv_campaigner_campaign)
        TextView tvCampaigner;

        @BindView(R.id.tv_jumlah_relawan_item_campaign)
        TextView tvJumlahRelawan;

        @BindView(R.id.tv_jumlah_donasi_item_campaign)
        TextView tvJumlahDonasi;

        @BindView(R.id.iv_item_campaign)
        ImageView ivCampaign;

        @BindView(R.id.tv_hari_tanggal_pelaksanaan_campaign)
        TextView tvHariTanggalMulaiPelaksanaan;

        @BindView(R.id.tv_waktu_pelaksanaan_campaign)
        TextView tvWaktuMulaiPelaksanaan;

        FollowedActivityViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}   