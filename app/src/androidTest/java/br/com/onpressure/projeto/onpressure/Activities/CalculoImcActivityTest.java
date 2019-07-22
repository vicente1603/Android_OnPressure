package br.com.onpressure.projeto.onpressure.Activities;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import br.com.onpressure.projeto.onpressure.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.anyOf;

public class CalculoImcActivityTest {

    @Rule
    public ActivityTestRule<CalculoImcActivity> mActivityTestRule = new ActivityTestRule<>(CalculoImcActivity.class, false, true);

    @Test
    public void mostrarCamposDaView() {

        onView(anyOf(withId(R.id.txtPeso))).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(anyOf(withId(R.id.txtAltura))).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(anyOf(withId(R.id.btnCalcular))).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    @Test
    public void preencherECalcularImcComSucesso() {

        Espresso.onView(withId(R.id.txtPeso)).perform(typeText("80"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.txtAltura)).perform(typeText("180"), closeSoftKeyboard());
        onView(withId(R.id.btnCalcular)).perform(click());
    }

}
