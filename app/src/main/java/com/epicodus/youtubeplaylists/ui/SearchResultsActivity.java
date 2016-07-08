package com.epicodus.youtubeplaylists.ui;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.youtubeplaylists.R;
import com.epicodus.youtubeplaylists.models.Video;
import com.epicodus.youtubeplaylists.services.SearchService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SearchResultsActivity extends AppCompatActivity {
    public static final String TAG = SearchResultsActivity.class.getSimpleName();
    @Bind(R.id.searchTermsTextView) TextView mSearchTermsTextView;
    @Bind(R.id.searchListView) ListView mSearchListView;

    public ArrayList<Video> mVideos = new ArrayList<>();

    String[] results = {"Video 1", "Video 2", "Video 3", "Video 4", "Video 5", "Video 6", "Video 7", "Video 8", "Video 9"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        Resources res = getResources();
        String searchTerms = intent.getStringExtra("searchTerms");
        mSearchTermsTextView.setText(String.format(res.getString(R.string.search_terms), searchTerms));

        getVideos(searchTerms);

    }

    private void getVideos(String searchTerms) {
        final SearchService searchService = new SearchService();

        searchService.findVideos(searchTerms, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
        });

        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, results);
        mSearchListView.setAdapter(adapter);
        mSearchListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(SearchResultsActivity.this, results[position], Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                mVideos = searchService.processResults(response);

                SearchResultsActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        mAdapter = new MovieListAdapter(getApplicationContext(), mMovies);
                        Log.d(TAG, "run: " + String.valueOf(mAdapter));
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(MovieActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                    }
                });
            }
        });
    }
}
