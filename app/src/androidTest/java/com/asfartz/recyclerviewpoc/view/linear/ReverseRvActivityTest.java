package com.asfartz.recyclerviewpoc.view.linear;

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;

import com.asfartz.recyclerviewpoc.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4ClassRunner.class)
public class ReverseRvActivityTest {

    @Rule
    public ActivityTestRule<ReverseRvActivity> rule = new ActivityTestRule<>(ReverseRvActivity.class);

    @Test
    public void test_isTitleVisible() {
        onView(withId(R.id.reverse_rv_title)).check(matches(isDisplayed()));

        onView(withId(R.id.reverse_rv_add_button)).check(matches(isDisplayed()));
        onView(withId(R.id.reverse_rv_add_button)).check(matches(isClickable()));

    }
}