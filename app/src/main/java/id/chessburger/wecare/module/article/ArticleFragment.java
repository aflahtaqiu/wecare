package id.chessburger.wecare.module.article;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.chessburger.wecare.R;
import id.chessburger.wecare.base.BaseFragment;
import id.chessburger.wecare.model.Activity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticleFragment extends BaseFragment implements IArticleView {

    @BindView(R.id.recyclerview_kegiatan_lain_schedule)
    RecyclerView recyclerViewArtikel;

    private static ArticleFragment articleFragment;

    private ArticlePresenter presenter;
    private ListArticleAdapter adapter;

    private List<Activity> activityList = new ArrayList<>();

    public ArticleFragment() {
        // Required empty public constructor
    }

    public static ArticleFragment getInstance() {
        if (articleFragment == null) {
            articleFragment = new ArticleFragment();
        }
        return articleFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article, container, false);
        ButterKnife.bind(this, view);


        presenter = new ArticlePresenter(this);
        presenter.getDoneActivities();

        setRecyclerView();
        return view;
    }

    private void setRecyclerView() {
        adapter = new ListArticleAdapter(this.activityList, getContext());

        recyclerViewArtikel.setAdapter(adapter);
        recyclerViewArtikel.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.article_menu, menu);
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

    @Override
    public void showListActivities(List<Activity> activities) {
        this.activityList.clear();
        this.activityList.addAll(activities);

        adapter.notifyDataSetChanged();
    }
}
