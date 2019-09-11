package id.chessburger.wecare.module.detail_campaigned_activity.undone_activity.hasil_kampanye_volunteer;

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
import de.hdodenhof.circleimageview.CircleImageView;
import id.chessburger.wecare.R;
import id.chessburger.wecare.model.FollowedActivity;

/**
 * Created by aflah on 11/09/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */

public class ListVolunteerAdapter extends RecyclerView.Adapter<ListVolunteerAdapter.ListVolunteerViewHolder> {

    private UnDoneCampaignHasilKampanyePresenter presenter;
    private final Context context;
    private List<FollowedActivity> items;

    ListVolunteerAdapter(List<FollowedActivity> items, Context context,
                         UnDoneCampaignHasilKampanyePresenter presenter) {
        this.items = items;
        this.context = context;
        this.presenter = presenter;
    }

    @Override
    public ListVolunteerViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_absence_volunteer, parent, false);
        return new ListVolunteerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ListVolunteerViewHolder holder, int position) {
        FollowedActivity item = items.get(position);

        Picasso.get().load(item.getUser().getPhoto());
//                .error(R.mipmap.img_no_picture)
//                .into(holder.ivPicture);

        holder.tvNamaUser.setText(item.getUser().getName());

        holder.ivDecline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                items.remove(position);

                notifyItemRemoved(position);
                notifyItemRangeChanged(position, items.size());
            }
        });

        holder.ivChecked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.absenceVolunteer( item.getIdUser(), item.getIdActivity());
            }
        });
    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }

    class ListVolunteerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_picture_user)
        CircleImageView ivPicture;

        @BindView(R.id.tv_nama_user)
        TextView tvNamaUser;

        @BindView(R.id.iv_decline)
        ImageView ivDecline;

        @BindView(R.id.iv_checked)
        ImageView ivChecked;

        ListVolunteerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}   