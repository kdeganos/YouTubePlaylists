package com.epicodus.youtubeplaylists.ui;

import android.app.ActionBar;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.epicodus.youtubeplaylists.R;
import com.epicodus.youtubeplaylists.adapters.VideoListAdapter;
import com.epicodus.youtubeplaylists.models.VideoObj;
import com.epicodus.youtubeplaylists.services.SearchService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SearchActivity extends AppCompatActivity {
    public static final String TAG = SearchActivity.class.getSimpleName();
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    @Bind(R.id.toolbar_top) Toolbar mToolbarTop;

    private VideoListAdapter mAdapter;

    public ArrayList<VideoObj> mVideos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String searchTerms = intent.getStringExtra("searchTerms");

        TextView mToolTitle = (TextView) mToolbarTop.findViewById(R.id.toolbar_title);
        mToolTitle.setText(searchTerms);
        setSupportActionBar(mToolbarTop);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        getVideos(searchTerms);

    }

    private void getVideos(String searchTerms) {
        final SearchService searchService = new SearchService();

        searchService.findVideos(searchTerms, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mVideos = searchService.processResults(response);

                SearchActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        mAdapter = new VideoListAdapter(getApplicationContext(), mVideos);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(SearchActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                    }
                });
            }
        });
    }
}
