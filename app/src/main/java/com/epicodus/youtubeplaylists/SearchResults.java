package com.epicodus.youtubeplaylists;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SearchResults extends AppCompatActivity {
    @Bind(R.id.searchTermsTextView) TextView mSearchTermsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String searchTerms = intent.getStringExtra("searchTerms");
        mSearchTermsTextView.setText(searchTerms);

    }
}
