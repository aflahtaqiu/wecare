package id.chessburger.wecare.module.home;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.chessburger.wecare.R;
import id.chessburger.wecare.model.Activity;
import id.chessburger.wecare.model.enumerations.CommunicationKeys;
import id.chessburger.wecare.model.enumerations.TipeKegiatan;
import id.chessburger.wecare.module.detail_activity_search_volunter.DetailActSearchVolunteerActivity;
import id.chessburger.wecare.module.mainact.MainActivity;
import id.chessburger.wecare.utils.CommunicationUtils;
import id.chessburger.wecare.utils.DateTimeUtils;

/**
 * Created by aflah on 28/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public class ListActivityHomeAdapter extends RecyclerView.Adapter<ListActivityHomeAdapter.ActivityHomeViewHolder> {
    private final Context context;
    private List<Activity> items;

    private static final int ITEM_CARI_RELAWAN = 1;
    private static final int ITEM_CARI_TEMPAT = 2;

    public ListActivityHomeAdapter(List<Activity> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public ActivityHomeViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {

        int idLayout;

        if (viewType == ITEM_CARI_RELAWAN) {
            idLayout = R.layout.item_activity_relawan_home;
        } else {
            idLayout = R.layout.item_activity_tempat_home;
        }

        View v = LayoutInflater.from(parent.getContext())
                .inflate(idLayout, parent, false);
        return new ActivityHomeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ActivityHomeViewHolder holder, int position) {
        Activity item = items.get(position);

        holder.tvNameActivity.setText(item.getNameActivity());
        holder.tvTanggalPelaksanaan.setText(DateTimeUtils.dateToString(item.getStartDate(), DateTimeUtils.FORMAT_DDMMYYYY));
        holder.tvDeskripsiActivity.setText(item.getDescription());
        holder.tvTipeActivity.setText(item.getType().getName());

        if (item.getType().getId() == ITEM_CARI_RELAWAN) {
            holder.itemView.setOnClickListener(new OnActSearchVolunteerClickListener(item));
        } else {

        }
    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (TextUtils.equals(items.get(position).getType().getName(), TipeKegiatan.CARI_RELAWAN.getTipeKegiatan())){
            return ITEM_CARI_RELAWAN;
        } else {
            return ITEM_CARI_TEMPAT;
        }
    }

    public class ActivityHomeViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_foto_item_activity_home)
        ImageView ivFotoActivity;

        @BindView(R.id.tv_name_activity_item_activity_home)
        TextView tvNameActivity;

        @BindView(R.id.tv_tanggal_pelaksanaan_item_activity_home)
        TextView tvTanggalPelaksanaan;

        @BindView(R.id.tv_deskripsi_singkat_item_activity_home)
        TextView tvDeskripsiActivity;

        @BindView(R.id.tv_tipe_activity_item_home)
        TextView tvTipeActivity;

        public ActivityHomeViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private class OnActSearchVolunteerClickListener implements View.OnClickListener {
        private final Activity item;

        public OnActSearchVolunteerClickListener(Activity item) {
            this.item = item;
        }

        @Override
        public void onClick(View view) {
            Bundle bundle = new Bundle();
            bundle.putInt(CommunicationKeys.SELECTED_ACTIVITY.getKey(), item.getId());
            CommunicationUtils.changeActivity((MainActivity) context, DetailActSearchVolunteerActivity.class,
                    bundle, CommunicationKeys.BUNDLE_KEY.getKey(), false);
        }
    }
}