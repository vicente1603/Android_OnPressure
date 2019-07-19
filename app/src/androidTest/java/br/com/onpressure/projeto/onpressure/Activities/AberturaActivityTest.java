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
public class AberturaActivityTest {

    @Rule
    public ActivityTestRule<CadastroActivity> mActivityTestRule = new ActivityTestRule<>(CadastroActivity.class, false, true);

    @Test
    public void mostrarCamposDaView() {

        onView(anyOf(withId(R.id.txtEmail))).check(matches((withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE))));
        onView(anyOf(withId(R.id.txtNomeCompleto))).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(anyOf(withId(R.id.txtDataNascimento))).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(anyOf(withId(R.id.spnTipoSanguineo))).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(anyOf(withId(R.id.spnGrauHipertensao))).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(anyOf(withId(R.id.txtNumeroTelefone))).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(anyOf(withId(R.id.txtNomeContato))).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(anyOf(withId(R.id.txtDataNascimento))).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(anyOf(withId(R.id.txtTelefoneContato))).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(anyOf(withId(R.id.btnEntrar))).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(anyOf(withId(R.id.radioSex))).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(anyOf(withId(R.id.radioTratamento))).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    @Test
    public void preencherCadastroESalvar() {

        Espresso.onView(withId(R.id.txtEmail)).perform(typeText("vicente@ufs.br"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.txtNomeCompleto)).perform(typeText("Vicente Santiago"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.txtDataNascimento)).perform(typeText("16/03/1995"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.txtOcupacao)).perform(typeText("Programador"), closeSoftKeyboard());

        Espresso.onView(withId(R.id.spnTipoSanguineo)).perform(click());
        onData(allOf(is(instanceOf(String.class)))).atPosition(1).perform(click());

        Espresso.onView(withId(R.id.spnGrauHipertensao)).perform(click());
        onData(allOf(is(instanceOf(String.class)))).atPosition(1).perform(click());

        Espresso.onView(withId(R.id.txtNumeroTelefone)).perform(typeText("7999999"), closeSoftKeyboard());

        onView(withId(R.id.radioMale)).perform(click());
        onView(withId(R.id.radioMale)).check(matches(isChecked()));
        onView(withId(R.id.radioFemale)).check(matches(isNotChecked()));

        onView(withId(R.id.radioSim)).perform(click());
        onView(withId(R.id.radioSim)).check(matches(isChecked()));
        onView(withId(R.id.radioNão)).check(matches(isNotChecked()));

        Espresso.onView(withId(R.id.txtNomeContato)).perform( scrollTo(), typeText("Fatima"), closeSoftKeyboard());;
        Espresso.onView(withId(R.id.txtTelefoneContato)).perform(typeText("799999"), closeSoftKeyboard());

        onView(withId(R.id.btnEntrar)).perform(click());
    }
}
