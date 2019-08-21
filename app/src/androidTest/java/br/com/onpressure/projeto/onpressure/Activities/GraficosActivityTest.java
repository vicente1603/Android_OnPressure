package br.com.onpressure.projeto.onpressure.Activities;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.onpressure.projeto.onpressure.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class GraficosActivityTest {

    @Rule
    public ActivityTestRule<GraficosActivity> mActivityTestRule = new ActivityTestRule<>(GraficosActivity.class, false, true);

    @Test
    public void testeBotoesGraficos(){

        onView(withId(R.id.btnPAS)).perform(click());
        onView(withId(R.id.layout_graphPAS)).check(matches(isDisplayed()));

        onView(withId(R.id.btnPAD)).perform(click());
        onView(withId(R.id.layout_graphPAD)).check(matches(isDisplayed()));

        onView(withId(R.id.btnPASePAD)).perform(click());
        onView(withId(R.id.layout_graphPADePAD)).check(matches(isDisplayed()));
    }

}
