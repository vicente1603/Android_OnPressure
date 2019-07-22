package br.com.onpressure.projeto.onpressure.Activities;


import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.onpressure.projeto.onpressure.R;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AberturaActivityTestRecorder {

    @Rule
    public ActivityTestRule<AberturaActivity> mActivityTestRule = new ActivityTestRule<>(AberturaActivity.class);

    @Test
    public void aberturaActivityTestRecorder() {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html


        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.txtEmail),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.support.design.widget.TextInputLayout")),
                                        0),
                                0),
                        isDisplayed()));
        appCompatEditText.perform(click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.txtEmail),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.support.design.widget.TextInputLayout")),
                                        0),
                                0),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("vicente@dcomp.com"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.txtNomeCompleto),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.support.design.widget.TextInputLayout")),
                                        0),
                                0),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText("Vicente"), closeSoftKeyboard());

        ViewInteraction maskedEditText15 = onView(
                allOf(withId(R.id.txtDataNascimento), withText("1"),
                        isDisplayed()));
        maskedEditText15.perform(closeSoftKeyboard());

        ViewInteraction maskedEditText16 = onView(
                allOf(withId(R.id.txtDataNascimento), withText("16"),
                        isDisplayed()));
        maskedEditText16.perform(closeSoftKeyboard());

        ViewInteraction maskedEditText17 = onView(
                allOf(withId(R.id.txtDataNascimento), withText("16/0"),
                        isDisplayed()));
        maskedEditText17.perform(closeSoftKeyboard());

        ViewInteraction maskedEditText18 = onView(
                allOf(withId(R.id.txtDataNascimento), withText("16/03"),
                        isDisplayed()));
        maskedEditText18.perform(closeSoftKeyboard());

        ViewInteraction maskedEditText19 = onView(
                allOf(withId(R.id.txtDataNascimento), withText("16/03/1"),
                        isDisplayed()));
        maskedEditText19.perform(closeSoftKeyboard());

        ViewInteraction maskedEditText20 = onView(
                allOf(withId(R.id.txtDataNascimento), withText("16/03/19"),
                        isDisplayed()));
        maskedEditText20.perform(closeSoftKeyboard());

        ViewInteraction maskedEditTex21 = onView(
                allOf(withId(R.id.txtDataNascimento), withText("16/03/199"),
                        isDisplayed()));
        maskedEditTex21.perform(closeSoftKeyboard());

        ViewInteraction maskedEditText22 = onView(
                allOf(withId(R.id.txtDataNascimento), withText("16/03/1995"),
                        isDisplayed()));
        maskedEditText22.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.txtOcupacao),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.support.design.widget.TextInputLayout")),
                                        0),
                                0),
                        isDisplayed()));
        appCompatEditText4.perform(replaceText("Programador"), closeSoftKeyboard());

        ViewInteraction appCompatSpinner = onView(
                allOf(withId(R.id.spnTipoSanguineo),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.linearLayoutBodyLogin),
                                        5),
                                1)));
        appCompatSpinner.perform(scrollTo(), click());

        DataInteraction appCompatTextView = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(5);
        appCompatTextView.perform(click());

        ViewInteraction appCompatSpinner2 = onView(
                allOf(withId(R.id.spnGrauHipertensao),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.linearLayoutBodyLogin),
                                        7),
                                1)));
        appCompatSpinner2.perform(scrollTo(), click());

        DataInteraction appCompatTextView2 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(5);
        appCompatTextView2.perform(click());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.txtNumeroTelefone),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.support.design.widget.TextInputLayout")),
                                        0),
                                0),
                        isDisplayed()));
        appCompatEditText5.perform(replaceText("7999999999"), closeSoftKeyboard());

        ViewInteraction appCompatRadioButton = onView(
                allOf(withId(R.id.radioSim), withText("Sim"),
                        childAtPosition(
                                allOf(withId(R.id.radioTratamento),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                0),
                        isDisplayed()));
        appCompatRadioButton.perform(click());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.btnEntrar), withText("Cadastrar"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                1)));
        appCompatButton.perform(scrollTo(), click());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
