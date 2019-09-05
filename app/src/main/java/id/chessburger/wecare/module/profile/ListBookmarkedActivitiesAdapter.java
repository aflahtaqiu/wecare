package id.chessburger.wecare.module.profile;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
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
import id.chessburger.wecare.module.detail_activity_search_place.DetailActSearchPlaceActivity;
import id.chessburger.wecare.module.detail_activity_search_volunter.DetailActSearchVolunterActivity;
import id.chessburger.wecare.module.mainact.MainActivity;
import id.chessburger.wecare.utils.CommunicationUtils;
import id.chessburger.wecare.utils.DateTimeUtils;

/**
 * Created by aflah on 28/08/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public class ListBookmarkedActivitiesAdapter extends RecyclerView.Adapter<ListBookmarkedActivitiesAdapter.BookmarkedActivitiesViewHolder> {
    private final Context context;
    private List<Activity> items;

    private static final int ITEM_CARI_RELAWAN = 1;
    private static final int ITEM_CARI_TEMPAT = 2;

    ListBookmarkedActivitiesAdapter(List<Activity> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public BookmarkedActivitiesViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {

        int idLayout;

        if (viewType == ITEM_CARI_RELAWAN) {
            idLayout = R.layout.item_activity_relawan_home;
        } else {
            idLayout = R.layout.item_activity_tempat_home;
        }

        View v = LayoutInflater.from(parent.getContext())
                .inflate(idLayout, parent, false);
        return new BookmarkedActivitiesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(BookmarkedActivitiesViewHolder holder, int position) {
        Activity item = items.get(position);

        Log.e("lele", item.toString());

        holder.tvNameActivity.setText(item.getNameActivity());
        holder.tvTanggalPelaksanaan.setText(DateTimeUtils.dateToString(item.getStartDate(), DateTimeUtils.FORMAT_DDMMYYYY));
        holder.tvDeskripsiActivity.setText(item.getDescription());

        if (item.getTypeId() == ITEM_CARI_RELAWAN) {
            holder.tvTipeActivity.setText(TipeKegiatan.CARI_RELAWAN.getTipeKegiatan());
            holder.itemView.setOnClickListener(new OnActSearchVolunteerClickListener(item));
        } else {
            holder.tvTipeActivity.setText(TipeKegiatan.CARTI_LOKASI.getTipeKegiatan());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putInt(CommunicationKeys.SELECTED_ACTIVITY.getKey(), item.getId());
                    CommunicationUtils.changeActivity((MainActivity) context, DetailActSearchPlaceActivity.class,
                            bundle, CommunicationKeys.BUNDLE_KEY.getKey(), false);
                }
            });
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
        if (items.get(position).getTypeId() == ITEM_CARI_RELAWAN){
            return ITEM_CARI_RELAWAN;
        } else {
            return ITEM_CARI_TEMPAT;
        }
    }

    public class BookmarkedActivitiesViewHolder extends RecyclerView.ViewHolder {

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

        public BookmarkedActivitiesViewHolder(@NonNull View itemView) {
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
            CommunicationUtils.changeActivity((MainActivity) context, DetailActSearchVolunterActivity.class,
                    bundle, CommunicationKeys.BUNDLE_KEY.getKey(), false);
        }
    }
}