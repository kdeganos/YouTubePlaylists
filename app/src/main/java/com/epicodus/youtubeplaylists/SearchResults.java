package com.epicodus.youtubeplaylists;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SearchResults extends AppCompatActivity {
    @Bind(R.id.searchTermsTextView) TextView mSearchTermsTextView;
    @Bind(R.id.searchListView) ListView mSearchListView;

    String[] results = {"Video 1", "Video 2", "Video 3", "Video 4", "Video 5", "Video 6", "Video 7", "Video 8", "Video 9"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        Resources res = getResources();
        String searchTerms = String.format(res.getString(R.string.search_terms), intent.getStringExtra("searchTerms"));
        mSearchTermsTextView.setText(searchTerms);

        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, results);
        mSearchListView.setAdapter(adapter);
        mSearchListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(SearchResults.this, results[position], Toast.LENGTH_LONG).show();
            }
        });
    }
}
