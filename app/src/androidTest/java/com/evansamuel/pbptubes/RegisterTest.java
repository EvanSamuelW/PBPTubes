package com.evansamuel.pbptubes;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class RegisterTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void registerTest() {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.btnRegister1), withText("Register"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                1),
                        isDisplayed()));
        materialButton.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.btnRegisterUser), withText("Register"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        7),
                                0),
                        isDisplayed()));
        materialButton2.perform(click());

        ViewInteraction textInputEditText = onView(
                allOf(withId(R.id.edtName),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.edtNameLayout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText.perform(click());

        ViewInteraction textInputEditText2 = onView(
                allOf(withId(R.id.edtName),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.edtNameLayout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText2.perform(replaceText("William"), closeSoftKeyboard());


        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.btnRegisterUser), withText("Register"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        7),
                                0),
                        isDisplayed()));
        materialButton3.perform(click());

        ViewInteraction textInputEditText3 = onView(
                allOf(withId(R.id.edtAddress),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.edtAddressLayout),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText3.perform(replaceText("jln cilik riwut"), closeSoftKeyboard());


        ViewInteraction materialButton4 = onView(
                allOf(withId(R.id.btnRegisterUser), withText("Register"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        7),
                                0),
                        isDisplayed()));
        materialButton4.perform(click());

        ViewInteraction textInputEditText4 = onView(
                allOf(withId(R.id.edtEmailRegister),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.edtEmailLayout),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText4.perform(replaceText("onsin200"), closeSoftKeyboard());


        ViewInteraction materialButton5 = onView(
                allOf(withId(R.id.btnRegisterUser), withText("Register"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        7),
                                0),
                        isDisplayed()));
        materialButton5.perform(click());

        ViewInteraction textInputEditText5 = onView(
                allOf(withId(R.id.edtHand),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.edtTelponLayout),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText5.perform(replaceText("081346800553"), closeSoftKeyboard());

        ViewInteraction materialButton6 = onView(
                allOf(withId(R.id.btnRegisterUser), withText("Register"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        7),
                                0),
                        isDisplayed()));
        materialButton6.perform(click());

        ViewInteraction textInputEditText6 = onView(
                allOf(withId(R.id.edtUsername),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.edtUsernameLayout),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText6.perform(replaceText("hehehe"), closeSoftKeyboard());


        ViewInteraction materialButton7 = onView(
                allOf(withId(R.id.btnRegisterUser), withText("Register"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        7),
                                0),
                        isDisplayed()));
        materialButton7.perform(click());

        ViewInteraction textInputEditText7 = onView(
                allOf(withId(R.id.edtPasswordRegister),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.edtPasswordLayout),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText7.perform(replaceText("hehe"), closeSoftKeyboard());


        ViewInteraction materialButton8 = onView(
                allOf(withId(R.id.btnRegisterUser), withText("Register"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        7),
                                0),
                        isDisplayed()));
        materialButton8.perform(click());

        ViewInteraction textInputEditText8 = onView(
                allOf(withId(R.id.edtEmailRegister), withText("onsin200"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.edtEmailLayout),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText8.perform(replaceText("wlourensius@gmail.com"));

        ViewInteraction textInputEditText9 = onView(
                allOf(withId(R.id.edtEmailRegister), withText("wlourensius@gmail.com"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.edtEmailLayout),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText9.perform(closeSoftKeyboard());


        ViewInteraction materialButton9 = onView(
                allOf(withId(R.id.btnRegisterUser), withText("Register"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        7),
                                0),
                        isDisplayed()));
        materialButton9.perform(click());

        ViewInteraction textInputEditText10 = onView(
                allOf(withId(R.id.edtPasswordRegister), withText("hehe"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.edtPasswordLayout),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText10.perform(replaceText("hehehe"));

        ViewInteraction textInputEditText11 = onView(
                allOf(withId(R.id.edtPasswordRegister), withText("hehehe"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.edtPasswordLayout),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText11.perform(closeSoftKeyboard());


        ViewInteraction materialButton10 = onView(
                allOf(withId(R.id.btnRegisterUser), withText("Register"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        7),
                                0),
                        isDisplayed()));
        materialButton10.perform(click());

        ViewInteraction textInputEditText12 = onView(
                allOf(withId(R.id.edtEmailRegister), withText("wlourensius@gmail.com"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.edtEmailLayout),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText12.perform(replaceText("onsin20@gmail.com"));

        ViewInteraction textInputEditText13 = onView(
                allOf(withId(R.id.edtEmailRegister), withText("onsin20@gmail.com"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.edtEmailLayout),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText13.perform(closeSoftKeyboard());


        ViewInteraction materialButton11 = onView(
                allOf(withId(R.id.btnRegisterUser), withText("Register"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        7),
                                0),
                        isDisplayed()));
        materialButton11.perform(click());
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
