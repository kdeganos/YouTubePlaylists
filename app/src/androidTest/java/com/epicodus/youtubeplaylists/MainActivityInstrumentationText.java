package com.epicodus.youtubeplaylists;

import android.support.test.rule.ActivityTestRule;

import com.epicodus.youtubeplaylists.ui.MainActivity;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Guest on 7/1/16.
 */
public class MainActivityInstrumentationText {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void memberNameIsSentToMemberActivity() {
        String memberName = "New User";
        onView(withId(R.id.memberNameEditText)).perform(typeText(memberName));
        onView(withId(R.id.signInButton)).perform(click());
        onView(withId(R.id.memberNameTextView)).check(matches(withText("New User")));
    }
}
