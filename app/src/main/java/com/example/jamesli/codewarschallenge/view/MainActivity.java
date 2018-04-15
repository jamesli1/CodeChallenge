package com.example.jamesli.codewarschallenge.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.jamesli.codewarschallenge.MyApplication;
import com.example.jamesli.codewarschallenge.R;
import com.example.jamesli.codewarschallenge.error.ErrorHandler;
import com.example.jamesli.codewarschallenge.model.Constants;
import com.example.jamesli.codewarschallenge.model.User;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainPresenter.View{

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.loading_progress_bar)
    ProgressBar mProgressBar;

    private SearchView mSearchView;
    private UserAdapter mAdapter;

    @Inject
    MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((MyApplication) getApplication()).getApplicationComponent().inject(this);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        hideLoading();
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new UserAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnSelectedItemChangeListener(userName -> {
            Intent intent = new Intent(this, ChallengesActivity.class);
            intent.putExtra(Constants.STRING_USERNAME, userName);
            startActivity(intent);

        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.startPresenting(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        mPresenter.stopPresenting();
    }

    @Override
    public void onResume() {
        super.onResume();
        mAdapter.loadLocalData();
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        final MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        mSearchView = (SearchView) searchItem.getActionView();
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String username) {
                showLoading();
                mPresenter.getUser(username);
                return false;
            }
            @Override
            public boolean onQueryTextChange(final String newText) {
                return false;
            }
        });
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_reorder:
                mAdapter.reOrderByRank();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void handleResponse(User user) {
        hideLoading();
        mAdapter.addData(user);
    }

    public void handleError(Throwable throwable) {
        hideLoading();
        String message = getString(ErrorHandler.getMessageFor(throwable));
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    public void hideLoading() {
        mProgressBar.setVisibility(View.GONE);
    }
}
