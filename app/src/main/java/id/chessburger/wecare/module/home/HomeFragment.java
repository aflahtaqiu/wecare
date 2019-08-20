package id.chessburger.wecare.module.home;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.chessburger.wecare.R;
import id.chessburger.wecare.base.BaseFragment;

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

    public static HomeFragment homeFragment;

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

        return view;
    }

    private void showBanners() {
        int [] banners = {R.mipmap.banner_1, R.mipmap.banner_2, R.mipmap.banner_3, R.mipmap.banner_4};
        carouselBanner.setPageCount(banners.length);
        carouselBanner.setImageListener(new BannersImageListener(banners));
    }

    @Override
    public void onStart() {
        super.onStart();
        customToolbar();
    }

    private void customToolbar() {
        getActivity().setTitle(getResources().getString(R.string.app_name));
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.home_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
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

    private static class BannersImageListener implements ImageListener {
        private final int[] banners;

        public BannersImageListener(int[] banners) {
            this.banners = banners;
        }

        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(banners[position]);
        }
    }
}
