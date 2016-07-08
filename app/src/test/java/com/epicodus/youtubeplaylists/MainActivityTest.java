package com.epicodus.youtubeplaylists;

import android.os.Build;
import android.widget.Button;

import com.epicodus.youtubeplaylists.ui.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertTrue;

/**
 * Created by Guest on 7/1/16.
 */

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
public class MainActivityTest {
    private MainActivity activity;

    @Before
    public void setup() {
        activity = Robolectric.setupActivity(MainActivity.class);
    }

    @Test
    public void validateButtonText() {
        Button signInButton = (Button) activity.findViewById(R.id.signInButton);
        assertTrue("Sign In".equals(signInButton.getText().toString()));
    }
}
