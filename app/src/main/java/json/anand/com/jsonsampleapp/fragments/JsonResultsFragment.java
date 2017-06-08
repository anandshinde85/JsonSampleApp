package json.anand.com.jsonsampleapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

import json.anand.com.jsonsampleapp.R;
import json.anand.com.jsonsampleapp.adapters.WordListAdapter;
import json.anand.com.presenter.WordListPresenter;
import wordlist.example.com.commons.mvpviews.WordListView;
import wordlist.example.com.commons.server.entities.Word;

/**
 * A placeholder fragment containing Json results.
 *
 * @author Anand Shinde
 */
public class JsonResultsFragment extends Fragment implements WordListView {
  private WordListPresenter listPresenter;
  private ProgressBar progressBar;
  private List<Word> words;
  private RecyclerView recyclerView;

  public JsonResultsFragment() {
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    listPresenter = new WordListPresenter(this);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_json_results, container, false);
    progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
    recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
    return view;
  }

  @Override
  public void onStart() {
    super.onStart();
    if (words == null) {
      listPresenter.start();
    }
  }

  @Override
  public void onStop() {
    listPresenter.stop();
    super.onStop();
  }

  @Override
  public void populateList(final List<Word> words) {
    getActivity().runOnUiThread(new Runnable() {
      @Override
      public void run() {
        JsonResultsFragment.this.words = words;
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getViewContext());
        recyclerView.setLayoutManager(layoutManager);

        WordListAdapter adapter = new WordListAdapter(getViewContext());
        adapter.setWords(words);
        recyclerView.setAdapter(adapter);
      }
    });
  }

  @Override
  public void showLoading(final boolean show) {
    getActivity().runOnUiThread(new Runnable() {
      @Override
      public void run() {
        if (show) {
          progressBar.setVisibility(View.VISIBLE);
        } else {
          progressBar.setVisibility(View.GONE);
        }
      }
    });
  }

  @Override
  public void showError() {
    getActivity().runOnUiThread(new Runnable() {
      @Override
      public void run() {
        listPresenter.stop();
        showLoading(false);
        Snackbar snackbar =
            Snackbar.make(getView(), "Something went wrong!", Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("Retry", new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            listPresenter.start();
          }
        });
        snackbar.show();
      }
    });
  }

  @Override
  public Context getViewContext() {
    return getActivity();
  }
}
