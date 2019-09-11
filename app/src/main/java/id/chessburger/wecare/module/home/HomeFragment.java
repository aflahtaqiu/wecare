package id.chessburger.wecare.module.home;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;
import id.chessburger.wecare.R;
import id.chessburger.wecare.base.BaseFragment;
import id.chessburger.wecare.model.Activity;
import id.chessburger.wecare.model.ActivityCategory;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment implements IHomeView {

    @BindView(R.id.cv_banner)
    CarouselView carouselBanner;

    @BindView(R.id.iv_sort_activities_home)
    ImageView ivSort;

    @BindView(R.id.iv_filter_activities_home)
    ImageView ivFilter;

    @BindView(R.id.recyclerview_activities_home)
    RecyclerView recyclerViewActivities;

    @BindView(R.id.rv_categories)
    RecyclerView recyclerViewCategories;

    @BindView(R.id.et_search_activity_home)
    EditText etSearch;

    public static HomeFragment homeFragment;

    private List<Activity> activityList = new ArrayList<>();
    private List<ActivityCategory> categories = new ArrayList<>();


    private ListActivityHomeAdapter adapter;
    private ListCategoryAdapter categoryAdapter;

    private HomePresenter presenter;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment getInstance() {
        if (homeFragment == null) {
            homeFragment = new HomeFragment();
        }
        return homeFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        presenter = new HomePresenter(this);

        showBanners();
        setRecyclerView();

        presenter.getAllActivities();
        presenter.getCategories();

        return view;
    }

    private void showBanners() {
        int [] banners = {R.mipmap.banner_1, R.mipmap.banner_2, R.mipmap.banner_3, R.mipmap.banner_4};
        carouselBanner.setPageCount(banners.length);
        carouselBanner.setImageListener(new BannersImageListener(banners));
    }

    private void setRecyclerView() {
        adapter = new ListActivityHomeAdapter(this.activityList, getContext());
        categoryAdapter = new ListCategoryAdapter(this.categories, getContext(), presenter);

        recyclerViewActivities.setAdapter(adapter);
        recyclerViewCategories.setAdapter(categoryAdapter);

        recyclerViewActivities.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerViewCategories.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));
    }

    @Override
    public void onStart() {
        super.onStart();
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

    @OnTextChanged(R.id.et_search_activity_home)
    public void onEtSearchChangeListener (CharSequence text) {
        presenter.searchActivitiesByName(text.toString());
    }

    @Override
    public void showListActivities(List<Activity> activities) {
        this.activityList.clear();
        this.activityList.addAll(activities);

        adapter.notifyDataSetChanged();
    }

    @Override
    public void showCategories(List<ActivityCategory> activityCategories) {
        this.categories.clear();
        this.categories.add(ActivityCategory.builder().id(0).name("Semua").build());
        this.categories.addAll(activityCategories);

        categoryAdapter.notifyDataSetChanged();
    }

    private static class BannersImageListener implements ImageListener {
        private final int[] banners;

        BannersImageListener(int[] banners) {
            this.banners = banners;
        }

        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(banners[position]);
        }
    }
}
