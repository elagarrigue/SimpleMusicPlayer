package musicplayer.home

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import musicplayer.presentation.R
import musicplayer.presentation.home.MusicPlayerActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MusicPlayerActivityTest {

    @get:Rule
    var activityRule: ActivityTestRule<MusicPlayerActivity>
            = ActivityTestRule(MusicPlayerActivity::class.java)

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        context.setTheme(R.style.Theme_AppCompat_Light)
    }

    @Test
    fun shouldStartActivity() {
        onView(withId(R.id.songTitle))
            .check(matches(withText("Total Breakdown - Brad Sucks")))
    }

    @Test
    fun shouldPlayNext() {
        onView(withId(R.id.songTitle))
            .check(matches(withText("Total Breakdown - Brad Sucks")))

        onView(withId(R.id.buttonNext)).perform(click())

        onView(withId(R.id.songTitle))
            .check(matches(withText("Sorry - Comfort Fit")))

    }

    @Test
    fun shouldPlayPrevious() {
        onView(withId(R.id.songTitle))
            .check(matches(withText("Total Breakdown - Brad Sucks")))

        onView(withId(R.id.buttonPrevious)).perform(click())

        onView(withId(R.id.songTitle))
            .check(matches(withText("Swan Song - Paper Navy")))

    }
}