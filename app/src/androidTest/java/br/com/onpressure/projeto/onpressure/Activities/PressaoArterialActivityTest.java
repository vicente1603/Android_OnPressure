package br.com.onpressure.projeto.onpressure.Activities;


import android.support.test.espresso.Espresso;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.onpressure.projeto.onpressure.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
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

//    @Test
//    public void checarVazio(){
//        onView(withId(R.id.txtPressaoSistolica)).check(matches(hasErrorText(("Não pode ser vazio"))));
//        onView(withId(R.id.txtPressaoDiastolica)).check(matches(hasErrorText(("Não pode ser vazio"))));
//        onView(withId(R.id.txtFrequenciaCardiaca)).check(matches(hasErrorText(("Não pode ser vazio"))));
//
//        onView(withId(R.id.btnRegistrar)).perform(click());
//
//    }

}


