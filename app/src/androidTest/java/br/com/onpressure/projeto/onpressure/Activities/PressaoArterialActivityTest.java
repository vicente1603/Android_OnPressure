package br.com.onpressure.projeto.onpressure.Activities;


import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.espresso.proto.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.onpressure.projeto.onpressure.R;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isNotChecked;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;

@LargeTest
@RunWith(AndroidJUnit4.class)

public class PressaoArterialActivityTest {

    @Rule
    public ActivityTestRule<PressaoArterialActivity> mActivityTestRule = new ActivityTestRule<>(PressaoArterialActivity.class, false, true);


    @Test
    public void mostrarCamposDaView() {

        onView(anyOf(withId(R.id.txtPressaoSistolica))).check(matches((withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE))));
        onView(anyOf(withId(R.id.txtPressaoDiastolica))).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(anyOf(withId(R.id.txtFrequenciaCardiaca))).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    @Test
    public void preencherPressaoERegistrarComSucesso() {

        Espresso.onView(withId(R.id.txtPressaoSistolica)).perform(typeText("125"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.txtPressaoDiastolica)).perform(typeText("85"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.txtFrequenciaCardiaca)).perform(typeText("80"), closeSoftKeyboard());

        onView(withId(R.id.btnRegistrar)).perform(click());

    }

    @Test
    public void naoPermitirCamposVazios(){
//        onView(withId(R.id.txtPressaoSistolica)).check(matches(not(withText(""))));
//        onView(withId(R.id.txtPressaoDiastolica)).check(matches(not(withText(""))));
//        onView(withId(R.id.txtFrequenciaCardiaca)).check(matches(not(withText(""))));

    }
}


