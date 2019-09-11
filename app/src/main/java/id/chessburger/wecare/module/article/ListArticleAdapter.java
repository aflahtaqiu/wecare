package id.chessburger.wecare.module.article;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.chessburger.wecare.R;
import id.chessburger.wecare.model.Activity;

/**
 * Created by aflah on 11/09/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public class ListArticleAdapter extends RecyclerView.Adapter<ListArticleAdapter.ListArticleViewHolder> {
    private final Context context;
    private List<Activity> items;

    public ListArticleAdapter(List<Activity> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public ListArticleViewHolder onCreateViewHolder(ViewGroup parent,
                                                    int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_article, parent, false);
        return new ListArticleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ListArticleViewHolder holder, int position) {
        Activity item = items.get(position);

        Picasso.get().load(item.getPhoto()).into(holder.ivPhoto);

        String penyelenggara = "Oleh " + item.getCampaigner().getName();

        holder.tvName.setText(item.getNameActivity());
        holder.tvPenyelenggara.setText(penyelenggara);
        holder.tvReviewLaporan.setText(item.getReportText());
    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }

    class ListArticleViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_activity_photo)
        ImageView ivPhoto;

        @BindView(R.id.tv_name)
        TextView tvName;

        @BindView(R.id.tv_penyelenggara)
        TextView tvPenyelenggara;

        @BindView(R.id.tv_review_laporan)
        TextView tvReviewLaporan;

        ListArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}   