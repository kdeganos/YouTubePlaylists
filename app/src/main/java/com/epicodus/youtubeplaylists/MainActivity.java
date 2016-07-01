package com.epicodus.youtubeplaylists;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.signInButton) Button mSignInButton;
    @Bind(R.id.memberNameEditText) EditText mMemberName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mSignInButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mSignInButton) {
            String memberName = mMemberName.getText().toString();
            Intent intent = new Intent(MainActivity.this, MemberActivity.class);
            intent.putExtra("memberName", memberName);
            startActivity(intent);
        }
    }
}
