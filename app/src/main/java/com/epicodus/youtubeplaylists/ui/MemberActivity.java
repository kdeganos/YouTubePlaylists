package com.epicodus.youtubeplaylists.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.epicodus.youtubeplaylists.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MemberActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.memberNameTextView) TextView mMemberNameTextView;
    @Bind(R.id.searchTermsEditText) EditText mSearchTermsEditText;
    @Bind(R.id.searchButton) Button mSearchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String memberName = intent.getStringExtra("memberName");
        mMemberNameTextView.setText(memberName);

        mSearchButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mSearchButton) {
            String searchTerms = mSearchTermsEditText.getText().toString();
            Intent intent = new Intent(MemberActivity.this, SearchResultsActivity.class);
            intent.putExtra("searchTerms", searchTerms);
            startActivity(intent);
        }
    }
}
