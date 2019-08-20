package id.chessburger.wecare.module.article;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import id.chessburger.wecare.R;
import id.chessburger.wecare.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticleFragment extends BaseFragment implements IArticleView {

    private static ArticleFragment articleFragment;

    private ArticlePresenter presenter;

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
        presenter = new ArticlePresenter(this);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        customToolbar();
    }

    private void customToolbar() {
        getActivity().setTitle(getResources().getString(R.string.baca_artikel_yuk));
        setHasOptionsMenu(true);
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
}
