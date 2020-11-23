package com.android.marvelcharacters

import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.android.marvelcharacters.mvp.view.activities.MainActivity
import com.android.marvelcharacters.mvp.view.adapters.CharactersRecyclerViewAdapter
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.android.marvelcharacters", appContext.packageName)
    }

    @Test
    fun testCharactersSearch() {
        launch(MainActivity::class.java)
        onView(withId(R.id.characterNameEditText)).perform(typeText("spi"))
        onView(withId(R.id.searchImageButton)).perform(click())
        Thread.sleep(5000);

        onView(withId(R.id.charactersRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition<CharactersRecyclerViewAdapter.ViewHolder>(5, click()))
        Thread.sleep(5000);
        onView(ViewMatchers.withText("Comics")).perform(click());
        Thread.sleep(2000);
        onView(ViewMatchers.withText("Series")).perform(click());
        Thread.sleep(2000);
        onView(ViewMatchers.withText("Events")).perform(click());
        Thread.sleep(2000);
    }
}