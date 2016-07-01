package com.epicodus.youtubeplaylists;

import android.support.test.rule.ActivityTestRule;

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
public class MemberActivityInstrumentationTest {
    @Rule
    public ActivityTestRule<MemberActivity> activityTestRule = new ActivityTestRule<>(MemberActivity.class);

    @Test
    public void memberNameIsSentToMemberActivity() {
        String searchTerms = "New Search";
        onView(withId(R.id.searchTermsEditText)).perform(typeText(searchTerms));
        onView(withId(R.id.searchButton)).perform(click());
        onView(withId(R.id.searchTermsTextView)).check(matches(withText("\"New Search\"")));
    }
}
