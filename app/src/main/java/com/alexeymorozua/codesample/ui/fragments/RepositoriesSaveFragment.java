package com.alexeymorozua.codesample.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import com.alexeymorozua.codesample.R;
import com.alexeymorozua.codesample.mvp.data.model.vo.RepositoryDetail;
import com.alexeymorozua.codesample.mvp.presenters.RepositoriesSavePresenter;
import com.alexeymorozua.codesample.mvp.views.RepositoriesSaveView;
import com.alexeymorozua.codesample.ui.adapters.RepositoriesSaveAdapter;
import com.alexeymorozua.codesample.util.ItemClickSupport;
import com.arellomobile.mvp.presenter.InjectPresenter;
import java.util.List;

/**
 * Created by john on 12.12.2016.
 */

public class RepositoriesSaveFragment extends BaseFragment implements RepositoriesSaveView {

  @InjectPresenter RepositoriesSavePresenter mRepositoriesSavePresenter;

  @BindView(R.id.text_fragment_save_repositories) TextView mNoRepositoriesSaveTextView;
  @BindView(R.id.recycler_view_fragment_save_repositories) RecyclerView
      mRepositoriesSaveRecyclerView;

  private RepositoriesSaveAdapter mRepositoriesSaveAdapter;

  public RepositoriesSaveFragment() {
    super(R.layout.fragment_reposotories_save);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
    mRepositoriesSaveRecyclerView.setLayoutManager(mLayoutManager);
    mRepositoriesSaveRecyclerView.setItemAnimator(new DefaultItemAnimator());

    mRepositoriesSaveAdapter = new RepositoriesSaveAdapter();
    mRepositoriesSaveRecyclerView.setAdapter(mRepositoriesSaveAdapter);

    ItemClickSupport.addTo(mRepositoriesSaveRecyclerView)
        .setOnItemClickListener(
            (recyclerView, position, v) -> mRepositoriesSavePresenter.showRepositoryDetail(
                mRepositoriesSaveAdapter.getItem(position)));
  }

  @Override public void addRepository(RepositoryDetail repositoryDetail) {
    mNoRepositoriesSaveTextView.setVisibility(View.GONE);
    mRepositoriesSaveAdapter.addRepository(repositoryDetail);
  }

  @Override public void deleteRepository(RepositoryDetail repositoryDetail) {
    mRepositoriesSaveAdapter.deleteRepository(repositoryDetail);
    if (mRepositoriesSaveAdapter.getItemCount() == 0) {
      mNoRepositoriesSaveTextView.setVisibility(View.VISIBLE);
    }
  }

  @Override public void addAllRepositories(List<RepositoryDetail> repositories) {
    if (!repositories.isEmpty()) {
      mNoRepositoriesSaveTextView.setVisibility(View.GONE);
      mRepositoriesSaveAdapter.addRepositories(repositories);
    }
  }

  @Override public void deleteAllSaveRepositories() {
    mRepositoriesSaveAdapter.deleteAllRepositories();
    if (mRepositoriesSaveAdapter.getItemCount() == 0) {
      mNoRepositoriesSaveTextView.setVisibility(View.VISIBLE);
    }
  }
}
