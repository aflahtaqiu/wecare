package id.chessburger.wecare.module.campaign_sudah_terlaksana;

import android.content.Context;
import android.os.Bundle;
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
import id.chessburger.wecare.model.enumerations.CommunicationKeys;
import id.chessburger.wecare.module.detail_campaigned_activity.done_activity.DoneCampaignedActivity;
import id.chessburger.wecare.module.mainact.MainActivity;
import id.chessburger.wecare.utils.CommunicationUtils;

/**
 * Created by aflah on 08/09/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public class ListCampaignActivityAdapter extends RecyclerView.Adapter<ListCampaignActivityAdapter.CampaignActivityViewHolder> {

    private final Context context;
    private List<Activity> items;

    ListCampaignActivityAdapter(List<Activity> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public CampaignActivityViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_campaign, parent, false);
        return new CampaignActivityViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CampaignActivityViewHolder holder, int position) {
        Activity item = items.get(position);

        String penyelenggara = context.getResources().getString(R.string.oleh) + " " + item.getCampaigner().getName();

        Picasso.get().load(item.getPhoto()).fit().into(holder.ivCampaign);

        holder.tvNameActivity.setText(item.getNameActivity());
        holder.tvCampaigner.setText(penyelenggara);

        holder.itemView.setOnClickListener(new OnItemClickListener(item));
    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }

    public class CampaignActivityViewHolder extends RecyclerView.ViewHolder {

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

        CampaignActivityViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

    private class OnItemClickListener implements View.OnClickListener {
        private final Activity item;

        public OnItemClickListener(Activity item) {
            this.item = item;
        }

        @Override
        public void onClick(View view) {
            Bundle bundle = new Bundle();
            bundle.putInt(CommunicationKeys.SELECTED_TYPE.getKey(), item.getTypeId());
            CommunicationUtils.changeActivity((MainActivity) context, DoneCampaignedActivity.class,
                    bundle,CommunicationKeys.BUNDLE_KEY.getKey(), false);
        }
    }
}   