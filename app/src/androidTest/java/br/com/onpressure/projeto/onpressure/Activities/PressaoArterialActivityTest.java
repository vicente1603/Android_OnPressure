package br.com.onpressure.projeto.onpressure.Activities;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.SdkSuppress;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.FragmentActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.onpressure.projeto.onpressure.Fragmentos.Ajuda_Frag;
import br.com.onpressure.projeto.onpressure.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
//@SdkSuppress(minSdkVersion = 18)
public class PressaoArterialActivityTest {

    @Rule
    public final ActivityTestRule<PressaoArterialActivity> mActivityTestRule = new ActivityTestRule<>(PressaoArterialActivity.class, false, false);

    private static final Intent MY_ACTIVITY_INTENT = new Intent(InstrumentationRegistry.getTargetContext(), PressaoArterialActivity.class);

    @Before
    public void setup() {

        mActivityTestRule.launchActivity(MY_ACTIVITY_INTENT);
    }

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
    public void testeBotaoAjuda() {

        onView(withId(R.id.btn_ajuda)).perform(click());

//        Ajuda_Frag ajuda_frag = new Ajuda_Frag();
//        mActivityTestRule.getActivity().getFragmentManager().beginTransaction().add(R.layout.dialog_frag_ajuda, ajuda_frag).commit();
//        onView(anyOf(withId(R.id.layoutAjuda))).check(matches((withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE))));
    }

}


