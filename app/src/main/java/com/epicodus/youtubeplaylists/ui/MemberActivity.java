package com.epicodus.youtubeplaylists.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.epicodus.youtubeplaylists.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MemberActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.searchTermsEditText) EditText mSearchTermsEditText;
    @Bind(R.id.searchButton) Button mSearchButton;
    @Bind(R.id.toolbar_top)
    Toolbar mToolbarTop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String memberName = intent.getStringExtra("memberName");

        TextView mToolTitle = (TextView) mToolbarTop.findViewById(R.id.toolbar_title);
        mToolTitle.setText(memberName);
        setSupportActionBar(mToolbarTop);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mSearchButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mSearchButton) {
            String searchTerms = mSearchTermsEditText.getText().toString();
            Intent intent = new Intent(MemberActivity.this, SearchActivity.class);
            intent.putExtra("searchTerms", searchTerms);
            startActivity(intent);
        }
    }
}
