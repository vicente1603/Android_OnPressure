package br.com.onpressure.projeto.onpressure.Activities;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.RootMatchers;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.regex.Matcher;

import br.com.onpressure.projeto.onpressure.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)

public class HistoricoActivityTest {

    @Rule
    public ActivityTestRule<HistoricoActivity> mActivityTestRule = new ActivityTestRule<>(HistoricoActivity.class, false, true);

    @Test
    public void testeScrollAteOFinalPA(){

        RecyclerView recyclerView = mActivityTestRule.getActivity().findViewById(R.id.recyclerViewPressao);
        int itemCount = recyclerView.getAdapter().getItemCount();

        Espresso.onView(ViewMatchers.withId(R.id.recyclerViewPressao))
                .inRoot(RootMatchers.withDecorView(
                        Matchers.is(mActivityTestRule.getActivity().getWindow().getDecorView())))
                .perform(RecyclerViewActions.scrollToPosition(itemCount - 1));
    }

    @Test
    public void testeScrollAteOFinalIMC(){

        onView(withId(R.id.btnIMC)).perform(click());

        RecyclerView recyclerView = mActivityTestRule.getActivity().findViewById(R.id.recyclerViewImc);
        int itemCount = recyclerView.getAdapter().getItemCount();

        Espresso.onView(ViewMatchers.withId(R.id.recyclerViewImc))
                .inRoot(RootMatchers.withDecorView(
                        Matchers.is(mActivityTestRule.getActivity().getWindow().getDecorView())))
                .perform(RecyclerViewActions.scrollToPosition(itemCount - 1));
    }

    @Test
    public void testeBotoesRecycleView(){

        onView(withId(R.id.btnIMC)).perform(click());

        onView(withId(R.id.recyclerViewImc)).check(matches(isDisplayed()));

        onView(withId(R.id.btnPA)).perform(click());

        onView(withId(R.id.recyclerViewPressao)).check(matches(isDisplayed()));
    }
}
