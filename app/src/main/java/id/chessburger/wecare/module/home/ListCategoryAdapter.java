package id.chessburger.wecare.module.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.chessburger.wecare.R;
import id.chessburger.wecare.model.ActivityCategory;

/**
 * Created by aflah on 11/09/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public class ListCategoryAdapter extends RecyclerView.Adapter<ListCategoryAdapter.ListCategoryViewHolder> {
    private final Context context;
    private List<ActivityCategory> items;
    private HomePresenter presenter;

    private int selectedCategory = 0;

    ListCategoryAdapter(List<ActivityCategory> items, Context context, HomePresenter presenter) {
        this.items = items;
        this.context = context;
        this.presenter = presenter;
    }

    @Override
    public ListCategoryViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category, parent, false);
        return new ListCategoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ListCategoryViewHolder holder, int position) {
        ActivityCategory item = items.get(position);
        holder.tvNameCategory.setText(item.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedCategory = position;
                presenter.getActivitiesByCategory(item.getId());
                notifyDataSetChanged();
            }
        });

        if (selectedCategory == position) {
            holder.itemView.setBackground(context.getResources().getDrawable(R.drawable.btn_round_red_solid));
        } else {
            holder.itemView.setBackground(context.getResources().getDrawable(R.drawable.btn_round_white_border_grey));
        }
    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }

    class ListCategoryViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_category_name)
        TextView tvNameCategory;

        ListCategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}   