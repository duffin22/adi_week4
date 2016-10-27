package com.genassembly.dotdashdot.animaljunitapp;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.widget.ListView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.StringStartsWith.startsWith;
import static org.hamcrest.object.HasToString.hasToString;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

    @Test
    public void helloWorldDisplays() {
        onView(withText("Hello world!")).check(matches(isDisplayed()));
    }

    @Test
    public void greetingOnClickWorks() {
        onView(withId(R.id.greeting))
            .perform(click())
            .check(matches(withText("Goodbye world!")));
    }

    @Test
    public void listViewOnClickWorks() {
        onData(hasToString(startsWith("Snake")))
                .inAdapterView(withId(R.id.animal_list_view)).atPosition(0)
                .perform(click());

        onView(withId(R.id.greeting)).check(matches(not(withText("Hello world!"))));

    }


}
