package com.epicodus.youtubeplaylists;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MemberActivity extends AppCompatActivity {
    @Bind(R.id.memberNameTextView) TextView mMemberNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String memberName = intent.getStringExtra("memberName");
        mMemberNameTextView.setText(memberName);
    }
}
